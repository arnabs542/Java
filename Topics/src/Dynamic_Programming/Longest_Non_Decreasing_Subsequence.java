package Dynamic_Programming;

import java.util.Arrays;

//https://www.youtube.com/watch?v=fV-TF4OvZpk

/*
 * 	Longest Non Decreasing Subsequence (Also variants: Longest Increasing subsequence (LIS) or Longest Decreasing subsequence) is a classical
 * 	DP problem.
 * 	Given an array of potentially duplicate containing integers, find the longest subsequence (sub array that might not be contagious) such that
 * 	they are in non-decreasing order (can be same values going next each other; not strictly increasing)
 * 
 * 	We can use the core concept of DP: Asking subproblems, getting the answer to the subproblem, and using the answer to achieve the overall
 * 	answer of the problem.
 * 	The subproblems are: What is the Longest Non-Decreasing subsequence of the array IF I HAVE TO INVOLVE THIS INTEGER INTO IT?
 * 	
 * 	Eg: We know the answer to the array -1 3 0 (Let's assume we know) is 2 (Ans: [-1 3] or [-1 0] ). If I have to insert integer 2 into it, what
 * 	will be the new answer?
 * 	The answer would be 3 (Ans: [-1 0 2] ), but how would we know?
 * 	First, we will do validation to each of the subproblems. We'll start from -1.
 * 	If we see the value to insert is LESS THAN the latest value of the sub array we're considering, it's impossible so we'll skip that
 * 	
 * 		SubProb #1: With array [-1], what is answer if i have to insert 2? --- 2 ( [-1 ,2] )
 * 		SubProb #2: With array [-1, 3], what is answer if i have to insert 2? --- 2 ( [-1, 2] or [-1, 3] )
 * 		SubProb #3: With array [-1, 3, 0] ), what is answer if i have to insert 2? --- 3 ( [-1, 0, 2] )
 * 
 * 		The way to get the answer is by looking at the answer to the previous answer to [-1, 3, 0] and check if the integer to insert, 2 is
 * 		greater than the latest element, 0. If yes, then after inserting the integer it will result in 2 + 1, which is 3.
 * 		Since answer of 3 is greater than those in other sub arrays, we'll proudly put them in the dp cell.
 * 
 * 	This way we've covered all the possibilities if I really have to insert the integer into different sub arrays. 
 */

public class Longest_Non_Decreasing_Subsequence {

	public static int lnds(int[] arr) {
		int[] dp = new int[arr.length];
		Arrays.fill(dp, 1);
		
		//The integer at index i must be inserted! What will be the possible longest N-D subsequence?
		for (int i = 1; i < arr.length; i ++ ) {
			//We will consider the sub array up to the one before index i
			for (int j = 0; j < i; j ++ ) {
				if (arr[i] >= arr[j] ) {
					dp[i] = Math.max( dp[j] + 1, dp[i]);
				}
			}
		}
		
		int max = 1;
		for (int i: dp) {
			max = Math.max(i, max);
		}
		return max;
	}
	
	public static void main(String[]args) {
		int[] arr = {-1, 3, 4, 5, 2, 2, 2, 2};
		System.out.println( lnds( arr));
	}
	
	
}
