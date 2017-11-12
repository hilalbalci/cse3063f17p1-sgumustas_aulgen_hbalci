public class DieTournament {
	
	public static void makeTournament (Player [] players) {
		int length = players.length;
		for (int i = 0; i < length; i++) {
			tossTournament(players[i]);
		}
		System.out.println();
		sort(players, length);
		System.out.println("\nNew Precedence of Starts:\n");
		printNewList(players, length);
	}
	
	public static void printNewList(Player [] players, int length) {
		//int length = players.length;
		for (int i = 0; i< length; i++) {
			if (i!=length - 1)
			System.out.print(players[i].getName() + ", ");
			else
				System.out.println(players[i].getName() + "\n");	
		}
	}
	
	public static void sort (Player [] players, int length) {
		//int length = players.length;
		Player temp;
		for (int i = 0; i < length; i++) {
			for (int k = i+1; k < length; k++) {
				if (players[i].getTournamentID() < players[k].getTournamentID()) {
					//System.out.println("Player " + players[k].getName() + " got infront of " + players[i].getName() + ".");
					/*Player*/ temp = players[i];
					players[i] = players[k];
					players[k] = temp;
				}
			}
		}
	}
	
	public static void tossTournament(Player a) {
		Die die = new Die();
		a.setTournamentID(die.getDiceValue());
		System.out.println(a.getName() + " tossed " + a.getTournamentID() + " in tournament.");
	}
}