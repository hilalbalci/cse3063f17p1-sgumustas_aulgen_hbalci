public class GoSquare extends Square {
	public GoSquare(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		
		System.out.println("[Turn " + (player.getMovement() + 1) + "] [" +
		board.squares[player.getPreviousLocation()].getName() + "] [TL"
		+ player.getMoney().getMoney() + "] " + player.getName() + 
		" will gain TL200 by passing through Start!");
		
		player.increaseMoney(200);
	}

}
