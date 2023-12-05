package kino.service.impl;

import kino.exception.film.customException.FilmNotFoundException;
import kino.exception.filmRating.customException.FilmRatingDuplicateException;
import kino.model.FilmRating;
import kino.repository.FilmRatingRepository;
import kino.service.FilmRatingService;
import kino.service.FilmService;
import kino.rabbitmq.RabbitMqProducer;
import kino.rabbitmq.resource.UserResource;
import kino.utils.FilmRatingMapper;
import kino.utils.FilmRatingResource;
import kino.utils.FilmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmRatingServiceImpl implements FilmRatingService {

    @Autowired
    RabbitMqProducer rabbitMqProducer;

    @Autowired
    FilmService filmService;

    @Autowired
    FilmRatingRepository filmRatingRepository;

    @Autowired
    FilmRatingMapper filmRatingMapper;

    @Override
    public List<FilmRatingResource> getAllFilmRatingForUserId(UUID userId) {
        List<FilmRating> filmRatingResourceList = filmRatingRepository.findByUserId(userId);
        return filmRatingResourceList.stream().map(film -> filmRatingMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmRatingResource> getAllFilmRatingForUserIdAndFilmId(UUID userId, UUID filmId) {
        List<FilmRating> filmRatingResourceList = filmRatingRepository.findByUserIdAndFilmId(userId, filmId);
        return filmRatingResourceList.stream().map(film -> filmRatingMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public List<FilmRatingResource> getAllFilmRatingForUsername(String username) throws InterruptedException {
        UserResource userResource = rabbitMqProducer.getUserByUsername(username);
        List<FilmRating> filmRatingResourceList = filmRatingRepository.findByUserId(userResource.id());
        return filmRatingResourceList.stream().map(film -> filmRatingMapper.toResource(film)).collect(Collectors.toList());
    }


    @Override
    public List<FilmRatingResource> getAllFilmRatings() {
        List<FilmRating> filmRatingResourceList = filmRatingRepository.findAllFilms();
        return filmRatingResourceList.stream().map(film -> filmRatingMapper.toResource(film)).collect(Collectors.toList());
    }

    @Override
    public FilmRatingResource addFilmRatingForUserId(UUID userId, String tmdbId, Integer rating){
        List<FilmResource> filmResources = filmService.getFilmByTmdbId(tmdbId);
        if (filmResources.isEmpty()){
            throw new FilmNotFoundException("Film with specified tmdbId is not in db.");
        }
        FilmRatingResource filmRatingResource = new FilmRatingResource(
                UUID.randomUUID(),
                userId,
                filmResources.get(0).id(),
                rating
        );
        return save(filmRatingResource);
    }

    @Override
    public FilmRatingResource save(FilmRatingResource filmRatingResource) {
        List<FilmRatingResource> filmRatingResourceList = getAllFilmRatingForUserIdAndFilmId(filmRatingResource.user_id(), filmRatingResource.film_id());
        if (!filmRatingResourceList.isEmpty()){
            throw new FilmRatingDuplicateException("Attempt to insert duplicate of film rating.");
        }
        FilmRating savedFilmRating = filmRatingRepository.save(filmRatingMapper.toFilmRating(filmRatingResource));
        return filmRatingMapper.toResource(savedFilmRating);
    }
}
