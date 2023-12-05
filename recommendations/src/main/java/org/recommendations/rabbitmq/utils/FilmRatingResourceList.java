package org.recommendations.rabbitmq.utils;

import java.io.Serializable;
import java.util.List;

public record FilmRatingResourceList(
        List<FilmRatingResource> filmRatingResources
) implements Serializable
{}
