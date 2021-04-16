package Medium;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
/*
 * 	This is a Stack String problem.
 * 
 * 	Well, if previous k characters are the very same character, then obviously we will
 * 	remove that entire group from the string. Problem is, after removing, we must also
 * 	know what is the character before the removed group. Eg:
 * 
 * 		"aabbba", k = 3
 * 	After removing 3 'b', we must also know 3 'a' can be removed next. 
 * 
 * 	This calls for the stack data structure. Imagine the string as a stack. If the stack top
 * 	is k consecutive characters of same type, we can pop them. What remains on top of stack will
 * 	be the characters before the removed group!
 * 
 * 	Since K can be large, it's better to keep a counter instead. For every character,
 * 		>	If stack peek is same character as current one, increment counter for that stack top
 * 		>	Otherwise, push a new character to top of stack, with counter initialized as 1
 * 	Also in each iteration, be sure to check the stack top whether it can be popped, once detect that
 * 	the counter is >= k.
 */

public class Remove_All_Adjacent_Duplicates_In_String_II {
	
	public String removeDuplicates(String s, int k) {
		Deque<Character> chars = new LinkedList<>();
		Deque<Integer> counts = new LinkedList<>();
		
		chars.push('-'); counts.push(0);
		
		for (char c: s.toCharArray() ) {
			if (c == chars.peek()) {
				counts.push( counts.pop() + 1 );
			} else {
				chars.push(c);
				counts.push(1);
			}
			
			//	Accumulated k characters alreday. Remove that duplicates
			if (counts.peek() == k) {
				chars.pop();
				counts.pop();
			}
		}
		
		chars.pollLast(); counts.pollLast();		//	Remove dummy node
		
		StringBuilder sb = new StringBuilder();
		while (!chars.isEmpty()) {
			char c = chars.pollLast();
			int count = counts.pollLast();
			
			while (count-- > 0) sb.append(c);
		}
		return sb.toString();
    }
	
}
