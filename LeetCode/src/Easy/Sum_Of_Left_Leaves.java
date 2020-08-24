package Easy;

import java.util.Stack;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/sum-of-left-leaves/

/*
 * 		This is a tree DFS problem.
 * 
 * 		A leaf is a node which doesn't have any more child nodes. We have to find the sum of all left leaves
 * 		
 * 		At every node that we meet, we check if the left node of it is the leaf node
 * 			>	Check if current node has left node
 * 			>	Check if left node has left child. If yes it is not a leaf node
 * 			>	Check if left node has right child. If yes it is not a leaf node
 * 
 * 		The problem can be solved iteratively or recursively.
 * 		Iterative method also uses DFS, with Stack data structure to aid us
 */

public class Sum_Of_Left_Leaves {
	
    public int sumOfLeftLeaves(TreeNode root) {
    	if (root == null) return 0;
    	
    	int sum = 0;
    	if (root.left != null && root.left.left == null && root.left.right == null)
    		sum += root.left.val;
    	
    	return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right); 
    }
    
    
    public int sumOfLeftLeavesI(TreeNode root) {
    	Stack<TreeNode> stack = new Stack<>();
    	
    	if (root != null)
    		stack.add(root);
    	
    	int sum = 0;
    	
    	while ( !stack.isEmpty() ) {
    		TreeNode node = stack.pop();
    		
    		if (node.left != null && node.left.left == null && node.left.right == null) {
    			sum += node.left.val;
    		}
    		
    		if (node.left != null) stack.push( node.left);
    		if (node.right != null) stack.push( node.right);
    	}
    	
    	return sum;
    }

}
