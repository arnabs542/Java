package Easy;

//https://leetcode.com/problems/range-sum-query-immutable/
/*
 * 	A simply prefix sum problem.
 * 
 * 	To query the sumRange in O(1) time, say we have a prefix sum array which at index i, records the sum of elements
 * 	in original array from nums[0] to nums[i], then we can easily find out the sum from nums[a] to nums[b]. See:
 * 
 * 		   a              b
 * 	[ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 	|----||----------------|
 * 	 EXC    INC
 * 
 * 	To find out the sum from a to b inclusive, we have to just find out sum of elements up until b (EXC+INC), then substracting
 * 	the sum of elements up to but excluding a (EXC).  
 */

class NumArray {
	int[] prefixSum;
	
    public NumArray(int[] nums) {
    	// PrefixSum[] of sized len+1. in prefixSum[i] means sum from nums[0] to nums[i-1] (prefixSum[0] == 0)
        prefixSum = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; ++i)
        	prefixSum[i+1] = prefixSum[i] + nums[i];
    }
    
    public int sumRange(int left, int right) {
        return this.prefixSum[right+1] - this.prefixSum[left]; 
    }
}

public class Range_Sum_Query_Immutable {}
