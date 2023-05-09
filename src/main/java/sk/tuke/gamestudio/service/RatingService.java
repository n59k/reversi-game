package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

public interface RatingService {
    void setRating(Rating rating) throws RatingException;

    public List<Rating> getRatings(String game) throws RatingException;

    int getRating(String game) throws RatingException;

    void updateRating(Rating rating);

    int getRatingAll(String game, String player);

    double getAverageRating(String game);
}
