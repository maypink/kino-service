package org.recommendations.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.recommendations.exception.customException.RecommendationNotFoundException;
import org.recommendations.model.Recommendation;
import org.recommendations.rabbitmq.RabbitMqProducer;
import org.recommendations.rabbitmq.utils.UserResource;
import org.recommendations.repository.RecommendationRepository;
import org.recommendations.service.RecommendationGenerationService;
import org.recommendations.service.RecommendationService;
import org.recommendations.utils.RecommendationMapper;
import org.recommendations.utils.RecommendationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {

    public static final Integer NUMBER_OF_RECOMMENDATIONS = 2;

    @Autowired
    RecommendationGenerationService recommendationGenerationService;

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    RecommendationMapper recommendationMapper;

    @Autowired
    RabbitMqProducer rabbitMqProducer;

    @Override
    public List<RecommendationResource> getRecommendationForUsername(String username) throws InterruptedException {
        UserResource userResource = rabbitMqProducer.getUserByUsername(username);
        UUID userId = userResource.id();
        List<RecommendationResource> recommendationResourceList = findAllByUserId(userId);
        if (recommendationResourceList.size() < NUMBER_OF_RECOMMENDATIONS){
            List<RecommendationResource> generatedRecommendations = recommendationGenerationService.generateRecommendationsForUsername(userId, username, NUMBER_OF_RECOMMENDATIONS);
            for (RecommendationResource recommendationResource: generatedRecommendations){
                save(recommendationResource);
            }
            return generatedRecommendations;
        } else {
            return recommendationResourceList
                    .stream()
                    .sorted(Comparator.comparing( RecommendationResource::dateTime ).reversed())
                    .limit(NUMBER_OF_RECOMMENDATIONS)
                    .toList();
        }
    }

    @Override
    public List<RecommendationResource> findAllByUserId(UUID userId) {
        List<Recommendation> recommendationList = recommendationRepository.findByUserId(userId);
        return recommendationList.stream().map(r -> recommendationMapper.toResource(r)).toList();
    }

    @Override
    public List<RecommendationResource> findAllByUserIdAndFilmId(UUID userId, UUID filmId) {
        List<Recommendation> recommendationList = recommendationRepository.findByUserIdAndFilmId(userId, filmId);
        return recommendationList.stream().map(r -> recommendationMapper.toResource(r)).toList();
    }

    @Override
    public RecommendationResource save(RecommendationResource recommendationResource) {
        List<RecommendationResource> recommendationResourceList =
                findAllByUserIdAndFilmId(recommendationResource.userId(), recommendationResource.filmId());
        if (!recommendationResourceList.isEmpty()){
            recommendationRepository.updateById(
                    recommendationResourceList.get(0).id(),
                    recommendationResource.dateTime(),
                    recommendationResource.score());
            RecommendationResource recommendationResourceToReturn = new RecommendationResource(
                    recommendationResourceList.get(0).id(),
                    recommendationResource.userId(),
                    recommendationResource.filmId(),
                    recommendationResource.score(),
                    recommendationResource.dateTime());
            return recommendationResourceToReturn;
        } else {
            Recommendation recommendation = new Recommendation(
                    recommendationResource.id(),
                    recommendationResource.userId(),
                    recommendationResource.filmId(),
                    recommendationResource.score(),
                    recommendationResource.dateTime()
            );
            recommendationRepository.save(recommendation);
            return recommendationMapper.toResource(recommendation);
        }
    }

    @Override
    public RecommendationResource delete(RecommendationResource recommendationResource) {
        List<RecommendationResource> recommendationResourceList = findAllByUserIdAndFilmId(recommendationResource.userId(), recommendationResource.filmId());
        if (recommendationResourceList.isEmpty()){
            throw new RecommendationNotFoundException("Recommendation with specified user_id and film_id was not found.");
        }
        RecommendationResource recommendationResource1 = recommendationResourceList.get(0);
        Recommendation recommendationToDelete = new Recommendation(
                recommendationResource1.id(),
                recommendationResource.userId(),
                recommendationResource.filmId(),
                recommendationResource.score(),
                recommendationResource.dateTime()
        );
        recommendationRepository.delete(recommendationToDelete);
        return recommendationMapper.toResource(recommendationToDelete);
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduleRecommendationsActualization() {
        List<Recommendation> recommendationList = recommendationRepository.findAll();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Recommendation> recsToDelete = recommendationList
                .stream()
                // drop recommendations that were given more than a minute ago
                .filter(recommendation -> Duration.between(recommendation.getDateTime(), currentDateTime).getSeconds() / 60 > 1)
                .toList();
        log.info("{} recommendations expired.", recsToDelete.size());
        for (Recommendation rec: recsToDelete){
            recommendationRepository.delete(rec);
        }
    }
}
