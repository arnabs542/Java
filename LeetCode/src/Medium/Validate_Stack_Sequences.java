package Medium;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/validate-stack-sequences/
/*
 * This is simply a Stack question.
 * 
 * Each iteration, I can do the following only:
 * 	>	If stack empty or stack top is not equal to current pop element, then push the next element
 * 	>	Otherwise, pop the top of stack as the pop element is same as stack top
 * 
 * If successfully iterate over all of the pop elements, then return true.
 * If ran out of elements to push, then return false.
 * 
 * Time complexity is O(2N). Each element is operated twice - push and pop
 */

public class Validate_Stack_Sequences {

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		final int len = pushed.length;
		Deque<Integer> stack = new LinkedList<>();
		int push = 0, pop = 0;
		
		while (pop < len) {
			if (stack.isEmpty() || stack.peek() != popped[pop] ) {
				if (push >= len) return false;
				stack.push(pushed[push++]);
			} else {
				stack.pop();
				++pop;
			}
		}
		return true;
	}

}
