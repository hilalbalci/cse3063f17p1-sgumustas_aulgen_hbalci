public class Money {
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
