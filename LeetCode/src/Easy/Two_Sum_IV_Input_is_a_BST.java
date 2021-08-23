package Easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
/*
 * This is a BST problem.
 * 
 * Firstly, we can apply the original solution to two sum problem - using a HashSet to check presence of a
 * value.
 * Perform DFS on the tree. For each node, check if k - node.val existed in Set or not. If yes, return true.
 * if not, record node value in Set and continue.
 * 
 * -------------------------
 * 
 * Another solution with better space complexity (O(H)) where H is height of tree, is to utilize BST property-
 * ability to find value in O(Log N) time. FOr each node, find if the node with k - node.val is present in the
 * tree or not.
 * 	This will use O(N log N) time and O(log N) stack space, assuming tree is balanced.
 */

public class Two_Sum_IV_Input_is_a_BST {
	
	// DFS with a HashSet solution
	public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, new HashSet<>() );
    }
	
	// Performs in order traversal on tree to see if two numbers can form to k
	private boolean dfs(TreeNode node, int k, Set<Integer> prev) {
		if (node == null) return false;

		// Check if using this node's value, can we form k?
		if (prev.contains(k - node.val)) return true; 
		// Record current node
		prev.add(node.val);
		
		//Search left & Right subtree
		return dfs(node.left, k, prev) || dfs(node.right, k, prev);
	}
	
	
	
	
	// O(log N) - Assume optimized tree - stack space solution - Nested DFS
	public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
		Deque<TreeNode> dfs =  new ArrayDeque<>();
		dfs.add(root);
		
		while (!dfs.isEmpty()) {
			TreeNode node = dfs.pop();
			if (find(root, k-node.val, node)) return true;
			
			if (node.right != null) dfs.push(node.right);
			if (node.left != null) dfs.push(node.left);
		}
		return false;
    }
	
	private boolean find(TreeNode node, int t, TreeNode ignored) {
		if (node == null) return false;
		return node.val == t && node != ignored || find( (node.val < t)? node.right: node.left, t, ignored);
	}
}
