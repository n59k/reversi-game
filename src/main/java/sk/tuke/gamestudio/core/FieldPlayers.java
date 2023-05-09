package sk.tuke.gamestudio.core;

public class FieldPlayers {
    public static void fieldPlayers(char[][] board, int size) {
        board[size / 2 - 1][size / 2 - 1] = getPlayerIcon();
        board[size / 2][size / 2] = getPlayerIcon();
        board[size / 2 - 1][size / 2] = getBotIcon();
        board[size / 2][size / 2 - 1] = getBotIcon();
    }

    public static char getBotIcon() {
        return '#';
    }

    public static char getPlayerIcon() {
        return '$';
    }
}
