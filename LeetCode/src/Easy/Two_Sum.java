package Easy;


//https://leetcode.com/problems/two-sum/submissions/

import java.util.HashMap;

/*
 * 	To avoid finding the number complement one by one, iterating through the array, we use a Hash map to map the number to its index
 * 	If applying 2 passes, we first create a full map by iterating through the array, then, we fix the numbers one by one to try and find its
 * 	complement if it exists in the array
 * 
 * 
 *	Remember that to reach the target given a fixed key, we have to find (target - key)
 *	If it's present in the array, that's it we found the answer (except watch out for duplicate numbers)
 *	If it's not present, that means this is the wrong key and we proceed to attempt the next key
 */

public class Two_Sum {
	
	//Brute force
//	public int[] twoSum(int[] nums, int target) {
//		for (int i = 0; i < nums.length - 1; i ++ ) {
//			for (int j = i+1; j < nums.length; j++ ) {
//				if (nums[i] + nums[j] == target)
//					return new int[] {i,j};
//			}
//		}
//		return new int[] {};
//    }
	
	public int[] twoSum(int[] nums, int target) {
		//Mapping from a number to its index
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i ++ ) {
			int find = target - nums[i];
			if (map.containsKey(find) && map.get(find) != i )
				return new int[] {i , map.get(find)};
			
			map.put(nums[i], i );
		}
		return new int[] {};
	}
		
}
