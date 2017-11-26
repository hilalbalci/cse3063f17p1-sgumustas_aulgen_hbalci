public class GoSquare extends Square {
    public GoSquare(String name) {
        super(name, 0, 0);
    }

    @Override
    public void doAction(Player player, Board board) {
        Printer.print(player, " will gain TL200 by passing through GO!", board.squares);
        player.increaseMoney(200);
    }
}