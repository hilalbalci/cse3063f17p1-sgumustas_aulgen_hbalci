public class GoSquare extends Square {
	public GoSquare(String name) {
		super(name);
	}
	@Override
	public void doAction(Player player, Board board) {
		Printer.print(player, " will gain TL200 by passing through Start!", board.squares);
		player.increaseMoney(200);
	}
}