package kino.utils;

import kino.model.Genre;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenreMapper {

    public GenreResource toResource(Genre genre){
        return new GenreResource(genre.getId(), genre.getGenre());
    }

    public Genre toGenre(GenreResource genreResource){
        return new Genre(genreResource.getId(), genreResource.getGenre());
    }
}
