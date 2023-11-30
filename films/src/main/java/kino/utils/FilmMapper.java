package kino.utils;

import kino.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmMapper {

    @Autowired
    GenreMapper genreMapper;

    public FilmResource toResource(Film film){
        List<GenreResource> genreResourceList = film.getGenres().stream().map(genre -> genreMapper.toResource(genre)).toList();
        return new FilmResource(film.getTitle(), film.getYear(), genreResourceList);
    }
}
