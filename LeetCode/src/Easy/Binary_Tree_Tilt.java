package Easy;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/binary-tree-tilt/

/*
 * 	This is a Binary Tree Depth First Search - Postorder traversal problem.
 * 
 * 	To obtain the 'tilt' of a parent node, we have to first
 * 		>	Find the sum of nodes in left subtree (Left)
 * 		>	Find the sum of nodes in right subtree (Right)
 * 		>	Calculate the absolute difference in the sum of left and right subtree (Parent)
 * 		>	Return the sum to upper level (Left + Right + Parent)
 */

public class Binary_Tree_Tilt {
	private int res = 0;
	
	public int findTilt(TreeNode root) {
		recurse(root);
		return res;
    }
	
	private int recurse(TreeNode node) {
		if (node == null) return 0;
		int left = recurse(node.left);
		int right = recurse(node.right);
		
		res += Math.abs( left - right);
		return node.val + left + right;
	}
}
