import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Monopoly {
    private static Scanner scanner = new Scanner(System.in);
    private Board board;
    static BufferedWriter wr = null;

    public Monopoly(int totalPlayer, String[] playerNames, int startMoney) {
        board = new Board(totalPlayer, playerNames, getFileName(), startMoney);
        DieTournament.makeTournament(board.getPlayers());
    }

    public static void main(String[] args) {
        try {
            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("").getAbsolutePath() + "\\src/" + "output.txt")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Welcome to Monopoly!\n");
        int totalPlayer = getDesiredPlayerNumber();
        Monopoly game = new Monopoly(totalPlayer, getDesiredPlayerName(totalPlayer), getDesiredStartingMoney());
        game.startGame();
    }

    public void startGame() {
        Printer.print("GAME STARTS!\n==========", true);
        Player temp;
        while (!board.isOver()) {
            temp = board.getPlayer();
            if (!temp.hasLost()) {
                board.movePlayer(temp);
            }
            board.nextTurn();
            if (board.isOver()) {
                Printer.print("Game is over! All players are bankrupt except " + board.getWinner().getName() + ".\n" + board.getWinner().getName() + " is the winner!", true);
            }
        }
        Printer.print("========\nEND GAME", true);
        Printer.print("\nBankrupt players are: ", true);
        DieTournament.printNewList(board.getBankRuptedPlayers(), board.getBankRuptedPlayers().length);
        try {
            wr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isInteger(String input) {
        int i = 0, length = input.length();
        while (i < length) {
            if (!(input.charAt(i) <= '9' && input.charAt(i) >= '0')) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int getDesiredStartingMoney() {
        String input;
        int number;
        do {
            System.out.println("How much money should players start with?");
            input = scanner.nextLine();
            while (!isInteger(input) || input.equalsIgnoreCase("")) {
                System.out.println("You must enter only integer numbers!");
                System.out.println("How much money should players start with?");
                input = scanner.nextLine();
            }
            number = Integer.parseInt(input);
            if (number <= 200) {
                System.err.println("\nYou must enter a number greater than or equal to 200!");
            }
        } while (number <= 200);
        return number;
    }

    private static int getDesiredPlayerNumber() {
        String input;
        int number;
        do {
            System.out.print("How many people are playing?\nPlayers (2 - 8): ");
            input = scanner.nextLine();
            while (!isInteger(input) || input.equalsIgnoreCase("")) {
                System.out.println("You must enter only integer numbers!");
                System.out.print("\nHow many people are playing?\nPlayers (2 - 8): ");
                input = scanner.nextLine();
            }
            number = Integer.parseInt(input);
            if (number > 8 || number < 2) {
                System.err.println("\nYou must enter a number between 2 - 8!");
            }
        } while (number > 8 || number < 2);
        return number;
    }

    private static String[] getDesiredPlayerName(int totalPlayer) {
        String[] names = new String[totalPlayer];
        System.out.println("\nCan you please give player names?\n");
        for (int i = 0; i < totalPlayer; i++) {
            do {
                switch (i) {
                    case 0:
                        System.out.print("1st ");
                        break;
                    case 1:
                        System.out.print("2nd ");
                        break;
                    case 2:
                        System.out.print("3rd ");
                        break;
                    default:
                        System.out.print((i + 1) + "th ");
                }
                System.out.print("Player's name: ");
                names[i] = scanner.nextLine();
                if (names[i].equalsIgnoreCase("")) {
                    System.out.println("You didn't enter any name! Please give a name!\n");
                }
            } while (names[i].equalsIgnoreCase(""));
        }
        System.out.println();
        return names;
    }

    public static String getFileName() {
        System.out.println("Please enter your input file's path (e.g. \"resources/input.csv\"): ");
        String fileName = scanner.nextLine();
        scanner.close();
        return new File("").getAbsolutePath() + "\\src/" + fileName;
    }
}