package kino.service.impl;

import kino.exception.genre.customException.GenreDuplicateException;
import kino.model.Genre;
import kino.repository.GenreRepository;
import kino.service.GenreService;
import kino.utils.GenreMapper;
import kino.utils.GenreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    GenreMapper genreMapper;

    @Override
    public List<GenreResource> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(g -> genreMapper.toResource(g)).toList();
    }

    @Override
    public List<GenreResource> findGenreByGenre(String genre) {
        List<Genre> genres = genreRepository.findByGenre(genre);
        return genres.stream().map(g -> genreMapper.toResource(g)).toList();
    }

    public GenreResource save(GenreResource genreResource) throws GenreDuplicateException{
        List<GenreResource> genreResourceList = findGenreByGenre(genreResource.genre());
        if (!genreResourceList.isEmpty()){
            throw new GenreDuplicateException("Attempt to insert duplicate of genre.");
        }
        Genre genre = new Genre(
                genreResource.id(),
                genreResource.genre());
        genreRepository.save(genre);
        return genreMapper.toResource(genre);
    }
}
