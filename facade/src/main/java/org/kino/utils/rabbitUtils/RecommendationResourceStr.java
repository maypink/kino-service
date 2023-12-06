package org.kino.utils.rabbitUtils;

import java.util.UUID;

public record RecommendationResourceStr (

        UUID id,

        UUID userId,

        UUID filmId,

        Double score,

        String dateTime
)
{}