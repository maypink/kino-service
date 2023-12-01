package org.recommendations.service.impl;

import org.recommendations.exception.customException.RecommendationNotFoundException;
import org.recommendations.model.Recommendation;
import org.recommendations.repository.RecommendationRepository;
import org.recommendations.service.RecommendationService;
import org.recommendations.utils.RecommendationMapper;
import org.recommendations.utils.RecommendationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    RecommendationMapper recommendationMapper;

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
                findAllByUserIdAndFilmId(recommendationResource.getUserId(), recommendationResource.getFilmId());
        if (!recommendationResourceList.isEmpty()){
            recommendationRepository.updateById(
                    recommendationResourceList.get(0).getId(),
                    recommendationResource.getDateTime(),
                    recommendationResource.getScore());
            RecommendationResource recommendationResourceToReturn = new RecommendationResource(
                    recommendationResourceList.get(0).getId(),
                    recommendationResource.getUserId(),
                    recommendationResource.getFilmId(),
                    recommendationResource.getScore(),
                    recommendationResource.getDateTime());
            return recommendationResourceToReturn;
        } else {
            Recommendation recommendation = new Recommendation(
                    recommendationResource.getId(),
                    recommendationResource.getUserId(),
                    recommendationResource.getFilmId(),
                    recommendationResource.getScore(),
                    recommendationResource.getDateTime()
            );
            recommendationRepository.save(recommendation);
            return recommendationMapper.toResource(recommendation);
        }
    }

    @Override
    public RecommendationResource delete(RecommendationResource recommendationResource) {
        List<RecommendationResource> recommendationResourceList = findAllByUserIdAndFilmId(recommendationResource.getUserId(), recommendationResource.getFilmId());
        if (recommendationResourceList.isEmpty()){
            throw new RecommendationNotFoundException("Recommendation with specified user_id and film_id was not found.");
        }
        RecommendationResource recommendationResource1 = recommendationResourceList.get(0);
        Recommendation recommendationToDelete = new Recommendation(
                recommendationResource1.getId(),
                recommendationResource.getUserId(),
                recommendationResource.getFilmId(),
                recommendationResource1.getScore(),
                recommendationResource1.getDateTime()
        );
        recommendationRepository.delete(recommendationToDelete);
        return recommendationMapper.toResource(recommendationToDelete);
    }
}
