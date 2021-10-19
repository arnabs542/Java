package Easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/next-greater-element-i/
/*
 * 	This is a monotonic stack + Hashmap problem.
 * 
 * 	You could do it in brute force. For each element in nums1, we iterate through nums2 to find its next larger element.
 * 	
 * 	To do it using monotonic stack, what we'll do is:
 * 		Iterate backwards to maintain a monotonic stack. The top of stack will be smallest element we encountered so far,
 * 		and the bottom of stack will be the largest element so far.
 * 		For each element in nums2:
 * 			>	While the stack top is smaller than current element in nums2, we pop the stack top.
 * 			>	Now, if the current element in nums2 is also in nums1 (Use hashmap to check), then the stack top is essentially
 * 				the 'next greater element' we are looking for (Put -1 if stack is empty)
 * 			>	Since we popped away any element in stack that is smaller than current element, we can safely add current
 * 				element into the stack.
 */

public class Next_Greater_Element_I {
	
	// Brute force solution - O(N * M) time
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        final int n = nums1.length;
        final int m = nums2.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; ++i) {
        	int target = nums1[i];
        	res[i] = -1;
        	
        	int pt = 0;
        	while (nums2[pt] != target) ++pt;
        	
        	for (pt = pt+1; pt < m; ++pt) {
        		if (target < nums2[pt]) {
        			res[i] = nums2[pt];
        			break;
        		}
        	}
        }
        return res;
    }
	
	
	
	// Monotonic stack backwards iteration solution - O(N + M) time O(N + M) space
	public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
		final int n = nums1.length;
        final int m = nums2.length;
		Map<Integer, Integer> nums1Map = new HashMap<>();
		Deque<Integer> monotonicStack = new ArrayDeque<>();
		int[] res = new int[n];
		
		// Step 1: Map Integers to index in nums1
		for (int i = 0; i < n; ++i)
			nums1Map.put(nums1[i], i);
		
		// Step 2: Backward iteration to maintain monotonic stack
		for (int i = m-1; i >= 0; --i) {
			int e = nums2[i];
			
			// Pop everything that is smaller than current element
			while (!monotonicStack.isEmpty() && monotonicStack.peek() <= e)
				monotonicStack.pop();
			
			// If the current element is in nums1, we need to give next larger element.
			if (nums1Map.containsKey( e ) )
				res[ nums1Map.get(e) ] = monotonicStack.isEmpty()? -1: monotonicStack.peek();

			// Add this element, since it is next smaller element in the stack.
			monotonicStack.push(e);
		}
		return res;
	}
}
