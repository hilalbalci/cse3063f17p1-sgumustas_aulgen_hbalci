public class LuxuryTax extends Square {

	public LuxuryTax(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		Printer.print(player, " will lose TL75 by paying Luxury Tax!", board.squares);
		player.reduceMoney(75);
	}
}