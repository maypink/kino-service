package kino.client.Tmdb.utils;

import java.util.UUID;

public record FilmInfoResource(
    UUID id,

    String tmdbId,

    String posterPath
)
{}
