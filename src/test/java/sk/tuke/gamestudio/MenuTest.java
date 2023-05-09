package sk.tuke.gamestudio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.game.Menu;
import sk.tuke.gamestudio.service.CommentServiceJDBC;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MenuTest {

    @Test
    @DisplayName("Testovanie funkčnosti tretej položky menu")
    void testMenu3() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n4".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu.menu();
        assertTrue(out.toString().contains("mykyta.kolmohorov@student.tuke.sk"));
    }

    @Test
    @DisplayName("Testovanie funkčnosti štvrtej položky menu")
    void testMenu4() {
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu.menu();
        assertTrue(out.toString().contains("Ďakujeme! Dovidenia."));
    }

    @Test
    @DisplayName("Kontrola pridania komentára")
    void testWriteComment() {
        CommentServiceJDBC commentService = new CommentServiceJDBC();
        String player = "Test";
        String comments = "Dajaky komentar";
        String nameOfTheGame = "Reversi";

        Comment comment = new Comment(player, nameOfTheGame, comments, new Date());
        assertDoesNotThrow(() -> {
            commentService.addComment(comment);
        });
    }

}
