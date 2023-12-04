package kino.utils;

import java.io.Serializable;

public record FilmSearchInfo(

        String title,

        Integer year

) implements Serializable {
}
