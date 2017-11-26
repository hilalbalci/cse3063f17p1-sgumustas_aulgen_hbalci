public class Printer {
    public static void print(Player player, String string, Square[] squares) {
        print("[Turn " + (player.getMovement() + 1) +
                "]\t[" + squares[player.getLocation()].getName() +
                "]\t[TL" + player.getMoney().getMoney() +
                "]\tPlayer " + player.getName() + string, true);
    }

    public static void print(String a, boolean line) {
        if (line) {
            System.out.println(a);
            try {
                Monopoly.wr.write(a);
                Monopoly.wr.newLine();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.print(a);
            try {
                Monopoly.wr.write(a);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}