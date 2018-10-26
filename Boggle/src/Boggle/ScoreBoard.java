package Boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreBoard {
	
	private ArrayList<Score> playerScores;
	private static String filename;
	private Score playerScore;
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	private ArrayList<Scores> Sorting  = new ArrayList<Scores>();
	
	
	/*public ScoreBoard(ArrayList<String> players, String filename) {
		playerScores = new ArrayList<Score>();
		this.filename = filename;
	}
	public ScoreBoard(Score player, String filename) {
		playerScores = new ArrayList<Score>();
		playerScores.add(player);
		this.filename = filename;
		
		name = new ArrayList<String>();
		scores = new ArrayList<Integer>();
		
		
	}*/
	public void getting() throws FileNotFoundException
	{
		File F = new File("BoggleHighScore.txt");
		
		Scanner in = new Scanner(F);
		
		while(in.hasNextLine())
		{
			in.next();
			Scores s = new Scores(in.next(), in.nextInt());
			Sorting.add(s);
		}
	}
	

	
	public void printing(String filename, String playername, int currentScore) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(filename);
		Scores s = new Scores(playername, currentScore);
		Sorting.add(s);
		
		Sorting.sort((b,a) -> new Integer(a.getPlayerScore()).compareTo(b.getPlayerScore()));

		
		for(int i = 0; i < Sorting.size(); i++)
		{
			out.println(i + " " + Sorting.get(i).getPlayerName() + " " + Sorting.get(i).getPlayerScore());
		}
		out.close();
	}
	
//	public void setScore(String player, int score)
//	{
//		for(int i = 0; i< playerScores.size();i++)
//		{
//			if(playerScores.get(i).getPlayerName().equals(player))
//			{
//				playerScores.get(i).setPlayerScore(score);
//			}
//		}		
//	}
//	public Score getScore(String player)
//	{
//		for(int i = 0; i< playerScores.size();i++)
//		{
//			if(playerScores.get(i).getPlayerName().equals(player))
//			{
//				return playerScores.get(i);
//			}
//		}	
//		return null;
//	}
}
