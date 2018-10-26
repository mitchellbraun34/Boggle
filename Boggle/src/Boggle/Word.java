package Boggle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class designed to hold and check the letters in a word.
 * @author Mr. Turner
 */
public class Word {

	private ArrayList<String> letters;
	private String combined;
	private int wordLength;

	/**
	 * Constructs a word object and a dictionary.
	 * @param word The letters of the array that will compose the word.
	 * @throws FileNotFoundException If the text file holding the words
	 * 	in the dictionary is not found.
	 */
	public Word(ArrayList<String> word) {
		letters = new ArrayList<String>();
		combined = "";

		for(String letter : word) {
			letters.add(letter);
			combined += letter;
		}
		wordLength = letters.size();
	}

	public void addLetter(String letter) {
		letters.add(letter);
		combined += letter;
		wordLength++;
	}
	
	/**
	 * Allows the user to use he length method similar to Strings.
	 * @return The length of the word.
	 */
	public int length() {
		return wordLength;
	}

	/**
	 * Determines whether or not the word is in the English dictionary.
	 * @return Whether the word was found
	 */
	public boolean validWord(Dictionary dict) throws InvalidWordException {
		for(String word: dict.getWords()) {
			if(word.equalsIgnoreCase(combined)) {
				return true;
			}
		}
		throw new InvalidWordException("The word was not found in the dictionary.");
	}
	
	
	
	@Override
	public String toString(){
		return combined;
	}
}