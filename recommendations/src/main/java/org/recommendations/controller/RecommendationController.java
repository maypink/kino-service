package org.recommendations.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.recommendations.exception.ResponseRecommendationErrorException;
import org.recommendations.exception.customException.RecommendationNotFoundException;
import org.recommendations.service.RecommendationService;
import org.recommendations.utils.RecommendationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    @Autowired
    RecommendationService recommendationService;

    @Operation(
            summary = "Get recommendations be userId."
    )
    @GetMapping("recommendation/{userId}")
    public ResponseEntity<?> getRecommendationsByUserId(@PathVariable @Parameter(description = "userId") UUID userId) {

        List<RecommendationResource> recommendationResourceList = recommendationService.findAllByUserId(userId);
        if (recommendationResourceList.isEmpty()){
            throw new RecommendationNotFoundException("User with specified user_id was not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(recommendationResourceList);
        }
    }

    @Operation(
            summary = "Add recommendation."
    )
    @PostMapping("/new")
    public ResponseEntity<?> add(@RequestParam @Parameter(description = "userId") UUID userId,
                                 @RequestParam @Parameter(description = "filmId") UUID filmId,
                                 @RequestParam @Parameter(description = "score") Double score) throws ResponseRecommendationErrorException {

        RecommendationResource recommendationResource = new RecommendationResource(UUID.randomUUID(), userId, filmId, score, LocalDateTime.now());
        recommendationService.save(recommendationResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendationResource);
    }

    @Operation(
            summary = "Delete recommendation."
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@RequestParam @Parameter(description = "userId") UUID userId,
                                    @RequestParam @Parameter(description = "filmId") UUID filmId) throws ResponseRecommendationErrorException {

        RecommendationResource recommendationResource = new RecommendationResource(userId, filmId);
        recommendationService.delete(recommendationResource);
        return ResponseEntity.status(HttpStatus.OK).body(recommendationResource);
    }
}
