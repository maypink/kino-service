package kino.rabbitmq.resource;

import java.io.Serializable;

public record FilmSearchInfo(

        String title,

        Integer year

) implements Serializable {
}
