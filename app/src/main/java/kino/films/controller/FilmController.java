package kino.films.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kino.films.exception.customException.FilmNotFoundException;
import kino.films.service.FilmService;
import kino.films.utils.FilmResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    @Autowired
    FilmService filmService;

    @Operation(
            summary = "Get film from database."
    )
    @GetMapping("/{title}")
    public ResponseEntity<?> get(@PathVariable @Parameter(description = "title") String title) {

        List<FilmResource> filmResources = filmService.getFilmsByTitle(title);
        if (filmResources.isEmpty()){
            throw new FilmNotFoundException("Film with specified title was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(filmResources);
        }
    }
}
