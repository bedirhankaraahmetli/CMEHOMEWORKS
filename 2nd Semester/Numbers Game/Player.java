
public class Player {

	String player_name;
	double player_score;
	
	public Player (String player_name, double player_score) {
		this.player_name = player_name;
		this.player_score = player_score;
	}
	
	public Player () {
		
	}

	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public double getPlayer_score() {
		return player_score;
	}
	public void setPlayer_score(double player_score) {
		this.player_score = player_score;
	}
}
