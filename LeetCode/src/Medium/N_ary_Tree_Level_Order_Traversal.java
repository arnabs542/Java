package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/n-ary-tree-level-order-traversal/
/*
 *  This is a BFS, level order traversal problem of tree.
 *  
 *  For BFS, we use queue for the job, recording the size of the layer in each iteration so we don't interfere
 *  between levels.
 */

class Node {
	public int val;
	public List<Node> children;
	public Node() {}
	public Node(int _val) { val = _val; }
	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}

public class N_ary_Tree_Level_Order_Traversal {
	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		
		Queue<Node> bfs = new ArrayDeque<>();
		bfs.add(root);
		
		while (!bfs.isEmpty() ) {
			List<Integer> level = new ArrayList<>(bfs.size() );
			for (int i = bfs.size(); i > 0; --i) {
				Node poll = bfs.poll();
				level.add(poll.val);
				for (Node child: poll.children)
					bfs.offer(child);
			}
			res.add(level);
		}
		return res;
	}
}
