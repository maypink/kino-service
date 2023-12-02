package kino.service.impl;

import kino.client.Tmdb.FilmClient;
import kino.client.Tmdb.FilmInfoApiResponce;
import kino.client.Tmdb.TmdbApiResponce;
import kino.client.Tmdb.TmdbApiResponceById;
import kino.client.Tmdb.utils.FilmInfoResource;
import kino.client.Tmdb.utils.GenreTmdbEnum;
import kino.exception.film.customException.FilmNotFoundException;
import kino.service.FilmClientService;
import kino.utils.FilmResource;
import kino.utils.GenreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FilmClientServiceImpl implements FilmClientService {

    @Autowired
    FilmClient filmClient;

    @Override
    public FilmResource getFilmResourceByTitleAndYear(String title, Integer year) throws FilmNotFoundException {
        TmdbApiResponce tmdbApiResponce = filmClient.getFilmInfoByTitle(title);
        if (tmdbApiResponce.results().isEmpty()){
            throw new FilmNotFoundException("There is no such film in TMDb.");
        }
        // get film with appropriate release date
        for (FilmInfoApiResponce filmInfoApiResponce: tmdbApiResponce.results()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate filmReleaseDate = LocalDate.parse(filmInfoApiResponce.release_date(), formatter);
            if (filmReleaseDate.getYear() == year){
                // form film resource
                return new FilmResource(
                        UUID.randomUUID(),
                        filmInfoApiResponce.title(),
                        year,
                        new FilmInfoResource(UUID.randomUUID(), String.valueOf(filmInfoApiResponce.id()), filmInfoApiResponce.poster_path()),
                        filmInfoApiResponce.genre_ids().stream().map(g -> new GenreResource(UUID.randomUUID(), GenreTmdbEnum.fromInteger(g))).toList());
            }
        }
        throw new FilmNotFoundException("There is no such film in TMDb.");
    }

    @Override
    public FilmResource getFilmResourceByTmdbId(String tmdbId) {
        TmdbApiResponceById tmdbApiResponceById = filmClient.getFilmById(tmdbId);
        if (tmdbApiResponceById.status_code() != null){
            throw new FilmNotFoundException("There is no such film in TMDb.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filmReleaseDate = LocalDate.parse(tmdbApiResponceById.release_date(), formatter);
        // get film with appropriate release date
        return new FilmResource(
                UUID.randomUUID(),
                tmdbApiResponceById.title(),
                filmReleaseDate.getYear(),
                new FilmInfoResource(UUID.randomUUID(), String.valueOf(tmdbApiResponceById.id()), tmdbApiResponceById.poster_path()),
                tmdbApiResponceById.genres().stream().map(g -> new GenreResource(UUID.randomUUID(), GenreTmdbEnum.fromInteger(Integer.parseInt(g.get("id"))))).toList());
    }
}
