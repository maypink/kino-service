package org.recommendations.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import org.recommendations.exception.customException.RecommendationDuplicateException;
import org.recommendations.exception.customException.RecommendationException;
import org.recommendations.exception.customException.RecommendationNotFoundException;
import org.recommendations.exception.customException.RecommendationTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecommendationServiceAdviceController {

    @ExceptionHandler(value = ResponseRecommendationErrorException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(ResponseRecommendationErrorException ex, HttpServletRequest request) {
        ResponseEntity<RecommendationErrorResponse> filmErrorResponse = ex.getRecommendationErrorResponse();
        return new ResponseEntity<>(new ErrorResponse(filmErrorResponse.getStatusCode().value(), filmErrorResponse.getBody().error().message(), filmErrorResponse.getBody().error().code(), request.getRequestURI()), filmErrorResponse.getStatusCode());
    }

    @ExceptionHandler(value = RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForRateLimiter(RequestNotPermitted ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage(), "Too many requests to server", request.getRequestURI()), HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(value = RecommendationNotFoundException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(RecommendationNotFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RecommendationDuplicateException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(RecommendationDuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RecommendationTransactionException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(RecommendationTransactionException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> handleException(RecommendationException ex, HttpServletRequest request, HttpStatusCode httpStatusCode){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(httpStatusCode.value(), httpStatusCode.toString(), message, request.getRequestURI()), httpStatusCode);
    }

}
