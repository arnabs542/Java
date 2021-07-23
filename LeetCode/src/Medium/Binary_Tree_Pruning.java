package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/binary-tree-pruning/
/*
 * 	This is a easy-medium Binary Tree, DFS (Postorder Traversal) problem.
 * 
 * 	Given a node and asked to prune it, we need to do the same thing to left and right subtree.
 * 	At the end of pruning both subtree, if they ended up being completely pruned (null), and current node is 0 as well,
 * 	prune current node.
 * 
 * 	Base case is when node is null, then we return null.
 * 	Otherwise, return null only when after pruning both subtrees, both left and right are null, and node val is 0
 */


public class Binary_Tree_Pruning {
	public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode pruneLeft = pruneTree(root.left);
        TreeNode pruneRight = pruneTree(root.right);
        root.left = pruneLeft;
        root.right = pruneRight;
        if (pruneLeft == null && pruneRight == null && root.val == 0)
        	return null;
        return root;
    }
}
