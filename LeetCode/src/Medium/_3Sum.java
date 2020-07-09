package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/3sum/

/*
 * 	My first try is to seperate the numbers into the 3 sections: Positive, zero, and Negative. Iterate through the array and put them into respective map
 * 	which maps from the value to the frequency. For the value zero just a counter is enough.
 * 
 * 	Now notice the key for a three sum. For any positive integer, there must be AT LEAST ONE negative integer for it to reduce it to zero. This goes the same
 * 	for negative integer, which must have one positive integer to increase it to zero.
 * 	This decrease the possibility of combination down to 4 of them:
 * 		-Positive, Positive, Negative
 * 		-Positive, Negative, Negative
 * 		-Positive, Zero, Negative
 * 		-Zero, Zero, Zero
 * 
 * 	Therefore we will iterate the positive integer keysets of unique values, and from negative integer keysets of unique value, check if the third number that
 * 	reduce the pair to zero exists or not. If the targeted integer is same as the positive int or negative int selected, we have to check the frequency of it is
 * 	greater than 2 or not.
 * 	A crucial point is it can cause duplicate issues. Selecting 1 as positive and -3 as negative will make combination of [1, -3, 2]
 * 											 	Later, Selecting 2 as positive and -3 as negative will make combination of [2, -3, 1]
 * 
 * 	A way to solve this is to enforce a rule where the target must be less than the negative int or greater than the positive int for it to pass the test and
 * 	create a combination. Since from the 2 duplicate combinations are of two different int, it must have 1 of the combination to be true for it to pass the test
 * 	while the other will fail.
 * 
 * --------------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	The real solution is to first sort the array. Then we have to iterate the non-positive part of the array one by one to choose the first num.
 * 	Remember to check if the current first number is same as previous (Duplicate), if it's duplicate, skip this iteration and go forward
 * 	
 * 	Now the first number is selected, we can know the target to find is actually -(n1), negative of first integer. From here, use two pointers where left pointer is
 * 	first number index + 1, and right pointer is end of array. Note this sorted array pattern:
 * 
 * 	More negative									More positive
 * 	------------------------------------------------------------>
 * 
 * 	Therefore we can find the sum of number of the 2 pointers. If it is equal to target, then append this 3 pairs to the result array, and move the left and right
 * 	pointer until there is no duplicates, which will be checked again
 * 	If the sum of two number is greater than target, that means the right pointer is too big perhaps, try to decrement right pointer
 * 	If the sum of two number is lesser than target, that means the left pointer is too small perhaps, so try to increment the left pointer
 * 
 * 	Until the first number iterates until positive number, which means the integers to right side is always positive, that is impossible to form zero therefore we
 * 	can safely break and return result here.
 * 
 */

public class _3Sum {
	
//	public List<List<Integer>> threeSum(int[] nums) {
//		HashMap<Integer, Integer> pos = new HashMap<>();
//		HashMap<Integer, Integer> neg = new HashMap<>();
//		int zero = 0;
//		
//		for (int i: nums ) {
//			if (i == 0)
//				zero ++;
//			else if (i > 0)
//				pos.put(i, pos.getOrDefault(i, 0) + 1 );
//			else
//				neg.put(i, neg.getOrDefault(i, 0) + 1 );
//		}
//		
//		Set<Integer> posset = pos.keySet();
//		Set<Integer> negset = neg.keySet();
//		List<List<Integer>> res = new ArrayList<>();
//		
//		for (int p: posset) {
//			for (int n: negset) {
//				int target = (p + n) * -1;
//				System.out.printf("%d, %d and %d \n", p, n, target);
//				if (target == p) {
//					if (pos.get(p) >= 2 )
//						res.add( new ArrayList<>( Arrays.asList(p, p, n) ) );
//				}
//				else if (target == n) {
//					if (neg.get(n) >= 2 )
//						res.add( new ArrayList<>( Arrays.asList(p, n, n) ) );
//				}
//				else if (target == 0 && zero >= 1) {
//					res.add( new ArrayList<>( Arrays.asList(p, n, 0) ) );
//				}
//				else if ( (pos.containsKey(target) || neg.containsKey(target) ) && (target < n || target > p) ) {
//					res.add( new ArrayList<>( Arrays.asList(p, n, target) ) );
//				}
//			}
//		}
//		
//		if (zero >= 3) {
//			res.add( new ArrayList<>( Arrays.asList(0, 0, 0) ) );
//		}
//		
//		return res;
//	}
	
	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> res = new HashSet<>();
		
		Arrays.parallelSort(nums);
		
		for (int i = 0; i < nums.length - 2; i ++ ) {
			if (i != 0 && nums[i - 1] == nums[i] ) continue;
			if (nums[i] > 0) break;
			int target = -nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			
			while (left < right) {
				int sum = nums[left] + nums[right];
				if (sum == target) {
					res.add( Arrays.asList(nums[i], nums[left], nums[right] ) );
					do {
						left ++;
					} while (left < right && nums[left] == nums[left - 1]);
					do {
						right --;
					} while (left < right && nums[right] == nums[right + 1]);
				}
				else if (sum > target)
					right --;
				else
					left ++;
			}
			
		}
		return new ArrayList<>(res);
	}
	
	
	
	public static void main(String[]args) {
		Set<List<Integer> > set = new HashSet<>();
		set.add( Arrays.asList(1,2,3) );
		set.add( Arrays.asList(1,3,2) );
		
		System.out.println(set);
		
	}
}
