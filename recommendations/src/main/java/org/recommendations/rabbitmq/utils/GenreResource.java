package org.kino.utils;

import java.io.Serializable;
import java.util.UUID;

public record GenreResource(

        UUID id,
        String genre
) implements Serializable
{}
