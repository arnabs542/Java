package Medium;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

//https://leetcode.com/problems/find-all-duplicates-in-an-array/

/*
 * 	Since the problem restricts the value to be 1 to n only, then we can exploit the property of indexing of the array itself.
 * 	
 * 	If we found a number, we will first check if the number at that position num - 1 is already itself. If not, then just safely swap
 * 	it with the current number.
 * 	Otherwise if the number is already there, then we know this is a duplicate. Add that to hashset or something, which takes O(N) space
 * 
 * 	If don't want to use hashset, use two pass method, where first pass is just placing to respective places, ignoring swapping
 *  if the number is already in its respective place
 *  
 *  ------------------------------------------------------------------------------------------------
 *  
 *  Another method is using negation of the value. Since all numbers are ensured to be positive, every time we found a number,
 *  we can just replace the value at index num - 1 to its negative value. This way we know that the number at this index occurred before.
 *  
 */

public class Find_All_Duplicates_In_An_Array {
	
//	public List<Integer> findDuplicates(int[] nums) {
//		List<Integer> res = new ArrayList<>();
//		
//		int pointer = 0;
//		while (pointer < nums.length ) {
//			int num = nums[pointer];
//			
//			if (nums[num - 1] == num) {
//				pointer ++;
//			} else {
//				int temp = nums[num - 1];
//				nums[num - 1] = num;
//				nums[pointer] = temp;
//			}
//		}
//		
//		for (int i = 0; i < nums.length; i ++ ) {
//			if (nums[i] != i + 1)
//				res.add(nums[i] );
//		}
//		
//		return res;
//	}
	
	
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		
		for (int i = 0; i < nums.length; i ++ ) {
			int num = Math.abs(nums[i] );
			
			if (nums[num - 1] < 0) {
				res.add(num);
			}
			else nums[num - 1] *= -1;
		}
		return res;
	}
	
}
