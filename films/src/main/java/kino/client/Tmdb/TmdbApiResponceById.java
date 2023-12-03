package kino.client.Tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;

public record TmdbApiResponceById(
        Boolean adult,
        @JsonProperty("backdrop_path")
        String backdropPath,
        Integer id,
        // may be "name" for some movies instead
        String title,
        String name,
        @JsonProperty("original_language")
        String originalLanguage,
        @JsonProperty("original_title")
        String originalTitle,
        String overview,
        @JsonProperty("poster_path")
        String posterPath,
        @JsonProperty("media_type")
        String mediaType,
        @JsonProperty("genres")
        List<LinkedHashMap<String, String>> genres,
        Float popularity,
        @JsonProperty("release_date")
        String releaseDate,
        @JsonProperty("first_air_date")
        String firstAirDate,
        Boolean video,
        @JsonProperty("vote_average")
        Float voteAverage,
        @JsonProperty("vote_count")
        Integer voteCount,
        @JsonProperty("status_code")
        Integer statusCode
) {
}
