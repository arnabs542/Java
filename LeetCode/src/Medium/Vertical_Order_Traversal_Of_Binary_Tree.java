package Medium;

import java.util.List;
import java.util.Map;

import Binary_Tree.TreeNode;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.PriorityQueue;

//https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

/*
 * 	The question asks to find the vertical order traversal of binary tree.
 * 	We generalize this question as follows:
 * 		>	The root starts at coordinate (0,0)
 * 		>	For the left child and right child, the y position will go down by 1
 * 		>	For the left child, x position goes -1; For the right child, x position goes +1
 *
 *	Therefore, we will need to generate a list such that the list contains a series of list, where the first list represents all the
 *	'points' at lowest vertical line of x.
 *	Among the vertical line lists (x), It should have another list which contains the value of nodes in certain sorted order:
 *		>	The nodes shall be from highest y value to lowest y value (Descending order of y).
 *		>	If there is collision (Same x,y position), then sort by node value in ascending order
 *
 *	To have a certain sorted order, we could apply TreeMap. To handle collision cases, we apply PriorityQueue (Heaps)
 *	We will have a TreeMap which key represents x (vertical line). It maps to another TreeMap which key is y (vertical position).
 *	The y Treemap maps to a PriorityQueue which handles collision cases (Which already have it sorted in order)
 *
 *	--------------------------------------------------------------------------------------------------------
 *
 *	Another method to use only just a PriorityQueue (Heap) is creating object instances out of exisiting nodes.
 *	For each node, we will create an instance of an object which contains the information:
 *		>	X Position
 *		>	Y Position
 *		>	Value
 *
 *	When put into the PriorityQueue, our custom comparator will sort it based on following precedences:
 *		>	Comparing X positions in Ascending Order	(Priority)
 *		>	Comparing Y positions in Descending Order	(Second Priority - Same X position)
 *		>	Comparing node values						(Last Priority - Same X and Y; Collision)
 *
 *	THen when output into a multi-leveled list, we simply pop each one. If the X position had increased, we just add a new list
 *	into the result list and continue
 *
 */		


public class Vertical_Order_Traversal_Of_Binary_Tree {
	
	public List<List<Integer>> verticalTraversal(TreeNode root) {
        
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer> > > xy = new TreeMap<>();
		
		recursion(root, xy, 0, 0);
		
		List<List<Integer>> res = new LinkedList<>();
		
		for (int x: xy.keySet() ) {
			List<Integer> ylist =  new LinkedList<>();
			TreeMap<Integer, PriorityQueue<Integer>> ymap = xy.get(x);
			
			for (int y: ymap.descendingKeySet() ) {
				PriorityQueue<Integer> queue = ymap.get(y);
				
				while (!queue.isEmpty() )
					ylist.add( queue.poll() );
			}
			res.add( ylist);
		}
		
		return res;
		
    }
	
	private void recursion(TreeNode node, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> xy, int x, int y) {
		if (node == null) return;
		
		xy.putIfAbsent(x, new TreeMap<Integer, PriorityQueue<Integer>>() );
		
		TreeMap<Integer, PriorityQueue<Integer>> ymap = xy.get(x);
		ymap.putIfAbsent(y, new PriorityQueue<Integer>() );
		
		PriorityQueue<Integer> collision = ymap.get(y);
		collision.add(node.val);
		
		recursion(node.left, xy, x-1, y-1);
		recursion(node.right, xy, x+1, y-1);
	}
}
