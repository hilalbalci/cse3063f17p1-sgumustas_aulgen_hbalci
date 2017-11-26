public class GoSquare extends Square {
<<<<<<< HEAD
    public GoSquare(String name) {
        super(name, 0, 0);
    }

    @Override
    public void doAction(Player player, Board board) {
        Printer.print(player, " will gain TL200 by passing through GO!", board.squares);
        player.increaseMoney(200);
    }
}
=======
	public GoSquare(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		player.getMoney().increaseMoney(200);
	}

}
>>>>>>> eafa3ad1d8f6dc5c8999e635de3730c7e86992c7
