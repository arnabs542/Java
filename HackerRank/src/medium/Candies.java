package medium;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/candies/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This problem can be solved in two pass. The question asks to find the minimum candies to be bought such that the candies received by
 * 	the child must be greater than its neighbor which has lower mark than itself.
 * 
 * 	Since we still don't have enough information from just one pass, we can do two passes and split the problem into 2 parts:
 * 		1. First pass: The child whose mark is greater than left neighbor will receive more candies than the left neighbor.
 * 						Otherwise, the candies is given at minimum amount (1)
 * 						This pass will solve the up trends
 * 
 * 		2. Second pass: The child whose mark is greater than right neighbor will receive more candies than the right neighbor, but
 * 						if the candies received from first pass (comparing to left) is greater, then don't change anything.
 * 						This pass will solve the down trends
 * 
 * 	Consider example [3,2,3,4,5,4,3,2,1]
 * 
 * 	After first pass the candies received by each child will be [1,1,2,3,4,1,1,1,1]
 * 		*Notice in first pass first element is always 1, since no left child to compare to. So just set it to 1.
 * 	
 * 	Then we combine the result by using Math max in second pass [2,1,2,3,5,4,3,2,1]
 * 		*Notice in second pass last element will only compare with left element, which is done in first pass. Therefore there is no need
 * 		 to include it in loop.
 * 
 */

public class Candies {
	
	static long candies(int n, int[] arr) {
		
		int[] candies = new int[n];
		candies[0] = 1;
		
		for (int i = 1; i < n; i ++ ) {
			if (arr[i] > arr[i - 1] )
				candies[i] = candies[i-1] + 1;
		}
		for (int i = n - 2; n >= 0; i -- ) {
			if (arr[i] > arr[i + 1] )
				candies[i] = Math.max( candies[i] , candies[i+1] + 1);
		}
		
		return Arrays.stream(candies).sum();
    }
}
