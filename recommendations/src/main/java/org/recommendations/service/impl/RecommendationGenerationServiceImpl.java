package org.recommendations.service.impl;

import org.recommendations.rabbitmq.RabbitMqProducer;
import org.recommendations.rabbitmq.utils.FilmListResource;
import org.recommendations.rabbitmq.utils.FilmRatingResource;
import org.recommendations.rabbitmq.utils.FilmRatingResourceList;
import org.recommendations.rabbitmq.utils.FilmResource;
import org.recommendations.service.RecommendationGenerationService;
import org.recommendations.utils.RecommendationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

@Service
public class RecommendationGenerationServiceImpl implements RecommendationGenerationService {

    @Autowired
    RabbitMqProducer rabbitMqProducer;

    @Override
    public List<RecommendationResource> generateRecommendationsForUsername(UUID userId, String username, Integer recommendationsNumber) throws InterruptedException {
        FilmListResource filmListResource = rabbitMqProducer.getAllFilms();
        FilmRatingResourceList filmRatingResourceList = rabbitMqProducer.getAllFilmRatingsByUsername(username);
        List<UUID> ratedFilmsIds = filmRatingResourceList.filmRatingResources().stream().map(FilmRatingResource::film_id).toList();
        List<UUID> allFilmsIds = filmListResource.films().stream().map(FilmResource::id).toList();
        ArrayList<UUID> unwatchedFilmsIds = new ArrayList<UUID>(allFilmsIds
                .stream()
                .filter(e -> !ratedFilmsIds.contains(e))
                .toList());
        Collections.shuffle(unwatchedFilmsIds);
        return unwatchedFilmsIds
                .stream()
                .map(id -> new RecommendationResource(
                        UUID.randomUUID(),
                        userId,
                        id,
                        Math.random(),
                        LocalDateTime.now()))
                .limit(recommendationsNumber)
                .toList();
    }
}
