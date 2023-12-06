package kino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kino.exception.film.ResponseFilmErrorException;
import kino.exception.film.customException.FilmNotFoundException;
import kino.service.FilmRatingService;
import kino.service.FilmService;
import kino.utils.FilmRatingResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/films-ratings")
@RequiredArgsConstructor
public class FilmRatingController {

    @Autowired
    FilmRatingService filmRatingService;

    @Autowired
    FilmService filmService;

    @Operation(
            summary = "Get all films ratings."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllFilmsRatings() {
        List<FilmRatingResource> filmRatingResources = filmRatingService.getAllFilmRatings();
        return ResponseEntity.status(HttpStatus.OK).body(filmRatingResources);
    }

    @Operation(
            summary = "Get films rating by username."
    )
    @GetMapping("/{username}")
    public ResponseEntity<?> getFilmRatingsByUsername(@RequestParam @Parameter(description = "username") String username) throws InterruptedException {

        List<FilmRatingResource> filmRatingResources = filmRatingService.getAllFilmRatingForUsername(username);
        if (filmRatingResources.isEmpty()){
            throw new FilmNotFoundException("Ratings for specified user_id were not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(filmRatingResources);
        }
    }

    @Operation(
            summary = "Add film rating for user and tmdb_id."
    )
    @PostMapping("/ratings/{tmdb_id}/{user_id}")
    public ResponseEntity<?> addFilmRatingForUserId(@RequestParam @Parameter(description = "user_id") UUID userId,
                                                    @RequestParam @Parameter(description = "tmdb_id") String tmdbId,
                                                    @RequestParam Integer rating) throws ResponseFilmErrorException {

        FilmRatingResource filmRatingResource = filmRatingService.addFilmRatingForUserId(userId, tmdbId, rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmRatingResource);
    }
}
