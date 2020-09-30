package Hard;

//https://leetcode.com/problems/first-missing-positive/

/*
 * 	This is a pure array problem.
 * 	We are required to find the first positive numbers missing in the array.
 * 	Meaning, if 1 is not present, ans is 1. Else, if 2 is not present, ans is 2. Else, if 3 is not present, ans is 3 etc...
 * 	The array is unsorted, how can we do this in O(N) time (few passes) and O(1) space? (Few variables)
 * 
 * 	
 * 	We would exploit the natural property of arrays - Their indexing. Although the array is indexed from 0 to N, we can think of them
 * 	as representing 1 to N+1.
 * 	(Now I will use 1 based indexing for arrays, NO 0 based indexing)
 * 	The index 1 shall store the value of 1 itself. The index 2 shall store the value of 2 itself. Basically, what the index is, the value
 * 	stored in it shall be the same.
 * 	Imagine if we have this array, where element at index I are value I itself, except ONE which is missing. If we iterate from beginning
 * 	of array (From 1 to N), once we detect the missing number, then it is the missing first positive number alright!
 * 	
 * 	How would we transform the array into the said form? We will keep a pointer to a position in the array, starting from the beginning.
 * 	Do this:
 * 
 * 		>	If the value is negative, zero, larger than the size of the array, or already in the suitable place (INDEX = VALUE)
 * 				Ignore this value. Increment the pointer
 * 
 * 		>	Otherwise, the value must have its respective place in the array. In other words, the value is a valid index of the array.
 *			Therefore, we will put this element into the suitable place, which is the index the value is pointing to.
 *			How about the element at the original place? We will swap it into the current pointer place then. Therefore, keep the pointer.
 *			Don't move the pointer
 *
 *			Consider the case of duplicates. [1, 1]. The pointer at 0 will keep attempting to swap 1 with value 1 at index 1. This causes
 *			infinite loop. Therefore, check beforehand the value at the index we are about to swap first. If it is already suitable value,
 *			don't care about it and continue on.
 */

public class First_Missing_Positive {
	
	public int firstMissingPositive(int[] nums) {
		
		//	Note that index 0 is int 1. index 1 is int 2...
		int len = nums.length;
		int pt = 0;
		
		while (pt < len) {
			int val = nums[pt];
			if ( val <= 0 || val >= len || val == pt + 1) {
				pt++;
			} else {
				if ( nums[val - 1] == val ) pt++;
				else swap( nums, pt, nums[pt] - 1 );
			}
		}
		
		
		for (pt = 0; pt < len; pt ++ ) {
			if (pt + 1 != nums[pt] ) {
				return pt;
			}
		}
		return pt + 1;
		
		
	}
	
	
	private static void swap(int[] arr, int a, int b) {
		arr[a] = arr[a] + arr[b];
		arr[b] = arr[a] - arr[b];
		arr[a] = arr[a] - arr[b];
	}
	
}
