package kino.rabbitmq.resource;

import kino.utils.FilmResource;

import java.io.Serializable;
import java.util.List;

public record FilmListResource(
        List<FilmResource> films
) implements Serializable {
}
