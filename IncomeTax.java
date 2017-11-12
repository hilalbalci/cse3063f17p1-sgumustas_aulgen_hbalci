public class IncomeTax extends Square {

	public IncomeTax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Player player, Board board) {
		int a = player.getMoney().getMoney()/10;
		
		System.out.println("[Turn " + (player.getMovement() + 1) + "] [" +
		board.squares[player.getPreviousLocation()].getName() + "] [TL"
		+ player.getMoney().getMoney() + "] " + player.getName() + 
		" will lose TL" + a + " by paying Income Tax!");
		
		player.reduceMoney(a);
	}

}
