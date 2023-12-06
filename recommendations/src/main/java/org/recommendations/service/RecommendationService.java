package org.recommendations.service;

import org.recommendations.utils.RecommendationResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RecommendationService {

//    List<RecommendationResource> getRecommendationForUserId(UUID userId);

    List<RecommendationResource> getRecommendationForUsername(String username) throws InterruptedException;

    List<RecommendationResource> findAllByUserId(UUID userId);

    List<RecommendationResource> findAllByUserIdAndFilmId(UUID userId, UUID filmId);

    RecommendationResource save(RecommendationResource recommendationResource);

    RecommendationResource delete(RecommendationResource recommendationResource);
}
