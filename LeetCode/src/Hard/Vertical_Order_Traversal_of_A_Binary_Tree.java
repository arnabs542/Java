package Hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Binary_Tree.TreeNode;
import javafx.util.Pair;

//https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
/*	
 * 	Essentially, we have to return a list of lists, where each list represents a single
 *	X coordinate, starting from the smallest / leftmost X coordinate. 
 * 
 * 	In each of the list again, consists of the nodes sorted by their y coordinate. If they
 * 	has the same y coordinate however, it has to be sorted by their node value.
 * 
 * 	One solution is to have a Map of x-coordinate to the List. When traversing the tree,
 * 	we must keep track of the x and y corrdinates of the node.
 * 
 * 	Two things must keep sorted:
 *	> 	X coordinates must be sorted. In my solution I use TreeMap for that
 * 	>	Inside each list, Y coordinates must be sorted by first Y coordinate, then node values if they are same	
 *		I only do this in Java's stream
 */

public class Vertical_Order_Traversal_of_A_Binary_Tree {
	
	//	This solution utilizes BFS, but DFS with recursion can also be used. In that case, either u pass the
	//	TreeMap all around, or use a global Treemap.
	public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return null;
		
		Queue< Pair<TreeNode, int[] > > level = new ArrayDeque<>();
		TreeMap< Integer, List< Pair<TreeNode, int[] > > > map = new TreeMap<>();
		
		level.offer( new Pair<>(root, new int[] {0,0} ) );
		
		while (!level.isEmpty() ) {
			Pair<TreeNode, int[] > pop = level.poll();
			TreeNode node = pop.getKey();
			int[] pos = pop.getValue();
			
			map.putIfAbsent( pos[0] , new ArrayList<>() );
			map.get( pos[0] ).add( pop );
			
			if (node.left != null)
				level.offer( new Pair<>(node.left, new int[] {pos[0] - 1, pos[1] + 1} ) );
			if (node.right != null)
				level.offer( new Pair<>(node.right, new int[] {pos[0] + 1, pos[1] + 1} ) );
		}
		
		List<List<Integer>> res = map.values().
				stream().
				map( x -> x.stream().sorted( (a,b)-> {
					if (a.getValue()[1] == b.getValue()[1] ) return a.getKey().val - b.getKey().val;
					return a.getValue()[1] - b.getValue()[1];
				}).map( y -> y.getKey().val ).collect( Collectors.toList() ) ).
				collect( Collectors.toList() );
		
		return res;
    }
	
}
