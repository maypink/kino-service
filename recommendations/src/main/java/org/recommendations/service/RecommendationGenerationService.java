package org.recommendations.service;

import org.recommendations.utils.RecommendationResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RecommendationGenerationService {

    List<RecommendationResource> generateRecommendationsForUsername(UUID userId, String username, Integer recommendationsNumber) throws InterruptedException;
}
