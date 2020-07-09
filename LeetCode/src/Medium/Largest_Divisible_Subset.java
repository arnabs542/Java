package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/largest-divisible-subset/

/*
 * 	The intuition is from the DP question Largest Increasing Subsequence (LIS).
 *	First the array has to be sorted. In that way we ensure that the remainder checking is done easily.
 *	First we pick a index i starting from 0. We'll be generating the largest set for this element by checking the elements before i.
 *	We check if the checked element j is divisible by i. If yes, we compare the lengths of the subset from answer of j to the answer of i so far.
 *	If subset of j is longer, we'll overwrite the subset stored at i. This continues until j meet with i, which the element at i is appended to
 *	the subset, resulting in the longest divisible subset for the subproblem (Array until i).
 */

public class Largest_Divisible_Subset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		if (nums.length == 0) return new ArrayList<Integer>();
		
		List<Integer>[] dp = new ArrayList[nums.length];
		
		Arrays.sort(nums);
		
		for (int i = 0; i < dp.length; i ++ ) {
			dp[i] = new ArrayList<>();
			for (int j = 0; j < i; j++ ) {
				if (nums[i] % nums[j] == 0) {
					if (dp[j].size() > dp[i].size() )
						dp[i] = new ArrayList<>(dp[j]);
				}
			}
			dp[i].add(nums[i] );
		}
		
		List<Integer> result = dp[0];
		for (List<Integer> i: dp) {
			if (i.size() > result.size())
				result = i;
		}
		return result;
	}
}
