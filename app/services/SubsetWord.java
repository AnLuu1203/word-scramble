package services;

import java.util.ArrayList;
import java.util.List;

public class SubsetWord {
	private char[] word;
	private char[] wordSubset;
	private ArrayList<String> results;
	
	public SubsetWord(String word) {
		this.word = new char[word.length()];
		
		for (int i = 0; i < word.length(); i++) {
			this.word[i] = word.charAt(i);
		}
	}
	
	public List<String> getAllSubset() {
		this.results = new ArrayList<String>();
		for (int i = 1; i <= this.word.length; i++) {
			this.wordSubset = new char[i + 1];
			this.wordSubset[0] = ' ';
			this.subset(1, i, 0);
		}
		return this.results;
	}
	
	public void subset(int i, int k, int previousIndex) {
		for (int j = previousIndex + 1; j <= this.word.length - k + i; j++) {
			this.wordSubset[i] = this.word[j - 1];
			
			if (i == k) {
				this.addResult(this.wordSubset);
			} else {
				subset(i + 1, k, j);
			}
		}
	}
	
	private void addResult(char[] word) {
		String result = new String(word);
		result = result.substring(1, result.length());
		this.results.add(result);
	}
}
