package io.rachidassouani.moviecatalogservice.controller;

import io.rachidassouani.moviecatalogservice.model.CatalogItem;
import io.rachidassouani.moviecatalogservice.model.Movie;
import io.rachidassouani.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog/")
public class  MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsData/users/" + userId, UserRating.class);

        return ratings.getUserRating()
                .stream()
                .map(rating -> {
                    /*Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/ " + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                     */
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/ " + rating.getMovieId(), Movie.class);
                    assert movie != null;
                    return new CatalogItem(movie.getName(), "Desc of " + movie.getMovieId(), rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
