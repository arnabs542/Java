package Hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
/*
 * 	This is a Stack problem
 * 
 * 	One key intuition is that, every bar (x value) can be part of a rectangle, even if it is by itself (width = 1), it is
 * 	also a rectangle. Therefore the problem is whether the maximum rectangle starting with this bar is the global maximum
 * 	or not only.
 * 
 * 	--------------------------
 * 
 * 	Somehow the brute force solution works. For every x bar, it can be ending point for the rectangle that is formed behind
 * 	it.  (To the left)
 * 	For every x bar, we iterate again backwards to check the area of rectangle formed with width = 1, 2, 3... n.
 * 	This is time complexity of O(N^2) and it involves many repeated computation as the same rectangle is seen again
 * 	and again.
 * 
 * 	--------------------------
 * 
 * 	The intuition for O(N) solution is, every rectangle can also be the starting point of a rectangle. Just how far it
 * 	spans ahead, we don't know yet.
 * 
 * 	We will have a Stack which keeps track of "Started" rectangles behind of currently pointed bar.
 * 	For every bar, we first check:
 * 
 * 	>	If the bar is higher than previous started rects in the stack, it means that the previous started rect can extend
 * 		till the current bar, therefore we do nothing to them.
 * 		In addition, this current bar can start a rect, so we push the current index position and height to the Stack
 * 
 * 	>	If the bar however is lower than the previous started rect in the stack, it means this is the end of the previously
 * 		started rect. Pop it out, calculate the previous rect's area, update, and repeat the process until previous rect
 * 		is lower than current bar.
 * 		
 * 		Now we want to push the current bar into the Stack. But HOLD ON! Recall that the popped rects has height higher
 * 		than current one. Therefore this current rect shall had started before the current position! 
 * 
 * 		Thus, while popping the previous rects, we shall had keep track of the minimum starting position of them, and
 * 		use that as the starting position when we push current rect into the Stack.
 */

public class Largest_Rectangle_In_Histogram {
	
	//	Brute force solution
	public int largestRectangleArea(int[] heights) {
        int max = 0;
        final int len = heights.length;
        
        for (int i = 0; i < len; ++i) {
        	int minH = heights[i];
        	
        	for (int j = i; j >= 0; --j ) {
        		minH = Math.min( minH, heights[j]);
        		max = Math.max( max, (i - j + 1) * minH );
        	}
        }
        
        return max;
    }
	
	
	
	//	O(N) stack solution
	public int largestRectangleArea2(int[] heights) {
		final int len = heights.length;
		
		int res = 0;
		Deque< int[] > stack = new ArrayDeque<>();
		
		for (int i = 0; i < len; ++i ) {
			int h = heights[i];
			
			//	The suitable x position for the newly started rectangle. If the new bar is shorter, surely it can
			//	be started at x position way back
			int traceback = i;
			while (!stack.isEmpty() && stack.peek()[1] > h) {
				int[] pop = stack.pop();
				res = Math.max( res , (i - pop[0]) * pop[1] );
				traceback = pop[0];
			}
			
			if (stack.isEmpty() || stack.peek()[1] != h) 
				stack.push( new int[] {traceback, h} );
		}
		
		//	Reached the end of histogram, but there is unfinished rects.
		//	Finish them. Those rect can span until end of the histogram
		while (!stack.isEmpty() ) {
			int[] pop = stack.pop();
			res = Math.max( res , (len - pop[0]) * pop[1] );
		}
		
		return res;
	}

}
