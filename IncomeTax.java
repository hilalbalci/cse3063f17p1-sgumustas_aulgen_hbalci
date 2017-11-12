public class IncomeTax extends Square {

	public IncomeTax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Player player, Board board) {
		int a = player.getMoney().getMoney()/10;
		player.getMoney().reduceMoney(a);
	}

}
