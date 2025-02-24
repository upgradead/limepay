package com.directa24.main.challenge.limepay.service;

import com.directa24.main.challenge.limepay.exception.InvalidThresholdException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class MovieServiceImplIntegrationTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void whenThresholdIsMoreThanAnyDirectorsTotalMovies() {
        final List<String> directors = movieService.getDirectors(20);

        assertEquals(0, directors.size());
        System.out.println(directors);
    }

    @Test
    public void whenThresholdLessThanMinimum() {
        Exception exception = assertThrows(InvalidThresholdException.class, () -> {
            movieService.getDirectors(-1);
        });
        String expectedMessage = "Invalid threshold -1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void retrieveDirectorsHasThreshold4ShouldReturn2Directors() {
        final List<String> directors = movieService.getDirectors(4);

        final List<String> expectedDirectors = new ArrayList<>();
        final String firstDirector = "Martin Scorsese";
        expectedDirectors.add(firstDirector);
        final String secondDirector = "Woody Allen";
        expectedDirectors.add(secondDirector);

        assertEquals(2, directors.size());
        assertTrue(directors.containsAll(expectedDirectors));
        assertEquals(firstDirector, directors.get(0));
        assertEquals(secondDirector, directors.get(1));
    }

    @Test
    public void retrieveDirectorsHasThreshold0ShouldBeSorted() {
        final List<String> directors = movieService.getDirectors(0);

        final List<String> expectedDirectors = new ArrayList<>();
        final String firstDirector = "Clint Eastwood";
        expectedDirectors.add(firstDirector);
        final String secondDirector = "Juan José Campanella";
        expectedDirectors.add(secondDirector);
        final String thirdDirector = "M. Night Shyamalan";
        expectedDirectors.add(secondDirector);
        final String fourthDirector = "Martin Scorsese";
        expectedDirectors.add(secondDirector);
        final String fifthDirector = "Pedro Almodóvar";
        expectedDirectors.add(secondDirector);
        final String sixthDirector = "Quentin Tarantino";
        expectedDirectors.add(secondDirector);
        final String seventhDirector = "Woody Allen";
        expectedDirectors.add(secondDirector);

        assertEquals(7, directors.size());
        assertTrue(directors.containsAll(expectedDirectors));
        assertEquals(firstDirector, directors.get(0));
        assertEquals(secondDirector, directors.get(1));
        assertEquals(thirdDirector, directors.get(2));
        assertEquals(fourthDirector, directors.get(3));
        assertEquals(fifthDirector, directors.get(4));
        assertEquals(sixthDirector, directors.get(5));
        assertEquals(seventhDirector, directors.get(6));
    }
}