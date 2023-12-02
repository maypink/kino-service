package kino.model;

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


    public Film(UUID id,
         String title,
         Integer year,
         String tmdbId,
         Set<Genre> genres)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.tmdbId = tmdbId;
        this.genres = genres;
    }

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

    @Size(min = 1, message = "IMDb id is too short.")
    @Size(max = 15, message = "IMDb id is too long.")
    @Column(name = "tmdb_id")
    private String tmdbId;

    @ManyToMany
    @JoinTable(
            name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}
