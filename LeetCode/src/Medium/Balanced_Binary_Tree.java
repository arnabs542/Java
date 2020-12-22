package Medium;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/balanced-binary-tree/
/*
 * 	This is a Binary Tree DFS - Postorder traversal problem.
 * 
 * 	For every node, we want to obtain the maximum depth of the left and right subtree. Then, check if the difference
 * 	in depths exceed 1. If it does, it is unbalanced. Otherwise not.
 * 
 * 	Instead of top down approach where on each node, we obtain the depths, and if it is ok, recurse to check on child
 * 	nodes, which means the depth is repeatedly obtained, we should be doing bottom up approach.
 * 
 * 	In bottom up approach, if the child node is found to be already unbalanced, it will return some kind of flag to
 * 	indicate inbalance, and thus parent node can immediately return false. Otherwise, return the maximum depth of the
 * 	child node. Only then, parent node is able to compare immediately and return result without having to recurse into
 * 	child nodes again.
 * 
 * 	-----------------------------------------------------------------
 * 
 * 	An iterative method can be implemented using HashMaps. See on Binary_Tree/Binary_Tree_Postorder_Traversal for more
 * 	information on how to implement post order traversal using Stack.
 * 
 * 	Once the nodes are ready in post order form in Stack, we should pop the nodes.
 * 	For every node, we query the depth of left child and right child using the HashMap. If absent, simply get default value
 * 	of 0.
 * 	Compare the depths and immediately return if found imbalance.
 * 
 * 	-----------------------------------------------------------------------
 * 
 * 	Again, 1 Stack method is entirely possible. Also see Binary_Tree/Binary_Tree_Postorder_Traversal
 */

public class Balanced_Binary_Tree {
	public boolean isBalanced(TreeNode root) {
		return recurse(root, 1) != -1;
	}
	private int recurse(TreeNode node, int depth ) {
		if (node == null) return depth - 1;
		
		int leftDepth = recurse(node.left, depth+1);
		if (leftDepth == -1) return -1;		//	Early termination. No need search right subtree
		
		int rightDepth = recurse(node.right, depth+1);
		
		if (rightDepth == -1 || Math.abs(rightDepth - leftDepth) > 1) return -1;
		return Math.max(leftDepth, rightDepth);
	}
	
	
	
	public boolean isBalanced2(TreeNode root) {
		if (root == null) return true;
		
		Deque<TreeNode> temp = new ArrayDeque<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		temp.push(root);
		
		while (!temp.isEmpty() ) {
			TreeNode pop = temp.pop();
			stack.push(pop);

			if (pop.left != null) temp.push(pop.left);
			if (pop.right != null) temp.push(pop.right);
		}
		
		Map<TreeNode, Integer> getDepth = new HashMap<>();
		
		while (!stack.isEmpty() ) {
			TreeNode pop = stack.pop();
			int leftDeep = getDepth.getOrDefault( pop.left , 0 );
			int rightDeep = getDepth.getOrDefault( pop.right, 0 );
			if ( Math.abs(leftDeep - rightDeep) >= 2 ) return false;
			getDepth.put( pop, Math.max(leftDeep, rightDeep) + 1);
		}
		return true;
	}
	
	
	public boolean isBalanced3(TreeNode root) {
		if (root == null) return true;
		
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode last = null;
		TreeNode curr = root;
		Map<TreeNode, Integer> depth = new HashMap<>();
		
		while (curr != null || !stack.isEmpty() ) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.peek();
				
				if (curr.right == null || curr.right == last) {
					stack.pop();
					int left = depth.getOrDefault( curr.left, 0);
					int right = depth.getOrDefault( curr.right, 0);
					if ( Math.abs(left - right) >= 2 ) return false;
					depth.put( curr, Math.max(left, right) + 1);
					last = curr;
					curr = null;
				} else {
					curr = curr.right;
				}
			}
		}
		return true;
	}
}
