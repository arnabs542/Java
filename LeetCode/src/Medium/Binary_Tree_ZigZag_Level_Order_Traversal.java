package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

/*
 * 	A BFS/ DFS problem
 * 	At first level we travel from left to right, then second from right to left, then third from left to right again... alternating
 * 
 * 	For BFS we do it layer by layer, so it's easy to identify whether to reverse or not, since we only reach the new layer after the last
 * 	layer. Make a toggling boolean to alternate value for each layer. Alternatively we could also just deduct if the layer is reversed or not
 * 	based on layer level, which is obtained from the result list's size (If it is odd layer, then no reverse, otherwise reverse)
 * 
 * 	To know how many nodes belong to the next layer, upon reaching the new layer we will just record the size of the queue. Before even
 * 	entering a new layer and adding the node's left and right, all the nodes in the queue are of the same layer. Therefore just keep track
 * 	of the queue size and poll that many times.
 * 	A linked list would be preferred when inserting values. If it is reverse, then add at index 0, else add at last index.
 * 
 * 	------------------------------------------------------------------------------------------------------------
 * 
 * 	For DFS, it's almost the same but done recursively.
 * 	We will pass in informations: Node, level, reverse, and the result list itself.
 * 
 * 	If the level exceeds the result list size, then we would add a new list into it since this is the first time to travel until this
 * 	layer deep.
 * 	Depending on the reverse boolean, insert at the head or tail of linked list.
 * 	
 * 	Then recurse on the node's left then right. Pass in the negation of the reverse boolean and level + 1.
 * 
 */

public class Binary_Tree_ZigZag_Level_Order_Traversal {
	
	public class TreeNode {
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
	
//	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//		
//		if (root == null) return new LinkedList<>();
//		
//		List<List<Integer>> res = new LinkedList<>();
//		Queue<TreeNode> bfs = new LinkedList<>();
//		bfs.offer(root);
//		
//		boolean rev = false;
//		
//		while ( !bfs.isEmpty() ) {
//			List<Integer> li = new LinkedList<>();
//			int size = bfs.size();
//			
//			for (int i = 0; i < size; i ++ ) {
//				TreeNode polled = bfs.poll();
//				
//				if (rev) li.add(0, polled.val);
//				else li.add(polled.val);
//				
//				if (polled.left != null) bfs.offer(polled.left);
//				if (polled.right != null) bfs.offer(polled.right);
//			}
//			
//			res.add(li);
//			rev = !rev;
//		}
//		
//		return res;
//    }
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null) return new LinkedList<>();
		
		List<List<Integer>> res = new ArrayList<>();
		
		recurse(root, 1, false, res);
		
		return res;
	}
	private static void recurse(TreeNode node, int level, boolean reverse, List<List<Integer>> res) {
		if (node == null) return;
		
		if (res.size() < level) res.add( new LinkedList<>() );
		List<Integer> curr = res.get(level - 1);
		
		if (reverse) curr.add(0, node.val);
		else curr.add(node.val);
		
		recurse(node.left, level + 1, !reverse, res);
		recurse(node.right, level + 1, !reverse, res);
	}

}
