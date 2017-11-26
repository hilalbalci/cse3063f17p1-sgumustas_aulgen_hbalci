public class GoToJail extends Square {

    public GoToJail(String name) {
        super(name, 0, 0);
    }

    @Override
    public void doAction(Player player, Board board) {
        player.sendToJail(board.squares);
    }
}
