package kino.service;

import kino.utils.FilmResource;

import java.util.List;

public interface FilmService {

    List<FilmResource> getAllFilms();

    List<FilmResource> getFilmsByTitle(String title);

    List<FilmResource> getFilmsByGenre(String genre);

    List<FilmResource> getFilmByYear(Integer year);

    FilmResource save(FilmResource filmResource);
}
