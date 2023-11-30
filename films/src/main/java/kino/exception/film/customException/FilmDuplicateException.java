package kino.exception.film.customException;

public class FilmDuplicateException extends FilmException{
    public FilmDuplicateException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}