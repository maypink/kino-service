package kino.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "genres")
@NoArgsConstructor
public class Genre {

    public Genre(
            UUID id,
            String genre
    )
    {
        this.id = id;
        this.genre = genre;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 3, message = "Genre is too short.")
    @Size(max = 30, message = "Genre is too long.")
    private String genre;

    @ManyToMany
    private Set<Film> films;

}
