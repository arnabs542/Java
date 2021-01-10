package Contest;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 	This is a greedy problem
 */

public class Maximum_Score_From_Removing_Substrings {
	
	public int maximumGain(String s, int x, int y) {
		Deque<Character> stack = new ArrayDeque<>();
		int sum = 0;
		char first = x > y? 'a': 'b';
		char second = x > y? 'b':'a';
		
		for (char c: s.toCharArray() ) {
			
			//	If x greater, prefer 'ab'. Otherwise prefer 'ba'
			if (!stack.isEmpty() && c == second && stack.peekFirst() == first ) {
				sum += Math.max(x, y);
				stack.pop();
			} else 
				stack.push(c);
		}
		
		Deque<Character> stack2 = new ArrayDeque<>();
		while (!stack.isEmpty() ) {
			//	If x smaller, try 'ab'. Otherwise prefer 'ba'. Since popping from stack, 'ab' in stack b will pop first
			char c = stack.pop();
			
			if (!stack2.isEmpty() && c == second && stack2.peekFirst() == first ) {
				sum += Math.min( x, y );
				stack2.pop();
			} else 
				stack2.push(c);
		}
		
		return sum;
	}
	
}
