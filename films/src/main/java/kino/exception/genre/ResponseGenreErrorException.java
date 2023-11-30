package kino.exception.genre;

import org.springframework.http.ResponseEntity;

public class ResponseGenreErrorException extends RuntimeException{
    private final ResponseEntity<GenreErrorResponse> genreErrorResponse;

    public ResponseGenreErrorException(ResponseEntity<GenreErrorResponse> genreErrorResponse) {
        this.genreErrorResponse = genreErrorResponse;
    }

    public ResponseEntity<GenreErrorResponse> getGenreErrorResponse() {
        return genreErrorResponse;
    }
}
