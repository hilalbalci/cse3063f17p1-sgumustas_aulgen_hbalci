public class LuxuryTax extends Square {

	public LuxuryTax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Player player, Board board) {
		
		System.out.println("[Turn " + (player.getMovement() + 1) + "] [" +
		board.squares[player.getPreviousLocation()].getName() + "] [TL"
		+ player.getMoney().getMoney() + "] " + player.getName() + 
		" will lose TL75 by paying Luxury Tax!");
		
		player.reduceMoney(75);
	}

}
