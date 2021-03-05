package Easy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/average-of-levels-in-binary-tree/
/*
 * 	This is a Tree problem, easily solved using level-order traversal.
 * 
 * 	Using a queue, we can easily perform operations in level-wise order. Each iteration, the queue will contain
 * 	all elements in one level. Sum them up, average them, and add to results list
 * 
 * 	------------------------------------------------------
 * 
 * 	However, it is entirely possible using depth first search. In recursion (or Stack), we need to keep track of 
 * 	what levels we are in, and sum them all into one result array as well as update the level element frequency.
 *  At the end of DFS, convert them into average, and return
 */

public class Average_of_Levels_in_Binary_Tree {

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			double avg = 0;
			for (int i = 0; i < size; ++i) {
				TreeNode polled = queue.poll();
				avg += polled.val;
				
				if (polled.left != null) queue.add(polled.left);
				if (polled.right != null) queue.add(polled.right);
			}
			res.add( avg / size );
		}
		return res;
	}
	
}
