package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/jump-game-vi/
/*
 * 	This is a DP, Deque problem.
 * 
 * 	Looking at this question if you are a experienced with Dynamic Programming, you immediately know
 * 	to approach the problem bottom up (from back of array), such that we may have recorded dp[i]
 *  which shows the maximum score to reach end starting from index i
 *  
 *  The problem is the jump range of size k. If k is large, it means in each iteration, I have to check
 *  dp array ranging up to size k, thus time complexity of O(NK)
 *  
 *  If you've heard of monotonic dequeue before, it is perfect time to use it here. Each DP result will be
 *  stored in the queue, but we apply a algorithm where the queue will be always strictly decreasing.
 *  In other words, the front of the queue is largest in the queue, and the tail is smallest.
 *  
 *  This is done when we apply the following algorithm to the queue pushing:
 *  
 *  	>	While the element to push is greater than the tail of queue, pop the tail of queue
 *  	>	End of loop, the queue is either empty or the element in it is greater than the element to push.
 *  		Push the element to the tail of queue
 *  
 *  This way, everytime we peek at the front of queue, it is always the maximum DP element!
 *  
 *  One little thing is to check if the element is out of range. This can be done by recording the index as well
 *  and perform checking at each iteration.
 */

public class Jump_Game_VI {
	
	//	Applies a monotonic queue approach.
	//	If curr to insert element is smaller than tail elements, enqueue it.
	//	Otherwise, pop the smaller element from back of queue, until met with larger one or empty queue
	//	Also, need to keep index to check if out of range.
	public int maxResult(int[] nums, int k) {
		final int len = nums.length;
		
        Deque<int[]> deque = new ArrayDeque<>(len);
        deque.addLast( new int[] {nums[len - 1], len-1} );
        
        for (int i = len - 2; i >= 0; --i) {
        	//	Remove out of range DP result
        	if (!deque.isEmpty() && deque.peekFirst()[1] > i + k)
        		deque.pollFirst();
        	
        	int localBest = nums[i] + deque.peekFirst()[0];
        	
        	//	Pop out redundant smaller elements, and push current dp result
        	while (!deque.isEmpty() && deque.peekLast()[0] <= localBest)
        		deque.pollLast();
        	deque.addLast(new int[] {localBest, i} );
        }
        
        while (!deque.isEmpty()) {
        	int[] dp = deque.pollFirst();
        	if (dp[1] == 0) return dp[0];
        }
        return -1;
    }
	
}
