package kino.client.Tmdb.utils;

import java.io.Serializable;
import java.util.UUID;

public record FilmInfoResource(
    UUID id,

    String tmdbId,

    String posterPath
) implements Serializable
{}
