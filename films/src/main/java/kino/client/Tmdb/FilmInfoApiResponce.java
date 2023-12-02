package kino.client.Tmdb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record FilmInfoApiResponce(
        boolean adult,
        String backdrop_path,
        Integer id,
        String title,
        String original_language,
        String original_title,
        String overview,
        String poster_path,
        String media_type,
        List<Integer> genre_ids,
        Float popularity,
        String release_date,
        boolean video,
        Float vote_average,
        Integer vote_count){

}
