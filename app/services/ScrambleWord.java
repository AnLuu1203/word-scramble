package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import play.Play;
import models.Entry;

public class ScrambleWord {
	private String originWord;
	private ArrayList<String> dictionary;
	
	public ScrambleWord() throws IOException {
		// TODO Auto-generated constructor stub
		this.originWord = this.getRandomWord();
//		this.dictionary = new ArrayList<String>();
//		this.loadDictionary();
	}
	
	public ScrambleWord(String originWord) throws IOException {
		this.originWord = originWord;
	}
	
	public String getOriginWord() {
		return this.originWord;
	}
	
	private String getRandomWord() throws IOException {
        Entry randomEntry = Entry.getRandomEntry();
		return randomEntry.word.toLowerCase();
	}
	
	public String getShuffleWord() {
		int wordLength = this.originWord.length();
		ArrayList<Character> characters = new ArrayList<Character>(wordLength);
		
		for (char character : this.originWord.toCharArray()) {
			characters.add(character);
		}
		Collections.shuffle(characters);
		
		String shuffleWord = "";
		for (int i = 0; i < wordLength; i++) {
			shuffleWord += characters.get(i).toString();
		}
		
		return shuffleWord;
	}
	
	public List<String> getPossibleSubWords() {
		ArrayList<String> subsetWord = (ArrayList<String>) (new SubsetWord(this.originWord)).getAllSubset();
		ArrayList<String> results = new ArrayList<String>();
		
		for (String word : subsetWord) {
			ArrayList<String> permutation = (ArrayList<String>) (new PermutationWord(word)).getAllPermutation();
			results.addAll(permutation);
		}
		
		Set<String> hs = new HashSet<>();
		hs.addAll(results);
		results.clear();
		results.addAll(hs);
		return results;
	}
	
	public List<String> getValidSubWords() {
		ArrayList<String> possibleWord = (ArrayList<String>) this.getPossibleSubWords();
		ArrayList<String> results = new ArrayList<String>();
		for (String word : possibleWord) {
			if (this.validateWord(word)) {
				results.add(word);
			}
		}
		return results;
	}
	
	public boolean validateWord(String word) {
        return Entry.isWordExists(word);
	}
}
