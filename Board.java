//import java.util.ArrayList;

public class Board {
	//private ArrayList<Player> bankRupt = new ArrayList<Player>();
	private Player[] players;
	Square[] squares = new Square[40];
	Die die = new Die();
	int turn = 0;
	public Player[] getPlayers() {
		return players;
	}
	public Board (int totalPlayer, String [] playerNames) {
		players = new Player[totalPlayer];
		for (int i = 0; i<totalPlayer; i++) {
			players[i] = new Player(i, playerNames[i]);
		}
		for (int i = 0; i< 40; i++ ) {
			if (i==0) {
				squares[i] = new GoSquare("Start!");
			} else if (i==3) {
				squares[i] = new IncomeTax("Income Tax :(");
			} else if (i==10) {
				squares[i] = new Jail("JAIL!");
			} else if (i==20) {
				squares [i] = new FreeParking("Free Parking :)");
			} else if (i==38) {
				squares[i] = new LuxuryTax("Luxury Tax :(");
			} else {
				squares[i] = new Square(i + "th Place") {
					@Override
					public void doAction(Player player, Board board) {}
				};
			}
		}
	}
	
	public void movePlayer(Player player, int face) {
		int temp = player.getLocation(), newPosition = (temp + face)%40, turn = player.getMovement() + 1;
		player.setPreviousLocation(temp);
		if (player.getPreviousLocation() != 30 && newPosition != 10) {
			System.out.println("[Turn " + turn + "] ["
					+ squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" goes to " + squares[newPosition].getName());
		} else if (player.getPreviousLocation() != 30 && newPosition == 10){
			System.out.println("[Turn " + turn + "] ["
					+ squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" is visiting " + squares[newPosition].getName());
		} else {
			System.out.println("[Turn " + turn + "] ["
					+ squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" goes to " + squares[newPosition].getName());
		}
		player.setLocation(newPosition);
		squares[newPosition].doAction(player, this);
		if (player.getMoney().hasLost()) {
			System.out.println("[Turn " + turn + "] ["
					+ squares[newPosition].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" has lost the game due to bankrupt!");
			player.setLost(true);
			//bankRupt.add(player);
		} else {
			player.nextTurn();
		}
	}
	
/*	public ArrayList<Player> getBankRuptedPlayers () {
		return bankRupt;
	}
	*/
	public boolean isOver() {
		int i = 0, length = players.length;
		for (int k = 0; k<length; k++) {
			if (!players[k].hasLost()) {
				i++;
			}
		}
		return i<=1;
	}
	
	public Player getPlayer() {
		return players[turn];
	}
	
	public void nextTurn () {
		if (++turn >= players.length) {
			turn = 0;
		}
	}
}
