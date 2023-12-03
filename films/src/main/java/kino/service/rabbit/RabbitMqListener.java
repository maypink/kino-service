package kino.service.rabbit;

import com.google.gson.Gson;
import kino.service.FilmClientService;
import kino.service.FilmService;
import kino.utils.CustomMessage;
import kino.utils.FilmListResource;
import kino.utils.FilmResource;
import kino.utils.FilmSearchInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class RabbitMqListener {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmClientService filmClientService;

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
        log.info("Accepted on worker 2 for post film with id");
        FilmSearchInfo filmSearchInfo = gson.fromJson(filmInfo, FilmSearchInfo.class);
        FilmResource filmResource = filmClientService.getFilmResourceByTitleAndYear(filmSearchInfo.title(), filmSearchInfo.year());
        FilmResource savedFilmResource = filmService.save(filmResource);
        return gson.toJson(savedFilmResource);
    }
}