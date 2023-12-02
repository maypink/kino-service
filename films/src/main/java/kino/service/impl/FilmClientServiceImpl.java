package kino.service.impl;

import kino.client.Tmdb.FilmClient;
import kino.client.Tmdb.FilmInfoApiResponce;
import kino.client.Tmdb.TmdbApiResponce;
import kino.client.Tmdb.utils.FilmInfoMapper;
import kino.client.Tmdb.utils.FilmInfoResource;
import kino.client.Tmdb.utils.GenreTmdbEnum;
import kino.exception.film.customException.FilmDuplicateException;
import kino.exception.film.customException.FilmNotFoundException;
import kino.repository.FilmInfoRepository;
import kino.service.FilmClientService;
import kino.service.FilmInfoService;
import kino.service.FilmService;
import kino.utils.FilmResource;
import kino.utils.GenreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Service
public class FilmClientServiceImpl implements FilmClientService {

    @Autowired
    FilmClient filmClient;

    @Autowired
    FilmService filmService;

    @Autowired
    FilmInfoMapper filmInfoMapper;

    @Autowired
    FilmInfoService filmInfoService;

    @Override
    public FilmInfoResource getFilmInfoByTitleAndYear(String title, Integer year) throws FilmNotFoundException {
        TmdbApiResponce tmdbApiResponce = filmClient.getFilmInfoByTitle(title);
        if (tmdbApiResponce.results().isEmpty()){
            throw new FilmNotFoundException("There is no such film in TMDb.");
        }
        FilmInfoApiResponce filmInfoApiResponce = tmdbApiResponce.results().get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filmReleaseDate = LocalDate.parse(filmInfoApiResponce.release_date(), formatter);
        if (filmReleaseDate.getYear() != year){
            throw new FilmNotFoundException("There is no such film in TMDb.");
        }

        // check if there is a film in db
        List<FilmResource> filmResourceList = filmService.getFilmByTitleYear(filmInfoApiResponce.title(), filmReleaseDate.getYear());
        if (filmResourceList.isEmpty()){
            FilmResource filmResource = new FilmResource(
                    UUID.randomUUID(),
                    filmInfoApiResponce.title(),
                    filmReleaseDate.getYear(),
                    String.valueOf(filmInfoApiResponce.id()),
                    filmInfoApiResponce.genre_ids().stream().map(g -> new GenreResource(UUID.randomUUID(), GenreTmdbEnum.fromInteger(g))).toList());
            FilmResource savedFilmResource = filmService.save(filmResource);

            // save film info
            FilmInfoResource filmInfoResource = new FilmInfoResource(
                    UUID.randomUUID(),
                    savedFilmResource.getId(),
                    filmInfoApiResponce.poster_path());
            if (filmInfoService.getFilmInfosByFilmId(filmInfoResource.getFilmId()).isEmpty()){
                filmInfoService.save(filmInfoResource);
            }
        } else {
            throw new FilmDuplicateException("Attempt to insert duplicate of film.");
        }

        return filmInfoMapper.toResource(filmInfoApiResponce);
    }

    public void saveFilmInfo(FilmInfoResource filmInfoResource){
        return;
    }

}
