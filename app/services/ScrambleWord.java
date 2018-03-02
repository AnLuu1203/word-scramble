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
//		this.dictionary = new ArrayList<String>();
//		this.loadDictionary();
	}
	
	public String getOriginWord() {
		return this.originWord;
	}
	
	private void loadDictionary() throws IOException {
		File source = Play.application().getFile("app/assets/dictionary.txt");
		LineNumberReader lnr = new LineNumberReader(new FileReader(source));
		lnr.skip(Long.MAX_VALUE);
        int numWords = lnr.getLineNumber();
        lnr.close();
        
        Scanner words = new Scanner(source);
        for (int i = 0; i < numWords; i++) {
            String word = words.nextLine();
            this.dictionary.add(word);
        }
        words.close();
	}
	
	private String getRandomWord() throws IOException {
		File source = Play.application().getFile("app/assets/dictionary.txt");
		LineNumberReader lnr = new LineNumberReader(new FileReader(source));
        lnr.skip(Long.MAX_VALUE);
        int numWords = lnr.getLineNumber();
        lnr.close();
        Random generator = new Random();
        int randomWord = generator.nextInt(numWords - 1);
        String realWord = "";
        Scanner words = new Scanner(source);
        for (int i = 0; i < randomWord; i++) {
            realWord = words.nextLine();
        }
        words.close();
        
		return realWord;
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
	
	public List<String> getValidSubWords() throws IOException {
		ArrayList<String> possibleWord = (ArrayList<String>) this.getPossibleSubWords();
		ArrayList<String> results = new ArrayList<String>();
		for (String word : possibleWord) {
			if (this.validateWord(word)) {
				results.add(word);
			}
		}
		return results;
	}
	
	public boolean validateWord(String word) throws IOException {
		File source = Play.application().getFile("app/assets/dictionary.txt");
		LineNumberReader lnr = new LineNumberReader(new FileReader(source));
		lnr.skip(Long.MAX_VALUE);
        int numWords = lnr.getLineNumber();
        lnr.close();
        
        Scanner words = new Scanner(source);
        for (int i = 0; i < numWords; i++) {
            String w = words.nextLine();
            if (w.equals(word)) {
            	words.close();
            	return true;
            }
        }
        words.close();
        return false;
	}
}
