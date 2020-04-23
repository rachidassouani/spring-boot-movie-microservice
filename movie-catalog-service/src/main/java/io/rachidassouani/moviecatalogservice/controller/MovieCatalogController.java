package io.rachidassouani.moviecatalogservice.controller;

import io.rachidassouani.moviecatalogservice.model.CatalogItem;
import io.rachidassouani.moviecatalogservice.model.Movie;
import io.rachidassouani.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog/")
public class  MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("PK", 4),
                new Rating("the pursuit of ..", 5),
                new Rating("Parasite", 3),
                new Rating("Joker", 2)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    assert movie != null;
                    return new CatalogItem(movie.getName(), "Desc of " + movie.getMovieId(), rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
