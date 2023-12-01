package org.recommendations.exception.customException;

public class RecommendationTransactionException extends RecommendationException {
    public RecommendationTransactionException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
