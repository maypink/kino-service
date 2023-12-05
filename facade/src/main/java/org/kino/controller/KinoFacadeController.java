package org.kino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kino.rabbitmq.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class KinoFacadeController {

    @Autowired
    RabbitMqProducer rabbitMqProducer;

    // FILM SECTION
    @Operation(
            summary = "Get all films from db."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllFilms() throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMqProducer.getAllFilms());
    }

    @Operation(
            summary = "Add film with TMDb by its index."
    )
    @PostMapping("/newWithTMDbId/{tmdbId}")
    public ResponseEntity<?> addWithTMDbIds(@RequestParam @Parameter(description = "tmdbId") String tmdbId) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitMqProducer.addNewFilmWithId(tmdbId));
    }

    @Operation(
            summary = "Add film with TMDb by its title and year."
    )
    @PostMapping("/newWithTMDbTitleAndYear")
    public ResponseEntity<?> addWithTitleAndYear(@RequestParam @Parameter(description = "title") String title,
                                                 @RequestParam @Parameter(description = "year") Integer year) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitMqProducer.addNewFilmWithTitleAndYear(title, year));
    }

    //USER RATINGS SECTION
    @Operation(
            summary = "Get all user ratings from db."
    )
    @GetMapping("/ratings/{username}")
    public ResponseEntity<?> getAllFilmRatingsForUsername(@RequestParam @Parameter(description = "username") String username) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMqProducer.getAllFilmRatingsByUsername(username));
    }

    @Operation(
            summary = "Add user rating for specified tmdb_id."
    )
    @PostMapping("/ratings/new")
    public ResponseEntity<?> addFilmRatingForUsernameAndTmdbId(@RequestParam @Parameter(description = "username") String username,
                                                               @RequestParam @Parameter(description = "tmdbId") String tmdbId,
                                                               @RequestParam Integer rating) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitMqProducer.addFilmRatingForUsernameAndTmdbId(username, tmdbId, rating));
    }

    // USERS
    @Operation(
            summary = "Add user."
    )
    @PostMapping("/users/new")
    public ResponseEntity<?> addUser(@RequestParam @Parameter(description = "username") String username,
                                     @RequestParam @Parameter(description = "name") String name,
                                     @RequestParam @Parameter(description = "surname") String surname) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitMqProducer.addUser(username, name, surname));
    }
}
