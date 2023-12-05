package org.kino.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.kino.config.RabbitConfiguration;
import org.kino.utils.FilmRatingResource;
import org.kino.utils.UserResource;
import org.kino.utils.rabbitUtils.FilmListResource;
import org.kino.utils.FilmResource;
import org.kino.utils.rabbitUtils.FilmRatingInfo;
import org.kino.utils.rabbitUtils.FilmRatingResourceList;
import org.kino.utils.rabbitUtils.FilmSearchInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RabbitMqProducer {

    @Autowired
    RabbitConfiguration rabbitConfiguration;

    @Autowired
    RabbitTemplate template;

    private final Gson gson = new Gson();

    // FILMS
    public FilmListResource getAllFilms() throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.getAllFilms method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilms", "simpleMessage");
        // maybe if response is null then raise exception
        return gson.fromJson(response, FilmListResource.class);
    }

    public FilmResource addNewFilmWithId(String tmdbId) throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.addNewFilmWithId method was evoked.");
        String response = (String) template.convertSendAndReceive("addNewFilmWithId", tmdbId);
        return gson.fromJson(response, FilmResource.class);
    }

    public FilmResource addNewFilmWithTitleAndYear(String title, Integer year) throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.addNewFilmWithTitleAndYear method was evoked.");
        String response = (String) template.convertSendAndReceive(
                "addNewFilmWithTitleAndYear",
                gson.toJson(new FilmSearchInfo(title, year)));
        return gson.fromJson(response, FilmResource.class);
    }

    // RATINGS
    public FilmRatingResourceList getAllFilmRatingsByUsername(String username) throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.getAllFilmRatingsByUsername method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilmRatingsByUsernameFF", username);
        return gson.fromJson(response, FilmRatingResourceList.class);
    }

    public FilmRatingResource addFilmRatingForUsernameAndTmdbId(String username, String tmdbId, Integer rating) throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.addFilmRatingForUsernameAndTmdbId method was evoked.");
        String response = (String) template.convertSendAndReceive(
                "addFilmRatingForUsernameAndTmdbId",
                gson.toJson(new FilmRatingInfo(username, tmdbId, rating)));
        return gson.fromJson(response, FilmRatingResource.class);
    }

    // USERS
    public UserResource addUser(String username, String name, String surname) throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.addUser method was evoked.");
        String response = (String) rabbitConfiguration.rabbitTemplate().convertSendAndReceive(
                "addUser",
                gson.toJson(new UserResource(UUID.randomUUID(), username, name, surname)));
        return gson.fromJson(response, UserResource.class);
    }

    // RECOMMENDATIONS
    public FilmListResource getRecommendationForUserId() throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.getAllFilms method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilms", "simpleMessage");
        // maybe if response is null then raise exception
        return gson.fromJson(response, FilmListResource.class);
    }
}
