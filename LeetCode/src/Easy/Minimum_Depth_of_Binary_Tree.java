package Easy;

//https://leetcode.com/problems/minimum-depth-of-binary-tree/

import java.util.Deque;
import java.util.LinkedList;

import Binary_Tree.TreeNode;

/* ====================================================================================================
This is mainly a BFS problem.

Sure we can surely solve this problem by DFS. However with DFS, we have to traverse through all the nodes in order to ensure
the minimum depth is found. Imagine a tree where the left subtree has a depth of 500 and right subtree with only depth of
1. In this case DFS always has to traverse through all the nodes to reach the solution.

Instead, we shall perform BFS algorithm instead. We will be travelling level by level, and once on a level a node is found to
be leaf node, immediately return the result.

*/

public class Minimum_Depth_of_Binary_Tree {
	
	int minDepth(TreeNode root) {
		if (root == null) return 0;
		
		int res = 0;
		Deque<TreeNode> queue = new LinkedList<>();
		
		while (!queue.isEmpty() ) {
			int size = queue.size();
			res ++;
			while (size-- > 0) {
				TreeNode poll = queue.poll();
				if (poll.left != null) queue.push( poll.left );
				if (poll.right != null) queue.push( poll.right );
				if (poll.left == null && poll.right == null) return res;
			}
		}
		return -1;
	}
}
