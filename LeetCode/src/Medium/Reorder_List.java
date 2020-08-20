package Medium;

/*
 * 	A Linked List problem.
 * 
 * 	My idea is, first I have to find the half part of the linked list. With this I used the O(N) method to find the linked list
 * 	length and find its middle.
 * 	(	For optimization, it could be done using fast and slow two pointers method, just for finding middle node)
 * 
 * 	Then, cut the other half from the first half, then reverse the right half, so that we could insert it accordingly
 * 
 * 		1 -> 2 -> 3 -> 4 -> 5		(Example)
 * 
 * 		1 -> 2 		3 <- 4 <- 5
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
	
	class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	public void reorderList(ListNode head) {
		if (head == null) return;
		
		int half = findLen(head) / 2;
		if (half == 0) return;
		
		ListNode right = reverseHalf(half, head);
		
		//	While there is a node to attach next
		while (right != null) {
			ListNode next = head.next;
			head.next = right;
			head = right;
			right = next;
		}
		
	}
	
	private int findLen(ListNode head) {
		int count = 0;
		while (head != null) {
			count ++;
			head = head.next;
		}
		return count;
	}
	
	//	Will return the head of the right side reversed node. This:
	//		1 -> 2 -> 3				4 <- 5
	//	Will return node 5 in this case
	private ListNode reverseHalf(int half, ListNode head) {
		ListNode prev = null;
		for (int idx = 0; idx < half; idx ++ ) {
			prev = head;
			head = head.next;
		}
		prev.next = null;
		prev = null;
		
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		
		return prev;
	}

}
