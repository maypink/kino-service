package kino.exception.genre;

import kino.exception.film.FilmErrorResponseDescription;

public record GenreErrorResponse(
        FilmErrorResponseDescription error
) {

}
