package kino.rabbitmq.resource;

import kino.utils.FilmRatingResource;

import java.io.Serializable;
import java.util.List;

public record FilmRatingResourceList(
        List<FilmRatingResource> filmRatingResources
) implements Serializable
{}
