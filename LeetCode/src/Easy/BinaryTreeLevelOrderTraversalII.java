package Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

/*
 * 	The question is BFS/ DFS question which actually needs to have the results reversed by level, a.k.a from bottom to top.
 * 	For DFS solution, simply call upon a recursive function which takes the level argument as well as the final list which will be returned.
 * 	If the current level had yet to be added to the final list, which can be checked by using the size property, simply add a new list into it
 * 	Lastly after the DFS is done, reverse the list and return the result
 * 
 * 	For BFS solution, we have to know that we don't have to keep track of the level property. After finish searching for a level, the nodes left in the queue is exactly
 * 	all the next level's nodes, therefore for every level we should just iterate queue.size times and upon finishing the search of current level, append it to the result
 * 	list at the head (LinkedList performs better). This also solves the follow up question as for if the tree was streamed and we are unable to use the reverse method.
 */

public class BinaryTreeLevelOrderTraversalII {

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
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		
		recurse(root, 0, result);
		Collections.reverse(result);
		return result;
	}
	private void recurse(TreeNode node, int level, List<List<Integer>> result) {
		if (node == null) return;
		
		if (result.size() - 1 < level)
			result.add(new ArrayList<>() );
		result.get(level).add(node.val);
		
		recurse(node.left, level + 1, result);
		recurse(node.right, level + 1, result);
	}
	
	/* -------
	 * 	BFS
	 ---------*/
	
	public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		if (root != null) queue.offer(root);
		
		while (!queue.isEmpty() ) {
			LinkedList<Integer> currLvl = new LinkedList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++ ) {
				TreeNode poll = queue.poll();
				currLvl.add(poll.val);
				if (poll.left != null) queue.offer(poll.left);
				if (poll.right != null) queue.offer(poll.right);
			}
			result.add(0, currLvl);
		}
		return result;
	}
	
	public static void main(String[]args) {
	}
}
