package kino.exception.genre;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;

import kino.exception.ErrorResponse;
import kino.exception.genre.customException.GenreDuplicateException;
import kino.exception.genre.customException.GenreException;
import kino.exception.genre.customException.GenreNotFoundException;
import kino.exception.genre.customException.GenreTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenreServiceAdviceController {

    @ExceptionHandler(value = ResponseGenreErrorException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(ResponseGenreErrorException ex, HttpServletRequest request) {
        ResponseEntity<GenreErrorResponse> genreErrorResponse = ex.getGenreErrorResponse();
        return new ResponseEntity<>(new ErrorResponse(genreErrorResponse.getStatusCode().value(), genreErrorResponse.getBody().error().message(), genreErrorResponse.getBody().error().code(), request.getRequestURI()), genreErrorResponse.getStatusCode());
    }

    @ExceptionHandler(value = RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForRateLimiter(RequestNotPermitted ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage(), "Too many requests to server", request.getRequestURI()), HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(value = GenreNotFoundException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(GenreNotFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GenreDuplicateException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(GenreDuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = GenreTransactionException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(GenreTransactionException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> handleException(GenreException ex, HttpServletRequest request, HttpStatusCode httpStatusCode){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(httpStatusCode.value(), httpStatusCode.toString(), message, request.getRequestURI()), httpStatusCode);
    }

}
