public class Utilities extends Square {
    public Utilities(String name, int price, int rent) {
        super(name, price, rent);
    }

    @Override
    public void doAction(Player player, Board board) {
        if (this.getOwner()!=player && this.getOwner()!=null && this.getOwner().hasLost()) {
            this.setFree();
        }
        int a = this.getPrice(), c = player.getMoney().getMoney(), d;
        if (this.hasOwner() && this.getOwner() != player) {
            d = player.tossSingleDie() * 10;
            Printer.print(player, " tossed " + d + ".", board.squares);
            if (c - d >= 0) {
                player.reduceMoney(d);
                this.getOwner().increaseMoney(d);
                Printer.print(player, " has given TL" + d + " to player " + this.getOwner().getName() + " for utility!", board.squares);
            } else {
                Printer.print(player, " can't pay TL" + d + " because " + player.getName() + " doesn't have enough money to pay! It gives remaining of his money to " + this.getOwner().getName() + ".", board.squares);
                this.getOwner().increaseMoney(c);
                player.reduceMoney(c);
                player.setLost(true);
            }
        } else if (!this.hasOwner() && this.getOwner()==null) {
            if (player.tossSingleDie() > 4 && c >= a) {
                Printer.print(player, " has bought " + this.getName() + " for TL" + a + ".", board.squares);
                this.setOwner(player);
            } else {
                Printer.print(player, " doesn't buy " + this.getName() + "[TL" + this.getPrice() + "].", board.squares);
            }
        }
    }
}
