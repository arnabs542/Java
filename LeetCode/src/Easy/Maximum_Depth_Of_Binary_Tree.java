package Easy;

import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;
import javafx.util.Pair;

//https://leetcode.com/problems/maximum-depth-of-binary-tree/

/*
 * 	This is a BFS / DFS Tree Traversal problem. 
 * 	
 * 	It is most easily solved using recursion in 2 lines.
 * 	For BFS, we just count the possible levels there is. For DFS, we just record the level that each node is at, and update the
 * 	maximum value each time.
 */


public class Maximum_Depth_Of_Binary_Tree {
	
	public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max( maxDepth(root.left), maxDepth(root.right) );
    }
	
	
	public int maxDepthBFS(TreeNode root) {
		if (root == null) return 0;
		
		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.offer(root);
			
		int res = 0;
		
		while (!deque.isEmpty() ) {
			res ++;
			int size = deque.size();
			while (size-- > 0) {
				TreeNode node = deque.poll();
				if (node.left != null) deque.offer(node.left);
				if (node.right != null) deque.offer(node.right);
			}
		}
		return res;
	}
	
	
	public int maxDepthDFS(TreeNode root) {
		if (root == null) return 0;
		
		Deque<Pair<TreeNode, Integer>> deque = new ArrayDeque<>();
		deque.push( new Pair<>(root, 1) );
		
		int res = 0;
		
		while (!deque.isEmpty() ) {
			Pair<TreeNode, Integer> pair = deque.pop();
			res = Math.max(res, pair.getValue() );
			if (pair.getKey().left != null) 
				deque.push( new Pair<>(pair.getKey().left, pair.getValue() + 1) );
			if (pair.getKey().right != null) 
				deque.push( new Pair<>(pair.getKey().right, pair.getValue() + 1) );
		}
		return res;
	}
}
