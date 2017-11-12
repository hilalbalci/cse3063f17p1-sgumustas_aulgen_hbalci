import java.util.Random;

public class Board {
	private Player[] players;
	private Player [] bankRuptedPlayers;
	Square[] squares = new Square[40];
	Die die = new Die();
	int turn = 0, i=0;
	public Player[] getPlayers() {
		return players;
	}
	public Board (int totalPlayer, String [] playerNames) {
		players = new Player[totalPlayer];
		bankRuptedPlayers = new Player[totalPlayer];
		for (int i = 0; i<totalPlayer; i++) {
			players[i] = new Player(i, playerNames[i]);
		}
		for (int i = 0; i< 40; i++ ) {
			if (i==0) {
				squares[i] = new GoSquare("Start!");
			} else if (i==3) {
				squares[i] = new IncomeTax("Income Tax");
			} else if (i==10) {
				squares[i] = new Jail("JAIL!");
			} else if (i==20) {
				squares [i] = new FreeParking("Free Parking");
			} else if (i==30) {
				squares[i] = new GoToJail("Go To Jail");
			} else if (i==38) {
				squares[i] = new LuxuryTax("Luxury Tax");
			} else {
				squares[i] = new Square(i + "th Square") {
					@Override
					public void doAction(Player player, Board board) {}
				};
			}
		}
	}
	
	public void movePlayer(Player player, int face) {
		int temp = player.getLocation(), newPosition = (temp + face) % 40;
		player.setPreviousLocation(temp);
		if (player.isInJail() && player.jailHolder()<2) {
			int choice = choose();
			int a = player.tossSingleDie();
			int b = player.tossSingleDie();
			
			Printer.print(player, " is in jail. This is turn " + player.jailHolder() + " in jail! " + player.getName() + " will make a choice.", squares);
	
			if (choice == 0 && a==b) {
				
				Printer.print(player, " chose to get out of jail by throwing double.", squares);
				Printer.print(player, " tossed " + a + " and " + b + ". Getting out of jail!", squares);
				
				player.getOutOfJail();
				player.setJailHolderZero();
				player.setLocation(10 + a + b);
				squares[10 + a + b].doAction(player, this);
			
				Printer.print(player, " is visiting " + squares[10 + a + b].getName(), squares);
			} else if (choice == 0 && a!=b) {
				
				Printer.print(player, " chose to get out of jail by throwing double. If not " + player.getName() + " will wait.", squares);
				Printer.print(player, " tossed " + a + " and " + b + ". Can't get out of jail!", squares);
				
				player.increaseJailHolder();
			} else {
				
				Printer.print(player, " chose to get out of jail by paying TL50 whether " + player.getName() + " throws double dice or not.", squares);
				Printer.print(player, " tossed " + a + " and " + b + ". Getting out of jail!", squares);
				
				player.getOutOfJail();
				player.setJailHolderZero();
				player.setLocation(10 + a + b);
				player.reduceMoney(50);
				squares[10 + a + b].doAction(player, this);
				
				Printer.print(player, " has lost TL50 to get out of Jail and is visiting " + squares[10 + a + b].getName(), squares);
			}
		} else if (player.isInJail() && player.jailHolder()==2) {
			Printer.print(player, " is in jail. This is turn 3 in jail! Player will get out by paying.", squares);
			
			player.setJailHolderZero();
			player.getOutOfJail();
			player.reduceMoney(50);
			
			Printer.print(player, " has lost TL50 to get out of Jail and is visiting " + squares[newPosition].getName(), squares);
			
			player.setLocation(newPosition);
			squares[newPosition].doAction(player, this);
		} else if (newPosition == 30) {
			squares[30].doAction(player, this);
		} else {
			Printer.print(player, " is visiting " + squares[newPosition].getName(), squares);
			
			player.setLocation(newPosition);
			squares[newPosition].doAction(player, this);
		}
		
		if (player.getMoney().hasLost()) {
			Printer.print(player, " has lost the game due to bankrupt!", squares);
			player.setLost(true);
			bankRuptedPlayers[i++] = player;
		} else {
			player.nextTurn();
		}
	}

	
	public Player [] getBankRuptedPlayers () {
		return bankRuptedPlayers;
	}
	
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
			System.out.println();
		}
		
	}
	
	public int choose() {
		Random random = new Random();
		return random.nextInt(2);
	}
}