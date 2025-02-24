package com.directa24.main.challenge.limepay.controlller;


import com.directa24.main.challenge.limepay.dto.DirectorsDto;
import com.directa24.main.challenge.limepay.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    private MovieController movieController;
    private MovieService movieService;

    @BeforeEach
    public void init() {
        movieService = Mockito.mock(MovieService.class);
        movieController = new MovieController(movieService);
    }

    @Test
    public void whenDirectorsAreNotAvailableShouldReturnEmptyList() {
        when(movieService.getDirectors(anyInt())).thenReturn(new ArrayList<>());
        DirectorsDto directors = movieController.getDirectors(10);
        Assertions.assertEquals(0, directors.getDirectors().size());
        Mockito.verify(movieService, times(1)).getDirectors(anyInt());
    }

    @Test
    public void getDirectorsHappyPath() {
        when(movieService.getDirectors(anyInt())).thenReturn(Arrays.asList("Atest", "Btest"));
        DirectorsDto directors = movieController.getDirectors(4);
        Assertions.assertEquals(2, directors.getDirectors().size());
        Mockito.verify(movieService, times(1)).getDirectors(anyInt());
    }
}