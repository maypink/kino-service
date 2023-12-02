package kino.utils;

import kino.client.Tmdb.utils.FilmInfoResource;

import java.util.List;
import java.util.UUID;

public record FilmResource(

    UUID id,

    String title,

    Integer year,

    FilmInfoResource filmInfoResource,

    List<GenreResource> genre
)
{}
