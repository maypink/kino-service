package org.recommendations.exception.customException;

public class RecommendationException extends RuntimeException{
    String message;

    public RecommendationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
