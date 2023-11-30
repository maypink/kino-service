package kino.utils;

import kino.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResource toResource(Genre genre){
        return new GenreResource(genre.getGenre());
    }
}
