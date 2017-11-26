public class DieTournament {

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
}