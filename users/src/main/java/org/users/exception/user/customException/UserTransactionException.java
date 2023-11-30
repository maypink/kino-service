package org.users.exception.user.customException;

public class UserTransactionException extends UserException {
    public UserTransactionException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
