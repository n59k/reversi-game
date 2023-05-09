package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.core.ComputerMoving;
import sk.tuke.gamestudio.core.Field;
import sk.tuke.gamestudio.core.FieldPlayers;
import sk.tuke.gamestudio.core.PlayerMoving;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.ScorePlayer;
import sk.tuke.gamestudio.service.ScoreServiceJPA;

import java.util.Date;
import java.util.Scanner;

import static sk.tuke.gamestudio.core.FieldPlayers.getBotIcon;
import static sk.tuke.gamestudio.core.FieldPlayers.getPlayerIcon;

public class GameStructure {

    static final String nameOfTheGame = "Reversi";

    @Autowired
    private static ScoreService scoreService;
    private char[][] board;
    private int size = 6;

    public static void start() {
        int size = 6;
        int[][] moves = new int[size][size];
        char[][] board = new char[size][size];
        int noOfGames = 0;
        int noOfMoves = 0;
        int invalidMoves = 0;
        int player = 0;
        Scanner scanner = new Scanner(System.in);

        int intInput2 = 0, again = 0;
        int intInput = 0;

        boolean playAgain = true;
        while (playAgain) {
            boolean gameOver = false;
            player = ++noOfGames % 2;
            noOfMoves = 4;
            Field.initializingSquares(board, size);
            FieldPlayers.fieldPlayers(board, size);
            do {
                Field.printAll(board, size);
                if (player++ % 2 == 1) {
                    if (PlayerMoving.trueMoving(board, moves, getPlayerIcon(), size)) {

                        for (; ; ) {
                            System.out.print(
                                    "Zadajte prosím svoj presun (riadok a stĺpec, napríklad 11 alebo 42): ");
                            while (!scanner.hasNext()) {
                                scanner.next();
                                System.out.print("Zadajte správnu hodnotu: ");
                            }
                            String input = scanner.next();
                            if (input.length() != 2) {
                                System.out.print("Musíte napísať číslo, resp. riadok a stĺpec\n\n");
                                continue;
                            }
                            intInput = Integer.parseInt(input.substring(0, 1)) - 1;
                            intInput2 = Integer.parseInt(input.substring(1)) - 1;


                            if (intInput >= 0 && intInput2 >= 0 && intInput < size && intInput2 < size && moves[intInput][intInput2] != 0) {
                                PlayerMoving.doMoving(board, intInput, intInput2, getPlayerIcon(), size);
                                noOfMoves++;
                                break;
                            } else {
                                System.out.println("Presun nie je možný, skús to znova.");
                            }
                        }
                    } else if (++invalidMoves < 2) {
                        System.out.println("\nMusíte preskočiť ťah!");
                        scanner.nextLine();
                    } else
                        System.out.println("\nNie sú žiadne ťahy, takže hra sa skončila(:");
                } else {
                    if (PlayerMoving.trueMoving(board, moves, getBotIcon(), size)) {
                        invalidMoves = 0;
                        ComputerMoving.computerMoving(board, moves, getBotIcon(), size);
                        noOfMoves++;
                    } else {
                        if (++invalidMoves < 2)
                            System.out.println("\nMusim počkať");
                        else
                            System.out.println("\nNie sú žiadne ťahy, takže hra sa skončila(:");
                    }
                }
            } while (noOfMoves < size * size && invalidMoves < 2);
            Field.printAll(board, size);
            ScorePlayer.showAllScore(board, size);

        }
        scanner.close();
    }
}
