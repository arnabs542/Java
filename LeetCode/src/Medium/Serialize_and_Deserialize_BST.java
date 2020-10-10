package Medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//https://leetcode.com/problems/serialize-and-deserialize-bst/

/*
 *   This is a tree problem.
 *   We basically have to 'convert' a real, object binary search tree into a string, and vice versa.
 *   How are we going to do that?
 *   
 *   ===========================================================================================
 *   
 *   When approaching this problem, we see that it is a binary search tree, which recursion is more handy approach.
 *   
 *   When we received a string, would we like it in preorder, inorder or postorder form when we want to deserialize it?
 *   Since we are using recursion, it is best if the string that we received, contains the new node to create right at the
 *   beginning. Only then we would recurse on the left subtree, then only to the right subtree.
 *   
 *   THerefore, the solution is to use preorder traversal, with Queue combined. In the deserializing function, we will be
 *   polling the first element, create the node of it, then recurse on the left subtree then only on the right subtree.
 *   Therefore in the serializing function, we shall use preorder traversal when converting nodes into string, appending
 *   a delimiter (like comma ,) into the string as we progress
 *   
 *   The recursion will need a base case of some sort, otherwise it will keep on appending nodes, regardless if the node
 *   actually belongs there. Consider the preorder traversal:
 *   
 *       (2)
 *     (1)  (3)     -> [2,1,3]
 *     
 *   If we don't set a base case to stop when recursion call on (1), it will append 3 as child node of (1)!
 *   
 *   We have 2 ways to solve this:
 *   >	In serializing function, whenever we meet null node, we append 'X' which indicates a null there. Therefore string
 *   	will become [2,1,X,X,3,X,X], which recursion will stop when it detects a X
 *   >  In deserializing function, pass in the upper and lower bound, which the checking will be performed each time,
 *   	the node value which doesn't fall in the range will immediately return, since it don't belong to this place.
 *   
 *   
 */

import Binary_Tree.TreeNode;

public class Serialize_and_Deserialize_BST {
	
	/*
	 *  Every null node, we will be appending X, which is a base case
	 */
	class Codec {
		public String serialize(TreeNode node) {
			StringBuilder sb = new StringBuilder();
			serializeRecurse(node, sb);
			return sb.toString();
		}
		
		private void serializeRecurse(TreeNode node, StringBuilder sb) {
			if (node == null) {
				sb.append("X,");
				return;
			}
			
			sb.append( Integer.toString(node.val) );
			sb.append(',');
			
			serializeRecurse(node.left, sb);
			serializeRecurse(node.right, sb);
		}
		
		public TreeNode deserialize(String str) {
			String[] arr = str.split(",");
			Deque<String> queue = new ArrayDeque<>( Arrays.asList(arr) );
			return deserializeHelper( queue );
		}
		
		public TreeNode deserializeHelper( Deque<String> queue) {
			if (queue.isEmpty() ) return null;
			
			String str = queue.pop();
			if (str.equals("X")) return null;
			
			int value = Integer.parseInt(str);
			
			TreeNode node = new TreeNode(value);
			node.left = deserializeHelper( queue );
			node.right = deserializeHelper( queue);
			
			return node;
		}
	}
	
	//========= APPROACH 2: No 'X', use ranges instead =================================//
	
	
	class CodecV2 {
		public String serialize(TreeNode node) {
			StringBuilder sb = new StringBuilder();
			serializeRecurse( node, sb );
			return sb.toString();
		}
		
		private void serializeRecurse(TreeNode node, StringBuilder sb) {
			if (node == null) return;
			
			sb.append( Integer.toString(node.val) );
			sb.append(",");
			
			serializeRecurse(node.left, sb);
			serializeRecurse(node.right, sb);
		}
		
		public TreeNode deserialize(String str) {
			if (str.equals("") ) return null;
			String[] arr = str.split(",");
			Deque<String> queue = new ArrayDeque<>( Arrays.asList(arr) );
			return deserializeHelper( queue, Integer.MIN_VALUE, Integer.MAX_VALUE );
		}
		
		public TreeNode deserializeHelper( Deque<String> queue, int lower, int upper ) {
			if ( queue.isEmpty() ) return null;
			
			int val = Integer.parseInt( queue.peek() );
			if ( val < lower || val > upper ) return null;
			
			queue.poll();
			
			TreeNode node = new TreeNode(val);
			
			node.left = deserializeHelper( queue, lower, val);
			node.right = deserializeHelper( queue, val, upper);
			
			return node;
		}
	}

}
