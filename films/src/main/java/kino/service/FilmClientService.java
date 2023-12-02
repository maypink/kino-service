package kino.service;

import kino.client.Tmdb.utils.FilmInfoResource;

public interface FilmClientService {

    FilmInfoResource getFilmInfoByTitleAndYear(String title, Integer year);
}
