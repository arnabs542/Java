package Easy;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/same-tree/

/*
 * 	This is a DFS/BFS problem, which requires comparing the two binary trees node by node. Therefore it has to travel all the nodes
 * 	of the binary tree.
 * 	
 * 	For each pair of nodes, if BOTH the nodes are null, then return true (Since both are similar). Otherwise if ONLY one of them is null,
 * 	then it is false. If the nodes val does not match, it will be false also. Now, Since values are matching, we need to find the rest which
 * 	is both the node's left and right.
 */

public class Same_Tree {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left= left;
			this.right = right;
		}
	}
	
//	public boolean isSameTree(TreeNode p, TreeNode q) {
//		if (p == null && q == null) return true;
//		if (p == null || q == null || p.val != q.val )
//			return false;
//		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
//	}
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		q1.offer(p);
		q2.offer(q);
		
		while (!q1.isEmpty() && !q2.isEmpty() ) {
			TreeNode poll1 = q1.poll();
			TreeNode poll2 = q2.poll();
			
			if (poll1 == null && poll2 == null) 
				continue;
			if (poll1 == null || poll2 == null || poll1.val != poll2.val)
				return false;
			
			q1.add(poll1.left);
			q1.add(poll1.right);
			q2.add(poll2.left);
			q2.add(poll2.right);
		}
		
		return true;
	}
	
}








