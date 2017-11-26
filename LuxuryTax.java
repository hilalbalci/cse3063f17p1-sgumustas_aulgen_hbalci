public class LuxuryTax extends Square {

    public LuxuryTax(String name) {
        super(name, 0, 0);
    }

    @Override
    public void doAction(Player player, Board board) {
        int a = player.getMoney().getMoney();
        Printer.print(player, " will lose TL75 by paying Luxury Tax!", board.squares);
        if (a >= 75) {
            player.reduceMoney(75);
            Printer.print(player, " has lost TL75 by paying Luxury Tax!", board.squares);
        } else {
            player.reduceMoney(a);
            Printer.print(player, " gives his all money because " + player.getName() + " doesn't have enough money for Income Tax!", board.squares);
            player.setLost(true);
        }
    }
}