package kino.service.impl;

import kino.client.Tmdb.utils.FilmInfoMapper;
import kino.client.Tmdb.utils.FilmInfoResource;
import kino.model.FilmInfo;
import kino.repository.FilmInfoRepository;
import kino.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FilmInfoServiceImpl implements FilmInfoService {

    @Autowired
    FilmInfoRepository filmInfoRepository;

    @Autowired
    FilmInfoMapper filmInfoMapper;

    @Override
    public FilmInfoResource save(FilmInfoResource filmInfoResource) {
        FilmInfo filmInfo = new FilmInfo(
                filmInfoResource.id(),
                filmInfoResource.tmdbId(),
                filmInfoResource.posterPath());
        FilmInfo savedFilmInfo = filmInfoRepository.save(filmInfo);
        return filmInfoMapper.toResource(savedFilmInfo);
    }
}
