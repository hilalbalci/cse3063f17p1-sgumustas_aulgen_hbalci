public class Player {
    private int tournamentID = 0, id, location = 0, movement = 0, previousLocation = 0, jailHolder = 0, doubles = 0;
    private String name;
    private Money money = new Money();
    private boolean status = false, isInPrison = false;
    Die die = new Die();

    public Player(int id, String name, int startMoney) {
        this.id = id;
        this.name = name;
        this.money.setMoneyValue(startMoney);
    }

    public int doubles() {
        return doubles;
    }

    public void increaseDouble() {
        doubles++;
    }

    public void setDoubleZero() {
        doubles = 0;
    }

    public int getMovement() {
        return movement;
    }

    public void sendToJail(Square[] a) {
        Printer.print("[Turn " + (this.getMovement() + 1) + "]\t[" + a[this.getPreviousLocation()].getName() + "]\t" + "[TL"
                + this.getMoney().getMoney() + "]\t" + this.getName() +
                " goes to Jail!", true);
        this.setLocation(10);
        isInPrison = true;
    }

    public void getOutOfJail() {
        isInPrison = false;
        jailHolder = 0;
        doubles = 0;
    }

    public boolean isInJail() {
        return isInPrison;
    }

    public int tossSingleDie() {
        return die.getDiceValue();
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public int getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(int a) {
        previousLocation = a;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void reduceMoney(int a) {
        this.money.reduceMoney(a);
    }

    public void increaseMoney(int a) {
        this.money.increaseMoney(a);
    }

    public Money getMoney() {
        return this.money;
    }

    public void nextTurn() {
        movement++;
    }

    public void setLost(boolean a) {
        status = a;
    }

    public boolean hasLost() {
        return status;
    }

    public int jailHolder() {
        return jailHolder;
    }

    public void increaseJailHolder() {
        jailHolder++;
    }

}