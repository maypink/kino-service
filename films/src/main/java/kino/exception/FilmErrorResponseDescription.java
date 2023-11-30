package kino.exception;

public record FilmErrorResponseDescription(
        String code,
        String message
) {
}
