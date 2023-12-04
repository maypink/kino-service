package kino.exception.filmRating;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import kino.exception.ErrorResponse;
import kino.exception.filmRating.customException.FilmRatingDuplicateException;
import kino.exception.filmRating.customException.FilmRatingException;
import kino.exception.filmRating.customException.FilmRatingNotFoundException;
import kino.exception.filmRating.customException.FilmRatingTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FilmRatingServiceAdviceController {

    @ExceptionHandler(value = ResponseFilmRatingErrorException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(ResponseFilmRatingErrorException ex, HttpServletRequest request) {
        ResponseEntity<FilmRatingErrorResponse> filmErrorResponse = ex.getFilmRatingErrorResponse();
        return new ResponseEntity<>(new ErrorResponse(filmErrorResponse.getStatusCode().value(), filmErrorResponse.getBody().error().message(), filmErrorResponse.getBody().error().code(), request.getRequestURI()), filmErrorResponse.getStatusCode());
    }

    @ExceptionHandler(value = RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForRateLimiter(RequestNotPermitted ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage(), "Too many requests to server", request.getRequestURI()), HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(value = FilmRatingNotFoundException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(FilmRatingNotFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FilmRatingDuplicateException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(FilmRatingDuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = FilmRatingTransactionException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseKino(FilmRatingTransactionException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> handleException(FilmRatingException ex, HttpServletRequest request, HttpStatusCode httpStatusCode){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(httpStatusCode.value(), httpStatusCode.toString(), message, request.getRequestURI()), httpStatusCode);
    }

}
