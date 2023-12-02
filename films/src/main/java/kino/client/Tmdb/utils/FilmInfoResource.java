package kino.client.Tmdb.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class FilmInfoResource {
    UUID id;

    UUID filmId;

    String posterPath;

}
