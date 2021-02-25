package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
/*
 * One quick way to solve, is to actually sort the entire array, and compare both the sorted and unsorted arrays.
 * The leftmost mismatch is the start of the unsorted subarray, and the rightmost mismatch is the end of unsorted
 * subarray
 * 
 * ----------------------------------------------------------------------
 * 
 * A sorted array is always increasing. Meaning, the next element is always larger than all the elements, including
 * the largest one, in the previously seen elements.
 * 
 * Therefore, perform a linear scan. If the next element is larger than largest, then nevermind, the array is fine
 * 
 * If it is however smaller than largest, it can be either of two cases:
 * 
 * >	Smaller than the smallest element in unsorted subarray. That means like: 1,2, (5,4), -1
 * 		The (5,4) is unsorted segment, and smallest is 4. Since -1 is already smaller than 4, we have to extend the
 * 		starting point of the unsorted subarray until (1,2,5,4, -1)
 * 
 * >	Larger than the smallest element in unsorted subarray. In this case simply extend the end point. No need to 
 * 		extend starting point
 */

public class Shortest_Unsorted_Continuous_Array {
	
	//	Pointless solution: Sort the whole array to find out subarray to sort
	public int findUnsortedSubarray(int[] nums) {
		int[] cpy = Arrays.copyOf(nums, nums.length);
		Arrays.sort(cpy);
		
		int l = 0, r = nums.length - 1;
		
		while (l < nums.length && nums[l] == cpy[l]) ++l;
		while (r > l && nums[r] == cpy[r]) --r;
		
		return r-l == 0? 0: r-l+1;
	}
	
	
	//	One pass solution by recording min and max in unsorted array
	public int findUnsortedSubarray2(int[] nums) {
		final int len = nums.length;
		int start = Integer.MAX_VALUE, end = 0;
		int largest = Integer.MIN_VALUE, smallest = Integer.MAX_VALUE;
		
		for (int i = 0; i < len; ++i) {
			if (nums[i] >= largest) {
				largest = nums[i];				//	If the next num is larger, this element shall no need to sorted
			}
			//	Next int is smaller, it can have two cases: larger than smaller in sorted, or smaller than sorted
			//	If it is smaller than any of those in sorted, we need to extend the start.
			else {
				end = i;
				
				if (nums[i] < smallest) {
					start = Math.min(start, i-1);
					smallest = Math.min(smallest, nums[i]);
					
					while (start > 0 && nums[start-1] > smallest) {
						smallest = Math.min(smallest, nums[start-1]);
						--start;
					}
				}
			}
		}
		
		return start == Integer.MAX_VALUE? 0: end - start + 1;
	}
	

}
