package kino.service;

import kino.client.Tmdb.utils.FilmInfoResource;
import org.springframework.stereotype.Service;


@Service
public interface FilmInfoService {

    FilmInfoResource save(FilmInfoResource filmResource);
}
