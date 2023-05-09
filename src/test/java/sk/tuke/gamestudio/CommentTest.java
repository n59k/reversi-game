package sk.tuke.gamestudio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentServiceJDBC;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CommentTest {

    @Test
    @DisplayName("Test comment na pracovanie z Getterami")
    void testOnGetters() {
        String player = "player";
        String game = "game";
        String commentText = "comment by player";
        Date commentedOn = new Date();

        Comment comment = new Comment(player, game, commentText, commentedOn);

        assertEquals(player, comment.getPlayer());
        assertEquals(game, comment.getGame());
        assertEquals(commentText, comment.getComment());
        assertEquals(commentedOn, comment.getCommentedOn());
    }

    @Test
    @DisplayName("Test comment na pracovanie z Setterami")
    void testOnSetters() {

        Comment comment = new Comment();

        String player = "player";
        String game = "game";
        String commentText = "comment by player";
        Date commentedOn = new Date();

        comment.setPlayer(player);
        comment.setGame(game);
        comment.setComment(commentText);
        comment.setCommentedOn(commentedOn);

        assertEquals(player, comment.getPlayer());
        assertEquals(game, comment.getGame());
        assertEquals(commentText, comment.getComment());
        assertEquals(commentedOn, comment.getCommentedOn());
    }

    @Test
    @DisplayName("Test comment kontroluje, či metóda toString() funguje správne")
    void testToString() {
        String player = "player";
        String game = "game";
        String commentText = "comment by player";
        Date commentedOn = new Date();

        Comment comment = new Comment(player, game, commentText, commentedOn);

        String expected = "Comment{player='player', game='game', comment='comment by player', commentedOn=" + commentedOn.toString() + "}";
        assertEquals(expected, comment.toString());
    }

    @Test
    @DisplayName("Kontrola pridania komentára")
    void testWriteComment() {
        CommentServiceJDBC commentService = new CommentServiceJDBC();
        String player = "Player";
        String comments = "Dajaky komentar";
        String nameOfTheGame = "Reversi";
        Comment comment = new Comment(player, nameOfTheGame, comments, new Date());
        assertDoesNotThrow(() -> {
            commentService.addComment(comment);
        });
    }
}
