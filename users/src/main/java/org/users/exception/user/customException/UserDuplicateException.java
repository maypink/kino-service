package org.users.exception.user.customException;

public class UserDuplicateException extends UserException {
    public UserDuplicateException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}