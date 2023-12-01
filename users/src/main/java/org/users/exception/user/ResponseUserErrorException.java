package org.users.exception.user;

import org.springframework.http.ResponseEntity;

public class ResponseUserErrorException extends RuntimeException{
    private final ResponseEntity<UserErrorResponse> filmErrorResponse;

    public ResponseUserErrorException(ResponseEntity<UserErrorResponse> filmErrorResponse) {
        this.filmErrorResponse = filmErrorResponse;
    }

    public ResponseEntity<UserErrorResponse> getUserErrorResponse() {
        return filmErrorResponse;
    }
}
