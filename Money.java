public class Money {
<<<<<<< HEAD
    private int moneyValue;

    public void reduceMoney(int a) {
        moneyValue -= a;
    }

    public void increaseMoney(int a) {
        moneyValue += a;
    }

    public int getMoney() {
        return moneyValue;
    }

    public void setMoneyValue(int a) {
        this.moneyValue = a;
    }
}
=======
	private int moneyValue = 200;
	
	public void reduceMoney (int a) {
		moneyValue -= a;
	}
	
	public void increaseMoney (int a) {
		moneyValue += a;
	}
	
	public int getMoney() {
		return moneyValue;
	}
	
	public boolean hasLost () {
		return (moneyValue < 0);
	}
}
>>>>>>> eafa3ad1d8f6dc5c8999e635de3730c7e86992c7
