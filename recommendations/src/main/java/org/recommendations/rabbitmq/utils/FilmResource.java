package org.recommendations.rabbitmq.utils;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record FilmResource(

        UUID id,

        String title,

        Integer year,

        FilmInfoResource filmInfoResource,

        List<GenreResource> genre
) implements Serializable
{}
