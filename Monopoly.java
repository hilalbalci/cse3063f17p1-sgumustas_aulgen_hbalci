import java.util.Scanner;

public class Monopoly {
	Die die1 = new Die(), die2 = new Die();
	private static Scanner scanner = new Scanner(System.in);
	Board board;
	
	public Monopoly(int totalPlayer, String [] playerNames) {
		board = new Board(totalPlayer, playerNames);
		DieTournament dt = new DieTournament(board.getPlayers());
	}
	
	public static void main(String[] args) {
		System.out.println("\tMonopoly\n");
		int totalPlayer = getDesiredPlayerNumber();
		String [] playerNames = getDesiredPlayerName(totalPlayer);
		Monopoly game = new Monopoly(totalPlayer, playerNames);
		game.startGame();
	}
	
	public void startGame() {
		System.out.println("GAME STARTS!\n==========");
		for (int i = 0; i<25; i++) {
			board.makeMovement(i);
		}
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
				System.err.println("You must enter a number between 2 - 8!");
			}
		} while (number > 8  || number < 2);
		return number;
	}
	
	private static String [] getDesiredPlayerName (int totalPlayer) {
		String [] names = new String[totalPlayer]; 
		System.out.println("Can you please give player names?\n");
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
			System.out.println();
		}
		scanner.close();
		return names;
	}
	
}
