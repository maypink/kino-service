package kino.utils;

import kino.model.FilmRating;
import org.springframework.stereotype.Component;

@Component
public class FilmRatingMapper {

    public FilmRatingResource toResource(FilmRating filmRating){
        return new FilmRatingResource(
                filmRating.getId(),
                filmRating.getUserId(),
                filmRating.getFilm_id(),
                filmRating.getRating());
    }

    public FilmRating toFilmRating(FilmRatingResource filmRatingResource){
        return new FilmRating(
                filmRatingResource.film_id(),
                filmRatingResource.user_id(),
                filmRatingResource.film_id(),
                filmRatingResource.rating());
    }
}
