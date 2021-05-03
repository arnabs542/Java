package Easy;

//https://leetcode.com/problems/running-sum-of-1d-array/
/*
 * 	This is a simple running sum problem, required knowledge is simply loops
 * 
 * 	One method is to use separate variable to record your sum so far. On each iteration, add value
 * 	to the variable
 * 
 * 	Another one is to use the output array itself. Since last element represents sum of elements from
 * 	index 0 until i-1, to get sum from 0 to i require just taking last element added to current element
 * 
 * 	Recursive method is viable, but unnecessary other than to practice
 */

public class Running_Sum_of_1d_Array {
	
	//	Iterative method via variable recording
	public int[] runningSum(int[] nums) {
		int[] res = new int[nums.length];
		int runsum = 0;
		for (int i = 0; i < nums.length; ++i)
			res[i] = runsum += nums[i];
		return res;
    }
	
	//	Iterative method via previous computations. (DP?)
	public int[] runningSum2(int[] nums) {
		int[] res = new int[nums.length];
		res[0] = nums[0];
		for (int i = 1; i < nums.length; ++i) 
			res[i] = res[i-1] + nums[i];
		return res;
	}
	
	
	//	Bonus: Recursive
	public int[] runningSum3(int[] nums) {
		int[] res = new int[nums.length];
		res[0] = nums[0];
		recurse(nums, res, 1);
		return res;
	}
	private void recurse(int[] nums, int[] res, int i) {
		if (i >= nums.length) return;
		res[i] = res[i-1] + nums[i];
		recurse(nums, res, i+1);
	}
}
