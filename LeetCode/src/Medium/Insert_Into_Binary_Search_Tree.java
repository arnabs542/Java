package Medium;

//https://leetcode.com/problems/insert-into-a-binary-search-tree/

/*
 * 	This is a simple Binary Search Tree Insertion Question.
 * 
 * 	When we insert an element into a normal BST (Not balanced), it will usually be added as a leaf node.
 * 	This is quite of a recursive problem to it, we are repeating asking one question:
 * 	>	Should I insert a node in this current node?
 * 
 * 	Therefore this problem can be solved recursively, or iteratively.
 * 
 * 	Remember the property: Left subtree always has all values less than the current node,
 * 						   Right subtree always has all values greater than the current node.
 *	Therefore at each node, we compare the value to insert with the node's value, to determine whether
 *	the inserted node shall go into left side or right side
 *
 *	WHen approaching recursively, each child node of current node is considered the root node in that
 *	recursion call. When the root node is null, simply create a new node with the value and return.
 */

import Binary_Tree.TreeNode;

public class Insert_Into_Binary_Search_Tree {
	
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) return new TreeNode(val);
		
		if (root.val > val) {
			root.left = insertIntoBST(root.left , val);
		} else {
			root.right = insertIntoBST(root.right , val);
		}
		return root;
	}
	
	public TreeNode insertIntoBST_iterative(TreeNode root, int val) {
		TreeNode curr = root;
		
		//	The only case this is false is when the root itself initially is already null.
		while (curr != null) {
			if (curr.val > val ) {
				if (curr.left == null) {
					curr.left = new TreeNode(val);
					return root;
				}
				curr = curr.left;
			}
			else {
				if (curr.right == null) {
					if (curr.right == null) {
						curr.right = new TreeNode(val);
						return root;
					}
				}
				curr = curr.right;
			}
		}
		
		//	Since the root initially is already null, we return the new node.
		return new TreeNode(val);
	}
}
