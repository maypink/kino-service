package org.recommendations.rabbitmq;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.recommendations.config.RabbitMqConfiguration;
import org.recommendations.rabbitmq.utils.FilmListResource;
import org.recommendations.rabbitmq.utils.FilmRatingResourceList;
import org.recommendations.rabbitmq.utils.UserResource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqProducer {

    @Autowired
    RabbitTemplate template;

    private final Gson gson = new Gson();

    // USERS
    public UserResource getUserByUsername(String username) throws InterruptedException {
        log.info("Films Service -- RabbitMqProducer.getUserByUsername method was evoked.");
        String response = (String) template.convertSendAndReceive("getUserByUsernameForRecommendations", username);
        // maybe if response is null then raise exception
        return gson.fromJson(response, UserResource.class);
    }

    // FILMS
    public FilmListResource getAllFilms() throws InterruptedException {
        log.info("Recommendations Service -- RabbitMqProducer.getAllFilms method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilmsForRecommendations", "simpleMessage");
        // maybe if response is null then raise exception
        return gson.fromJson(response, FilmListResource.class);
    }

    // RATINGS
    public FilmRatingResourceList getAllFilmRatingsByUsername(String username) throws InterruptedException {
        log.info("Recommendations Service -- RabbitMqProducer.getAllFilmRatingsByUsername method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilmRatingsByUsernameForRecommendations", username);
        return gson.fromJson(response, FilmRatingResourceList.class);
    }
}
