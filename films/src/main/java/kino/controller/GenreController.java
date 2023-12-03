package kino.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kino.exception.genre.ResponseGenreErrorException;
import kino.service.GenreService;
import kino.utils.GenreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    @Autowired
    private final GenreService genreService;

    @Operation(
            summary = "Get all genres from database."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllGenres() {
        List<GenreResource> genreResources = genreService.findAllGenres();
        return ResponseEntity.status(HttpStatus.OK).body(genreResources);
    }

    @Operation(
            summary = "Add genre."
    )
    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestParam @Parameter(description = "genre") String genre) throws ResponseGenreErrorException {

        GenreResource genreResource = new GenreResource(UUID.randomUUID(), genre);
        genreService.save(genreResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreResource);
    }
}
