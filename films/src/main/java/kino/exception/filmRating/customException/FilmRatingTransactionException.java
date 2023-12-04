package kino.exception.filmRating.customException;

public class FilmRatingTransactionException extends FilmRatingException {
    public FilmRatingTransactionException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }
}
