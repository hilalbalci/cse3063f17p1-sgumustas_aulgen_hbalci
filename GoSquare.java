public class GoSquare extends Square {
	public GoSquare(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		player.getMoney().increaseMoney(200);
	}

}
