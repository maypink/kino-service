package kino.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class FilmResource {

    String title;

    Integer year;

    List<GenreResource> genre;
}
