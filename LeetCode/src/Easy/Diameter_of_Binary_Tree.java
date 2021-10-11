package Easy;
import Binary_Tree.TreeNode;


//https://leetcode.com/problems/diameter-of-binary-tree/
/*
 * This is a binary tree problem.
 * 
 * This problem can be broken down into the same problem for each subnode, thus the use of recursion here.
 * The idea is, the longest diameter is simply the maximum depth of left subtree + maximum depth of right subtree for a given parent node
 * (root). However, we don't necessary need to pass through the root node of the original problem, Instead, every node in the tree can 
 * potentially become the root node that is passed through in the diameter. Therefore take into account of that.
 */

public class Diameter_of_Binary_Tree {
	int res = 0;
	
	public int diameterOfBinaryTree(TreeNode root) {
		getDepthAndUpdateRes(root);
		return res;
    }
	
	private int getDepthAndUpdateRes(TreeNode root) {
		if (root == null) return 0;
		// Get depth of both sides of the tree.
		int left = getDepthAndUpdateRes(root.left);
		int right = getDepthAndUpdateRes(root.right);
		
		// If I connected left and right subtree, will it give me longest diameter?
		res = Math.max(res, left + right);
		// Return maximum depth of this subtree.
		return Math.max(left, right) + 1;
	}
	
}
