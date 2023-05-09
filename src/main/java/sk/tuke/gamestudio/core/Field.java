package sk.tuke.gamestudio.core;

import java.util.Scanner;


public class Field {
    private static int size = 0;

//    public static int fieldSize() {
//        Scanner scanner = new Scanner(System.in);
//        String inputSize;
//        System.out.printf("-----------------------------\n");
//        System.out.println("Najprv musíte nastaviť veľkosť hracieho poľa. Od 5 do 15 a číslo musí byť parné.");
//        System.out.println("Napríklad, keď napíšete teraz 6, tak hracie pole bude mať teraz rozmery 6x6");
//
//        boolean isInteger = false;
//        while (!isInteger) {
//            if (scanner.hasNextLine()) {
//                inputSize = scanner.nextLine();
//                try {
//                    size = Integer.parseInt(inputSize);
//                    if (size >= 2 && size <= 15 && size % 2 == 0) {
//                        isInteger = true;
//                    } else {
//                        System.out.println("Zadané číslo musí byť párne a medzi 5 a 15. Zadajte prosím správne číslo:");
//
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Zadali ste neplatné číslo. Zadajte prosím celé číslo:");
//
//                }
//            } else {
//                System.out.println("Zadajte prosím celé číslo:");
//                scanner.next();
//            }
//        }
//        System.out.println("Zvolená veľkosť poľa je: " + size);
//        System.out.println("Ďakujem!");
//        System.out.println("\nHráš sa s: " + FieldPlayers.getPlayerIcon());
//        return size;
//    }

    public static int fieldSize(int size) {
        // убедитесь, что размер поля четный и находится в диапазоне от 5 до 15
        if (size % 2 != 0 || size < 5 || size > 15) {
            throw new IllegalArgumentException("Размер поля должен быть четным и находиться в диапазоне от 5 до 15");
        }
        Field.size = size;
        return size;
    }

    public static void initializingSquares(char[][] board, int size) {
        //инициализации всех клеток поля символами пробела
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = ' ';
            }
        }
    }
    public static char[][] createBoard(int size) {
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
        return board;
    }

    public static void printAll(char[][] board, int size) {
        //вывод на экран всех клеток поля и разделительных линий
        System.out.println();
        char[] columns = new char[size];
        //Заполнение массива буквами столбцов
        for (int col = 0; col < size; col++) {
            columns[col] = (char)('a' + col);
        }

        printVerticals(columns);
        printHorizontal(size);
        // Вывод клеток и вертикальных линий
        for (int row = 0; row < size; row++) {
            System.out.printf("%2d|", row + 1);
            for (int col = 0; col < size; col++) {
                System.out.printf(" %c |", board[row][col]);
            }
            System.out.println();
            printHorizontal(size);
        }
    }

    private static void printVerticals(char[] columns) {
        System.out.print("  ");
        for (char c : columns) {
            System.out.printf("   %c", c);
        }
        System.out.println();
    }

    private static void printHorizontal(int size) {
        System.out.print("  +");
        for (int col = 0; col < size; col++) {
            System.out.print("---+");
        }
        System.out.println();
    }

    public static char[][] getBoard() {
        char[][] board = new char[size][size];
        initializingSquares(board, size);
        return board;
    }

    public static int getSize() {
        return size;
    }

}