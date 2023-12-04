package kino.service;

import kino.model.FilmRating;
import kino.utils.FilmRatingResource;
import kino.utils.FilmResource;

import java.util.List;
import java.util.UUID;

public interface FilmRatingService {
    List<FilmRatingResource> getAllFilmRatingForUserId(UUID userId);

    List<FilmRatingResource> getAllFilmRatingForUserIdAndFilmId(UUID userId, UUID filmId);

    List<FilmRatingResource> getAllFilmRatings();

    FilmRatingResource addFilmRatingForUserId(UUID userId, String tmdbId, Integer rating);

    FilmRatingResource save(FilmRatingResource filmResource);
}
