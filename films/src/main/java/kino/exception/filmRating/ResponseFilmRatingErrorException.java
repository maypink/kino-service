package kino.exception.filmRating;

import org.springframework.http.ResponseEntity;

public class ResponseFilmRatingErrorException extends RuntimeException{
    private final ResponseEntity<FilmRatingErrorResponse> filmErrorResponse;

    public ResponseFilmRatingErrorException(ResponseEntity<FilmRatingErrorResponse> filmErrorResponse) {
        this.filmErrorResponse = filmErrorResponse;
    }

    public ResponseEntity<FilmRatingErrorResponse> getFilmRatingErrorResponse() {
        return filmErrorResponse;
    }
}
