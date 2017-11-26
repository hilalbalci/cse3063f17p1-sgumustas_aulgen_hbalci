<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Board {
    private Player[] players;
    private Player[] bankRuptedPlayers;
    private Player winner;
    Square[] squares = new Square[40];
    Die die = new Die();
    int turn = 0, i = 0;

    public Player[] getPlayers() {
        return players;
    }

    public Board(int totalPlayer, String[] playerNames, String fileName, int startMoney) {
        players = new Player[totalPlayer];
        bankRuptedPlayers = new Player[totalPlayer];
        for (int i = 0; i < totalPlayer; i++) {
            players[i] = new Player(i, playerNames[i], startMoney);
        }
        squares = getSquareProperties(fileName);
    }

    public Square[] getSquareProperties(String fileName) {
        BufferedReader br = null;
        Square[] squares = new Square[40];
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line = br.readLine();
            String[] array;
            if (line.split("[;]+")[0].equalsIgnoreCase("position") || line.split("[;]+")[0].equalsIgnoreCase("price") ||
                    line.split("[;]+")[0].equalsIgnoreCase("rent")) {
                line = br.readLine();
            }
            for (int i = 1; i <= 40; i++) {
                array = line.split("[;]+");
                while (line != null) {
                    if (array[0].equalsIgnoreCase(Integer.toString(i))) {
                        squares[i - 1] = new Square("Square " + array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2])) {
                            @Override
                            public void doAction(Player player, Board board) {
                                if (this.getOwner()!=player && this.getOwner()!=null && this.getOwner().hasLost()) {
                                    this.setFree();
                                }
                                int c = player.getMoney().getMoney(), d = this.getRent();
                                if (this.hasOwner() && this.getOwner() != player) {
                                    if (c >= d) {
                                        player.reduceMoney(d);
                                        Printer.print(player, " has given TL" + d + " to player " + this.getOwner().getName() + " as rent!", board.squares);
                                        this.getOwner().increaseMoney(d);
                                    } else {
                                        player.reduceMoney(c);
                                        Printer.print(player, " has given his all money to player " + this.getOwner().getName() + " because " + player.getName() + " doesn't have enough money for rent!", board.squares);
                                        this.getOwner().increaseMoney(c);
                                        player.setLost(true);
                                    }
                                } else if (!this.hasOwner() && this.getOwner()==null) {
                                    if (player.tossSingleDie() > 4 && c >= this.getPrice()) {
                                        this.setOwner(player);
                                        Printer.print(player, " has bought " + this.getName() + " for TL" + this.getPrice() + ".", board.squares);
                                    } else {
                                        Printer.print(player, " doesn't buy " + this.getName() + "[TL" + this.getPrice() + "].", board.squares);
                                    }
                                }
                            }
                        };
                    } else if (i == 1) {
                        squares[i - 1] = new GoSquare("GO!");
                        break;
                    } else if (i == 5) {
                        squares[i - 1] = new IncomeTax("Income Tax!");
                        break;
                    } else if (i == 6 || i == 16 || i == 26 || i == 36) {
                        squares[i - 1] = new RailRoad("RailRoad " + ((i / 10) + 1), 200, 0);
                        break;
                    } else if (i == 11) {
                        squares[i - 1] = new Jail("Jail!");
                        break;
                    } else if (i == 13 || i == 29) {
                        if (i == 13) {
                            squares[i - 1] = new Utilities("Electric Utility", 150, 0);
                        } else {
                            squares[i - 1] = new Utilities("Water Utility", 150, 0);
                        }
                        break;
                    } else if (i == 21) {
                        squares[i - 1] = new FreeParking("Free Parking");
                        break;
                    } else if (i == 31) {
                        squares[i - 1] = new GoToJail("Go To Jail!");
                        break;
                    } else if (i == 39) {
                        squares[i - 1] = new LuxuryTax("Luxury Tax!");
                        break;
                    } else {
                        squares[i - 1] = new Square("Square " + i, 0, 0) {
                            @Override
                            public void doAction(Player player, Board board) {
                            }
                        };
                        break;
                    }
                    line = br.readLine();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("The path you provided is wrong! You must copy your input file/folder\n" +
                    "into main folder of game! (e.g. \"Monopoly/resources/input.csv\")");
            System.exit(1);
        }
        try {
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return squares;
    }

    public void movePlayer(Player player/*, int face*/) {
        int a, b;
        do {
            a = player.tossSingleDie();
            b = player.tossSingleDie();
            int temp = player.getLocation(), newPosition = (temp + a + b) % 40;
            player.setPreviousLocation(temp);
            if (player.isInJail()) {
                squares[10].doAction(player, this);
                break;
            } else {
                if (a == b && player.doubles() == 2) {
                    Printer.print(player, " tossed " + a + " and " + b + ". ", squares);
                    Printer.print(player, " tossed doubles 3 times. " + player.getName() + " will go to jail!", squares);
                    player.setLocation(player.getPreviousLocation());
                    player.sendToJail(squares);
                    player.setDoubleZero();
                    break;
                } else if ((a == b && player.doubles() != 2) || a != b) {
                    Printer.print(player, " tossed " + a + " and " + b + ". " + player.getName() + " is visiting " + squares[newPosition].getName(), squares);
                    player.setLocation(newPosition);
                    if (player.getLocation() != 10 && !player.isInJail()) {
                        squares[newPosition].doAction(player, this);
                    }
                    if (a == b) {
                        Printer.print(player, " tossed doubles. " + player.getName() + " will toss again!", squares);
                        player.increaseDouble();
                    } else {
                        player.setDoubleZero();
                    }
                }
            }
        } while (a == b && player.doubles() <= 2);

        if (player.hasLost()) {
            Printer.print(player, " has lost the game due to bankrupt!", squares);
            bankRuptedPlayers[i++] = player;
        } else {
            player.nextTurn();
        }
    }


    public Player[] getBankRuptedPlayers() {
        return bankRuptedPlayers;
    }

    public boolean isOver() {
        int i = 0, length = players.length;
        for (int k = 0; k < length; k++) {
            if (!players[k].hasLost()) {
                winner = players[k];
                i++;
            }
        }
        return i <= 1;
    }

    public Player getPlayer() {
        return players[turn];
    }

    public Player getWinner() {
        return winner;
    }

    public void nextTurn() {
        if (++turn >= players.length) {
            turn = 0;
            Printer.print("", true);
        }
    }
}
=======
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
>>>>>>> eafa3ad1d8f6dc5c8999e635de3730c7e86992c7
