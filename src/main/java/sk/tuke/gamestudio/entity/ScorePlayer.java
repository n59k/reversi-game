package sk.tuke.gamestudio.entity;

import sk.tuke.gamestudio.core.PlayerMoving;

import static sk.tuke.gamestudio.core.FieldPlayers.getBotIcon;
import static sk.tuke.gamestudio.core.FieldPlayers.getPlayerIcon;

public class ScorePlayer {


    public static int getScore(char[][] board, char player, int size) {
        int score = 0;
        char opponent = getOpponent(player);
        // Проходим по всем ячейкам на доске и вычисляем очки для текущего игрока
        // Увеличиваем счетчик очков, если ячейка занята текущим игроком
        // Уменьшаем счетчик очков, если ячейка занята соперником
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == opponent) {
                    score--;
                } else if (board[row][col] == player) {
                    score++;
                }
            }
        }
        return score;
    }

    public static int bestMove(char[][] board, int[][] moves, char player, int size) {
        // Возвращает количество очков лучшего хода
        int row = 0;
        int col = 0;
        int i = 0;
        int j = 0;
        // Проходим по всем доступным ходам и вычисляем количество очков для каждого хода
        // Выбираем лучший ход - тот, который приведет к наибольшему количеству очков
        char[][] tempBoard = new char[size][size];
        int prevScore = 0;
        int newScore = 0;

        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                if (moves[row][col] == 0) {
                    continue;
                }

                for (i = 0; i < size; i++) {
                    for (j = 0; j < size; j++) {
                        tempBoard[i][j] = board[i][j];
                    }
                }

                PlayerMoving.doMoving(tempBoard, row, col, player, size);

                newScore = ScorePlayer.getScore(tempBoard, player, size);

                if (prevScore < newScore) {
                    prevScore = newScore;
                }
            }
        }

        return prevScore;
    }

    public static int showPlayerScore(char[][] board, int size) {
        int playerScore = 0;


        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++) {
                playerScore += board[row][col] == getPlayerIcon() ? 1 : 0;
            }
        return playerScore;
    }

    public static int showBotScore(char[][] board, int size) {
        int botScore = 0;

        botScore = 0;
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++) {
                botScore += board[row][col] == getBotIcon() ? 1 : 0;
            }
        return botScore;
    }

    public static void showAllScore(char[][] board, int size) {

        System.out.printf("\nZískali ste " + "%d" + " bodov\n\n", showPlayerScore(board, size));
        System.out.printf("Váš oponent dostal " + "%d" + " bodov\n\n", ScorePlayer.showBotScore(board, size));

        if (showPlayerScore(board, size) > ScorePlayer.showBotScore(board, size)) {
            System.out.printf("Gratulujem, vyhral si!)\n\n");
        } else if (showPlayerScore(board, size) == ScorePlayer.showBotScore(board, size)) {
            System.out.printf("Remíza, skús to znova!\n\n");
        } else {
            System.out.printf("Bohužiaľ si prehral(\n\n");
        }

    }

    private static char getOpponent(char player) {
        return player == getPlayerIcon() ? getBotIcon() : getPlayerIcon();
    }

}
