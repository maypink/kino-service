package kino.service;

import kino.utils.FilmResource;

public interface FilmClientService {

    FilmResource getFilmResourceByTitleAndYear(String title, Integer year);

    FilmResource getFilmResourceByTmdbId(String tmdbId);
}
