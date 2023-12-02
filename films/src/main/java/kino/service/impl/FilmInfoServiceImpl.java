package kino.service.impl;

import kino.client.Tmdb.utils.FilmInfoMapper;
import kino.client.Tmdb.utils.FilmInfoResource;
import kino.exception.film.customException.FilmDuplicateException;
import kino.model.FilmInfo;
import kino.repository.FilmInfoRepository;
import kino.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmInfoServiceImpl implements FilmInfoService {

    @Autowired
    FilmInfoRepository filmInfoRepository;

    @Autowired
    FilmInfoMapper filmInfoMapper;

    @Override
    public List<FilmInfoResource> getFilmInfosByFilmId(UUID filmId) {
        List<FilmInfo> filmInfoList = filmInfoRepository.findByFilmId(filmId);
        return filmInfoList.stream().map(film -> filmInfoMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public FilmInfoResource save(FilmInfoResource filmInfoResource) {
        List<FilmInfoResource> filmInfoResourceList = getFilmInfosByFilmId(filmInfoResource.getFilmId());
        if (!filmInfoResourceList.isEmpty()){
            throw new FilmDuplicateException("Attempt to insert duplicate of genre.");
        }
        FilmInfo filmInfo = new FilmInfo(
                UUID.randomUUID(),
                filmInfoResource.getFilmId(),
                filmInfoResource.getPosterPath());
        filmInfoRepository.save(filmInfo);
        return filmInfoMapper.toResource(filmInfo);
    }
}
