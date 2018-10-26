package Boggle;

import java.util.ArrayList;

public class Score {
	private String playerName;
	private int playerScore;
	private ArrayList<Word> alreadyFound = new ArrayList<Word>();
	public Score(String PN) {
		playerName = PN;
		playerScore =0;
	}
	public void scoreWord(Word word, Dictionary dict) throws InvalidWordException
	{
		
		if(word.length() >= 3 && word.validWord(dict) == true && !alreadyFound.contains(word))
		{
			alreadyFound.add(word);
			switch(word.length())
			{
				case 3: playerScore++;
						break;
				case 4: playerScore++;
						break;
				case 5: playerScore = playerScore + 2;
						break;
				case 6: playerScore = playerScore + 3;
						break;
				case 7: playerScore = playerScore + 5;
						break;
				default: playerScore = playerScore + 11;
						break;
			}
		}
	}
	public int getScore()
	{
		return playerScore;
	}
	public int setPlayerScore(int score)
	{
		return playerScore = score;
	}
	public String getPlayerName()
	{
		return playerName;
	}
	public String setPlayerName(String name)
	{
		return playerName = name;
	}

}
