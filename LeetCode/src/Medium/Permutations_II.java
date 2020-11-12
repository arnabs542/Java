package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//https://leetcode.com/problems/permutations-ii/

/*
 * 	This is a Backtracking / Permutation Problem
 * 
 * 	For backtracking, the idea is simply from the list of numbers, we can pick one element out, append it to current
 * 	tracking array, remove from the list of available elements, and proceed the same procedure again until the available
 * 	element is empty. Then, we know that a permutation is generated.
 * 
 * 	However, this time, duplicates are allowed. In the backtracking solution, each recursion call basically means one
 * 	single place in the resulting array. Eg:
 * 		>	The first call will iterate over all possible values to be put in the FIRST PLACE.
 * 		>	The second layer call will iterate over all possible values to be put in the SECOND PLACE.
 * 	Therefore, we need a way to basically only iterate over the duplicated value ONLY ONCE in each of the calls, but
 * 	in the subsequent calls, it still knows that the duplicated value is also selectable.
 * 
 * 	This is achieved using frequency table.
 * 	At each layer calls, simply iterate over the key values of the table. If counter is 0, we know this key is used up
 * 	by prior calls. If not, we could attempt to insert the element here. Decrement the available count by 1 in the table,
 * 	then recurse into another layer. When the recurse layer returns, undo the changes.
 * 
 * 	==============================================================================================================
 * 
 * 	Another solution would be using the next permutation algorithm. Next permutation 100% complies with the duplicate
 * 	value law in this problem. Just that before the iteration itself, we have to sort the nums array, then add the initial
 * 	state into the result array first before it generates the next lexicographical permutation.
 * 
 * 	For more info, see Topics > Algorithms > Next_Lexicographical_Permutation
 */


public class Permutations_II {
	public List<List<Integer>> permuteUnique(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i: nums) {
			freq.compute(i , (k,v)-> v == null? 1: v + 1);
		}
		
		List<List<Integer>> res = new ArrayList<>();
		Deque<Integer> curr = new ArrayDeque<>( nums.length );
		
		recurse( res, freq, curr, nums.length );
		
		return res;
	}
	
	private void recurse(List<List<Integer>> res, Map<Integer, Integer> freq, Deque<Integer> curr, int sizeLeft ) {
		if ( sizeLeft <= 0 ) {
			res.add( new ArrayList<>(curr) );
			return;
		}
		
		freq.forEach( (k,v) -> {
			if (v == 0) return;
			
			freq.put( k, v - 1);
			curr.push(k);
			recurse( res, freq, curr, sizeLeft - 1);
			curr.pop();
			freq.put( k, v );
		});
	}
	
	
	
	
	public List<List<Integer>> permuteUnique2(int[] nums) { 
		
		int len = nums.length;
		Arrays.sort( nums );
		List<List<Integer>> res = new ArrayList<>();
		res.add( IntStream.of(nums).boxed().collect( Collectors.toList() ) );
		
		while (true) {
			int pivot = len - 2;
			while ( pivot >= 0 && nums[pivot + 1] <= nums[pivot] ) pivot --;
			
			if (pivot < 0) break;
			
			int swapper = len - 1;
			while ( nums[swapper] <= nums[pivot] ) swapper --;
			
			swapElem( nums, swapper, pivot );
			
			revSubArray( nums, pivot + 1, len - 1 );
			
			res.add( IntStream.of(nums).boxed().collect( Collectors.toList() ) );
		}
		return res;
	}
	
	private void swapElem(int[] arr, int i1, int i2 ) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	//	Reverses a subarray, provide the starting and ending index inclusively
	private void revSubArray(int[] arr, int from, int to) {
		while (from < to) {
			swapElem(arr, from, to);
			from ++; to --;
		}
	}
}
