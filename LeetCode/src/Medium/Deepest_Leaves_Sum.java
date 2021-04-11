package Medium;
import java.util.ArrayDeque;
import java.util.Queue;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/deepest-leaves-sum/
/*
 * 	This is a BFS Level order traversal / DFS problem
 * 
 * 	For deepest level in a Tree, it is best performed using a level order traversal using Queue. On every level,
 * 	we pop out the nodes at the layer, and sum their values.
 * 	If after popping all the nodes and there's no nodes left in the Queue, it is the final layer. Return immediately.
 * 
 * 	-------------------------------------
 * 
 * 	For DFS however, we need to record 2 information: deepestLayer and the sum of that deepest layer so far.
 * 	We perform preorder traversal on each node, and in the node, check its layer with deepest layer. 
 * 
 * 	>	If currLayer = deepestLayer, add the node value to the sum.
 * 	>	If currLayer > deepestLayer, reset sum to 0. Don't forget to add the current node value too.
 * 	
 */

public class Deepest_Leaves_Sum {
	
	//	Level order BFS traversal solution
	public int deepestLeavesSum(TreeNode root) {
		if (root == null) return 0;
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		
		while (!queue.isEmpty() ) {
			int size = queue.size();
			int sum = 0;
			
			while (size-- > 0) {
				TreeNode node = queue.poll();
				sum += node.val;
				if (node.left != null) queue.offer(node.left);
				if (node.right!= null) queue.offer(node.right);
			}
			
			if (queue.isEmpty()) return sum;
		}
		return 0;
    }
	
	
	//	DFS solution. An mutable array is used for state recording {maxDepth, sum}
	public int deepestLeavesSum2(TreeNode root) {
		int[] record = new int[2];
		dfs(root, 1, record);
		return record[1];
	}
	private void dfs(TreeNode root, int depth, int[] record) {
		if (root == null) return;
		if (depth > record[0]) {
			record[0] = depth;
			record[1] = 0;
		}
		if (depth == record[0]) record[1] += root.val;
		
		dfs(root.left, depth+1, record);
		dfs(root.right, depth+1, record);
	}
	
}
