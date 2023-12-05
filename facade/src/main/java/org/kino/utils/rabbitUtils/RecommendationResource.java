package org.kino.utils.rabbitUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public record RecommendationResource(

    UUID id,

    UUID userId,

    UUID filmId,

    Double score,

    LocalDateTime dateTime
    )
{}
