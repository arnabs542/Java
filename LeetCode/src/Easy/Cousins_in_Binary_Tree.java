package Easy;
import java.util.ArrayDeque;
import java.util.Queue;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/cousins-in-binary-tree/
/*
*	This is a Binary Tree problem.
* 
*	Since this problem involves depth, we can use BFS to allow for early termination.
*	The idea is:
*		Iterate layer by layer. Once one of x or y is found, we have no reason to go deeper into the layers. This is early termination and is better
*		as binary tree usually grow exponentially by depth.
* 
*	Keep a boolean flag to indicate whether one of x or y is found in the layer. Also keep int variable to reference the parent. Then, For each
*	node in current layer, check left and right (So we know who is the parent).
*	
*	At the end of layer, if only one node is located, then terminate early by returning false, since we know they are already not on the same level.
*/



public class Cousins_in_Binary_Tree {
	
	
	// Naive DFS solution - Search for 2 nodes
	public boolean isCousins(TreeNode root, int x, int y) {
		int[] x1 = depthAndParent(root, x);
		int[] y1 = depthAndParent(root, y);
		
		//If the node is at the root (which the method depthAndParent() is unable to detect) or somehow fails to find the solution,
		//Then the whole case fails.
        if (x1 == null || y1 == null) return false;
		return (x1[1] == y1[1] && x1[0] != y1[0] );
    }
	
	/*	Attempts to find the value from parent's node perspective. Returns a integer array of size 2 which
	 * 		index 0: the value of parent itself
	 * 		index 1: the depth so far, will increment by 1 every time it's passed upwards
	 * 	Note that if the number is on the root itself (topmost node), this method won't work and will return null for that
	 */
	static int[] depthAndParent(TreeNode node, int num) {
		//If the num is found at either left node or right node, start passing it upwards along with this node's (parent) value
		if ( (node.left != null && node.left.val == num) || (node.right != null && node.right.val == num) ) {
			return new int[]{node.val, 1};
		}
        
		//Try to search the left node recursively. If it is found, then start passing up with the incrementing depth
		int[] left = (node.left == null)? null: depthAndParent(node.left, num);
		if (left != null) {
			return new int[] {left[0], left[1] + 1};
		}
		
		//Try to search the right node recursively. If it is found, then start passing up with the incrementing depth
		int[] right = (node.right == null)? null: depthAndParent(node.right, num);
		if (right != null) {
			return new int[] {right[0], right[1] + 1};
		}
		return null;
	}
	
	
	
	public boolean isCousins2(TreeNode root, int x, int y) {
		if (root == null || root.val == x || root.val == y) return false;
		Queue<TreeNode> bfs = new ArrayDeque<>();
		bfs.add(root);

		boolean isLocated = false;
		int parent = -1;

		// Note that we don't check root node. This can be checked early if you want.
		while (!bfs.isEmpty()) {
			for (int size = bfs.size(); size != 0; --size) {
				TreeNode node = bfs.poll(); 

				// Left subtree
				if (node.left != null) {
					if (node.left.val == x || node.left.val == y) {
						if (isLocated) return true;
						isLocated = true;
						parent = node.val;
					}
					bfs.add(node.left);
				}
				// Right subtree
				if (node.right != null) {
					if (node.right.val == x || node.right.val == y) {
						// Same parent.
						if (parent == node.val) return false;
						if (isLocated) return true;
						isLocated = true;
						parent = node.val;
					}
					bfs.add(node.right);
				}
				
			}
			// Located 1 node on this level only, means different depth
			if (isLocated) return false;
		}
		// Iterated whole tree already.
		return false;
    }
	
}
