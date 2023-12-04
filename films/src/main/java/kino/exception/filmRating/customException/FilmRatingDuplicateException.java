package kino.exception.filmRating.customException;

public class FilmRatingDuplicateException extends FilmRatingException {
    public FilmRatingDuplicateException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}