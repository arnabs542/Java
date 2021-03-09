package Medium;
import java.util.ArrayDeque;
import java.util.Queue;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/add-one-row-to-tree/
/*
 * 	This is a Tree DFS / BFS problem
 * 
 * 	For BFS, we will use level order traversal using Queues. Traverse layer by layer, until we reached
 * 	at the layer d-1. This means we have all the nodes at layer d-1, and we are going to insert new nodes
 * 	at layer d, right below current layer of d-1.
 * 
 * 	For each node at d-1, first keep reference to their childrens. Then, their left and right child will be
 * 	assigned a new node, then, connect the old childrens to the new childrens. That's it.
 * 
 * 	---------------------------------------------------------------------
 * 
 * 	For DFS, use counting down of layer. Once the depth had reached 1, we know that this node at this recursion
 * 	call is a node at d-1. So keep reference to the children, insert new node as direct children, connect the
 * 	old children to the new nodes.
 * 
 * 	Note that special cases is when d=1, which the root of the tree has to be changed. Take care of the special
 * 	case separately.
 */

public class Add_One_Row_To_Tree {
	
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		//	Special case - New root has to be created
		if (d == 1) {
			TreeNode newroot = new TreeNode(v);
			newroot.left = root;
			return newroot;
		}
		
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        //	Traverse layers to reach layer d-1
        for (int level = 1; level < d-1; ++level) {
        	for (int size = queue.size(); size > 0; --size) {
        		TreeNode poll = queue.poll();
        		if (poll.left != null) queue.offer(poll.left);
        		if (poll.right != null) queue.offer(poll.right);
        	}
        }
        
        //	We have the nodes to d-1. For each node, insert new node, and connect descendants to the new node
        while (!queue.isEmpty() ) {
        	TreeNode poll = queue.poll();
        	TreeNode oldleft = poll.left;
        	TreeNode oldright = poll.right;
        	poll.left = new TreeNode(v);
        	poll.right = new TreeNode(v);
        	poll.left.left = oldleft;
        	poll.right.right = oldright;
        }
        return root;
    }
	
	
	//	DFS recursion method, to countdown depth. If it is 1, create a new node.
	public TreeNode addOneRow2(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode newnode = new TreeNode(v);
			newnode.left = root;
			return newnode;
		}
		recurse(root, v, d-1);
		return root;
	}
	public void recurse(TreeNode root, int v, int d) {
		if (root == null) return;
		
		if (d == 1) {
			TreeNode orileft = root.left;
			TreeNode oriright = root.right;
			root.left = new TreeNode(v);
			root.right = new TreeNode(v);
			root.left.left = orileft;
			root.right.right = oriright;
		} else {
			recurse(root.left, v, d-1);
			recurse(root.right, v, d-1);
		}
	}
	
}
