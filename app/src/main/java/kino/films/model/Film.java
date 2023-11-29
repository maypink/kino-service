package kino.films.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "films")
@NoArgsConstructor
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 1, message = "Title is too short.")
    @Size(max = 100, message = "Title is too long.")
    private String title;

    @Min(1800)
    @Max(2023)
    private Integer year;

    @ManyToMany
    @JoinTable(
            name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> filmGenres;
}
