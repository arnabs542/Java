package Medium;
import Linked_List.ListNode;

//https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
/*
 * 	This is a linked list, two pointers problem.
 * 
 * 	The inefficient solution is to convert the linked list into array, and swap them. Array allows accessing elements
 * 	through index, but consumes extra space to achieve this solution. Can we do it in O(N) time but O(1) extra space?
 * 
 * 	---------------------------------------------------------------------------------
 * 
 * 	The intuitive solution, is to simply get the length of the linked list, and count the nodes while traversing
 * 	in second pass, record those nodes to swap when the index matches. This solution requires two passes, but still
 * 	is O(N) time and in O(1) extra space
 * 
 * 	--------------------------------------------------------------------------------
 * 
 * 	Another solution, although same two passes required, can be considered. In both passes, we reverse the linked list,
 * 	while keep track of the kth node. At the end, we would require:
 * 		>	head of the reversed linked list
 * 		>	Kth node
 * 
 * 	After first iteration, we obtained first node to swap, and the linked list is reversed. Now if we were to reverse
 * 	the linked list again, it will go back to original. Additionally, if we search the kth element in reversed linked
 * 	list, we would get the second element to swap!
 * 
 * 	-------------------------------------------------------------------------------
 * 
 * 	The best optimal solution involves two pointers. The intuition is, keep a range/window of size k. Example:
 * 
 * 		k = 2 and l = 7
 * 
 * 		(	)	(	)	(	)	(	)	(	)	(	)	(	)
 * 		|-----------|
 * 	Initially, the 'range' end is at the first node to swap. Now let's move the range until the end!
 * 
 * 		(	)	(	)	(	)	(	)	(	)	(	)	(	)
 * 												|-----------|
 * 	Notice how the start of the range is stopped at the second node to swap? That's exactly the intuition behind
 * 	the two pointers solution!
 */


public class Swapping_Nodes_in_a_Linked_List {
	
	//	Two pass, counting length solution
	public ListNode swapNodes(ListNode head, int k) {
		
		//	Evaluate length of the linked list
		int len = 0;
		ListNode curr = head;
		while (curr != null) {
			++len;
			curr = curr.next;
		}
		
		//	Search for the corresponding nodes, kth node and n-k th node
		int other = len - k + 1;
		ListNode first = null, second = null;
		curr = head;
		
		while (first == null || second == null) {
			if (len == other) second = curr;
			if (len == k) first = curr;			//	Do not use else if. It may happen where n-k and k is same node
			
			curr = curr.next;
			--len;
		}
		
		//	Swap and return head
		int temp = second.val;
		second.val = first.val;
		first.val = temp;
		return head;
	}
	
	
	//	Two pass, reversing linked list solution
	public ListNode swapNodes2(ListNode head, int k) {
		ListNode[] result = getAndReverse(head, k);
		ListNode node1 = result[1];
		result = getAndReverse( result[0], k);
		ListNode node2 = result[1];
		
		int temp = node1.val;
		node1.val = node2.val;
		node2.val = temp;
		return result[0];
	}
	private ListNode[] getAndReverse(ListNode head, int k) {
		ListNode[] res = new ListNode[2];
		ListNode prev = null;
		
		while (head != null) {
			if (--k == 0) res[1] = head;
			
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		res[0] = prev;
		return res;
	}
	
	
	//	Two pointers, one pass solution
	public ListNode swapNodes3(ListNode head, int k) {
		ListNode n1 = null, n2 = null;
		
		for (ListNode p = head; p != null; p = p.next) {
			//	If slow pointer is already set up, move it forward
			n1 = n1 == null? null: n1.next;
			
			//	Met the first node to swap. Now setup the slow pointer!
			if (--k == 0) {
				n2 = p;
				n1 = head;
			}
		}
		
		int temp = n1.val;
		n1.val = n2.val;
		n2.val = temp;
		return head;
	}
	
}
