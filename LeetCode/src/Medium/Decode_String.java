package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/decode-string/

/*
 * 	This is a Depth First Search / Stack problem
 * 
 * 	A string may have several parts that have to be repeated. We essentially have to find out those parts, and
 * 	decode them first before joining back into the 'outer' string
 * 
 * 	This makes us think about recursion. A recursive function can take in a string, perform its function to decode
 * 	it, as soon as it encounters a repetition part, call upon another recursion, then at last, return the decoded string
 * 
 * 	First attempt, is when detected a digit, then it will go through the subsequent part of string to find the ending
 * 	bracket. Then, pass the substring enclosed into the recursion call to be decoded and returned as a string.
 * 	This approach does solve the problem, but in the worst case, when the brackets start very early and end at the tail
 * 	of the string, this solution will iterate over the string repeatedly and result in almost O(N^2) time.
 * 
 * 	Is there a one pass solution?
 * 
 * 	---------------------------------------------------------------------------------------
 * 
 * 	Actually, we don't have to find the ending bracket at all. As soon as we hit a opening bracket, we go deep one level
 * 	into recursion, and when we encounter a ending bracket, we should have been at the same level as the opening bracket!
 * 
 * 	Therefore, having a global index pointer ensures that we are only iterating only one pass through the string.
 * 	When we encounter a opening bracket, go deep one level of recursion which will decode the string anew. When it encounters
 * 	a ending bracket, it means the decoding of equivalent level is done, and it should return the decoded string up one level
 * 	to be processed.
 * 
 * 	The entire process relies on the global index pointer so that it is ensured one pass solution
 * 
 * 	----------------------------------------------------------------------------------------
 * 
 * 	How to implement it iteratively? In the recursive solution, we essentially store the last traced string state, and
 * 	start anew when we encounter a opening bracket. When it is done, we return the decoded string, and continue with the
 * 	last traced string state and append the decoded string to it.
 * 
 * 	Same can be done using Stack to store the last traced string state. When we encounter a opening bracket, we store the
 * 	current traced string into Stack, then create a new string to start the decoding process of inner string.
 * 	Once we encounter the ending bracket, we know the inner string is done decoded, so we pop out the upper traced string
 * 	from stack, and append the decoded result to it.
 */


public class Decode_String {
	
	//	Unoptimized solution using recursion. O(N^2) time due to repetitive iteration of string
	public String decodeString(String s) {
		final int len = s.length();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; i ++ ) {
			char c = s.charAt(i);
			
			//	If it is regular alphabet, append
			if ( Character.isAlphabetic(c) )
				sb.append(c);
			//	Otherwise it must be a numberic value, followed by square brackets containing the subproblem
			else {
				int repeat = 0;		//	The repetition value
				
				//	Extract the numeric value of repetition
				while ( Character.isDigit(c) ) {
					repeat = repeat * 10 + c - '0';
					c = s.charAt(++i);
				}
				
				i++;	//	After parsing integer, the pointer stops at '['. move to next character, which is start character
				int start = i;
				int brackets = 1;		//	Keep track of brackets encountered so far. Only close when brackets hit 0
				
				
				//	Search for end of the subproblem, where the bracket ends
				while (brackets > 0) {
					c = s.charAt(++i);
					if (c == '[') brackets ++;
					else if (c == ']') brackets --;
				}
				
				String result = decodeString( s.substring(start, i) );	// Now pointer shall be at closing ]
				
				while (repeat-- > 0) {
					sb.append( result );		//	Repeatedly append the result N times
				}
			}
		}
		
		return sb.toString();
	}
	
	
	
	//	Solution 2 using recursion. One pass of the string using global index pointer
	int i = 0;
	public String decodeString2(String s) {
		final int len = s.length();
		
		int repeat = 0;
		StringBuilder sb = new StringBuilder();
		
		while (i < len) {
			char c = s.charAt(i);
			
			if ( Character.isAlphabetic(c) ) {
				sb.append(c);
			}
			else if ( c == ']' ) {
				return sb.toString();
			}
			else if ( Character.isDigit(c) ) {
				repeat = repeat * 10 + c - '0';
			}
			else if ( c == '[' ) {
				i ++;
				String ret = decodeString2(s);
				
				while (repeat > 0) {
					sb.append( ret );
				}
			}
			i ++;
		}
		
		return sb.toString();
	}
	
	
	
	//	Iterative method using Stack
	public String decodeString3(String s) {
		final int len = s.length();
		
		Deque< StringBuilder > stack = new ArrayDeque<>();
		Deque< Integer > stackFreq = new ArrayDeque<>();
		StringBuilder curr = new StringBuilder();
		int i = 0;
		int repeat = 0;
		
		while (i < len) {
			char c = s.charAt(i);
			
			if ( Character.isAlphabetic(c) ) {
				curr.append(c);
			}
			else if ( Character.isDigit(c) ) {
				repeat = repeat * 10 + c - '0';
			}
			else if ( c == '[' ) {
				stack.push( curr );
				stackFreq.push( repeat );
				
				curr = new StringBuilder();
				repeat = 0;
			}
			else {
				StringBuilder last = stack.pop();
				int toRepeat = stackFreq.pop();
				
				while (toRepeat-- > 0) {
					last.append( curr );
				}
				curr = last;
			}
			i++;
		}
		return curr.toString();
	}
	
}
