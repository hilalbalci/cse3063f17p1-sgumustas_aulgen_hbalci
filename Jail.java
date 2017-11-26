<<<<<<< HEAD
import java.util.Random;

public class Jail extends Square {
    public Jail(String name) {
        super(name, 0, 0);
    }

    @Override
    public void doAction(Player player, Board board) {
        int a = player.tossSingleDie(), b = player.tossSingleDie(), c = 10 + a + b;
        if (player.jailHolder() < 2) {
            int choice = choose();
            Printer.print(player, " is in jail. This is turn " + (player.jailHolder() + 1) + " in jail! " + player.getName() + " will make a choice.", board.squares);
            if (choice == 0) {
                Printer.print(player, " chose to get out of jail by throwing double. If not " + player.getName() + " will wait.", board.squares);
                if (a == b) {
                    Printer.print(player, " tossed " + a + " and " + b + ". Getting out of jail!", board.squares);
                    player.getOutOfJail();
                    player.setLocation(c);
                    Printer.print(player, " is visiting " + board.squares[c].getName(), board.squares);
                    board.squares[c].doAction(player, board);
                } else {
                    Printer.print(player, " tossed " + a + " and " + b + ". Can't get out of jail!", board.squares);
                    player.increaseJailHolder();
                }
            } else {
                Printer.print(player, " chose to get out of jail by paying TL50 whether " + player.getName() + " throws double dice or not.", board.squares);
                Printer.print(player, " tossed " + a + " and " + b + ". Getting out of jail!", board.squares);
                isOutOrNot(player, board, c);
            }
        } else {
            Printer.print(player, " is in jail. This is turn 3 in jail! Player will get out by paying.", board.squares);
            isOutOrNot(player, board, c);
        }
    }

    private int choose() {
        Random random = new Random();
        return random.nextInt(2);
    }

    private void isOutOrNot(Player player, Board board, int c) {
        if (player.getMoney().getMoney() >= 50) {
            player.getOutOfJail();
            player.reduceMoney(50);
            player.setLocation(c);
            Printer.print(player, " has lost TL50 to get out of jail and is visiting " + board.squares[c].getName(), board.squares);
            board.squares[c].doAction(player, board);
        } else {
            Printer.print(player, " can't pay TL50 to get out of jail so it gives all of his money.", board.squares);
            player.reduceMoney(player.getMoney().getMoney());
            player.setLost(true);
        }
    }
}
=======
public class Jail extends Square {
	public Jail(String name) {
		super(name);
	}

	@Override
	public void doAction(Player player, Board board) {
		// TODO Auto-generated method stub
		
	}
	
}
>>>>>>> eafa3ad1d8f6dc5c8999e635de3730c7e86992c7
