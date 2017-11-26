public class RailRoad extends Square {

    public RailRoad(String name, int price, int rent) {
        super(name, price, rent);
    }

    @Override
    public void doAction(Player player, Board board) {
        if (this.getOwner()!=player && this.getOwner()!=null && this.getOwner().hasLost()) {
            this.setFree();
        }
        int a = this.getPrice(), c = player.getMoney().getMoney(), d;
        if (this.hasOwner() && this.getOwner() != player) {
            d = player.tossSingleDie() * 5 + 25;
            if (c - d >= 0) {
                Printer.print(player, " has tossed " + (d - 25)/5 + " and will pay " + d + " to owner.", board.squares);
                player.reduceMoney(d);
                this.getOwner().increaseMoney(d);
                Printer.print(player, " has given TL" + d + " to player " + this.getOwner().getName() + " for rent!", board.squares);
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
