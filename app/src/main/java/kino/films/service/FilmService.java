package kino.films.service;

import kino.films.utils.FilmResource;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FilmService {

    List<FilmResource> getAllFilms();

    List<FilmResource> getFilmsByTitle(String title);

    List<FilmResource> getFilmsByGenre(String genre);

    List<FilmResource> getFilmByYear(Integer year);
}
