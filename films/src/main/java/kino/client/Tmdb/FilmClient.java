package kino.client.Tmdb;

import io.swagger.v3.core.util.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface FilmClient {
    @RequestMapping(method = RequestMethod.GET, value = "movie/{filmId}?api_key=${feign.api_key}")
    TmdbApiResponceById getFilmById(@PathVariable("filmId") String filmId);

    @RequestMapping(method = RequestMethod.GET, value = "search/multi?api_key=${feign.api_key}&query={title}", produces = "application/json")
    TmdbApiResponce getFilmInfoByTitle(@PathVariable("title") String title);
}
