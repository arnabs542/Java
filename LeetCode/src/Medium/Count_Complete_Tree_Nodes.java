package Medium;

///https://leetcode.com/problems/count-complete-tree-nodes/

/*
 * 	This can be done very naively with a basic BFS or DFS, but we could do it in O( log n * log n) time complexity.
 * 	
 * 	The key here is that given the tree is a complete binary tree where nodes must be in the left at the last layer. We can find out the
 * 	maximum height of the tree by iterating through the left node every time.
 * 	Now we know the max height of the tree, we shall check if we go along the right node of the tree, is the height also same as the maximum height?
 * 
 * 	If the maximum height is equal to the height if I went right node first then left node all the way, that means the very last node of the tree
 * 	shall be in the right subtree. We know that the left subtree part is complete of max height. 
 * 	If the height of the tree if I went right node first then left node all the way is not equal, in fact, one less than the maximum height, that
 * 	means the very last node of the tree shall be in the left subtree. Now we know that the right subtree part is complete of the maximum height - 1
 * 
 * 	How do we went ahead and find out the number of nodes in the left part or right part? It so happens that the count is 2^(Height - 1).
 * 	Therefore we could use bit manipulation to perform this power of 2, through shifting of bits.
 */

public class Count_Complete_Tree_Nodes {
	public class TreeNode {
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
	
	public static int countNodes(TreeNode root) {
		int height = maxHeight(root);
		
		if (height == 0) return height;
		
		int rightHeight = 1 + maxHeight(root.right);
		
		if (height == rightHeight) {
			return (1 << height-1) + countNodes(root.right);
		}
		else
			return (1 << rightHeight-1) + countNodes(root.left);
	}
	
	//-------------------------------------------------------------------------------------------------------
	
	public static int countNodesIter(TreeNode root) {
		
		TreeNode current = root;
		int height = maxHeight(root);
		int count = 0;
		
		while (current != null) {
			int rightHeight = 1 + maxHeight(current.right);
			
			if (height == rightHeight) {
				count += (1 << height-1);
				current = current.right;
			}
			else {
				count += (1 << rightHeight-1);
				current = current.left;
			}
			height --;
		}
		
		return count;
	}
	
	private static int maxHeight(TreeNode node ) {
		TreeNode current = node;
		int count = 0;
		while (current != null) {
			count ++;
			current = current.left;
		}
		return count;
	}
	
}
