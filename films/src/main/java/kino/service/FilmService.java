package kino.service;

import kino.utils.FilmResource;

import java.util.List;

public interface FilmService {

    List<FilmResource> getAllFilms();

    List<FilmResource> getFilmsByTitle(String title);

    List<FilmResource> getFilmsByGenre(String genre);

    List<FilmResource> getFilmByYear(Integer year);

    List<FilmResource> getFilmByTitleYear(String title, Integer year);

    List<FilmResource> getFilmByTmdbId(String tmdbId);

    FilmResource save(FilmResource filmResource);
}
