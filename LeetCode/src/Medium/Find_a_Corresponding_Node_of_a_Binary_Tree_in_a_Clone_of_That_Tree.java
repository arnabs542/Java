package Medium;
import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
/*
 * This is a Tree, DFS or BFS problem.
 * 
 * Since values are unique, we can just perform regular search in the cloned tree, returning ASAP when found the
 * cloned node value is equal to target node's value.
 * 
 * --------------------------------------------------
 * 
 * If the values can be repeated, now the node value is meaningless. We have to compare using memory references, whether
 * the original node explored points to same memory location with target node or not. If yes, then only return the
 * corresponding cloned node.
 * 
 *	
 * With this idea, we have to traverse 2 trees in parallel to each other.
 * 
 * O(1) space is possible with Morris Traversal Algorithm, but it is harder to implement, and modifies the tree structure.
 */

public class Find_a_Corresponding_Node_of_a_Binary_Tree_in_a_Clone_of_That_Tree {
	
	//	Hacky solution. Since values is unique, directly searching in cloned node works
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null) return null;
        if (cloned.val == target.val ) return cloned;		//Found matching node value. Return
        
        final TreeNode left = getTargetCopy(null, cloned.left, target);		//Attempt to search recursively in left subtree
        return left == null? getTargetCopy(null, cloned.right, target): left;		//Return the left subtree result if found, otherwise recurse the right subtree and return its result
    }
	
	
	//	Real solution - Iterate in corresponding pairs DFS recursion
	public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;
        if ( original == target ) return cloned;
        
        final TreeNode left = getTargetCopy2(original.left, cloned.left, target);
        return left == null? getTargetCopy2(original.right, cloned.right, target): left;
    }
	
	
	//	Real solution - Iterative using Stack
	public final TreeNode getTargetCopy3(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Deque<TreeNode> ori = new ArrayDeque<>();
        Deque<TreeNode> clones = new ArrayDeque<>();
        ori.push(original);
        clones.push(cloned);
        
        while (!ori.isEmpty() ) {
        	final TreeNode o = ori.pop();
        	final TreeNode c = clones.pop();
        	
        	if (o == target) return c;
        	
        	if (o.left != null) {
        		ori.push( o.left);
        		clones.push( c.left);
        	}
        	if (o.right != null) {
        		ori.push( o.right );
        		clones.push( c.right);
        	}
        }
        return null;
    }
	
	
}
