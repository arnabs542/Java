package Medium;

import java.util.ArrayList;
import java.util.List;


//https://leetcode.com/problems/combination-sum-iii/

/*
 * 	This is a Backtracking problem. 
 * 
 * 	We have to select a set of digits (0-9) of specific length k such that sum of all digits add up to number n.
 * 	
 * 	This means for a combination problem. At every place of the digit, I will be selecting a digit, then the problem becomes
 * 	a sub problem such that 
 * 		>	K will become K - 1
 * 		>	N will become N - (selected digit)
 * 
 * 	Now, how do we ensure that no matter what digit we choose, it will never be duplicate? A handy trick is to limit the range
 * 	of possible values that we can choose at a particular digit place. 
 * 	See a possible duplicate for N = 10:
 * 		[1, 9], [9, 1]
 * 	Ask: Why does duplicate occur? When we selected 1 as 1st digit, at second place we try to select any digit from 1-9.
 * 		 Then when we select 9, we consider again all the digits from 1-9, resulting in duplication combination
 * 		 (Of course, if this is permutation problem then this logic is perfectly valid)
 * 		
 * 	Now, the root of the problem is: At every step, we are repeatedly considering all digits. If we limit the range of digits that
 * 	we can consider, then probably the problem is solved
 * 
 * 	The idea is: Only select digits in ascending order. That is, if we've just selected digit 1, then in the next step, only consider
 * 	digits from 2 to 9. If we've just selected digit 5, then in the next step, only consider digits from 6 to 9.
 * 	
 * 	See above example, if we follow the logic, [1,9] will be generated while [9,1] will not since 9 is larger than 1. We will not
 * 	consider any digit less than the last selected digit.
 * 	This will also generate a nice pattern: The result will be in lexicographical order (Sorted)
 * 
 * 
 * 
 * 	Time complexity: At worst case following this algorithm, we will be selecting 1 then 2 then 3 then 4 then 5 then 6 ... 9
 * 					 Each loop we will be limiting the range, but still almost equivalent to considering 1 to 9. Think as O(9)
 * 
 * 					 (Of course, this will be assuming that we check the size of the array is not over 9. If we didn't check,
 * 					  the recursion will continue until we exhausted all the available k, if n is large)
 * 					 
 * 
 * 	Space Complexity: Since we will be checking the array size to not exceed 9, therefore the depth of recursion stack is limited
 * 					  to only about 9 of them. 
 * 
 * 					(If no checking array size, then the depth of stack would go as far as O(K), exhausting all available digit places)
 */


public class Combination_Sum_III {
	
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<Integer> arr = new ArrayList<>(9);
		List<List<Integer>> res = new ArrayList<>();
        recurse(n, k, arr, res);
        
        return res;
	}
	
	private static void recurse(int target, int k, List<Integer> arr, List<List<Integer>> sol) {
		if (k == 0) {
			if (target == 0) 
				sol.add( new ArrayList<>(arr) );
			return;
		}
		
		int size = arr.size();
		if (size > 9) return;
		
		int limit = Math.min(9, target);
		int init = size == 0? 1: arr.get( size - 1) + 1;
		for (int i = init; i <= limit; i ++ ) {
			arr.add(i);
			recurse(target - i, k - 1, arr, sol);
			arr.remove( size );
		}
		
		
	}

}
