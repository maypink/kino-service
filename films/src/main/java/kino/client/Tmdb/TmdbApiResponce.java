package kino.client.Tmdb;

import java.util.ArrayList;

public record TmdbApiResponce(
        Integer page,
        ArrayList<FilmInfoApiResponce> results,

        Integer total_pages,

        Integer total_results
) {
}