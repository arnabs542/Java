package Dynamic_Programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
 * 
 * 	------------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	Now there is a even better way to find out the LIS, which actually uses an algorithm called Patience sort (Patience is another name for Solitaire game)
 * 
 * 	This algorithm almost follows a greedy algorithm. For every element we've just included into our array, we shall be trying to insert it into the longest
 * 	increasing subsequence so far. If it fails (The LIS so far has a tail value greater than to be inserted), then we would start decreasing down, be less greedy.
 * 	
 * 	Let's say we are trying to insert 8, and these two cases may occur:
 * 		Case 1: LIS [1,2,4,6,15]		- It would be better to replace the 15 with 8, so LIS becomes [1,2,4,6,8], which in future may fit in more values
 * 
 * 		Case 2: LIS [9,10,15]			- Since 8 is lesser than any of those, 8 should start a new LIS by itself, so that in future it may build a much longer LIS
 * 										  Eg in [9,10,15, (8) ,9,10,11,12,13]
 * 
 * 		Case 3: LIS [1,2,15,23]			- Obviously 8 cannot fit into 15 or 23, but can form a new LIS from [1,2] onwards. So we record that fact
 * 										  Eg in [1,2,15,23, (8) ,9,10,11,12,13] 
 * 		
 * 		Case 4: LIS [1,2,3,4]			- In this case of course it's best to insert 8 into the end of LIS, extending it into a longest LIS so far.
 * 
 * 	
 * 	We see that we need to keep track of several potential LIS at a time, but notice that at a time, LIS of specific length can only exist one at a time due to
 * 	Case #1. If there is a potential that it can be replaced, it will be replaced.
 * 
 * 	Like [1,2,5,6,7] and to insert is 3. Right now LIS of length 3 may be [1,2,5]. We could replace that with 3 into [1,2,3], which allow for more possible future
 * 	values
 * 
 * 	We don't have to iterate through each length to find the LIS that can be inserted. We could use binary search:
 * 		-If it could be inserted at mid, be greedy and try inserting in longer lengths
 * 		-If it could not be inserted at mid, then try inserting in shorter lengths
 * 
 * 	Therefore we need to keep a table of lengths, which map into the tail value of the LIS (Which is the highest value, due to the nature of LIS).
 *  WHen introduced with a new value in the array, check to see if it can be extended by longest LIS.
 *  If it does, then extend it into a new length.
 *  We need to check if it also can replace the length 1 LIS. If it does, then replace it. Else perform binary search
 * 
 * 
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
	
	//N log n time
	private static int lis(int[] arr ) {
		int length = 1;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, arr[0] );
		
		for (int i = 1; i < arr.length; i ++ ) {
			//Value is larger than the longest LIS, then extend it and update length counter
			if ( arr[i] > map.get(length) ) {
				length ++;
				map.put( length, arr[i] );
			}
			//Else if value is smaller than the 1 length LIS, it need to be replace
			else if ( arr[i] < map.get(1) ) {
				map.put( 1, arr[i] );
			}
			//Else perform binary search
			else {
				int left = 1, right = length;
				
				while (left < right) {
					int mid = left + (right - left) / 2;
					//If we found out the mid is equal, then there's is no need to replace it already. This is STRICTLY INCREASING LIS
					if (map.get(mid) == arr[i] ) {
						left = mid;
						break;
					}
					if ( map.get(mid) > arr[i] ) right = mid;
					else left = mid + 1;
				}
				
				map.put(left, arr[i]);
			}
		}
		
		return length;
		
	}
	
	
	
	
	public static void main(String[]args) {
		int[] arr = {-1, 3, 4, 5, 2, 2, 2, 2};
		System.out.println( lnds( arr));
	}
	
	
}
