package org.kino.utils.rabbitUtils;

import org.kino.utils.FilmResource;

import java.io.Serializable;
import java.util.List;

public record FilmListResource (
        List<FilmResource> films
) implements Serializable {
}
