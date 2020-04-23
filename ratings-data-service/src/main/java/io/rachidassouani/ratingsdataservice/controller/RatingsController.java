package io.rachidassouani.ratingsdataservice.controller;

import io.rachidassouani.ratingsdataservice.model.Rating;
import io.rachidassouani.ratingsdataservice.model.UserRating;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("ratingsData")
public class RatingsController {


    @GetMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("users/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("PK", 4),
                new Rating("the pursuit of ..", 5),
                new Rating("Parasite", 3),
                new Rating("Joker", 2)
        );
//        return ratings;
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return  userRating;

    }
}
