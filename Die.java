import java.util.Random;

public class Die {
	public int getDiceValue() {
		Random randomDiceGenerator = new Random();
		return randomDiceGenerator.nextInt(6) + 1;
	}
}