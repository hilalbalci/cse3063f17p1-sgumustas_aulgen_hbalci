public class LuxuryTax extends Square {

	public LuxuryTax(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction(Player player, Board board) {
		player.getMoney().reduceMoney(75);
	}

}
