package kino.utils;

import java.io.Serializable;
import java.util.List;

public record FilmListResource(
        List<FilmResource> films
) implements Serializable {
}
