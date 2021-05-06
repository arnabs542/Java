package Medium;

import java.util.ArrayList;
import java.util.List;

import Binary_Tree.TreeNode;
import Linked_List.ListNode;

//https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
/*
 * 	This is a recursion, breadth first search, tree problem
 * 
 * 	Given a sorted list, you know that the list is N length, and the best candidate to pick as root for
 * 	balanced tree is, the middle element, since every node to the left is in left subtree, and every node
 * 	at the right is in right subtree, picking the middle element ensures the left and right are balanced.
 * 
 * 	The problem comes when you don't know the lenght of list. Here propose 2 kind of solutions:
 * 
 * 	>	Solution 1: Convert linked list into array, using O(N) extra space.
 * 		Once converted list into array, middle element is easy to get. Therefore algorithm:
 * 		-	FInd middle element via calculation of middle index given start index and end index
 * 		-	Create root node on middle element
 * 		-	Assign root left child to be recursion of left subarray, up until current middle node
 * 		-	Assign root right child to be recursion of right subarray, from current middle node
 * 
 * 	>	Solution 2: At each recursion call, iterate thru the list to find middle element.
 * 		Since each recursion layer divides the linked list into 2, at most there will be LOG N calls. Therefore
 * 		time complexity is O(N log N), space is due to stack function calls, depth of DFS is O(log N)
 * 	
 * 		- 	Find middle element via iteration. Every recursive call require you to pass in the starting node and ending node
 * 			Use two pointers (slow & fast pt) technique to find middle
 * 		-	Create root node on middle element
 * 		-	Assign root left child to be recursion call (left, mid)
 * 		-	Assign root right child to be recursion call (mid.next, right)
 * 		-	Note: If recursion receive start == end, return null.
 */


public class Convert_Sorted_List_To_Binary_Search_Tree {
	
	//	O(N) time, O(N) space solution
	public TreeNode sortedListToBST(ListNode head) {
		List<TreeNode> nodes = new ArrayList<>();
		while (head != null) {
			nodes.add( new TreeNode(head.val) );
			head = head.next;
		}
		return recurse(nodes, 0, nodes.size() - 1);
    }
	
	private TreeNode recurse(List<TreeNode> nodes, int from, int to) {
		if (from == to) return nodes.get(from);
		if (from > to) return null;
		
		int mid = from + (to - from) / 2;
		nodes.get(mid).left = recurse(nodes, from, mid-1);
		nodes.get(mid).right = recurse(nodes, mid+1, to);
		return nodes.get(mid);
	}
	
	
	
	//	O(N log N) time, O(log N) stack space (recursion) solution
	public TreeNode sortedListToBST2(ListNode head) {
		if (head == null) return null;
		return recurse2(head, null);
	}
	private TreeNode recurse2(ListNode head, ListNode end) {
		if (head == end) return null;
		ListNode fast = head, slow = head;
		
		while (fast != end && fast.next != end) {
			fast = fast.next.next;
			if (fast == end) break;
			slow = slow.next;
		}
		TreeNode root = new TreeNode(slow.val);
		root.left = recurse2(head, slow);
		root.right = recurse2(slow.next, end);
		return root;
	}
}
