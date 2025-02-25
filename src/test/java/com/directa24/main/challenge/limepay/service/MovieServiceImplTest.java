package com.directa24.main.challenge.limepay.service;


import com.directa24.main.challenge.limepay.client.EronFeignClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.TreeMap;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private EronFeignClient feignClient;
    @InjectMocks
    private  MovieServiceImpl movieService;

    @Test
    public void returnSortedListWithAllAboveThreshold(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("B", 6);
        map.put("C", 6);
        map.put("A", 6);

        movieService.setDirectors(map);
        List<String> output = movieService.getDirectorsListBasedOnThreshold(4);
        Assert.assertEquals(3, output.size());
        Assert.assertEquals("A", output.get(0));
        Assert.assertEquals("B", output.get(1));
        Assert.assertEquals("C", output.get(2));
    }

    @Test
    public void thresholdMoreThanMaxOfAllDirectorsShouldReturnEmptyList(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("B", 6);
        map.put("C", 6);
        map.put("A", 6);

        movieService.setDirectors(map);
        List<String> output = movieService.getDirectorsListBasedOnThreshold(40);
        Assert.assertEquals(0, output.size());
    }

    @Test
    public void onlyOneDirectorExceedsThreshold(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("B", 4);
        map.put("C", 5);
        map.put("A", 6);

        movieService.setDirectors(map);
        List<String> output = movieService.getDirectorsListBasedOnThreshold(5);
        Assert.assertEquals(1, output.size());
        Assert.assertEquals("A", output.get(0));
    }

    @Test
    public void whenDirectorHaveSameThresholdShouldReturnEmptyList(){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("B", 4);

        movieService.setDirectors(map);
        List<String> output = movieService.getDirectorsListBasedOnThreshold(4);
        Assert.assertEquals(0, output.size());
    }
}