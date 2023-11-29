package kino.films.exception;

public record FilmErrorResponseDescription(
        String code,
        String message
) {
}
