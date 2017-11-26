public abstract class Square {
    private String name;
    private int price, rent;
    private Player owner = null;
    private boolean isOwned = false;

    public Square(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public abstract void doAction(Player player, Board board);

    public boolean hasOwner() {
        return isOwned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player a) {
        owner = a;
        a.reduceMoney(this.price);
        isOwned = true;
    }

    public void setFree () {
        owner = null;
        isOwned = false;
    }
}