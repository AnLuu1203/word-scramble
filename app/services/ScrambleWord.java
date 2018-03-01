package services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import play.Play;

public class ScrambleWord {
	private String originWord;
	
	public ScrambleWord() throws IOException {
		// TODO Auto-generated constructor stub
		this.originWord = this.getRandomWord();
	}
	
	public ScrambleWord(String originWord) {
		this.originWord = originWord;
	}
	
	public String getOriginWord() {
		return this.originWord;
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
}
