package Easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/word-pattern/

/*
 * 	HashTable problem. Notice that pattern = 'abba' and String = 'dog dog dog dog' would fail because a and b pattern would map to
 * 	the same word which is 'dog'
 * 
 * 	The direct solution is to keep 2 hashmaps.
 * 	Here I use one hashset which keeps the seen before pattern of size 26, since pattern can only be in [a-z]
 * 
 * 	Now, i will use a HashMap which maps from word to the pattern character.
 * 
 * 	Now for every word that i encounter in the String:
 * 
 * 		If the map already contains such word, check if the current pattern is equal to the past recorded one. If not, return false
 * 
 * 		If the map don't recorded such word,
 * 		>	Check if the current pattern is used before. If it is, the pattern is paired with another different word already.
 * 			Return false
 * 		>	Else, we can safely assign the pattern to this word, and continue with the next word.
 * 
 * 
 * 
 */

public class Word_Pattern {
	
	public boolean wordPattern(String pattern, String str) {
		if (str.isEmpty() || pattern.isEmpty() ) {
			return str.isEmpty() && pattern.isEmpty();
		}
		
		String[] arr = str.split(" ");
		
		if (arr.length != pattern.length() ) return false;
		
		boolean[] usedPattern = new boolean[26];
		Map<String, Character> map = new HashMap<>();
		
		for (int i = 0; i < pattern.length(); i ++ ) {
			String word = arr[i];
			char p = pattern.charAt(i);
			
			if ( map.containsKey(word) ) {
				if ( map.get(word) != p ) return false;
			} else {
				if ( usedPattern[p - 'a'] ) return false;
				usedPattern[p - 'a'] = true;
				map.put( word, p );
			}
		}
		return true;
	}
	
	
	

}
