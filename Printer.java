public class Printer {
	public static void print(Player player, String string, Square [] squares) {
		System.out.println("[Turn " + (player.getMovement() + 1) +
							"] [" + squares[player.getPreviousLocation()].getName() +
							"] [TL" + player.getMoney().getMoney() +
							"] " + player.getName() + string);
	}
}