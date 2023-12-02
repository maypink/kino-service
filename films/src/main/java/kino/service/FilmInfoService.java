package kino.service;

import kino.client.Tmdb.utils.FilmInfoResource;
import kino.utils.FilmResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface FilmInfoService {

    List<FilmInfoResource> getFilmInfosByFilmId(UUID filmId);

    FilmInfoResource save(FilmInfoResource filmResource);
}
