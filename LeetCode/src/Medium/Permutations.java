package Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/permutations/

/*
 * 	Classical backtracking problem
 * 	The used boolean is optional, but (probably) can speed up processing time a bit. However still time out limit for [1,2,3,4,5,6,7,8,9] though
 */

public class Permutations {

	public static List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> solution = new LinkedList<>();
        helpPerm(nums, new LinkedList<Integer>(), new boolean[nums.length], solution);
        return solution;
	}
	
	private static void helpPerm(int[] nums, LinkedList<Integer> soFar, boolean[] used, LinkedList<List<Integer>> solution) {
		if (soFar.size() == nums.length ) {
			solution.add(new LinkedList<>(soFar) );
			return;
		}
		
		for (int i = 0; i < nums.length; i ++ ) {
			if (used[i] ) {
				continue;
			}
			soFar.add(nums[i]);
			used[i] = true;
			helpPerm(nums, soFar, used, solution);
			soFar.remove((Integer)nums[i]);
			used[i] = false;
		}
		
	}
	
	public static void main(String[]args) {
		int[] nums = new int[] {1,2,3,4,5,6};
		System.out.println( permute(nums) );
	}
	
}
