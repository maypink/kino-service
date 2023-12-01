package org.recommendations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recommendations_table")
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "film_id")
    UUID filmId;

    @Column(name = "score")
    Double score;

    @Column(name = "date_time")
    LocalDateTime dateTime;
}
