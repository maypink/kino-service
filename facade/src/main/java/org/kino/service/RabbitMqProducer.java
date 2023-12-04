package org.kino.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.kino.utils.rabbitUtils.FilmListResource;
import org.kino.utils.FilmResource;
import org.kino.utils.rabbitUtils.FilmSearchInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqProducer {

    @Autowired
    RabbitTemplate template;

    private final Gson gson = new Gson();

    public FilmListResource getAllFilms() throws InterruptedException {
        log.info("Facade Service -- RabbitMqProducer.getAllFilms method was evoked.");
        String response = (String) template.convertSendAndReceive("getAllFilms", "simpleMessage");
        // maybe if response is null then raise exception
        return new Gson().fromJson(response, FilmListResource.class);
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
}
