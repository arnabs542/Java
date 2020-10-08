package Easy;

//https://leetcode.com/problems/binary-search/

/*
 * 	Textbook Algorithm - Binary search
 * 
 * 	Binary search can only be performed on an sorted array. If it's not sorted then binary search
 * 	can't be applied
 * 
 * 	The concept is like the guessing number game. Every guess will end up in half of the range covered
 * 	being eliminated. Since the range is being halved each time, we at most will be doing log N guesses
 * 	only.
 * 
 * 	Initially, we have the full range (left is 0 and right is index's tail element). We will make a guess
 * 	right at the middle of this range. Then depending on the guess value, we will be halving either
 * 	the left or right range.
 * 
 * 	When implementing, we have to think of how the range is halved, and how the mid is calculated as well
 * 
 * 	By default, the mid value calculated is always the lower half (Eg: 0 to 7, lower half is 3, upper
 * 	half is 4). To change to upper half, just add 1 to the lower half calculated.
 * 
 * 	Be careful of infinite loops. If the only pointer to move is - left = mid + 1, then DO NOT implement 
 * 	mid value using UPPER HALF. If the only pointer to move is - right = mid - 1, then DO NOT implement
 * 	mid value using LOWER HALF.
 */

public class Binary_Search {
	
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		if ( nums[left] != target ) return -1;
		return left;
	}
}
