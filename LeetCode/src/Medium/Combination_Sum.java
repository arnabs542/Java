package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/combination-sum/

/*
 * 	This is a backtracking problem.
 * 
 * 	This can be think of in a DP way:
 * 	Given a target integer and list of numbers we can choose from, we can actually pick every number, and have it reduced to a smaller
 * 	subproblem.
 * 	Say we've picked a number 5. Then, we add 5 to our resulting array, and the problem is reduced to target - 5.
 * 	Therefore, we can see that it is more suitable to use recursion to solve the problem in this case. In the recursion function
 * 	the most important thing to pass in is the currently tracked array and the target.
 * 
 * 	Note that the numbers can be repeatly selected, and only COMBINATIONS are allowed ( [1,2] and [2,1] are same, only one of them in solution )
 * 
 * 	First of all, when the target has reached 0, it is the base case where the currently tracked array is exactly one of the solutions. 
 * 	Add the copy of currently tracked array into solution and return.
 * 	Another base case is when the target has gone down below 0. This is a invalid solution so do nothing and return.
 * 
 * 	In general case, we will iterate over the elements of the candidates, starting from the last element selected. To avoid duplicates
 * 	( since only COMBINATION allowed ), we will start iteration from the last selected element's index ONLY. Each element will be selected,
 * 	we will add them to the tracked array, perform a recursive function call, then BACKTRACK by removing the added element.
 * 	
 * 	After the recursive calls, the solution array will be fulfilled, and ready to be returned
 * 
 * 	------------------------------------------------------------------------------------
 * 
 * 	This is a repeating-allowed case. Solutions like [1,1,1,1,1,...] are entirely possible. Therefore to optimize space, we better use
 * 	a frequency table for that.
 * 	Instead of using a currently tracked array, we use a frequency table. Upon target reaching 0, we will just construct a new List
 * 	and only then begin filling in the elements.
 */

public class Combination_Sum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new LinkedList<>();
        recurse( candidates, res, new Stack<>(), 0, target);
        return res;
    }
	
	private static void recurse(int[] candidates, List<List<Integer>> res, Stack<Integer> record, int idx, int target) {
		if (target < 0) return;
		if (target == 0) {
			res.add( new LinkedList<>( record ) );
			return;
		}
		
		for (int i = idx; i < candidates.length; i ++ ) {
			int n = candidates[i];
			record.push( n );
			recurse(candidates, res, record, i, target - n);
			record.pop();
		}
	}
	
	
	
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		int[] freq = new int[ candidates.length + 1 ];
		List<List<Integer>> result = new ArrayList<>();
		
		recurse2(freq, candidates, 0, target, result);
		
		return result;
	}
	
	private static void recurse2(int[] freq, int[] candidates, int index, int target, List<List<Integer>> res ) {
		if (target < 0) return;
		int len = candidates.length;
		if (target == 0) {
			List<Integer> li = new ArrayList<>( freq[ len ] );
			for (int i = 0; i <= len; i ++ ) {
				for (int j = 0; j < freq[i]; j ++ ) {
					li.add( candidates[i] );
				}
			}
			res.add( new ArrayList<>(li) );
			return;
		}
		
		for (int i = index; i < len; i ++ ) {
			freq[i] ++;
			freq[len + 1] ++;
			recurse2(freq, candidates, i, target - candidates[i], res);
			freq[i] --;
			freq[len + 1] --;
		}
	}
	
}
