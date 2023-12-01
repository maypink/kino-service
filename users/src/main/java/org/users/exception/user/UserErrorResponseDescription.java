package org.users.exception.user;

public record UserErrorResponseDescription(
        String code,
        String message
) {
}
