package Algorithms;

//https://leetcode.com/problems/maximum-subarray/

/*
 * 	Kadane's Algorithm is an algorithm of Dynamic Programming(DP) to tackle the problem of maximum sum of continuous sub-array in an array
 * 	Eg: [1, -3, 2, 1, -1], the maximum subarray is [2, 1], thus expected output would be 2+1 = 3
 * 
 * 	To approach this dynamically, we need to realise at any index i, the maximum can only be the element at index i itself, or the combination
 * 	of the previous maximum and this element at index i.
 * 	This opens up a way to solve the question in only using O(n) linear time since it iterates through the array 1 time only
 * 
 * 
 * 	An alternative way to solve this problem would be using Divide & conquer, which halves the array each time until the array is down to individual
 * 	element, and checks which one is greater: Left part of divided array, Right part of divided array, or crossing the midpoint, extending as combined array
 * 	but runs at O(n log n) time
 * 
 */

public class Kadanes_Algorithm {
	public static int maximumSubArray(int[] nums) {
		int max = nums[0];
		int prevMax = max;
		
		for (int i = 1; i < nums.length; i ++ ) {
			//The local maximum which consists of element at index i itself only
			int local = nums[i];
			//The combined which is the current element combined with the previous maximum
			int combined = local + prevMax;
			
			//Why would be the local may be greater than combined? Because the array element may be negative.
			
			//If the combined is greater than the element itself, take the element in; include it.
			if (combined > local) {
				prevMax = combined;
			}
			//Otherwise, this element by itself shall open up a new sub array by itself
			else {
				prevMax = local;
			}
			
			//Last but not least, remember that we are finding the maximum subarray. Compare them
			max = (prevMax > max)? prevMax: max;
		}
		return max;
	}
}
