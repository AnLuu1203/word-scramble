package services;

import java.util.ArrayList;
import java.util.List;

public class PermutationWord {
	private boolean[] used;
	private char[] word;
	private ArrayList<String> results;
	private char[] wordPermutation;
	
	public PermutationWord(String word) {
		this.word = new char[word.length()];
		this.used = new boolean[word.length()];
		this.wordPermutation = new char[word.length()];
		
		for (int i = 0; i < word.length(); i++) {
			this.word[i] = word.charAt(i);
			this.used[i] = false;
		}
	}
	
	public List<String> getAllPermutation() {
		this.results = new ArrayList<String>();
		this.permutation(0);
		return results;
	}
	
	private void permutation(int i) {
		for (int j = 0; j < this.word.length; j++) {
			if (!this.used[j]) {
				this.wordPermutation[i] = this.word[j];
				this.used[j] = true;
				
				if (i == this.word.length - 1) {
					this.addResult(this.wordPermutation);
				} else {
					this.permutation(i + 1);
				}
				this.used[j] = false;
			}
		}
	}
	
	private void addResult(char[] word) {
		String result = new String(word);
		this.results.add(result);
	}
}
