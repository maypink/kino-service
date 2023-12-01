package org.recommendations.exception;

public record RecommendationErrorResponse(
    RecommendationErrorResponseDescription error
) {

}
