package kino.exception.genre.customException;

import kino.exception.genre.customException.GenreException;

public class GenreDuplicateException extends GenreException {
    public GenreDuplicateException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}