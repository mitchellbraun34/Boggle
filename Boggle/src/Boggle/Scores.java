package Boggle;


public class Scores {
	
	String playerName;
	int Scores;
	public Scores(String playerNames, int Scores)
	{
		this.playerName = playerNames;
		this.Scores = Scores;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public int getPlayerScore()
	{
		return Scores;
	}
	
	
}
