package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/132-pattern/

/*
 * 	This is a Stack question.
 * 
 * 	We have to find a subsequence in array such that they are ordered by 1->3->2. 
 * 
 * 	One solution is O(N^2). This is done by iterating through the array, backwards. What we are doing is basically
 * 	fixing the value for the (1), then scan all the elements next to it to find if the (3->2) part exists in the right
 *  subarray. 
 *  To check if (3->2) exists, we simply have to use a max pointer. Observe that with one element currently, the next
 * 	element could only be increase or decrease. If it decreases, then we found the (3->2) pattern. Only then we check
 * 	if the (1) fixed is less than (2) or not. If not, continue checking.
 * 	The element that we will be holding is the maximum element encountered so far. Because it basically holds the (3),
 * 	which is optimally maximum.
 * 
 * 	=========================================================================================================
 * 
 * 	For the stack solution, we have to first obtain the min array.
 * 
 * 	The use of such O(N) min array is, at a certain position, we can immediately check one of the conditions:
 * 		Whether there is the (1) in the left side of subarray.
 * 
 * 	We will iterate through the array from right to left, selecting each element as the (3) element. Now we can check
 * 	if it satisfies the
 * 		(1)->(3)	-	Done by checking the min array before.
 * 		(3)->(2)	-	How?
 * 
 * 	We will keep a Stack for the potential candidates for the (2). Assume the stack contains the candidate, then we will
 * 	peek the element.
 * 
 * 	>	Is stack top (2) larger than current element, (3)?
 * 		-	YES - Found the answer ((1) must also valid). Return ans
 * 		-	NO  - Then that means stack top (2) is larger than current element (3). Ideally the potential candidate
 * 				  (2) must be as small as possible, but larger than (1). Since its proven this (3) is smaller, then
 * 				  push the (3) into the stack!
 * 
 * 	>	Now, at the start of every iteration we shall filter out invalid candidates for (2), which is those smaller
 * 		than min (1). This is because from right to left, the min array keeps increasing
 */

public class _132_Pattern {
	
	public boolean find132pattern(int[] nums) {
		int len = nums.length;
		
		for (int i = len - 1; i >= 0; i-- ) {
			int n = nums[i];
			
			int max = Integer.MIN_VALUE;
			for (int j = i + 1; j < len; j ++ ) {
				if ( nums[j] < max && nums[j] > n ) return true;
				max = Math.max( nums[j] , max );
			}
		}
		return false;
    }
	
	
	public boolean find132pattern2(int[] nums) {
		int len = nums.length;
		if (len < 3) return false;
		
		int[] min = new int[len];
		min[0] = nums[0];
		for (int i = 1; i < len; i ++ ) {
			min[i] = Math.min( nums[i], min[i-1] );
		}
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = len - 1; i >= 0; i -- ) {
			if ( nums[i] > min[i] ) {
				while (!stack.isEmpty() && stack.peek() >= min[i] ) {
					stack.pop();
				}
				if (!stack.isEmpty() && stack.peek() < nums[i] ) return true;
				stack.push(nums[i] );
			}
		}
		return false;
		
    }
	
	
}
