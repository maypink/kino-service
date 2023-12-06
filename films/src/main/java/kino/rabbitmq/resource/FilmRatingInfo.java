package kino.rabbitmq.resource;

import java.io.Serializable;

public record FilmRatingInfo(
        String username,

        String tmdbId,

        Integer rating
)
implements Serializable
{}
