package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/evaluate-reverse-polish-notation/
/*
 * 	This is a Stack problem. If you've learnt or even implemented a calculator app or some sort, then this
 * 	will be quite easy for you
 * 
 * 	Reverse polish notation is: (left expr) - (right expr) - (operator)
 * 
 * 	Thus, we push each of the numbers we see into the stack. Once we are met with a operator, pop the top 2 items
 * 	out from the stack. The first popped will be the right expr, the second popped will be the left expr, and you
 * 	perform the operation. Don't forget to push the result back into the stack!
 * 
 * 	At the end, the result will be the last lone item in the Stack.
 */

public class Evaluate_Reverse_Polish_Notation {
	
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (String s: tokens) {
			if ( s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-")) {
				int rOperand = stack.pop();
				int lOperand = stack.pop();
				if (s.equals("+")) stack.push(lOperand + rOperand);
				if (s.equals("-")) stack.push(lOperand - rOperand);
				if (s.equals("*")) stack.push(lOperand * rOperand);
				if (s.equals("/")) stack.push(lOperand / rOperand);
			} else stack.push( Integer.parseInt(s) );
		}
		return stack.pop();
    }
	
}
