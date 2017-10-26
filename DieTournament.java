public class DieTournament {
	Die die = new Die();
	public DieTournament (Player [] players) {
		int length = players.length;
		for (int i = 0; i < length; i++) {
			players[i].tossTournament(die);
		}
		System.out.println();
		sort(players);
		System.out.println("\nNew Precedence of Starts:\n");
		printNewList(players);
	}
	
	public static void printNewList(Player [] players) {
		int length = players.length;
		for (int i = 0; i< length; i++) {
			if (i!=length - 1)
			System.out.print(players[i].getName() + ", ");
			else
				System.out.println(players[i].getName() + "\n");	
		}
	}
	
	public static void sort (Player [] players) {
		int length = players.length;
		for (int i = 0; i < length; i++) {
			for (int k = i+1; k < length; k++) {
				if (players[i].getTournamentID() < players[k].getTournamentID()) {
					System.out.println("Player " + players[k].getName() + " got infront of " + players[i].getName() + ".");
					Player temp = players[i];
					players[i] = players[k];
					players[k] = temp;
				}
			}
		}
	}
}