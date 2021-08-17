package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/count-good-nodes-in-binary-tree/
/*
 *	A fairly simple DFS / BFS recursion binary tree problem.
 *
 * 	Every node that we visit, if we have the information about the maximum value from root to current node,
 * 	then I am able to know the number of good nodes in the tree by the recurrence relation:
 * 
 * 		(is myself a good node?) + (good node in left subtree) + (good node in right subtree).
 * 
 * 	Therefore we always pass the maximum value seen so far into the recursion
 */

public class Count_Good_Nodes_in_Binary_Tree {
	
	public int goodNodes(TreeNode root) {
		return goodNodes(root, Integer.MIN_VALUE);
    }
	
	private int goodNodes(TreeNode root, int max) {
		if (root == null) return 0;
		max = Math.max(root.val, max);
		
		return (root.val >= max? 1: 0) + goodNodes(root.left, max) + goodNodes(root.right, max);
	}
}
