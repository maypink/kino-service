package kino.exception.customException;

public class FilmException extends RuntimeException{
    String message;

    public FilmException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
