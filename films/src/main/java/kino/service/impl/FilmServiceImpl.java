package kino.service.impl;

import kino.client.Tmdb.utils.FilmInfoMapper;
import kino.client.Tmdb.utils.FilmInfoResource;
import kino.exception.film.customException.FilmDuplicateException;
import kino.model.Film;
import kino.model.FilmInfo;
import kino.repository.FilmRepository;
import kino.service.FilmInfoService;
import kino.service.FilmService;
import kino.service.GenreService;
import kino.utils.FilmMapper;
import kino.utils.FilmResource;
import kino.utils.GenreMapper;
import kino.utils.GenreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmInfoService filmInfoService;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    GenreService genreService;

    @Autowired
    FilmMapper filmMapper;

    @Autowired
    FilmInfoMapper filmInfoMapper;

    @Autowired
    GenreMapper genreMapper;

    @Override
    public List<FilmResource> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream().map(film -> filmMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmResource> getFilmsByTitle(String title) {
        List<Film> films = filmRepository.findByTitle(title);
        return films.stream().map(film -> filmMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmResource> getFilmsByGenre(String genre) {
        List<Film> films = filmRepository.findByGenre(genre);
        return films.stream().map(film -> filmMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmResource> getFilmByYear(Integer year) {
        List<Film> films = filmRepository.findByYear(year);
        return films.stream().map(film -> filmMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmResource> getFilmByTitleYear(String title, Integer year){
        List<Film> film = filmRepository.findByTitleAndYear(title, year);
        return film.stream().map(f -> filmMapper.toResource(f)).toList();
    }

    public FilmResource save(FilmResource filmResource){
        List<FilmResource> filmResourceList = getFilmByTitleYear(filmResource.title(), filmResource.year());
        if (!filmResourceList.isEmpty()){
            throw new FilmDuplicateException("Attempt to insert duplicate of film.");
        }
        // save all non-existent genres
        ArrayList<GenreResource> genreResourceListToSave = new ArrayList<>(Collections.emptyList());
        for (GenreResource genreResource: filmResource.genre() ){
            List<GenreResource> genreResourceList = genreService.findGenreByGenre(genreResource.genre());
            if (genreResourceList.isEmpty()){
                GenreResource savedGenreResource = genreService.save(genreResource);
                genreResourceListToSave.add(savedGenreResource);
            } else {
                genreResourceListToSave.add(genreResourceList.get(0));
            }
        }

        FilmInfo filmInfo = new FilmInfo(
                UUID.randomUUID(),
                filmResource.filmInfoResource().tmdbId(),
                filmResource.filmInfoResource().posterPath()
        );
        FilmInfoResource savedFilmInfoResource = filmInfoService.save(filmInfoMapper.toResource(filmInfo));

        Film film = new Film(
                filmResource.id(),
                filmResource.title(),
                filmResource.year(),
                filmInfoMapper.toFilmInfo(savedFilmInfoResource),
                genreResourceListToSave.stream().map(g -> genreMapper.toGenre(g)).collect(Collectors.toSet()));
        filmRepository.save(film);
        return filmMapper.toResource(film);
    }
}
