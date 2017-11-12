import java.util.Scanner;

public class Monopoly {
	private static Scanner scanner = new Scanner(System.in);
	private Board board;
	
	public Monopoly(int totalPlayer, String [] playerNames) {
		board = new Board(totalPlayer, playerNames);
		DieTournament.makeTournament(board.getPlayers());
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Monopoly!\n");
		int totalPlayer = getDesiredPlayerNumber();
		String [] playerNames = getDesiredPlayerName(totalPlayer);
		Monopoly game = new Monopoly(totalPlayer, playerNames);
		game.startGame(500*totalPlayer);
	}
	
	public void startGame(int a) {
		System.out.println("GAME STARTS!\n==========");
		Player temp;
		int i = 0;
		while (i < a && !board.isOver()) {
			temp = board.getPlayer();
			if (!temp.hasLost()) {
				board.movePlayer(temp, temp.tossDie(temp, board.squares));
			}
			board.nextTurn();
			i++;
		}
	/*	System.out.println("Bankrupt Players: ");
		for (int k = 0; i<board.getBankRuptedPlayers().size(); k++) {
			System.out.println(board.getBankRuptedPlayers().get(k).getName());
		}*/
		System.out.println("========\nEND GAME");
	}

	
	private static boolean isInteger(String input) {
		int i = 0, length = input.length();
		while(i < length) {
			if (!(input.charAt(i)<='9' && input.charAt(i)>='0')) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	private static int getDesiredPlayerNumber () {
		String input;
		int number;
		do {
			System.out.print("How many people are playing?\nPlayers (2 - 8): ");
			input = scanner.nextLine();
			while(!isInteger(input)) {
				System.err.println("You must enter only integer numbers!");
				System.out.print("\nHow many people are playing?\nPlayers (2 - 8): ");
				input = scanner.nextLine();
			}
			number = Integer.parseInt(input);
			if (number > 8 || number < 2) {
				System.err.println("\nYou must enter a number between 2 - 8!");
			}
		} while (number > 8  || number < 2);
		return number;
	}
	
	private static String [] getDesiredPlayerName (int totalPlayer) {
		String [] names = new String[totalPlayer]; 
		System.out.println("\nCan you please give player names?\n");
		for (int i = 0; i < totalPlayer; i++) {
			if (i == 0) {
				System.out.print((i+1) + "st ");
			} else if (i==1) {
				System.out.print((i+1) + "nd ");
			} else if (i==2) {
				System.out.print((i+1) + "rd ");
			} else {
				System.out.print((i+1) + "th ");
			}
			System.out.print("Player's name: ");
			names[i] = scanner.nextLine();
		}
		System.out.println();
		scanner.close();
		return names;
	}
	
}
