package sk.tuke.gamestudio.core;

import sk.tuke.gamestudio.entity.ScorePlayer;

public class ComputerMoving {

    public static void computerMoving(char[][] board, int[][] moves, char player, int size) {
        // Инициализируем переменные для хранения лучшего хода
        int bestRow = 0;
        int bestCol = 0;
        int minScore = 100;
        // Создаем временную копию доски и массива возможных ходов
        char[][] tempBoard;
        int[][] tempMoves = new int[size][size];
        char opponent = getOpponent(player);

        // Перебираем все клетки на доске
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // Если текущая клетка не является возможным ходом, пропускаем ее
                if (moves[row][col] == 0) {
                    continue;
                }
                // Создаем копию доски и осуществляем ход на эту клетку
                tempBoard = copyBoard(board, size);
                PlayerMoving.doMoving(tempBoard, row, col, player, size);
                // Обновляем возможные ходы оппонента
                PlayerMoving.trueMoving(tempBoard, tempMoves, opponent, size);
                // Вычисляем лучший ход для оппонента и получаем его оценку
                int newScore = ScorePlayer.bestMove(tempBoard, tempMoves, opponent, size);
                // Если оценка лучше, чем предыдущие, сохраняем новый ход как лучший
                if (newScore < minScore) {
                    minScore = newScore;
                    bestRow = row;
                    bestCol = col;
                }
            }
        }
        // Осуществляем лучший ход бота на доске
        PlayerMoving.doMoving(board, bestRow, bestCol, player, size);
    }

    private static char[][] copyBoard(char[][] board, int size) {
        char[][] tempBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            // Копируем массив строк с помощью метода
            System.arraycopy(board[i], 0, tempBoard[i], 0, size);
        }
        return tempBoard;
    }

    private static char getOpponent(char player) {
        return player == FieldPlayers.getPlayerIcon() ? FieldPlayers.getBotIcon() : FieldPlayers.getPlayerIcon();
    }
}


