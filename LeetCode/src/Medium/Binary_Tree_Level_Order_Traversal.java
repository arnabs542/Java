package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/binary-tree-level-order-traversal/
/*
 * 	A very standard breadth first search problem.
 * 
 * 	Using a Queue, we can traverse the binary tree layer by layer, fairly easily.
 * 	That's all I have to say actually
 */

public class Binary_Tree_Level_Order_Traversal {
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		
		while (!queue.isEmpty() ) {
			int width = queue.size();
			List<Integer> layer = new ArrayList<>();
			
			while (width-- > 0) {
				TreeNode node = queue.poll();
				layer.add(node.val);
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
			}
			res.add(layer);
		}
		return res;
    }
	
}
