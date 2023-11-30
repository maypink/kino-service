package kino.service;

import kino.utils.FilmResource;
import kino.utils.GenreResource;

import java.util.List;

public interface GenreService {

    List<GenreResource> findAllGenres();

    List<GenreResource> findGenreByGenre(String genre);

    GenreResource save(GenreResource genreResource);
}
