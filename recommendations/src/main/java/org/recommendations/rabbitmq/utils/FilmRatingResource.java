package org.recommendations.rabbitmq.utils;

import java.io.Serializable;
import java.util.UUID;

public record FilmRatingResource(
        UUID id,

        UUID user_id,

        UUID film_id,

        Integer rating

)
        implements Serializable
{ }
