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
@NoArgsConstructor
@AllArgsConstructor
public class FilmInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID filmId;

    private String posterPath;
}
