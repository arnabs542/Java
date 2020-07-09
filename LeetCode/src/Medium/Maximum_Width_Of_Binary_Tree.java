package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/maximum-width-of-binary-tree/

/*
 * 	This is a BFS/ DFS problem, where exploring every node should almost be necessary as binary trees are unpredictable and we cannot take a greedy approach on it.
 * 	
 * 	One way to solve using BFS is using a doubly linked list queue. We will get to know the width of the level based on the size of queue at each level.
 * 	The leading and trailing null must be removed prior to determining the width of the level (or size of queue). Then, for each node that we see, if it is not null,
 * 	then add it into the queue regardless if it is null or not. If it indeed is null, we will add 2 nulls into the queue as the null is between two nodes, therefore it
 * 	shall be included as width as well.
 * 
 * 	-------------------------------------------------------------------------------------------------------------
 * 
 * 	In DFS, we will still explore every node, but we need to keep track of an array of leftmost node's position and rightmost node for each level.
 * 	We know that the left child of a node has indexing of 2n while right child is of 2n + 1. Using this property, we are able to record the indices and from the
 * 	indices, get to know the width of each level.
 * 
 * 	Instead of using the normal indexing of nodes, where it is indexed like 1,2,3,4,5,6..., we can choose a indexing method where on each level, the leftmost node
 * 	shall index from 1. The formula is a lot similar like the normal one, which is 
 *		Right node: 2n
 *		Left node: 2n - 1
 *
 *	This way the nodes will be indexed as [1] [1,2] [1,2,3,4] [1,2,3,4,5,6,7,8]...
 *	This is to prevent overflow of integer for big binary trees because on every level the max integer goes up expontially by power of 2
 */

public class Maximum_Width_Of_Binary_Tree {
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
	
	
//	public int widthOfBinaryTree(TreeNode root) {
//		LinkedList<TreeNode> queue = new LinkedList<>();
//		queue.offer(root);
//		
//		int maxwid = 0;
//		
//		while (!queue.isEmpty() ) {
//			while (!queue.isEmpty() && queue.peekFirst() == null) {
//				queue.pollFirst();
//			}
//			while (!queue.isEmpty() && queue.peekLast() == null) {
//				queue.pollLast();
//			}
//			int width = queue.size();
//			maxwid = Math.max(width, maxwid);
//			
//			for (int i = 0; i < width; i ++ ) {
//				TreeNode poll = queue.poll();
//				if (poll != null) {
//					queue.offer(poll.left );
//					queue.offer(poll.right);
//				}
//				else {
//					queue.offer(null);
//					queue.offer(null);
//				}
//			}
//		}
//		
//		return maxwid;
//	}
	
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		dfs(root, 1, 1, left, right);
		
		int maxwid = 0;
		for (int i = 0; i < left.size(); i ++ ) {
			maxwid = Math.max(maxwid, right.get(i) - left.get(i) + 1);
		}
		return maxwid;	
	}
	
	private void dfs(TreeNode node, int relpos, int lvl, List<Integer> left, List<Integer> right) {
		if (left.size() < lvl) {
			left.add(relpos);
			right.add(relpos);
		}
		else {
			left.set(lvl - 1, Math.min(relpos, left.get(lvl - 1) ) );
			right.set(lvl - 1, Math.max(relpos, right.get(lvl - 1) ) );
		}
		
//		if (right.size() < lvl)
//			right.add(relpos);
//		else
//			right.set(lvl, Math.max(relpos, right.get(lvl) ) );
		
		if (node.left != null) {
			dfs (node.left, relpos * 2, lvl + 1, left, right);
		}
		if (node.right != null) {
			dfs(node.right, (relpos * 2) - 1, lvl + 1, left, right);
		}
	}
	
}
