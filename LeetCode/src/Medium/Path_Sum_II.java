package Medium;
import java.util.ArrayList;
import java.util.List;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/path-sum-ii/
/*
 * 	This is a backtracking, tree DFS problem.
 * 
 * 	We have to find path from ROOT to LEAF where SUM=TARGET.
 * 
 * 	Since we have to record paths, using DFS with backtracking is the ideal choice here instead of BFS, as it has
 * 	difficulties tracking the path
 */

public class Path_Sum_II {
	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
     
        backtrack(root, targetSum, new ArrayList<>(), res);
        return res;
    }
	
	private void backtrack(TreeNode curr, int target, List<Integer> path, List<List<Integer>> res) {
		target -= curr.val;
		path.add(curr.val);
		
		// Successfully sum to target and it is a leaf node.
		if (curr.left == null && curr.right == null && target == 0) {
			res.add( new ArrayList<>(path));	
		}
		// Otherwise, it may be (Not equal to sum), or (is not leaf node)
		else {
			if (curr.left != null) 
				backtrack(curr.left, target, path, res);
			if (curr.right != null)
				backtrack(curr.right, target, path, res);
		}
		
		// Remove current value before returning
		path.remove( path.size() - 1 );
	}
}
