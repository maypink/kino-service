package org.recommendations.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record RecommendationResource (

    UUID id,

    UUID userId,

    UUID filmId,

    Double score,

    LocalDateTime dateTime
    )
{}
