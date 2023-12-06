package org.kino.utils.rabbitUtils;

import org.kino.utils.FilmRatingResource;

import java.io.Serializable;
import java.util.List;

public record FilmRatingResourceList(
        List<FilmRatingResource> filmRatingResources
) implements Serializable
{}
