package kino.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "films_ratings")
@AllArgsConstructor
@NoArgsConstructor
public class FilmRating {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

//    @ManyToOne(targetEntity = Film.class)
//    @JoinColumn(name="film_id")
    private UUID film_id;

    @Column(name = "rating")
    private Integer rating;
}
