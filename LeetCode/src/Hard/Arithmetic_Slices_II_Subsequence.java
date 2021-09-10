package Hard;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
/*
 * 	This is a Dynamic Programming problem. 
 * 
 * 	For brute force, we essentially pick every element to be start of subsequence, then making choices:
 * 	whether to or not to include the element. This ends up O(2^N) time complexity.
 * 
 * 	-------------------------------------------
 * 
 * 	For every element i in the array nums, it can do two things:
 * 	1. It can extend any previous formed subsequence (if valid). This requires us to have knowledge about
 * 	   all the previously formed subsequence beforehand - The number of subsequences that can be extended
 * 	   by current element i.
 * 
 *  2. It can choose ANY previous element to form a new subsequence of length 2, which can be extended by
 *  	future elements.
 *  
 *  My initial implementation is to have nested HashMap as my DP array. DP[i] will store information on
 *  how many subsequences can be extended if the current element is i.
 *  However, extended subsequences can be further extended by future elements too! For this, DP[i] will
 *  instead also be a HashMap that maps (difference)->(count). Once extended, the extended subsequence
 *  can be further extended if element (i + difference) is met.
 *  
 *  However, my solution passes all but several test case. A mystery until now.
 *  
 *  ---------------------------------------------
 *  
 *  The official solution instead keeps a HashMap on each of the elements. It maps to number of subsequences
 *  by their arithmetic difference. Eg: DP[i].get(5) represents number of subsequences that end at element index
 *  i, that has arithmetic difference of 5.
 *  
 *  For every element i in iteration, we look backwards each element j from 0 up to but not include i. Calculate
 *  their difference, and see if a extended subsequence can be formed from j to i or not.
 */

public class Arithmetic_Slices_II_Subsequence {
	
	// Initial solution, fails last several test case. Don't know why, can't test because test case too large
	public int numberOfArithmeticSlices(int[] nums) {
		// Maps (Target) -> (Difference) -> (Count)
		final int len = nums.length;
		Map<Long, Map<Long, Integer>> mapper = new HashMap<>();
		int res = 0;
		
		for (int i = 1; i < len; ++i) {
			long n = nums[i];
			// Step 1: Check if it is able to extend any subsequence.
			if (mapper.containsKey(n)) {
				Map<Long, Integer> subsequences = mapper.get(n);
				// For each of the available subsequences, extend it.
				// Remember that once extended, it can be further extended by future elements
				for (long diff: subsequences.keySet() ) {
					int count = subsequences.get(diff);
					res += count;
					mapper.putIfAbsent( n + diff, new HashMap<>() );
					mapper.get(n + diff).compute(diff, (k,v)-> count + (v == null? 0: v) );
				}
			}
			// Step 2: this element can form any 2 element subsequence with previous elements
			for (int j = 0; j < i; ++j) {
				long diff = n - nums[j];
				long target = n + diff;
				
				mapper.putIfAbsent(target, new HashMap<>());
				mapper.get(target).compute(diff, (k,v)-> 1 + (v == null? 0: v));
			}
		}
		return res;
    }
	
	
	
	// Optimized DP solution. DP[i] stores a Hashmap that maps (diff) -> (count)
	public int numberOfArithmeticSlices2(int[] nums) {
		final int len = nums.length;
		int res = 0;
		Map<Integer, Integer>[] mapper = new Map[len];
		
		for (int i = 0; i < len; ++i) {
			mapper[i] = new HashMap<>();
			
			for (int j = 0; j < i; ++j) {
				long diff = (long)nums[i] - nums[j];
				if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
				
				// Extendable is simply how many subsequences that this element can extend
				// Say [1,1,3] and we are on index 2, currently processing index 1. Duplicates is to
				// take account on previously computed index 0, which is a duplicate. Same effect as +=
				int extendable = mapper[j].getOrDefault((int)diff, 0);
				int duplicates = mapper[i].getOrDefault((int)diff, 0);
				
				res += extendable;
				mapper[i].put((int)diff, extendable + duplicates + 1);
			}
		}
		return res;
	}
	
}
