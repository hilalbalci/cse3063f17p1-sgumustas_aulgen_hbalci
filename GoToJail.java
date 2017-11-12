public class GoToJail extends Square {

	public GoToJail(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Player player, Board board) {
		System.out.println("[Turn " + (player.getMovement() + 1) + "] [Go To Jail] [TL"
		+ player.getMoney().getMoney() + "] " + player.getName() + " goes to JAIL!");
		
		player.sendToJail();
	}

}
