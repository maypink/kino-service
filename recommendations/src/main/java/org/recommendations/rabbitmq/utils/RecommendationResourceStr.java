package org.recommendations.rabbitmq.utils;

import java.time.LocalDateTime;
import java.util.UUID;

public record RecommendationResourceStr (

        UUID id,

        UUID userId,

        UUID filmId,

        Double score,

        String dateTime
)
{}
