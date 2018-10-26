package Boggle;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class designed to keep track of all words in the English dictionary.
 * @author Mr. Turner
 */
public class Dictionary {

	/**
	 * Contains all of the words in the English Dictionary.
	 */
	private ArrayList<String> words = new ArrayList<String>();

	/**
	 * Creates a dictionary object.
	 * @param filename The text file holding all the words in the
	 * 	English dictionary with one word per line.
	 */
	public Dictionary(String filename) throws FileNotFoundException {

		File text = new File(filename);
		Scanner wordReader = new Scanner(text);
		while(wordReader.hasNextLine()) {
			words.add(wordReader.nextLine());
		}
		wordReader.close();
	}

	/**
	 * Getter method for the words in the dictionary.
	 * @return The ArrayList of Strings containing all the words
	 */
	public ArrayList<String> getWords(){
		return words;
	}
}