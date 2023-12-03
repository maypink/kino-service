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
@Table(name = "films_info")
@AllArgsConstructor
@NoArgsConstructor
public class FilmInfo {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tmdb_id")
    private String tmdbId;

    @Column(name = "poster_path")
    private String posterPath;
}
