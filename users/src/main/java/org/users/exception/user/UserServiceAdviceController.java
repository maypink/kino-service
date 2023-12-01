package org.users.exception.user;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import org.users.exception.ErrorResponse;
import org.users.exception.user.customException.UserDuplicateException;
import org.users.exception.user.customException.UserException;
import org.users.exception.user.customException.UserNotFoundException;
import org.users.exception.user.customException.UserTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserServiceAdviceController {

    @ExceptionHandler(value = ResponseUserErrorException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(ResponseUserErrorException ex, HttpServletRequest request) {
        ResponseEntity<UserErrorResponse> filmErrorResponse = ex.getUserErrorResponse();
        return new ResponseEntity<>(new ErrorResponse(filmErrorResponse.getStatusCode().value(), filmErrorResponse.getBody().error().message(), filmErrorResponse.getBody().error().code(), request.getRequestURI()), filmErrorResponse.getStatusCode());
    }

    @ExceptionHandler(value = RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForRateLimiter(RequestNotPermitted ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage(), "Too many requests to server", request.getRequestURI()), HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(UserNotFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserDuplicateException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(UserDuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserTransactionException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(UserTransactionException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> handleException(UserException ex, HttpServletRequest request, HttpStatusCode httpStatusCode){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(httpStatusCode.value(), httpStatusCode.toString(), message, request.getRequestURI()), httpStatusCode);
    }

}
