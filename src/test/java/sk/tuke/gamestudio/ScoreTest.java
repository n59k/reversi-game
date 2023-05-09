package sk.tuke.gamestudio;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Score;

//import sk.tuke.gamestudio.service.ScoreServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ScoreTest {
//    private ScoreServiceJDBC scoreService;

    @Test
    @DisplayName("Test score na pracovanie z Getterami")
    void testOnGetters() {
        String player = "Player";
        String game = "Reversi";
        int points = 10;
        Date playedOn = new Date();

        Score score = new Score(game, player, points, playedOn);

        Assert.assertEquals(player, score.getPlayer());
        Assert.assertEquals(game, score.getGame());
        Assert.assertEquals(points, score.getPoints());
        Assert.assertEquals(playedOn, score.getPlayedOn());
    }

    @Test
    @DisplayName("Test score na pracovanie z Setterami")
    void testOnSetters() {

        Score score = new Score();

        String player = "Player";
        String game = "Reversi";
        int points = 10;
        Date playedOn = new Date();

        score.setPlayer(player);
        score.setGame(game);
        score.setPoints(points);
        score.setPlayedOn(playedOn);

        Assert.assertEquals(player, score.getPlayer());
        Assert.assertEquals(game, score.getGame());
        Assert.assertEquals(points, score.getPoints());
        Assert.assertEquals(playedOn, score.getPlayedOn());
    }

//    @Test
//    @DisplayName("Kontrola pridania score")
//    void testSendScore() {
//        ScoreServiceJDBC scoreService = new ScoreServiceJDBC();
//        String player = "Player";
//        int points = 55;
//        String nameOfTheGame = "Reversi";
//
//        Score score = new Score(nameOfTheGame, player, points, new Date());
//        assertDoesNotThrow(() -> {
//            scoreService.addScore(score);
//        });
//    }
}
