package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/subsets-ii/
/*
 * 	This is a Backtracking, Optionally Bit Manipulation problem.
 * 
 * 	First of all, we cannot have duplicate sets, but duplicate elements are allowed:
 * 		>	{1,2,2} is valid
 * 		>	If {1,2} occurs twice in our result, it is invalid
 * 
 * 	Generation of permutations is associated with backtracking algorithm. Therefore we will use it.
 * 	To immediately know whether a element is seen previously, we can choose to sort the array. All duplicate elements will be
 * 	grouped together.
 * 	
 * 	In backtracking tree, each layer represents the set size. Initially, the set size is 0, then when we pick a element to include,
 * 	we go into next layer which set is size 1, and so on.
 * 	Therefore within a layer, we cannot include the same element twice, by checking the previous array element is same or not.
 * 
 * 	===========================================================================
 * 
 * 	The bit manipulation trick allows us to solve the problem iteratively without backtracking. Same idea, sort the array first.
 * 	Then, we will generate all permutations of the bits. This of course will still take O(2^N) time.
 * 
 * 	For each permutation, '1' bit represents to include the element in our set, and '0' means absence.
 *  However still, we need to check if there is duplicated elements, but is still easy to do so with sorted array.
 */

public class Subsets_II {
	
	
	// Backtracking solution
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		// Sort the numbers to allow checking for duplication
		Arrays.sort(nums);
		
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> progress = new ArrayList<>();
		
		backtrack(nums, 0, progress, res);
		return res;
    }
	
	// Each layer represents a size of set. Initially size of set is 0, then 1, then 2
	private void backtrack(int[] nums, int index, List<Integer> progress, List<List<Integer>> res) {
		res.add( new ArrayList<>(progress));
		
		if (index >= nums.length) return;
		
		// Pick an element to whether include or not
		for (int i = index; i < nums.length; ++i) {
			// If this element is same as previous element, then we already have a duplicate. Ignore it
			if (i-1 >= index && nums[i-1] == nums[i])
				continue;
			
			// Include the element, then proceed with next layer (size N+1)
			progress.add(nums[i]);
			backtrack(nums, i+1, progress, res);
			progress.remove( progress.size() - 1 );
		}
	}
	
}
