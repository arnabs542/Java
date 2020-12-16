package Medium;
import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;
import javafx.util.Pair;

//https://leetcode.com/problems/validate-binary-search-tree/
/*
 * 	This is a BST DFS - Preorder Traversal or Inorder traversal problem
 * 
 *  Every node, the left subtree must only have the value at maximum up to the current node's value. Similarly,
 *  the right subtree must only have the value at minimum from the current node's value.
 *  
 *  This can be done using pre order traversal. Every recursive call we would pass in the boundary where the node
 * 	is valid. As soon as detect the value of the node does not fall in valid range, immediately return false, which
 * 	will then be returned up to the recursion stack.
 * 
 * 	Preorder traversal iteratively is very intuitive. Explore node, push both sides into stack and continue
 * 
 * 	----------------------------------------------------------------------
 * 
 * 	Recall the property of Inorder traversal of BST - The values will be recorded as if it is sorted. Therefore,
 * 	for a valid BST, the values recorded must always be sorted from smallest to largest. If during the traversal
 * 	we detect some value disrupts the STRICTLY INCREASING ORDER, immediately return false
 * 
 * 	As well, this is done more easily using global variable (or mutable variable) with recursion
 * 
 * 	To implement Inorder Travversal using iterative approach is also plausible. However, the method is not as
 * 	intuitive. See the algorithm:
 * 
 * 		>	While root has left subtree, push to stack and makee root as left subtree itself
 * 		>	Now root shall be null. Pop the top of stack to be root, which is leftmost node of current tree	
 * 		>	Do something with that node
 * 		>	Explore the right subtree. Set root to right, and while root has left subtree, push to stack and 
 * 			make root as left subtree.
 */

public class Validate_Binary_Search_Tree {
	
	// Recursive method using Preorder Traversal
	public boolean isValidBST(TreeNode root) {
		return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	private boolean validate(TreeNode node, long left, long right) {
		if (node == null) return true;
		if (node.val <= left || node.val >= right) return false;
		
		return validate(node.left, left, node.val) && validate(node.right, node.val, right); 
	}
	
	
	// Iterative method using Preorder traversal
	public boolean isValidBST2(TreeNode root) {
		if (root == null) return true;
		
		Deque<TreeNode> stack = new ArrayDeque<>();
		Deque<Pair<Integer, Integer> > minmax = new ArrayDeque<>();
		
		stack.push(root);
		minmax.push( new Pair<>(null, null) );
		
		while (!stack.isEmpty() ) {
			TreeNode node = stack.pop();
			Pair<Integer, Integer> bound = minmax.pop();
			
			if ( (bound.getKey() != null && node.val <= bound.getKey() ) || (bound.getValue() != null && node.val >= bound.getValue() ) )
				return false;
			
			if (node.left != null) {
				stack.push(node.left);
				minmax.push( new Pair<>(bound.getKey(), node.val) );
			}
			if (node.right != null) {
				stack.push(node.right);
				minmax.push( new Pair<>(node.val, bound.getValue() ) );
			}
		}
		return true;
	}
	
	
	
	Integer prev = null;
	
	// Inorder traversal method
	public boolean isValidBST3(TreeNode root) {
		return isValid(root);
	}
	private boolean isValid(TreeNode node) {
		if (node == null) return true;
		if ( !isValid(node.left) ) return false;
		if (prev == null || node.val > prev) {
			prev = node.val;
			return isValid(node.right);
		}
		return false;
	}
	
	
	// Inorder Traversal method using iteratively
	public boolean isValidBST4(TreeNode root) {
		Deque<TreeNode> rootStacks = new ArrayDeque<>();
		
		while (root != null) {
			rootStacks.push(root);
			root = root.left;
		}
		
		while (!rootStacks.isEmpty() ) {
			root = rootStacks.pop();
			if (prev == null || root.val > prev) prev = root.val;
			else return false;
			root = root.right;
			while (root != null) {
				rootStacks.push(root);
				root = root.left;
			}
		}
		return true;
	}
	

}
