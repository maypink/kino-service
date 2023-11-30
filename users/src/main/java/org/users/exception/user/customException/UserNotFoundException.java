package org.users.exception.user.customException;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
