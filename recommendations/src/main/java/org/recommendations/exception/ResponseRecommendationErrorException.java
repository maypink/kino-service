package org.recommendations.exception;

import org.springframework.http.ResponseEntity;

public class ResponseRecommendationErrorException extends RuntimeException{
    private final ResponseEntity<RecommendationErrorResponse> filmErrorResponse;

    public ResponseRecommendationErrorException(ResponseEntity<RecommendationErrorResponse> filmErrorResponse) {
        this.filmErrorResponse = filmErrorResponse;
    }

    public ResponseEntity<RecommendationErrorResponse> getRecommendationErrorResponse() {
        return filmErrorResponse;
    }
}
