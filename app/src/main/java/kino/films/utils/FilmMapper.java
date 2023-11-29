package kino.films.utils;

import kino.films.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmMapper {

    @Autowired
    GenreMapper genreMapper;

    public FilmResource toResource(Film film){
        List<GenreResource> genreResourceList = film.getFilmGenres().stream().map(genre -> genreMapper.toResource(genre)).toList();
        return new FilmResource(film.getTitle(), film.getYear(), genreResourceList);
    }
}
