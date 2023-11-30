package kino.exception.genre.customException;

public class GenreException extends RuntimeException{
    String message;

    public GenreException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
