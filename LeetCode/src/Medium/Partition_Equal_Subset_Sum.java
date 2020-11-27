package Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/partition-equal-subset-sum/

/*
 * 	This is a Dynamic Programming Problem.
 * 
 * 	First of all, the key point to realize is: the array is partitioned into 2 parts of equal in their sum.
 * 	Let the sum be S, then add up the sums of two partition, S, be it S + S = 2S.
 * 	This means the sum of the entire array must be a even number.
 * 
 * 	Next, we can assume the array initially is already in one of partitions. Then, if we decide, we can remove
 * 	this element, E to be moved into another partition. THis will cause
 * 		Partition 1 sum to decrease by N
 * 		Partition 2 sum to increase by N
 * 
 * 	Therefore, if the sum of the partition 2 is increased to N, partition 1 sum must also be reduced to N as well.
 * 	Therefore the nature of problem had reduced to be finding subsequence that add up to S only!
 * 
 * 	---------------------------------
 * 
 * 	The idea is, each element we can either have it included in our sum, or not.
 * 	This can come up with a O(2^n) recursion solution, where when the sum is reduced from S to 0 exactly, then
 * 	it is already mean the partitioning is done and satisfied therefore returning true.
 * 
 * 	--------------------------------
 * 
 * 	By using set, we can keep track of each possible sum values formed by each possible element of previous sums.
 * 	THe possible sums for {1} is 1
 * 	THe possible sums for {1,2} is 1, 2, 3
 * 	THe possible sums for {1,2,3} is 1, 2, 3, 4, 5, 6
 * 
 *  Each element, we can check if the another term that causes it to add up to S exists in the set or not. If
 *  found, partitioning is entirely possible.
 *  	
 *  1 element generates 1 sum term, 2 element generates 1 + 2 sum term, 3 element generates 1 + 2 + 3 sum term.
 *  Therefore time complexity and space complexity is both O(N^2)
 *  
 *  -----------------------------------
 *  
 *  Let's see Dynamic Programming approach. This is similar to idea of 01 Knapsack Problem where given a bag of
 *  capacity K, find the maximum possible way to select N items each with weight Wi. In this classical problem,
 *  a tabular table where columns represents capacity of bag K from 0 to K, and rows represents the subarray of
 *  N items from {}, {N1}, {N1,N2}...
 *  Basically with each new item introduced in each row, to reach capacity K, we can either include or not include
 *  the item. Therefore just check 
 *  		MAX( not include (last row same col), include (last row, col - weight) + weight )
 * 
 * 	
 * 	By memoization, we use similar tabular method where column is the S, and rows are the indices of element. 
 * 	The grid is a boolean showing if we had tried to compute the similar S at same index of element again. If
 * 	it is true, directly return false back up to recursion stack.
 * 
 * 	-------------------------------------
 * 
 * 	The real dynamic programming bottom up approach is using tabular array where column is S, and rows are the
 * 	subarrays {}, {N1}, {N1, N2}...
 * 
 * 	Each element we encounter, we try to see if the subarray can achieve sum of S by including or not including the
 * 	introduced element. 
 * 		If not include, take the boolean from last row, same column
 * 		If include, take the boolean from last row, column - Ni
 * 
 * 	
 * 	Notice the DP only uses data from last row. This makes it very possible to compress into 1 dimensional DP
 * 	using space O(N) only.
 */



public class Partition_Equal_Subset_Sum {
	
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int n: nums) sum += n;
		
		if (sum % 2 != 0) return false;
		
		//	Else we have to reach sum / 2;
		sum /= 2;
		
		Set<Integer> set = new HashSet<>();
        set.add(0);
		for (int i: nums) {
			if (set.contains(sum - i) ) return true;
			
			List<Integer> li = new LinkedList<>();
			for (int j: set) {
				li.add( i + j );
			}
			set.addAll(li);
			set.add(i);
		}
		return false;
	}
	
	
	public boolean canPartitionMemoization(int[] nums) {
		int sum = 0;
		for (int n: nums) sum += n;
		
		if (sum % 2 != 0) return false;
		
		//	Else we have to reach sum / 2;
		sum /= 2;
		
		boolean[][] memoi = new boolean[nums.length + 1][sum + 1];
		
		if ( recurse(nums, 0, sum, memoi) ) return true;
		return false;
	}
	//	In memoization table, row index of 1 -> index in nums is 0. Index in argument is index of nums
	private boolean recurse(int[] nums, int idx, int sum, boolean[][] memoi) {
		if (sum == 0) return true;
		if (idx >= nums.length || sum < 0) return false;
		
		//	Computed this similar case before. Return immediately since before we didnt return true
		if ( memoi[idx + 1][sum] ) return false;
		
		if ( recurse(nums, idx + 1, sum - nums[idx], memoi) ) return true;
		if ( recurse(nums, idx + 1, sum, memoi) ) return true;
		
		//	Record that we've computed this case before.
		memoi[idx + 1][sum] = true;
		return false;
	}
	
	
	
	
	public boolean canPartition2D_DP(int[] nums) {
		int sum = 0;
		for (int n: nums) sum += n;
		
		if (sum % 2 != 0) return false;
		
		//	Else we have to reach sum / 2;
		sum /= 2;
		
		boolean[][] dp = new boolean[nums.length + 1][sum + 1];
		for (int i = 0; i <= nums.length; i++ ) 
			dp[i][0] = true;
		
		//	Nums element, equivalent to DP row number but + 1
		for (int i = 0; i < nums.length; i ++ ) {
			int n = nums[i];
			//	'Knapsack'
			for (int j = 1; j <= sum; j ++ ) {
				if (j - n >= 0)
					dp[i + 1][j] = dp[i][j] || dp[i][j - n];
			}
		}
		return dp[nums.length][sum];
	}
	
	
	public boolean canPartition1D_DP(int[] nums) {
		int sum = 0;
		for (int n: nums) sum += n;
		
		if (sum % 2 != 0) return false;
		
		//	Else we have to reach sum / 2;
		sum /= 2;
		
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;
		
		//	Nums element, equivalent to DP row number but + 1
		for (int i = 0; i < nums.length; i ++ ) {
			int n = nums[i];
			//	'Knapsack'
			for (int j = sum; j >= 1; j -- ) {
				if (j - n >= 0)
					dp[j] = dp[j] || dp[j - n];
			}
		}
		return dp[sum];
	}
	
}
