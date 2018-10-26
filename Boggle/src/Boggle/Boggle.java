package Boggle;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Boggle.GUI.Clickers;

public class Boggle extends GUI{
	
	public Boggle() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Board game;
	//private static GameTimer time = new GameTimer(10);
	private static Score CurrentScore;
	private static Dictionary dict;
	static String name;
	//private static ScoreBoard scores = new ScoreBoard();
	
	private final static int MAX_GAME_TIME = 100;
	
	private final static String DICT_FILENAME = "words.txt";
	//private final static String HIGHSCORES_FILENAME = "BoggleHighScore.txt";
	
	
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		//Board game = new Board();
		GUI gui = new GUI();
		Scanner input = new Scanner(System.in);		
			gameSetup(input);		
			gui.initialize();
			gui.Mirror();

	}
	private static void gameSetup(Scanner in) throws FileNotFoundException {
		//creates the board
		//game = new Board();
		
		//asks for player name
		JOptionPane.showMessageDialog(null,
				 "\n\nObjective:\nBoggle is designed to make as many words as possible in 180 seconds." + 
				"\n\nMove Instructions:\nCombine letters above/below, right/left, or diagonally from the preivous letter.\n"
				+ "You cannot go back over a cube that has been used.\n" + 
				"\n\nMaking Word Instructions:\nLeft click on the button you want to select\n"
				+ "Click CHECK when you are finished clicking on letters\n"
				+ "Words must be three or more letters long and found in the English language Dictonary.\n");
		name = JOptionPane.showInputDialog("What is your name?");
		
		//creates a new score
		//scores.getting();
	//	CurrentScore = new Score(name);
		
		//makes a new ScoreBoard
		//scores = new ScoreBoard(CurrentScore, HIGHSCORES_FILENAME);
		
		//attempts to create a dictionary 
		try {
			dict = new Dictionary(DICT_FILENAME);
		}
		catch(FileNotFoundException e) {
			System.out.println("The file for the dictionary was not found.");
			System.exit(0);
		}
		
		
		
	}
	
	
	private static void printGameObjective() {
		System.out.println("\n\nBoggle is designed to make as many words as possible in 180 seconds.\n");
	}

	/**
	 * Prints the instructions for where a player can move between letters.
	 */
	private static void printMoveInstructions() {
		System.out.println("Combine letters above/below, right/left, or diagonally from the preivous letter.\n"
				+ "You cannot go back over a cube that has been used.\n");
	}

	/**
	 * Prints the instructions finding words.
	 */
	private static void printMakingWordsInstructions() {
		System.out.println("Enter coordinates of letter cubes followed by ENTER to make a word.\n"
				+ "Submit the same coordinates twice to finish the word to be scored.\n"
				+ "Words must be three or more letters long and found in the English language Dictonary.\n");
	}

}
