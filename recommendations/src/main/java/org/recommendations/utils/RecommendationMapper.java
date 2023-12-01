package org.recommendations.utils;

import org.recommendations.model.Recommendation;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    public RecommendationResource toResource(Recommendation recommendation){
        return new RecommendationResource(
                recommendation.getId(),
                recommendation.getUserId(),
                recommendation.getFilmId(),
                recommendation.getScore(),
                recommendation.getDateTime());
    }

    public Recommendation toRecommendation(RecommendationResource recommendationResource){
        return new Recommendation(
                recommendationResource.getId(),
                recommendationResource.getUserId(),
                recommendationResource.getFilmId(),
                recommendationResource.getScore(),
                recommendationResource.getDateTime());
    }
}
