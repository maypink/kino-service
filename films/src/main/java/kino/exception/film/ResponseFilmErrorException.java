package kino.exception.film;

import kino.exception.film.FilmErrorResponse;
import org.springframework.http.ResponseEntity;

public class ResponseFilmErrorException extends RuntimeException{
    private final ResponseEntity<FilmErrorResponse> filmErrorResponse;

    public ResponseFilmErrorException(ResponseEntity<FilmErrorResponse> filmErrorResponse) {
        this.filmErrorResponse = filmErrorResponse;
    }

    public ResponseEntity<FilmErrorResponse> getFilmErrorResponse() {
        return filmErrorResponse;
    }
}
