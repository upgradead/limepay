package com.directa24.main.challenge.limepay.controlller;

import com.directa24.main.challenge.limepay.dto.DirectorsDto;
import com.directa24.main.challenge.limepay.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie API", description = "Get directors based on given criteria")
@RestController
@RequestMapping("/api")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Returns a list of threshold depending on threshold")
    @GetMapping(value = "/directors",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public DirectorsDto getDirectors(@RequestParam("threshold") @Min(0) int threshold) {
        log.info("Retrieving directors with threshold {}", threshold);
        List<String> directors = movieService.getDirectors(threshold);
        log.info("Retrieving directors completed");

        return new DirectorsDto(directors);
    }
}
