package Medium;

import java.util.HashMap;

//https://leetcode.com/problems/subarray-sum-equals-k/

public class Subarray_Sum_Equals_K {
//	public int subarraySum(int[] nums, int k) {
//		int counter = 0;
//		int sumSoFar = 0;
//		for (int size = 1; size <= nums.length; size ++ ) {
//			//Initialization
//			int window = 0;
//			sumSoFar += nums[size - 1];
//			window = sumSoFar;
//			counter += (window == k)? 1: 0;
//			
//			//Sliding window
//			for (int i = size; i < nums.length; i ++ ) {
//				window += nums[i];
//				window -= nums[i - size];
//				counter += (window == k)? 1:0;
//			}
//		}
//		return counter;
//    }	
	
	public int subarraySum(int[] nums, int k) {
		HashMap<Integer,Integer> cum = new HashMap<>();
		cum.put(0, 1);
		
		int sumSoFar = 0;
		int counter = 0;
		
		for (int n: nums) {
			sumSoFar += n;
			
			if (cum.containsKey(sumSoFar-k) ) {
				counter += cum.get(sumSoFar - k);
			}
			
			cum.putIfAbsent(sumSoFar, 0);
			cum.replace(sumSoFar, cum.get(sumSoFar) + 1);
		}
		return counter;
	}
}
