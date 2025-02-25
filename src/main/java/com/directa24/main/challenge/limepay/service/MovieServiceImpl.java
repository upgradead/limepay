package com.directa24.main.challenge.limepay.service;

import com.directa24.main.challenge.limepay.client.EronFeignClient;
import com.directa24.main.challenge.limepay.exception.InvalidThresholdException;
import com.directa24.main.challenge.limepay.model.Movie;
import com.directa24.main.challenge.limepay.model.MovieDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final EronFeignClient eronFeignClient;

    private TreeMap<String, Integer> directors = new TreeMap<>();
    private static int totalMovies = 0;

    @Autowired
    public MovieServiceImpl(final EronFeignClient eronFeignClient) {
        this.eronFeignClient = eronFeignClient;
    }

    @Override
    public List<String> getDirectors(final int threshold) {
        log.info("Getting directors with threshold {}", threshold);

        if (threshold < 0) {
            throw new InvalidThresholdException(threshold);
        }

        MovieDetails details = eronFeignClient.getMovies(0);

        if (totalMovies == 0 && totalMovies != details.getTotal()) {
            directors.clear();
            totalMovies = details.getTotal();

            for (int page = 1; page <= details.getTotalPages(); page++) {
                MovieDetails movieDetails = eronFeignClient.getMovies(page);
                List<Movie> data = movieDetails.getData();

                for (Movie movie : data) {
                    if (directors.containsKey(movie.getDirector())) {
                        directors.put(movie.getDirector(), directors.get(movie.getDirector()) + 1);
                    } else {
                        directors.put(movie.getDirector(), 1);
                    }
                }
            }
        }

        List<String> filteredDirectors = getDirectorsListBasedOnThreshold(threshold);

        log.info("Getting directors done");
        return filteredDirectors;
    }

    protected List<String> getDirectorsListBasedOnThreshold(int threshold) {
        return directors.entrySet().stream()
                .filter(a -> a.getValue() > threshold)
                .map(Map.Entry::getKey)
                .toList();
    }

    protected void setDirectors(final TreeMap<String, Integer> directors) {
        this.directors = directors;
    }
}
