public class Board {
	Player[] players;
	Location[] squares = new Location[40];
	Die die = new Die();
	
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
				squares[i] = new Location("Start!") {
					
					@Override
					public void doAction(Player player, Board board) {
						// TODO Auto-generated method stub
						
					}
				};
			} else {
				squares[i] = new Location(i + "th Place") {
					
					@Override
					public void doAction(Player player, Board board) {
						// TODO Auto-generated method stub
						
					}
				};
			}
		}
	}
	
	public void makeMovement(int k) {
		int length = this.getPlayers().length, dieValue;
		for (int i = 0; i<length; i++) {
			dieValue = die.getDiceValue() + die.getDiceValue();
			players[i].setLocation((players[i].getLocation() + dieValue)%40);
			System.out.println("Turn [" + (k + 1) + "]\t" + players[i].getName() + " tossed " + dieValue + ".");
			System.out.println("Turn [" + (k + 1) + "]\t" + players[i].getName() + " is at " + squares[players[i].getLocation()].getName() + ".");
		}
 	}
}
