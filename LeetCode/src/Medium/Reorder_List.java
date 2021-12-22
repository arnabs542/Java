package Medium;
import Linked_List.ListNode;

//https://leetcode.com/problems/reorder-list/
/*
 * 	A Linked List problem.
 * 
 * 	My idea is, first I have to find the half part of the linked list. With this I used the O(N) method to find the linked list
 * 	middle node using two pointers (Fast & slow pointer)
 * 
 * 	At the end, the middle pointer will point at the middle node, just before the other half of the linked list
 *  The one in bracket is the node selected as middle node after two pointers method
 * 	Eg: 1-2-(3)-4-5
 * 	Eg: 1-(2)-3-4
 * 
 * 	Then, cut the other half from the first half, then reverse the right half, so that we could insert it accordingly
 * 
 * 		1 -> 2 -> 3 -> 4 -> 5		(Example)
 * 				To
 * 		1 -> 2 	 |	3 <- 4 <- 5
 * 
 * 	Reversing is done by keeping track of previous node, and set the node's next to previous node
 * 	Be careful! Do not form a cycle in this process. Make sure that the node center's previous (Node 2 in example) next is null,
 * 	and the tail node of reversed linkedlist is null as well
 * 
 * 	
 * 	Then, we now have the two pointers: Left half head, and the right half head (Node 5 in example).
 * 
 * 	We could switch the role of the two pointers.
 * 	>	First Node 1 (Left) need to be attached to the Node 5 (Right). Now the Right (Node 5) would be Left, 
 * 		and Node 2 (Left's next) will be Right
 * 	>	Then Node 5 (Left) need to be attached to Node 2 (Right). Now the Right (Node 2) would be Left,
 * 		and Node 4 (Left's next) will be Right
 * 
 * 	The general rule is that We will first keep track of Left's next node. Attach the Left to the Right node,
 * 	and interchange the left and right pointer, where Right will be Left, and Left's next will be Right (Next node to attach)
 * 
 * 	We will keep doing this until the right is null, because right is the next node to attach. If it is null, there
 * 	is no more node to attach. The tail node shall be null or with a leftover node which is already at the position it is supposed
 * 	to be
 */

public class Reorder_List {
	
	public void reorderList(ListNode head) {
		// Step 1 - Get middle node
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		
		// Step 2 - Make the middle node's next point to null (Because it will be the end of left half of linked list)
		fast = slow.next;
		slow.next = null;
		slow = fast;


		// Step 3 - Reverse the right half of the linked list
		ListNode reversedHead = null;
		while (slow != null) {
			ListNode nxt = slow.next;
			slow.next = reversedHead;
			reversedHead = slow;
			slow = nxt;
		}


		// Step 4 - Interconnect normal and reversed linked list
		ListNode dummy = new ListNode();
		for (ListNode tail = dummy; head != null || reversedHead != null; ) {
			if (head != null) {
				tail.next = head;
				tail = head;
				head = head.next;
			}
			if (reversedHead != null) {
				tail.next = reversedHead;
				tail = reversedHead;
				reversedHead = reversedHead.next;
			}
		}
	}

}
