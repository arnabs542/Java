package Easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/invert-binary-tree/

/*
 * 	The key to solve this problem is to realize that an inverted binary tree has all of its node's child flip/ swapped, a.k.a left <--> right.
 * 	
 * 	Notice we does not need to do this from bottom up. Doing it top to bottom will result in same answer!
 */

class TreeNode {
	int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Invert_Binary_Tree {
	
	//Recursive method//
	public TreeNode invertTree(TreeNode root) {
		if (root == null) return null;
		
		recurse(root);
		return root;
    }
	
	public void recurse(TreeNode node) {
		if (node.left != null) recurse(node.left);
		if (node.right != null) recurse(node.right);
		TreeNode temp = node.right;
		node.right = node.left;
		node.left = temp;
		return;
	}
	
	//Iterative method from bottom to top (not best) //
	public TreeNode invertTreeIterative(TreeNode root) {
		if (root == null) return null;
		
		HashSet<TreeNode> visited = new HashSet<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		
		while (!stack.isEmpty() ) {
			TreeNode current = stack.pop();
			
			if (current.left != null && !visited.contains(current.left ) ) {
				stack.push(current); 
				stack.push(current.left);
				continue;
			}
			if (current.right != null && !visited.contains(current.right) ) {
				stack.push(current);
				stack.push(current.right);
				continue;
			}
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;
			visited.add(current);
		}
		
		return root;
	}
	
	//Iterative method realizing we could do top to bottom //
	public TreeNode invertTreeIterativeOptimize(TreeNode root) {
		if (root == null) return root;
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while (!queue.isEmpty() ) {
			TreeNode current = queue.poll();
			
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;
			
			if (current.left != null) queue.add(current.left);
			if (current.right != null) queue.add(current.right);
		}
		return root;
	}
	
}
