package kino.exception.film.customException;

public class FilmNotFoundException extends FilmException{
    public FilmNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
