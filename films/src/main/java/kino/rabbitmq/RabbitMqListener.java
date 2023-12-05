package kino.rabbitmq;

import com.google.gson.Gson;
import kino.exception.film.customException.FilmNotFoundException;
import kino.rabbitmq.resource.*;
import kino.service.FilmClientService;
import kino.service.FilmRatingService;
import kino.service.FilmService;
import kino.utils.FilmRatingResource;
import kino.utils.FilmResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class RabbitMqListener {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmClientService filmClientService;

    @Autowired
    FilmRatingService filmRatingService;

    @Autowired
    RabbitMqProducer rabbitMqProducer;

    private final Gson gson = new Gson();

    @RabbitListener(queues = "getAllFilms")
    public String worker1(String m) {
        log.info("Accepted on worker 1 for all films");
        List<FilmResource> allFilms = filmService.getAllFilms();
        FilmListResource filmListResource = new FilmListResource(allFilms);
        Gson gson = new Gson();
        return gson.toJson(filmListResource);
    }

    @RabbitListener(queues = "addNewFilmWithId")
    public String worker2(String tmdbId) {
        log.info("Accepted on worker 2 for post film with id");
        FilmResource filmResource = filmClientService.getFilmResourceByTmdbId(tmdbId);
        FilmResource savedFilmResource = filmService.save(filmResource);
        return gson.toJson(savedFilmResource);
    }

    @RabbitListener(queues = "addNewFilmWithTitleAndYear")
    public String worker3(String filmInfo) {
        log.info("Accepted on worker 3 for post film with id");
        FilmSearchInfo filmSearchInfo = gson.fromJson(filmInfo, FilmSearchInfo.class);
        FilmResource filmResource = filmClientService.getFilmResourceByTitleAndYear(filmSearchInfo.title(), filmSearchInfo.year());
        FilmResource savedFilmResource = filmService.save(filmResource);
        return gson.toJson(savedFilmResource);
    }

    @RabbitListener(queues = "getAllFilmRatingsByUsernameFF")
    public String worker4(String username) throws InterruptedException {
        log.info("Accepted on worker 4 for getAllFilmRatingsByUsernameFF");
        List<FilmRatingResource> filmRatingResources = filmRatingService.getAllFilmRatingForUsername(username);
        return gson.toJson(new FilmRatingResourceList(filmRatingResources));
    }

    @RabbitListener(queues = "addFilmRatingForUsernameAndTmdbId")
    public String worker5(String filmRatingInfo) throws InterruptedException {
        log.info("Accepted on worker 5 for addFilmRatingForUsernameAndTmdbId");
        FilmRatingInfo filmRatingInfo1 = gson.fromJson(filmRatingInfo, FilmRatingInfo.class);

        // get user id
        UserResource userResource = rabbitMqProducer.getUserByUsername(filmRatingInfo1.username());
        if (userResource == null){
            throw new FilmNotFoundException("Ratings for specified user_id were not found.");
        }
        UUID userId = userResource.id();

        // save film rating
        FilmRatingResource savedFilmRatingResource = filmRatingService.addFilmRatingForUserId(
                userId,
                filmRatingInfo1.tmdbId(),
                filmRatingInfo1.rating());

        return gson.toJson(savedFilmRatingResource);
    }

    @RabbitListener(queues = "getAllFilmRatingsByUsernameForRecommendations")
    public String worker6(String username) throws InterruptedException {
        log.info("Accepted on worker 6 for getAllFilmRatingsByUsernameForRecommendations");
        List<FilmRatingResource> filmRatingResources = filmRatingService.getAllFilmRatingForUsername(username);
        return gson.toJson(new FilmRatingResourceList(filmRatingResources));
    }

    @RabbitListener(queues = "getAllFilmsForRecommendations")
    public String worker7(String m) {
        log.info("Accepted on worker 7 for all films");
        List<FilmResource> allFilms = filmService.getAllFilms();
        FilmListResource filmListResource = new FilmListResource(allFilms);
        Gson gson = new Gson();
        return gson.toJson(filmListResource);
    }
}