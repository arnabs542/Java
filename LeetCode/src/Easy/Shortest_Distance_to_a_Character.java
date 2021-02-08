package Easy;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/problems/shortest-distance-to-a-character/
/*
 * 	This is a Array problem, potentially usage of Queue, but optimized solution don't use it
 * 
 * 	-------------------------------------------------------------
 * 
 * 	For each character in string, the closest character c may come from the left, or the right. Therefore, we
 * 	need this information for efficient processing.
 * 
 * 	To obtain this information, notice this: We have to discard away indices that are already far away from current
 * 	position. Data structures like stack and queue immediately comes to mind.
 * 
 * 	Therefore, first iteration records the indices of character c into the queue. Then, in second iteration, we hold
 * 	a previous indice of character c, and keep comparing with top of queue. If right side character is closer (top of
 * 	queue), we poll (update) and discard away the left index of character c.
 * 
 * 	Worst case space complexity is O(N) if the string is entirely character c.
 * 
 * 	-----------------------------------------------------------------
 * 
 * 	As said, character c may come from left or right. Therefore, split the problem into smaller ,easier to solve
 * 	subproblems:
 * 
 * 		>	Shortest distance to a character STRICTLY FROM LEFT SIDE ONLY
 * 		>	Shortest distance to a character STRICTLY FROM RIGHT SIDE ONLY
 * 
 * 	Take the minimum from these 2 solutions, we will get global solution.
 */

public class Shortest_Distance_to_a_Character {

	//	Using Queue - Two Passes
	public int[] shortestToChar(String s, char c) {
		final int len = s.length();
		int[] res = new int[len];
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 0; i < len; ++i)
			if (s.charAt(i) == c) queue.offer(i);
		queue.offer(Integer.MAX_VALUE);			//	To avoid having to check if queue empty
		
		int curr = queue.poll();	//	Pop the closest from left
		
		for (int i = 0; i < len; ++i) {
			if ( Math.abs(i - curr) > Math.abs(queue.peek() - i) ) curr = queue.poll();	//	Update
			res[i] = Math.abs(i - curr);
		}
		
		return res;
    }
	
	
	//	Two pass solution : L to R and R to L
	public int[] shortestToChar2(String s, char c) {
		final int len = s.length();
		int[] res = new int[len];
		
		int curr = Integer.MIN_VALUE / 2;
		for (int i = 0; i < len; ++i) {
			if (s.charAt(i) == c) curr = i;
			res[i] = Math.abs( i - curr );
		}
		
		curr = Integer.MAX_VALUE / 2;
		for (int i = len - 1; i >= 0; --i) {
			if (s.charAt(i) == c) curr = i;
			res[i] = Math.min( res[i], Math.abs( curr - i ) );
		}
		
		return res;
	}
	
}
