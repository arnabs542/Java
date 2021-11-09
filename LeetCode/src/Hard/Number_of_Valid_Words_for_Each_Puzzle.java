package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
/*
 * 	This is a bit mainpulation, HashMap problem.
 * 
 * 	Of course, the easy brute force solution would be to iterate through each puzzle, then for each puzzle, iterate through
 * 	the words to determine if each one is valid or not. Using a counter to get the answer.
 * 	
 * 	However, the problem constraint disallow that. Input size too big.
 * 
 * 	--------------------------------------------------------------------------------------------
 * 
 *	What the problem asks, is to find out for each puzzle string: how many valid words are there respective to this puzzle:
 *		- The word MUST contain the first character of this puzzle
 *		- All the characters in word must also exist in puzzle.
 *
 *	Now, since these strings are all lowercase, we can use bit manipulation trick: 
 *		int is 32 bit long. Let a=1, b=2, c=3...,
 *		then we can flip the ith bit to 1 whenever the ith character exists in the string.
 *		
 *		Say: "abd", then the bitstring is 000...001011
 *										  zyx   fedcba 
 *
 *	So, my proposed algorithm is to preprocess the words first, then only determine the answer for each puzzle string
 *
 *		Initialize a hashmap that maps <bitstring -> frequency>
 *
 *		For each word:
 *			Get the bitstring. If number of 1's exceed 7, it is an impossible case.
 *			Increment in the hashmap, +1 to frequency.
 *
 *		For each puzzle:
 *			Get the bitstring.
 *
 *			Now, try all combinations of this bitstring by switching off some 1's inside and retrieve frequency from HashMap.
 *			Eg: 10011, we could try 10011, 10001, 10010, 10000, 00011, 00010, 00001, 00000
 *			HOWEVER, due to the constraint in that "first char of puzzle must be in word", we cannot flip the one character
 *			that is the first char of the puzzle. Take note of that
 *
 *
 * -------------------------------------------------------------------------
 *
 *	My main takeaway from this problem, is the way of generating submasks: 
 *		10011 => { 10011, 10001, 10010, 10000, 00011, 00010, 00001, 00000 }
 *
 *	The way to generate submasks is:
 *
 *		Initialize submask with the starting bitmask: 10011
 *		Loop:
 *			Mask = (Mask - 1) & bitmask	
 *
 *	And there you have it!
 */

public class Number_of_Valid_Words_for_Each_Puzzle {
	
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		// Requirements
		// maps first char => map<int, int>
		//		maps state(bits) => number of occurrences
		Map<Integer, Integer> codes = new HashMap<>();
		
		for (String s: words) {
			int bits = getBit(s);
			// If the hamming weight > 7, it is an impossible case
			if ( Integer.bitCount(bits) > 7 ) continue;
			
			codes.compute(bits, (k,v)-> v == null? 1: v+1);
		}
		
		List<Integer> res = new ArrayList<>();
		
		// For each puzzle, see how many words can it satisfy?
		// Use the submask generating trick:
		for (String s: puzzles) {
			int bits = getBit(s);
			int requiredBit = s.charAt(0) - 'a';
			
			int count = 0;
			for (int submask = bits; submask > 0; submask = (submask - 1) & bits) {
				if ( (submask & (1 << requiredBit) ) == 0) continue;
				count += codes.getOrDefault(submask, 0);
			}
			
			res.add(count);
			
			// If you use old way - Recursion
			//	res.add( getPossibleMatches(bits, 0, s.charAt(0), codes) );
		}
		
		return res;
    }
	
	
	
	// Recursion way of generating submask. Not really performant wise
	private int getPossibleMatches(int bits, int pos, char puzzleFirstChar, Map<Integer,Integer> codes) {
		if (pos >= 26) return codes.getOrDefault(bits, 0);
		// Current bit is a 0, or it is the puzzle's first char that we cannot exclude. Proceed
		if ( (bits & (1 << pos) ) == 0 || pos == puzzleFirstChar - 'a')
			return getPossibleMatches(bits, pos+1, puzzleFirstChar, codes);
		
		// Current bit is 1. It can be on or off.
		int off = bits ^ (1 << pos);
		return getPossibleMatches(bits, pos+1, puzzleFirstChar, codes) + 
				getPossibleMatches(off, pos+1, puzzleFirstChar, codes);
	}
	
	
	
	// Get an integer where the front 26 bits represent presence of a particular character
	// Eg: "abd" -> 0000...001011
	private int getBit(String s) {
		int res = 0;
		
		for (char c: s.toCharArray()) 
			res |= (1 << (c - 'a'));
	
		return res;
	}
	
}
