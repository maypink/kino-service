package kino.client.Tmdb.utils;

import kino.model.FilmInfo;
import org.springframework.stereotype.Component;

@Component
public class FilmInfoMapper {

    public FilmInfoResource toResource(FilmInfo filmInfo){
        return new FilmInfoResource(filmInfo.getId(), filmInfo.getTmdbId(), filmInfo.getPosterPath());
    }

    public FilmInfo toFilmInfo(FilmInfoResource filmInfoResource){
        return new FilmInfo(
                filmInfoResource.id(),
                filmInfoResource.tmdbId(),
                filmInfoResource.posterPath()
        );
    }
}