package org.recommendations.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class RecommendationResource {

    private static final Double DEFAULT_SCORE = 0D;

    public RecommendationResource(UUID userId,
                                  UUID filmId){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.filmId = filmId;
        this.score = DEFAULT_SCORE;
        this.dateTime = LocalDateTime.now();
    }

    UUID id;

    UUID userId;

    UUID filmId;

    Double score;

    LocalDateTime dateTime;
}
