package Easy;

//https://leetcode.com/problems/search-in-a-binary-search-tree/

/*
 * Simple binary search tree problem. Just find according to binary search tree rules and see if it is found or leads to null.
 */

public class Search_in_Binary_Search_Tree {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left= left;
			this.right = right;
		}
	}
	
	public TreeNode searchBST(TreeNode root, int val) {
		TreeNode current = root;
		while (current != null || current.val != val) {
			current = (current.val > val)? current.left: current.right; 
		}
		return current;
    }
}
