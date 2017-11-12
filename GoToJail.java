public class GoToJail extends Square {

	public GoToJail(String name) {
		super(name);
	}
	@Override
	public void doAction(Player player, Board board) {
		Printer.print(player, " goes to Go To Jail!", board.squares);
		System.out.println("[Turn " + (player.getMovement() + 1) + "] [Go To Jail] " + "[TL"
				+ player.getMoney().getMoney() + "] " + player.getName() + 
				" goes to Jail!");
		player.sendToJail();
	}
}
