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
         FilmInfo filmInfo,
         Set<Genre> genres)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.filmInfo = filmInfo;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "film_info_id", referencedColumnName = "id")
    private FilmInfo filmInfo;

    @OneToMany(mappedBy="film_id")
    private Set<FilmRating> ratings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}
