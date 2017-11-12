public class IncomeTax extends Square {

	public IncomeTax(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		int a = player.getMoney().getMoney()/10;
		Printer.print(player, " will lose TL" + a + " by paying Income Tax!", board.squares);
		player.reduceMoney(a);
	}
}
