package Medium;

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/

/*
 * 	This is a Binary Tree Depth First Search problem
 * 
 * 	There is 2 approach - Top down or bottom up. Top down is easier to implement and more clean. However I first came up
 * 	with bottom up.
 * 
 * 	To find the maximum difference between node and ancestor, we could have a recursion function that returns the
 * 	maximum node and minimum node of the left and right subtree. Then, we could try to find the difference from those
 * 	max and min, updating the global variable when a greater difference was found.
 * 
 * 	----------------------------------
 * 
 * 	Instead of having a recursion function which reports the maximum and minimum value in subtree, why don't we just
 * 	report the maximum and minimum value of the ancestors down to the child nodes? 
 * 	Since taking the absolute difference makes no difference whether it is child - ancestor or ancestor - child, 
 * 	implementing this way is much more elegant, and clean
 */

import Binary_Tree.TreeNode;

public class Maximum_Difference_Between_Node_and_Ancestor {
	
	int res = 0;
	int min, max;
	
	public int maxAncestorDiff(TreeNode root) {
		if (root == null) return 0;
		
		recurseTopBottom(root, root.val, root.val);
		
		return res;
    }
	
	
	private void recurseTopBottom(TreeNode node, int min, int max) {
		if (node == null) return;
		
		res = Math.max( res , Math.abs(node.val - min) );
		res = Math.max( res,  Math.abs(node.val - max) );
		
		recurseTopBottom(node.left, Math.min(node.val, min), Math.max(node.val, max) );
		recurseTopBottom(node.right, Math.min(node.val, min), Math.max(node.val, max) );
	}
	
	
	
	private int[] recurse(TreeNode node) {
		int[] left = {node.val, node.val};
		int[] right = {node.val, node.val};
		
		if (node.left != null) left = recurse(node.left);
		if (node.right != null) right = recurse(node.right);
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i: left) {
			max = Math.max(max , i);
			min = Math.min(min, i);
		}
		for (int i: right) {
			max = Math.max(max, i);
			min = Math.min(min, i);
		}
		
		res = Math.max(res, Math.abs(node.val - max) );
		res = Math.max(res, Math.abs(node.val - min) );
		
		return new int[] {
			Math.min( node.val, min),
			Math.max( node.val, max)
		};
	}
	private void recurseGlobal(TreeNode node) {
		int[] arr = { node.val, node.val, node.val, node.val};
		
		if (node.left != null) {
			recurseGlobal(node.left);
			arr[0] = min;
			arr[1] = max;
		}
		if (node.right != null) {
			recurseGlobal(node.right);
			arr[2] = min;
			arr[3] = max;
		}
		
		int lmin = Integer.MAX_VALUE, lmax = Integer.MIN_VALUE;
		for (int i: arr) {
			lmin = Math.min(lmin, i);
			lmax = Math.max(lmax, i);
		}
		
		res = Math.max( res , Math.abs(node.val - lmin) );
		res = Math.max( res, Math.abs(node.val - lmax) );
		
		min = Math.min( lmin, node.val);
		max = Math.max( lmax, node.val);
	}
	
	
	
}
