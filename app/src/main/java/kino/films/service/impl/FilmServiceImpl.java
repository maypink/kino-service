package kino.films.service.impl;

import kino.films.model.Film;
import kino.films.repository.FilmRepository;
import kino.films.service.FilmService;
import kino.films.utils.FilmMapper;
import kino.films.utils.FilmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmMapper filmMapper;

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
}
