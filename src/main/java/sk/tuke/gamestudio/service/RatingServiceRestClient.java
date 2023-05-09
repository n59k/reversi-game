package sk.tuke.gamestudio.service;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

public class RatingServiceRestClient implements RatingService {
    private final String URL = "http://localhost:8080/api/rating";

    RestTemplate restTemplate = new RestTemplate();


    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(URL, rating, Rating.class);
    }

    @Override
    public List<Rating> getRatings(String game) throws RatingException {
        return null;
    }

    @Override
    public int getRating(String game) {
        Integer rating = restTemplate.getForObject(URL + "/" + game, Integer.class);
        if (rating != null) {
            return rating;
        } else {
            return 0;
        }
    }

    @Override
    public void updateRating(Rating rating) {

    }

    @Override
    public int getRatingAll(String game, String player) {
        return 0;
    }

    @Override
    public double getAverageRating(String game) {
        return 0;
    }

}
