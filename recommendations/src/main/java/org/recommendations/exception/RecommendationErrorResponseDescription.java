package org.recommendations.exception;

public record RecommendationErrorResponseDescription(
        String code,
        String message
) {
}
