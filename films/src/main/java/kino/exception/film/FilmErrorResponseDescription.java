package kino.exception.film;

public record FilmErrorResponseDescription(
        String code,
        String message
) {
}
