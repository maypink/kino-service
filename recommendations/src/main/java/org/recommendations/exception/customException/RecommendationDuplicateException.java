package org.recommendations.exception.customException;

public class RecommendationDuplicateException extends RecommendationException {
    public RecommendationDuplicateException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}