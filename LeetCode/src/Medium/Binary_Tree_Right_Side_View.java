package Medium;
import Binary_Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-right-side-view/
/*
 * 	This is a recursion - DFS/ BFS Level order traversal problem.
 * 
 * 	First let's talk about recursion. How can we solve in recursion?
 * 	In normal preorder traversal, the ordering is always root -> left -> right. Therefore the leftmost
 * 	node is always explored first.
 * 	If we do the reverse, then the rightmost node will always be explored first before its left part!
 * 
 * 	However, consider this case:
 * 
 * 			( )
 * 			/ \
 * 		  ( )  ( )
 * 		  /
 * 		( )
 * 
 * 	How do we add the last node at level 2? (Root is lvl 0). 
 * 	In the recursion, we are passing around the result list. Also, the recursion has a property: if the node
 * 	explored is the very first node to be seen in the current level, then it must be the rightmost one!
 * 	Therefore, check the list's size. It is equivalent to the node levels added. If current level haven't been occupied
 * 	yet, do add it!
 * 
 * 	----------------------------------------------------------------------------------------------
 * 
 * 	We could also perform a level order traversal (BFS). However, do it reversely so the rightmost node is always
 * 	at the front of the queue in each level.
 * 	
 */

public class Binary_Tree_Right_Side_View {
	
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		recurse(root, 0, res);
		return res;
	}
	private void recurse(TreeNode node, int level, List<Integer> res) {
		if (node == null) return;
		
		if (level == res.size() )
			res.add( node.val );
		
		recurse(node.right, level+1, res);
		recurse(node.left, level+1, res);
	}
	
	
	
	public List<Integer> rightSideView2(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		Queue<TreeNode> level = new ArrayDeque<>();
		if (root != null) level.offer(root);
		
		while(!level.isEmpty() ) {
			int size = level.size();
			res.add( level.peek().val );
			
			while (size-- > 0) {
				TreeNode node = level.poll();
				if (node.right != null) level.offer( node.right );
				if (node.left != null) level.offer( node.left );
			}
		}
		return res;
	}
	
}
