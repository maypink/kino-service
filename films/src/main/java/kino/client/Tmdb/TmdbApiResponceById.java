package kino.client.Tmdb;

import java.util.LinkedHashMap;
import java.util.List;

public record TmdbApiResponceById(
        boolean adult,
        String backdrop_path,
        Integer id,
        // may be "name" for some movies instead
        String title,
        String original_language,
        String original_title,
        String overview,
        String poster_path,
        String media_type,
        List<LinkedHashMap<String, String>> genres,
        Float popularity,
        // may be "first_air_date" for some movies instead
        String release_date,
        boolean video,
        Float vote_average,
        Integer vote_count,

        Integer status_code
) {
}
