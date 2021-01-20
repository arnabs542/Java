package Easy;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/valid-parentheses/
/*
 * 	This is a Stack problem.
 * 
 * 	The stack will store the seen opening brackets (, { or [
 * 	The top of the stack will be the most recent seen opening bracket.
 * 	
 * 	If we encounter a closing bracket, it must be matching the most recent
 * 	opening bracket for the parenthesis string to be valid
 */

public class Valid_Parenthesis {
	
	public boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		
		for (char c: s.toCharArray() ) {
			if (c == '(' || c == '{' || c == '[')
				stack.push(c);
			else {
                if (stack.isEmpty() ) return false;
				char p = stack.pop();
				if ( c == ')' && p != '('
					|| c == '}' && p != '{'
					|| c == ']' && p != '[')
					return false;
			}
		}
		return stack.isEmpty();
	}

}
