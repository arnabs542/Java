package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/subsets/

/*
 * 	One solution is to use what is known as Cascading, which is more similar to dynamic programming approach (USing subproblems and previously
 * 		computed solutions)
 * 
 * 	Initialize a list of results that will be returned at the end of the function. Add a default empty set into it.
 * 	Now we will loop a linear pass through the list of integers, selecting one integer at a time.
 * 	For each selected integer, it is going to be appended to all the lists in the result list.
 * 	Therefore a simulation run for [1,2,3]:
 * 		Intermission: () + []
 * 		1 Selected: ( [] ) + [1]
 * 		2 Selected: ( [], [1] ) + [2], [1,2]
 * 		3 Selected: ( [], [1], [2], [1,2] ) + [3], [1,3], [2,3], [1,2,3]
 * 	Remember it needs to make a copy of each item from the result list each time, resulting in O(N) time
 * 	
 * 	The overall time complexity is O(N * 2^n) because we have to loop it for 2^n times, and N is the time taken to copy array
 * 	The space complexity is O(N * 2^n) as well because the 2^n is the number of combinations there is for the result array, and
 * 		N is for the size of integers that each individual list hold, which in worst case is, all the integers
 * 
 * -------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	Another solution is to use bitmask of size n. Each bits are 0 representing absence of this element and 1 representing presence of this
 * 	element. We can generate it easily because we know the last permutation must be all 1's, and the number of permutation is known for
 * 	2^n. We just initialize a int from 0, add all the way up to 2^n - 1 inclusive (totaling up to 8 permutations)
 * 
 * 	Then for each permutation we check for each bit one by one if we shall include this integer inside our current set or not.
 * 
 */

public class Subsets {
	
//	public List<List<Integer>> subsets(int[] nums) {
//		List<List<Integer>> res = new ArrayList<>();
//		res.add( new LinkedList<>() );
//		
//		for (int n: nums ) {
//			int len = res.size();
//			for (int i = 0; i < len; i ++ ) {
//				List<Integer> copy = new ArrayList<>(res.get(i) );
//				copy.add(n);
//				res.add(copy);
//			}
//		}
//		
//		return res;
//	}
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		int len = nums.length;
		int max = (int)(Math.pow( 2, len) - 1 );
		
		for (int i = 0; i <= max; i ++ ) {
			
			LinkedList<Integer> curr = new LinkedList<>();
			int mask = 1;
			for (int pos = len - 1; pos >= 0; pos --) {
				if ( (i & mask) != 0 )
					curr.add(nums[pos] );
				mask <<= 1;
			}
			res.add(curr);
			
		}
		return res;
	}
	
	
	
}
