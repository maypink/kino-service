package kino.exception.filmRating.customException;

public class FilmRatingNotFoundException extends FilmRatingException {
    public FilmRatingNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
