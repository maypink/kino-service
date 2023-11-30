package kino.exception.customException;

public class FilmTransactionException extends FilmException{
    public FilmTransactionException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
