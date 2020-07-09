package Easy;

//https://leetcode.com/problems/cousins-in-binary-tree/
//Also don't know how this works. A Depth first search or Breadth First search question
//Note that this is not a BINARY SEARCH TREE, Just a simple tree (unordered)


//class for a TreeNode
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {
	}
	TreeNode(int val) {
		this.val = val;
	}
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
//end of class TreeNode


public class Cousins_in_Binary_Tree {
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
	
}
