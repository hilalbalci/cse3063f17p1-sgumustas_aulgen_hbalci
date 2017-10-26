public class Player {
	private int tournamentID = 0, id;
	private int location = 0;
	private String name;
	
	public Player (int id, String name) {
		this.name = name;
	}
	
	public int getID() {
		return id;
	}
	
	public void tossTournament(Die die) {
		setTournamentID(die.getDiceValue());
		System.out.println(this.getName() + " tossed " + this.getTournamentID() + " in tournament.");
	}
	
	public void setTournamentID (int tournamentID) {
		this.tournamentID = tournamentID;
	}
	
	public int getTournamentID () {
		return tournamentID;
	}
	
	public void setLocation (int location) {
		this.location = location;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLocation () {
		return location;
	}
	
	public String getName() {
		return name;
	}
}
