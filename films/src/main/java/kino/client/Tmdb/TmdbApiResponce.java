package kino.client.Tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public record TmdbApiResponce(
        Integer page,
        ArrayList<FilmInfoApiResponce> results,
        @JsonProperty("total_pages")
        Integer totalPages,
        @JsonProperty("total_results")
        Integer totalResults
) {
}