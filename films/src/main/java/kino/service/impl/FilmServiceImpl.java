package kino.service.impl;

import kino.exception.film.customException.FilmDuplicateException;
import kino.model.Film;
import kino.repository.FilmRepository;
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
    FilmRepository filmRepository;

    @Autowired
    GenreService genreService;

    @Autowired
    FilmMapper filmMapper;

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

    public List<FilmResource> getFilmByTitleYear(String title, Integer year){
        List<Film> film = filmRepository.findByTitleAndYear(title, year);
        return film.stream().map(f -> filmMapper.toResource(f)).toList();
    }

    public FilmResource save(FilmResource filmResource){
        List<FilmResource> filmResourceList = getFilmByTitleYear(filmResource.getTitle(), filmResource.getYear());
        if (!filmResourceList.isEmpty()){
            throw new FilmDuplicateException("Attempt to insert duplicate of genre.");
        }
        // save all non-existent genres
        ArrayList<GenreResource> genreResourceListToSave = new ArrayList<>(Collections.emptyList());
        for (GenreResource genreResource: filmResource.getGenre() ){
            List<GenreResource> genreResourceList = genreService.findGenreByGenre(genreResource.getGenre());
            if (genreResourceList.isEmpty()){
                GenreResource savedGenreResource = genreService.save(genreResource);
                genreResourceListToSave.add(savedGenreResource);
            } else {
                genreResourceListToSave.add(genreResourceList.get(0));
            }
        }

        Film film = new Film(
                UUID.randomUUID(),
                filmResource.getTitle(),
                filmResource.getYear(),
                genreResourceListToSave.stream().map(g -> genreMapper.toGenre(g)).collect(Collectors.toSet()));
        filmRepository.save(film);
        return filmResource;
    }
}
