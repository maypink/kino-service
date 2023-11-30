package kino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kino.exception.film.ResponseFilmErrorException;
import kino.exception.film.customException.FilmNotFoundException;
import kino.service.FilmService;
import kino.utils.FilmResource;
import kino.utils.GenreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    @Autowired
    FilmService filmService;

    @Operation(
            summary = "Get films from database by title."
    )
    @GetMapping("title/{title}")
    public ResponseEntity<?> getFilmsByTitle(@PathVariable @Parameter(description = "title") String title) {

        List<FilmResource> filmResources = filmService.getFilmsByTitle(title);
        if (filmResources.isEmpty()){
            throw new FilmNotFoundException("Film with specified title was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(filmResources);
        }
    }

    @Operation(
            summary = "Get films from database by genre."
    )
    @GetMapping("genres")
    public ResponseEntity<?> getFilmsByGenres(@RequestParam("genres") String genre) {

        List<FilmResource> filmResources = filmService.getFilmsByGenre(genre);
        if (filmResources.isEmpty()){
            throw new FilmNotFoundException("Film with specified genre was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(filmResources);
        }
    }

    @Operation(
            summary = "Get films from database by year."
    )
    @GetMapping("year/{year}")
    public ResponseEntity<?> getFilmsByYear(@PathVariable @Parameter(description = "year") Integer year) {

        List<FilmResource> filmResources = filmService.getFilmByYear(year);
        if (filmResources.isEmpty()){
            throw new FilmNotFoundException("Film with specified year was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(filmResources);
        }
    }

    @Operation(
            summary = "Get all films from database."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllFilms() {
        List<FilmResource> weatherResources = filmService.getAllFilms();
        return ResponseEntity.status(HttpStatus.OK).body(weatherResources);
    }

    @Operation(
            summary = "Add film."
    )
    @PostMapping("/new")
    // TODO: ADD FILMS FROM IMDB
    public ResponseEntity<?> add(@RequestParam @Parameter(description = "title") String title,
                                 @RequestParam @Parameter(description = "year") Integer year,
                                 @RequestParam @Parameter(description = "genre") List<String> genre) throws ResponseFilmErrorException {

        FilmResource filmResource = new FilmResource(UUID.randomUUID(), title, year, genre.stream().map(g -> new GenreResource(UUID.randomUUID(), g)).toList());
        filmService.save(filmResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmResource);
    }
}
