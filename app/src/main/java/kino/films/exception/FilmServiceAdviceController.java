package kino.films.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import kino.films.exception.customException.FilmDuplicateException;
import kino.films.exception.customException.FilmException;
import kino.films.exception.customException.FilmNotFoundException;
import kino.films.exception.customException.FilmTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FilmServiceAdviceController {

    @ExceptionHandler(value = ResponseFilmErrorException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForWeatherApi(ResponseFilmErrorException ex, HttpServletRequest request) {
        ResponseEntity<FilmErrorResponse> filmErrorResponse = ex.getFilmErrorResponse();
        return new ResponseEntity<>(new ErrorResponse(filmErrorResponse.getStatusCode().value(), filmErrorResponse.getBody().error().message(), filmErrorResponse.getBody().error().code(), request.getRequestURI()), filmErrorResponse.getStatusCode());
    }

    @ExceptionHandler(value = RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForRateLimiter(RequestNotPermitted ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), ex.getMessage(), "Too many requests to server", request.getRequestURI()), HttpStatus.TOO_MANY_REQUESTS);
    }
    @ExceptionHandler(value = FilmNotFoundException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForWeatherApi(FilmNotFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FilmDuplicateException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForWeatherApi(FilmDuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = FilmTransactionException.class)
    public ResponseEntity<ErrorResponse> getErrorResponseForWeatherApi(FilmTransactionException ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ErrorResponse> handleException(FilmException ex, HttpServletRequest request, HttpStatusCode httpStatusCode){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(httpStatusCode.value(), httpStatusCode.toString(), message, request.getRequestURI()), httpStatusCode);
    }

}
