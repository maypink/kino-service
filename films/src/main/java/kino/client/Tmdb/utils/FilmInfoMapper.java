package kino.client.Tmdb.utils;

import kino.client.Tmdb.FilmInfoApiResponce;
import kino.model.FilmInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FilmInfoMapper {

    public FilmInfoResource toResource(FilmInfoApiResponce filmInfoApiResponce){
        return new FilmInfoResource(UUID.randomUUID(), UUID.randomUUID(), filmInfoApiResponce.poster_path());
    }

    public FilmInfoResource toResource(FilmInfo filmInfo){
        return new FilmInfoResource(filmInfo.getId(), filmInfo.getFilmId(), filmInfo.getPosterPath());
    }
}