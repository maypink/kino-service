package kino.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class FilmResource {

    UUID id;

    String title;

    Integer year;

    List<GenreResource> genre;
}
