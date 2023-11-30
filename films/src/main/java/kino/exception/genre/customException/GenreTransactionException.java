package kino.exception.genre.customException;

public class GenreTransactionException extends GenreException {
    public GenreTransactionException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}

