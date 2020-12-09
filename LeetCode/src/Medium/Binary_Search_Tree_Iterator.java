package Medium;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/binary-search-tree-iterator/

/*
 * 	This is a Binary Search Tree Inorder Traversal Problem, with possible solution using Stack DS
 * 
 * 	
 * 	The most 'brute force' way yet easiest one is to immediately flatten the BST once the iterator is constructed.
 * 	FLatten can be done by simple recursion of inorder traversal (left - root - right) and storing it in a
 * 	possibly FIFO DS like Queue
 * 	This way of implementation ensures O(1) retrieval and checking, but the initialization does take O(N) and also
 * 	O(N) space as long as lifecycle of iterator haven't finish.
 * 
 * 	----------------------------------------------------------------
 * 
 * 	A solution worth mentioned is to traverse the BST each time it is queried. Keeping the last node as record,
 * 	we can find the next least larger node in O(log N) time, assuming BST is balanced
 * 
 * 	This requires 2 pointers. One pointing to current node to explore, and the one pointing to the one previously
 * 	explored node.
 * 		When the current node is larger than the last recorded / returned node,
 * 			>	We have to search left side for possible smaller node but larger than last recorded.
 * 			>	So, record in prev, set currrent to left subtree.
 * 		When the current node is smaller than or equal to last recorded / returned node,
 * 			>	This node is IMPOSSIBLE to be returned since it is smaller.
 * 			>	However, we still need to search right subtree for possible solution
 * 
 * 	At the end, the current pointer will be null and previous is the solution.
 * 	If the node doesn't exist, the current pointer, which is initialized to last recorded, should remain same,
 * 	and from that information we know there is no more next node.
 * 
 * 	It can be optimized to automatically search for next node during retrieval function, so the checking function
 * 	can be done in O(1) time
 * 
 * 	Approach is O(log N) for retrieval, O(1) checking, and O(1) space.
 * 
 * 	------------------------------------------------------------------------------------
 * 
 * 	We can simulate the recursion approach, but iteratively, and ability to pause the recursion.
 * 
 * 	Idea is this:
 * 		>	Assume we know already the next node to pop. What will come after the node to pop?
 * 		>	If it contains right subtree, then it IS the right subtree's leftmost node.
 * 		>	Otherwise, it must be parent node.
 * 
 * 	Therefore, we will create a Data Structure that stores only the LEFT NODE of the current subtree. Since
 * 	from the root to the leftmost node, we would want to pop the leftmost node first, so we need LIFO - Stack.
 * 
 * 		>	With root node, push the root node and go to its left node. Repeat this while node is not null
 * 		
 * 		>	When we want to pop, check the node has a rightsubtree or not. 
 * 			-	Yes: Treat right node as root node and keep pushing the left node of it while node is not null
 * 					 like above step. So next time we pop is the right subtree's leftmost node and so on
 * 			-	No:	The stack top will now be parent node of current popped node. Next time it can be safely popped
 * 					too
 * 
 * 	This algorithm runs Averagely O(1) (at worst, O(h) node is traversed at retrieval)
 *  time and O(h) space, h is height of tree.
 */

public class Binary_Search_Tree_Iterator {

	
	//	Optimized Stack solution
	class BSTIterator {
		Deque<TreeNode> stack;
		public BSTIterator(TreeNode root) {
			stack = new ArrayDeque<>();
			
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
		}
		
		public int next() {
			TreeNode node = stack.pop();
			TreeNode right_subtree = node.right;
			while (right_subtree != null) {
				stack.push(right_subtree);
				right_subtree = right_subtree.left;
			}
			return node.val;
		}
		
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	}
	
	
	//	Flatten solution, O(N) space but O(1) guaranteed retrieval
	class BSTIterator2 {
		Deque<TreeNode> queue;
		public BSTIterator2(TreeNode root) {
			queue = new LinkedList<>();
			recurse(root);
		}
		private void recurse(TreeNode node) {
			if (node == null) return;
			
			recurse(node.left);
			queue.offer(node);
			recurse(node.right);
		}
		
		public int next() {
			return queue.poll().val;
		}
		
		public boolean hasNext() {
			return !queue.isEmpty();
		}
	}
	
	
	//	O(N log N) time solution - Traverse each query
	class BSTIterator3 {
		int val;
		TreeNode root;
		int next;
		
		public BSTIterator3(TreeNode root) {
			val = Integer.MIN_VALUE; 
			this.root = root;
			next = prepareNext();
		}
		
		private int prepareNext() {
			int prev = val;
			TreeNode curr = root;
			
			while (curr != null) {
				if (curr.val > val) {
					prev = curr.val;
					curr = curr.left;
				} else 
					curr = curr.right;
			}
			return prev;
		}
		
		//	Find the next value larger than val
		public int next() {
			val = next;
			next = prepareNext();
			return val;
		}
		
		public boolean hasNext() {
			return !(val == next);
		}
	}
	
}
