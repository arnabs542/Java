package Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/word-break-ii/

/*
 *	This is a DP problem, but can be solved without it, but using memoization (which i do, and end up in lengthy code)
 *
 *	First of all, create a Trie structure based on the wordDict to be able to quickly detect if a word is in the list without performing a linear scan.
 *	
 *	Since we have to form all valid words from wordDict by seperating current string with space, so when i detected that a word is present in a substring of
 *	the original str, then i should check if i perform a space here, will the other part of substring form a valid string?
 *	Therefore define a recursive function that given an index, the string and the trie, it's function is TO FIND ANY STRING THAT FORMS A VALID STRING OF WORDDICT
 *	UNTIL THE END OF STRING.
 *
 *	
 */

class Trie {
	Map<Character, Trie> dict = new HashMap<>();
	String isWord;
	
	@Override
	public String toString() {
		return dict.toString() + (isWord == null? "": isWord);
	}
}


public class Word_Break_II {
	public static List<String> wordBreak(String s, List<String> wordDict) {
		
		//Construction of Trie
		Trie root = new Trie();
		
		for (String words: wordDict) {
			Trie curr = root;
		
			for (char c: words.toCharArray() ) {
				curr.dict.putIfAbsent(c , new Trie() );
				curr = curr.dict.get(c);
			}
			
			curr.isWord = words;
		}
		//End of Construction of Trie
		
		//Stores the valid strings that extend until the end of string. The key is the starting index of the string
		//Eg: applepen, dic = [le, pen], then At Key= 5 it should have [pen], since starting from index 5 a valid string 'pen' can be formed until end of string
		//	  Therefore at Key=3 it should have [le pen]
		Map<Integer, List<String>> cache = new HashMap<>();
		
		List<String> toreturn = findWords(0, s, root, cache);
		return toreturn == null? new ArrayList<>(): toreturn;
		
	}
	
	
	public static List<String> findWords(int index, String s, Trie root, Map<Integer, List<String>> cache ) {
		//The index to search exceeded, which means there is a valid word at end of string. Return arraylist with blank string so it will get appended
		if (index >= s.length() ) return new ArrayList<>( Arrays.asList("") );
		// Valid string starting from this index has been computed before. Just return it from memoization
		if (cache.containsKey(index) ) return cache.get(index);
		
		List<String> res = new ArrayList<String>();
		
		Trie curr = root;
		char c = s.charAt(index);
		
		while (curr.dict.containsKey(c) ) {
			curr = curr.dict.get(c);
			
			//We've found a valid word until here. Try and find the next valid string
			if (curr.isWord != null) {
				
				List<String> findRes = findWords(index + 1, s, root, cache );
				
				if (findRes != null) {
					//	If this is first time computing the next valid string, then store it
					cache.putIfAbsent(index + 1, findRes);
					//	Add this valid word into all of the valid strings returned
					for (String results: findRes) {
						res.add( curr.isWord + (results.length() == 0? "": " ") + results);
					}
				}
			}
		
			//	Get the next character in the string. If it is out of range, just make it an impossible character to find in trie
			c = (++index < s.length() )? s.charAt(index): '~';
		}
		
		return res;
	}
	
	
	
	
	public static void main(String[]args) {
		List<String> li = new ArrayList<>();
		li.addAll( Arrays.asList("a", "abc", "b", "cd") );
		
		List<String> li2 = new ArrayList<>( Arrays.asList("apple", "pen", "applepen", "pine", "pineapple") );
		
		System.out.println( wordBreak("pineapplepenapple", li2) );
	}
}
