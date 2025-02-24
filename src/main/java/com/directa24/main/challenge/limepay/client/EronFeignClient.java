package com.directa24.main.challenge.limepay.client;

import com.directa24.main.challenge.limepay.model.MovieDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eron-international", url = "${eron.url}")
public interface EronFeignClient {

    @GetMapping(value = "/api/movies/search?page={page}", produces = MediaType.APPLICATION_JSON_VALUE)
    MovieDetails getMovies(@PathVariable("page") int page);
}
