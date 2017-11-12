import java.util.Random;

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
		int temp = player.getLocation(), newPosition = (temp + face) % 40, turn = player.getMovement() + 1;
		player.setPreviousLocation(temp);
		if (player.isInJail() && player.jailHolder()<2) {
			int choice = choose();
			int a = player.tossSingleDie();
			int b = player.tossSingleDie();
			
			System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" is in jail. This is turn " + player.jailHolder() + " in jail! " + player.getName() + " will make a choice.");
						
			if (choice == 0 && a==b) {
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" chose to get out of jail by throwing double.");
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" tossed " + a + " and " + b + ". Getting out of jail!");
				
				
				player.getOutOfJail();
				player.setJailHolderZero();
				player.setLocation(10 + a + b);
				squares[10 + a + b].doAction(player, this);
			
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" is visiting " + squares[10 + a + b].getName());
			
			
			
			} else if (choice == 0 && a!=b) {
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" chose to get out of jail by throwing double. If not " + player.getName() + " will wait.");
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" tossed " + a + " and " + b + ". Can't get out of jail!");
				
				
				player.increaseJailHolder();
				
			} else {
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" chose to get out of jail by paying TL50 whether " + player.getName() + " throws double dice or not.");
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" tossed " + a + " and " + b + ". Getting out of jail!");
				
				
				player.getOutOfJail();
				player.setJailHolderZero();
				player.setLocation(10 + a + b);
				player.reduceMoney(50);
				squares[10 + a + b].doAction(player, this);
				
				
				System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
						+ player.getMoney().getMoney() + "] " + player.getName() + 
						" has lost TL50 to get out of Jail and is visiting " + squares[10 + a + b].getName());
			}
		} else if (player.isInJail() && player.jailHolder()==2) {
			System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" is in jail. This is turn 3 in jail! Player will get out by paying.");
			
			player.setJailHolderZero();
			player.getOutOfJail();
			player.reduceMoney(50);
			
			System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" has lost TL50 to get out of Jail and is visiting " + squares[newPosition].getName());
			
			player.setLocation(newPosition);
			squares[newPosition].doAction(player, this);
		} else if (newPosition == 30) {
			squares[30].doAction(player, this);
		} else {
			
			System.out.println("[Turn " + turn + "] [" + squares[temp].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" is visiting " + squares[newPosition].getName());
			
			player.setLocation(newPosition);
			squares[newPosition].doAction(player, this);
		}
		
		if (player.getMoney().hasLost()) {
			System.out.println("[Turn " + turn + "] ["
					+ squares[newPosition].getName() + "] [TL"
					+ player.getMoney().getMoney() + "] " + player.getName() + 
					" has lost the game due to bankrupt!");
			player.setLost(true);
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
			System.out.println();
		}
		
	}
	
	public int choose() {
		Random random = new Random();
		return random.nextInt(2);
	}
}
