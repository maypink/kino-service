package kino.exception.genre.customException;

public class GenreNotFoundException extends GenreException {
    public GenreNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
