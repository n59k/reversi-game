package sk.tuke.gamestudio.server.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.server.controller.user.UserController;
import sk.tuke.gamestudio.service.ScoreService;

import sk.tuke.gamestudio.core.ComputerMoving;
import sk.tuke.gamestudio.core.Field;
import sk.tuke.gamestudio.core.FieldPlayers;
import sk.tuke.gamestudio.core.PlayerMoving;

import java.util.Date;


@Controller
@RequestMapping("/reversi")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReversiController {
    @Autowired
    private UserController userController;
    @Autowired
    private ScoreService scoreService;

    private char[][] board;
    private int size;
    int[][] moves;

    @PostMapping("/size")
    public String reversiSize(@RequestParam("size") int newSize) {
        size = newSize;
        moves = new int[size][size];
        return "redirect:/reversi";
    }

    @GetMapping("")
    public String reversi(Model model) {
        if (size > 0) {
            board = Field.createBoard(size);
            FieldPlayers.fieldPlayers(board, size);
            PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size);
            String html = generateHtml(board, moves, size);
            model.addAttribute("boardHtml", html);
        }
        return "game";
    }



    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResponseEntity<String> makeMove(@RequestParam("row") int row, @RequestParam("col") int col, Model model) {

        boolean playerHasValidMoves = PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size);
        if (playerHasValidMoves && moves[row][col] != 0) {
            PlayerMoving.doMoving(board, row, col, FieldPlayers.getPlayerIcon(), size);
            PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size); // Обновляем moves после хода игрока
        }

        boolean botHasValidMoves = PlayerMoving.trueMoving(board, moves, FieldPlayers.getBotIcon(), size);
        if (botHasValidMoves) {
            ComputerMoving.computerMoving(board, moves, FieldPlayers.getBotIcon(), size);
            PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size); // Обновляем moves после хода бота
        }
        PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size);
        String html = generateHtml(board, moves, size);
        model.addAttribute("boardHtml", html);

        int playerScore = countScore(board, FieldPlayers.getPlayerIcon());
        int botScore = countScore(board, FieldPlayers.getBotIcon());

        boolean gameOver = isGameOver();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("boardHtml", html);
        jsonResponse.put("playerScore", playerScore);
        jsonResponse.put("botScore", botScore);
        jsonResponse.put("gameOver", gameOver);
        if (gameOver) {
            String email = userController.getLoggedUser().getLogin();
            saveScore(email, playerScore);
        }

        return ResponseEntity.ok(jsonResponse.toString());

        }
    private int countScore(char[][] board, char player) {
        int score = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == player) {
                    score++;
                }
            }
        }
        return score;
    }

    private void saveScore(String email, int score) {
        Score newScore = new Score(email, "reversi", score, new Date());
        scoreService.addScore(newScore);
    }

    private boolean isGameOver() {
        return !PlayerMoving.trueMoving(board, moves, FieldPlayers.getPlayerIcon(), size) &&
                !PlayerMoving.trueMoving(board, moves, FieldPlayers.getBotIcon(), size);
    }


    private String generateHtml(char[][] board, int[][] moves, int size) {
        String html = "<table id=\"board\">";
        for (int row = 0; row < size; row++) {
            html += "<tr>";
            for (int col = 0; col < size; col++) {
                String backgroundImage = "none";
                if (board[row][col] == FieldPlayers.getBotIcon()) {
                    backgroundImage = "<img class=\"w-20 h-20 m-1\" src='images/reversi-game/cell-bot.png'>";
                } else if (board[row][col] == FieldPlayers.getPlayerIcon()) {
                    backgroundImage = "<img class=\"w-20 h-20 m-1 transition-all ease-in-out duration-700 hover:duration-700 hover:-translate-y-1\" src='images/reversi-game/cell-player.png'>";
                } else if (moves[row][col] != 0) {
                    backgroundImage = "<img class=\"w-20 h-20 m-1 transition-all ease-in-out duration-700 hover:duration-700 hover:-translate-y-1\" src='/images/reversi-game/cell-movable.png'>";
                } else {
                    backgroundImage = "<img class=\"w-20 h-20 m-1\" src='/images/reversi-game/cell.png'>";
                }
                html += "<td data-row=\"" + row + "\" data-col=\"" + col + "\">" + backgroundImage + "</td>";
            }
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }

}
