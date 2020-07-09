package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/find-the-duplicate-number/

/*
 * 	The "If programming was an anime" question: Floyd's tortoise and hare, or cycle detection algorithm.
 * 	Alternatives to this solution: Binary search: (O (n log n + log n) ), sort the array, and find the part where the length seems off. Then
 * 													we narrow down the half part of array has the duplicate.
 * 									Hashing: Using Sets, find duplicate
 * 									Simple sort: Sort the array and use linear scan find the element appearing twice.
 * 	
 * 	Given an array where each number is actually representing a valid array index (1 to n, but len(arr) is n + 1), we can actually form a directed
 * 	graph where the integer contained in array points to the index of the array containing another integer.
 * 	Since there is exactly 2 duplicate integers, then that means THERE WILL BE 2 NODES POINTING TO THE SAME OTHER NODE, AKA A CYCLE IS FORMED
 * 
 * 	Eg: [1,3,4,2,2]
 * 	If we draw out the directed graph, it would look like this:
 * 		1 -> 3 -> 2 -> 4 -> 2
 * 						\__/
 * 	Notice the duplicate number "2" points to the node containing 4. Our goal is to find out the entry point to the node 4.
 * 	The duplicate number is always located 1 node BEFORE the entry point of the cycle
 * 
 * 	We first create a tortoise and a hare and make them iterate until they are equal in value. Reaching at this point, there are either 2 possibilities:
 * 		1. The pointers are pointing at same node (the meet up point), which means they are both already in a cycle
 * 		2. The pointers are pointing at different node of same value (Which is duplicate number itself). But either way one pointer must be inside
 * 			the cycle already and the pointers now can be aligned to meet at the cycle entry point
 * 
 * 	Either way, before the pointers meet up at the entry point, it must mean during one step before, the pointers are pointing at the duplicated
 * 	number. Therefore we can simply straightforward follow the algorithm, moving one pointer back to starting point (index 0) and let them move
 * 	1 step at a time until they are equal.
 * 	Before they will meet at the cycle intersection point, they will collide already since the duplicate numbers is always one node before the
 * 	beginning of cycle itself
 * 
 * ----------------------------------------------------------------------------------------------------------------------
 * 
 * BINARY SOLUTION
 * O(n log n ) solution. We will be playing guessing high low game. We guess a mid value. THen, we count how many integers in array are less or equal than
 * mid value. Supposedly without duplicates (where every integer is unique), it shall have exactly mid counts. If it was more, then we know
 * the duplicated value is within range 1 to mid, else if it has exactly mid counts, then the duplicated number is greater than mid.
 */

public class Find_The_Duplicate_Number {
	public int findDuplicate(int[] nums) {
		int fast = nums[0];
		int slow = nums[0];
		
		do {
			slow = nums[slow];
			fast = nums[nums[fast] ];
		} while (slow != fast);
//		if (firstIndex(nums, fast) != lastIndex(nums, slow) ) return fast;
		
		slow = nums[0];
		while (fast != slow) {
			slow = nums[slow];
			fast = nums[fast];
		}
//		int fInd = firstIndex(nums,fast);
//		if (fInd != lastIndex(nums,slow) ) return fast;
		
		return fast;
	}
	
//	static int firstIndex(int[] nums, int find) {
//		for (int i = 0; i < nums.length; i ++ ) {
//			if (nums[i] == find) return i;
//		}
//		return -1;
//	}
//	
//	static int lastIndex(int[] nums, int find) {
//		for (int i = nums.length - 1; i >= 0; i --) {
//			if (nums[i] == find) return i;
//		}
//		return -1;
//	}
	
	public int findBinary (int[] nums) {
		int left = 1;
		int right = nums.length - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = 0;
			for (int n: nums)
				count += (n <= mid)? 1: 0; 
			
			if (count > mid) right = mid;
			else left = mid + 1;
		}
		return left;
	}
//	
//	public static void main(String[]args) {
//		int[] arr = {1,3,4,2,2};
//		System.out.println(firstIndex(arr, 2) );
//	}
}
