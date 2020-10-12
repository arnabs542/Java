package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.TreeSet;

//https://leetcode.com/problems/remove-duplicate-letters/

/*
 * 	This is mainly a Stack + Greedy problem.
 * 
 * 	Given a string consists of only lowercase alphabets. Return a string of max size 26 such that:
 * 	>	Remove all duplicate letters, such that only one of each alphabet remains in the result
 * 	>	The order of original string mustn't be altered
 * 	>	Among all possible answers, it must have LOWEST LEXICOGRAPHICAL ORDER
 * 
 * 	That means, If say we have alphabets [a,b,c] in our string. If we have a 'a', and after 'a' we know there is a 'b',
 * 	then only we can safely use this 'a' in our result.
 * 	On the other hand, if the 'a' doesn't have any 'b' following it (Meaning that all the 'b' are in front of 'a'), then
 * 	we have no choice but to use 'b' in front of 'a'
 * 	We shall always try to be greedy, and put the lowest lexicographical alphabet in front of everything.
 * 
 * 	How do we able to know if there is a 'b' remaining after the 'a'? We can simply keep some information on the positions
 * 	of the letters, or even better, FREQUENCIES.
 * 	On iteration, we will keep a frequency counter. Whenever we meet a character, the counter goes down by 1. Whenever
 * 	the counter still is larger than 0, that means there are this character behind the current position.
 * 
 * 	The action is: to try and keep adding the characters to the Stack ( Remember to add only if the character is never
 * 	used before!). 
 * 	When the stack's top has HIGHER lexicographical order than the current character to be inserted, check the top character's
 * 	frequency left. If there is still that same character left in behind part, then safely pop it out. Keep popping until
 * 	either stack is empty or the top is LOWER lexicographical. THen only we add the character.
 * 
 * 	
 */

public class Remove_Duplicate_Letters {
	
	public String removeDuplicateLetters(String s) {
	
		int[] count = new int[26];
		
		for ( char c: s.toCharArray() ) {
			count[ c - 'a' ] ++;
		}
		
		Deque<Character> stack = new ArrayDeque<>();
		boolean[] used = new boolean[26];
		
		for ( char c: s.toCharArray() ) {
			
			//	This character is 'used' up. Deduct the frequency.
			count[ c - 'a' ] --;
			
			//	The character is currently in the string. Continue iteration
			if (!used[ c- 'a'] ) continue;
			
			//	While this character is less than top of the stack, and top of stack still have that character later
			//	in the string, pop the top of stack.
			while (!stack.isEmpty() && c < stack.peek() && count[ stack.peek() - 'a'] > 0 ) {
				used[ stack.pop() - 'a' ] = false;
			}
			
			stack.push( c );
			used[ c - 'a' ] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.ensureCapacity(26);
		
		while ( !stack.isEmpty() ) {
			sb.insert( sb.length(), stack.pop() );
		}
		return sb.reverse().toString();
	}
}
