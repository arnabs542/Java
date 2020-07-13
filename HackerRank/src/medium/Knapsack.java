package medium;

import java.util.HashSet;
import java.util.Set;

//https://www.hackerrank.com/challenges/unbounded-knapsack/problem

/*
 * 	This is a dynamic programming problem. To find the closest sum to target, we have to work our way up, from empty set and include one
 * 	integer at a time, up until the full integer set. We also need to determine the closest sum from 0, up until the target k.
 * 
 * 	If the target k is 0, then no matter what our integer set is, closest sum is 0.
 * 	And if the integer set is empty, then closest sum will also be 0.
 * 
 * 	Now, to determine the closest sum at a particular target with currently newly included integer i, we have to check conditions:
 * 		IF the newly included integer i can divide the target perfectly, then the closest sum is that target k
 * 		Else we have to check if I use this integer, the target now becomes k - i. Check the same row but column k - i if the content
 * 			is equal to the target (column number) of k - i itself. If the content is equal to the column number, that means the reduced target
 * 			number (k - i) can be perfectly reduced to 0 and therefore we can safely put target in this grid cell.
 * 		Else, we take the maximum of row above (Max sum without including this integer) and column behind (Including this integer but
 * 			previous target )
 * 
 * 	The space complexity again can be reduced to O(N) because it only uses the row behind and same row's content.
 */

public class Knapsack {
	
	static int unboundedKnapsack(int k, int[] arr) {
		Set<Integer> set = new HashSet<>();
		for (int i: arr)
			set.add(i);
		
		int[] dp = new int[k + 1];
		int res = 0;
		
		for (int i: set) {
			for (int c = 1; c < k + 1; c ++ ) {
				if (c % i == 0)
					dp[c] = c;
				else if (c - i >= 0 && dp[c - i] == c - i)
					dp[c] = c;
				else
					dp[c] = Math.max(dp[c-1], dp[c] );
			}
			//Use below code to print out the dp table
//			for (int a: dp)
//				System.out.print(a + " ");
//			System.out.println();
			res = Math.max(dp[k], res);
		}
		return res;
    }
}
