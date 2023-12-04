package kino.exception.filmRating.customException;

public class FilmRatingException extends RuntimeException{
    String message;

    public FilmRatingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
