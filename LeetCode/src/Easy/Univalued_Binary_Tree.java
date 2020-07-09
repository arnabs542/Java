package Easy;

//https://leetcode.com/problems/univalued-binary-tree/

/*
 * Simple DFS / BFS problem
 */

import java.util.LinkedList;
import java.util.Queue;



public class Univalued_Binary_Tree {
	
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
	
	public boolean isUnivalTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		int unival = root.val;
		queue.add(root);
		
		while(!queue.isEmpty() ) {
			TreeNode node = queue.poll();
			if (node == null) continue;
			
			if (node.val != unival) return false;
			queue.add(node.left);
			queue.add(node.right);
		}
		return true;
    }
	
	public boolean isUnivalTreeDFS(TreeNode root) {
		int val = root.val;
		return dfs(root.left, val) && dfs(root.right, val);
    }
	public boolean dfs(TreeNode node, int val) {
		if (node == null) return true;
		
		if (node.val != val) return false;
		else {
			return dfs(node.left, val) && dfs(node.right, val);
		}
	}
}
