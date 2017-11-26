public class DieTournament {
<<<<<<< HEAD

    public static void makeTournament(Player[] players) {
        int length = players.length;
        Printer.print("Die Tournament Starting", true);
        for (int i = 0; i < length; i++) {
            tossTournament(players[i]);
        }
        sort(players, length);
        Printer.print("\nNew Precedence of Starts: ", false);
        printNewList(players, length);
    }

    public static void printNewList(Player[] players, int length) {
        for (int i = 0; i < length; i++) {
            if (players[i] != null) {
                if (i != length - 1)
                    Printer.print(players[i].getName() + ", ", false);
                else
                    Printer.print(players[i].getName(), true);
            }
        }
    }

    public static void sort(Player[] players, int length) {
        Player temp;
        for (int i = 0; i < length; i++) {
            for (int k = i + 1; k < length; k++) {
                if (players[i].getTournamentID() < players[k].getTournamentID()) {
                    temp = players[i];
                    players[i] = players[k];
                    players[k] = temp;
                }
            }
        }
    }

    public static void tossTournament(Player a) {
        Die die = new Die();
        a.setTournamentID(die.getDiceValue());
        Printer.print(a.getName() + " tossed " + a.getTournamentID() + " in tournament.", true);
    }
=======
	
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
>>>>>>> eafa3ad1d8f6dc5c8999e635de3730c7e86992c7
}