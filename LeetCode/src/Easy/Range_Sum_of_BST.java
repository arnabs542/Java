package Easy;

import Binary_Tree.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/range-sum-of-bst/

/*
*   This is a BST traversal problem, which is easily solved using recursion or any iteration method of Stack or Queue
* 
*   At each recursion call, we receive a node. Since we know the left and right boundaries, we check:
*   If the node's value is in range, this node value must be included in sum.
*   Then, we know left subtree all values must be less than this node value, and reverse for right subtree.
* 
*   If the left boundary is still lower than the node value, then some node in left subtree may be valid node. Recurse
*   left
*   If the right boundary is still higher than the node value, then some node in right subtree may be valid node.
*   Recurse right
* 
*   ================================================================
* 
*   The very same can be done iteratively, usign Stack (DFS) or Queue (BFS)
* 
*/

public class Range_Sum_of_BST {
	
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) return 0;
		
		// Whether the current node is included in calculation
		int sum = (L <= root.val && root.val <= R?  root.val: 0);
		
		// Node value is less than left boundary - Only search right
		if (root.val <= L) return sum + rangeSumBST(root.right, L, R);
		
		// Node value is greater than right boundary - only search left
		if (root.val >= R) return sum + rangeSumBST(root.left, L, R);
		
		// Intermediate - Search both
		return sum + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
	}
	
	
	public int rangeSumBSTStack(TreeNode root, int L, int R) {
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		int sum = 0;
		
		while (!stack.isEmpty() ) {
			TreeNode node = stack.pop();
			
			if (L <= node.val && node.val <= R) sum += node.val;
			
			if (node.val >= R && node.left != null)
				stack.push(node.left);
			else if (node.val <= L && node.right != null)
				stack.push(node.right);
			else {
				if (node.right != null) stack.push(node.right);
				if (node.left != null) stack.push(node.left);
			}
		}
		return sum;
	}
	
	public int rangeSumBSTQueue(TreeNode root, int L, int R) {
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		int sum = 0;
		
		while (!list.isEmpty() ) {
			TreeNode node = list.poll();
			
			if (L <= node.val && node.val <= R) sum += node.val;
			
			if (node.val >= R && node.left != null)
				list.add(node.left);
			else if (node.val <= L && node.right != null)
				list.add(node.right);
			else {
				if (node.left != null) list.add(node.left);
				if (node.right != null) list.add(node.right);
			}
		}
		return sum;
	}
	
}
