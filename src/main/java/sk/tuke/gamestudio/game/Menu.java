package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.core.Field;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.ScorePlayer;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Menu {
    public static String playerName;

    @Autowired
    private static CommentService commentService;

    @Autowired
    private static RatingService ratingService;

    public static Object menu() {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.printf("-----------------------------\n");
            System.out.println("1: Spustiť hru");
            System.out.println("2: Score");
            System.out.println("3: Informácia");
            System.out.println("4: Exit");
            System.out.printf("-----------------------------\n");

            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
            } else {
                break;
            }

            switch (command) {
                case "1":
                    playerName = setName();

                    GameStructure.start();
                    break;
                case "2":
                    ScorePlayer.showPlayerScore(Field.getBoard(), Field.getSize());
                    break;
                case "3":
                    information();
                    break;
                case "4":
                    exit();
                    break;
                default:
                    System.out.println("Príkaz je napísaný nesprávne!");
            }

        }
        while (!command.equals("4"));
        scanner.close();
        return null;
    }

    public static void additionMenu() {
        Scanner scanner = new Scanner(System.in);
        String additionCommand;
        do {
            System.out.printf("-----------------------------\n");
            System.out.println("5: Napísať komentár");
            System.out.println("6: Ohodnotiť hru");
            System.out.println("7: Exit");
            System.out.printf("-----------------------------\n");

            if (scanner.hasNextLine()) {
                additionCommand = scanner.nextLine();
            } else {
                break;
            }

            switch (additionCommand) {
                case "5":
                    //comment
                    writeComment();
                    break;
                case "6":
                    //rating
                    writeRating();
                    break;
                case "7":
                    //return to main menu
                    menu();
                    break;
                default:
                    //not available
                    System.out.println("Príkaz je napísaný nesprávne!");
            }

        }
        while (!additionCommand.equals("7"));
        scanner.close();
    }

    public static String setName() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Napíš si svoje meno: ");
        String playerName = scanner.nextLine().trim();
        System.out.println();
        return playerName;
    }

    private static void writeComment() throws CommentException {

        System.out.println("Napíš si komentár! ");
        String commentInput = new Scanner(System.in).nextLine().trim();
        Comment comment = new Comment(Menu.playerName, GameStructure.nameOfTheGame, commentInput, new Date());
        commentService.addComment(comment);

    }

    private static void writeRating() throws CommentException {

        System.out.println("Ohodnoť si hru! ");
        int ratingInput = 0;
        System.out.println("Zadaj číslo od 1 do 5: ");
        try {
            ratingInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Tu musi byt cislo");
        }
        if (ratingInput < 1) {
            ratingInput = 1;
        } else if (ratingInput > 5) {
            ratingInput = 5;
        }
        Rating rating = new Rating(Menu.playerName, GameStructure.nameOfTheGame, ratingInput, new Date());

        ratingService.setRating(rating);
    }

    public static void information() {
        System.out.println("----------------------------");
        System.out.println("Mykyta Kolmohorov");
        System.out.println("mykyta.kolmohorov@student.tuke.sk");
    }

    public static void exit() {
        System.out.println("----------------------------");
        System.out.println("Ďakujeme! Dovidenia.");
    }
}
