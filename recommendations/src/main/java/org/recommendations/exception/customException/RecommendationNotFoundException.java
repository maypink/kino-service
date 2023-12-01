package org.recommendations.exception.customException;

public class RecommendationNotFoundException extends RecommendationException {
    public RecommendationNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
