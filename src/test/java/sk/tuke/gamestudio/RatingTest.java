package sk.tuke.gamestudio;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sk.tuke.gamestudio.entity.Rating;

import static org.junit.jupiter.api.Assertions.*;

public class RatingTest {

    private static final String TEST_URL = "jdbc:postgresql://localhost/test";
    private static final String TEST_USER = "test";
    private static final String TEST_PASSWORD = "test";


    @Test
    @DisplayName("Test rating na pracovanie z Getterami")
    void testOnGetters() {
        String player = "Player";
        String game = "Reversi";
        int rating = 5;
        Date ratedOn = new Date();

        Rating rating1 = new Rating(player, game, rating, ratedOn);

        Assert.assertEquals(player, rating1.getPlayer());
        Assert.assertEquals(game, rating1.getGame());
        Assert.assertEquals(rating, rating1.getRating());
        Assert.assertEquals(ratedOn, rating1.getRatedOn());
    }

    @Test
    @DisplayName("Test rating na pracovanie z Setterami")
    void testOnSetters() {

        Rating rating1 = new Rating();

        String player = "Player";
        String game = "Reversi";
        int rating = 4;
        Date ratedOn = new Date();

        rating1.setPlayer(player);
        rating1.setGame(game);
        rating1.setRating(rating);
        rating1.setRatedOn(ratedOn);

        Assert.assertEquals(player, rating1.getPlayer());
        Assert.assertEquals(game, rating1.getGame());
        Assert.assertEquals(rating, rating1.getRating());
        Assert.assertEquals(ratedOn, rating1.getRatedOn());
    }

//    @Test
//    @DisplayName("Kontrola pridania ratingu")
//    void testSendRating() {
//        RatingServiceJDBC ratingService = new RatingServiceJDBC();
//        String player = "Player";
//        String nameOfTheGame = "Reversi";
//        int rating = 5;
//        Rating rating1 = new Rating(player, nameOfTheGame, rating, new Date());
//        assertDoesNotThrow(() -> {
//            ratingService.setRating(rating1);
//        });
//    }
}
