package org.kino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kino.service.RabbitMqProducer;
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
}
