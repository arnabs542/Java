package Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/k-diff-pairs-in-an-array/

/*
 * 	This problem is asking for number of unique pairs which has difference of K. Note that pairs must be unique, therefore
 * 	for [1,1,2] and K = 1, the answer shall be 1, not 2.
 * 
 * 	One slower solution is to use sorting and binary search. Basically for each number, we need to check if its complement (which forms
 * 	up to difference K) is exist in the array.	
 * 	However, be careful about duplicates because [1,2] and [2,1] are same and we shall not count it twice. This can be allevated by enforcing
 * 	a rule: We assume that the number chosen is greater than the other pair. This will ensure that only on the greater element we will be
 * 	performing the search on, not on the smaller element and thus, no duplicates
 * 	Since the elements are sorted, We only have to perform binary search on the left side of the array. Also, as soon as we find out the
 * 	value to be searched is greater than the element selected, we skip the search directly.
 * 
 * 	Time complexity is O(N log N) due to sorting, but it takes only O(1), if we sort in place.
 * 
 * 	==============================================================================================================
 * 
 * 	We could trade off some space for time. We basically have to keep track of the frequency table of the array in a Hash Table.
 * 	
 * 	The reason for keeping track of frequency instead of using Set is because in special case of K = 0, we need to see if the element
 * 	contain multiple copies of itself in the original array.
 * 
 * 	After frequency table is done, we iterate over all the keys, skipping those elements which search value is greater than element itself.
 * 	If the K is 0, check if the selected element has multiple copies of itself.
 * 	Else, just check if the array contains the search value, which shall me smaller than the selected element.
 * 
 * 	Time complexity is O(N) and same for space complexity.
 */

public class K_diff_Pairs_In_An_Array {
	
	public int findPairs(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i: nums) {
			int freq = map.getOrDefault(i, 0);
			map.put( i, freq + 1);
		}
		
		
		int res = 0;
		
		for (int i: map.keySet() ) {
			int toFind = i - k;
			if (toFind == i )
				res += (map.get(i) > 1)? 1: 0;
			else if (toFind < i && map.containsKey(toFind) ) {
				res ++;
			}
		}
		
		return res;
	}
	
	
	
	public int findPairs2(int[] nums, int k) {
		int res = 0;
		int len = nums.length;
		Arrays.sort(nums);
		
		for (int i = 1; i < len; i ++ ) {
			if (i < len - 1 && nums[i] == nums[i+1] ) continue;
			
			int search = nums[i] - k;
			if (search > nums[i] ) continue;		//Greater. It could not possibly be found on left side
			res += binarySearch(nums, search, i)? 1: 0;
			
		}
		
		return res;
	}
	
	private boolean binarySearch(int[] nums, int search, int lim) {
		int left = 0, right = lim - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			if ( nums[mid] < search ) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left] == search;
	}

}
