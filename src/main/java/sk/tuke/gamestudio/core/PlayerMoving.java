package sk.tuke.gamestudio.core;

import static sk.tuke.gamestudio.core.FieldPlayers.getBotIcon;
import static sk.tuke.gamestudio.core.FieldPlayers.getPlayerIcon;

public class PlayerMoving {

    public static boolean trueMoving(char[][] board, int[][] moves, char player, int size) {
        // Количество возможных ходов
        int noOfMoves = 0;
        char opponent = getOpponent(player);
        // Обход всех клеток поля
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                //двумерный массив целых чисел для хранения информации о возможных ходах игрока
                moves[row][col] = 0;
                // Если клетка занята, переходим к следующей
                if (board[row][col] != '.') {
                    continue;
                }
                // Проверяем все возможные направления движения фишки игрока. Поиск ходов для текущей ячейки
                for (int rowdelta = -1; rowdelta <= 1; rowdelta++) {
                    for (int coldelta = -1; coldelta <= 1; coldelta++) {
                        // Вычисление новых координат для проверки
                        int newRow = row + rowdelta;
                        int newCol = col + coldelta;
                        // Если новые координаты находятся за пределами доски или равны текущим координатам, переход к следующим координатам
                        if (isOutOfBounds(newRow, newCol, size) || (rowdelta == 0 && coldelta == 0)) {
                            continue;
                        }
                        // Если новая ячейка занята символом противника, проверяем возможность переворота
                        if (board[newRow][newCol] == opponent) {
                            int x = newRow;
                            int y = newCol;
                            // Проверка всех ячеек по направлению хода до первой пустой ячейки или ячейки с символом игрока
                            while (true) {
                                x += rowdelta;
                                y += coldelta;
                                // Если новые координаты находятся за пределами доски или новая ячейка пуста, завершаем цикл
                                if (isOutOfBounds(x, y, size) || board[x][y] == ' ') {
                                    break;
                                }
                                // Если новая ячейка занята символом игрока, фиксируем возможный ход и увеличиваем счетчик ходов
                                if (board[x][y] == player) {
                                    moves[row][col] = 1;
                                    noOfMoves++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Если есть возможные ходы, возвращаем true
        return noOfMoves > 0;
    }

    public static void doMoving(char[][] board, int row, int col, char player, int size) {
        int rowDelta, colDelta, x, y;
        char opponent = getOpponent(player);
        // Установить игрока на данной клетке доски
        board[row][col] = player;
        // Проход по всем возможным направлениям
        for (rowDelta = -1; rowDelta <= 1; rowDelta++) {
            for (colDelta = -1; colDelta <= 1; colDelta++) {
                // Пропустить текущую клетку или клетки, которые находятся за границей доски
                if (isOutOfBounds(row + rowDelta, col + colDelta, size) || (rowDelta == 0 && colDelta == 0)) {
                    continue;
                }
                // Если на найденной ячейке есть символ противника, то начинаем искать цепочку ячеек, которые можно захватить
                if (board[row + rowDelta][col + colDelta] == opponent) {
                    x = row + rowDelta;
                    y = col + colDelta;
                    //Продолжаем искать ячейки, пока не встретим границу доски или пустую ячейку
                    while (true) {
                        x += rowDelta;
                        y += colDelta;
                        //Если нашли ячейку с символом игрока
                        if (isOutOfBounds(x, y, size) || board[x][y] == ' ') {
                            break;
                        }
                        //то в цикле захватываем все ячейки противника на пути до нее, меняя символ на символ игрока.
                        if (board[x][y] == player) {
                            while (board[x -= rowDelta][y -= colDelta] == opponent) {
                                board[x][y] = player;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    // Метод для проверки, выходят ли координаты за пределы доски
    private static boolean isOutOfBounds(int row, int col, int size) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    private static char getOpponent(char player) {
        return player == getPlayerIcon() ? getBotIcon() : getPlayerIcon();
    }
}
