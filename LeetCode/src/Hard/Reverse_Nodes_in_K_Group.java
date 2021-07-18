package Hard;
import Linked_List.ListNode;

//https://leetcode.com/problems/reverse-nodes-in-k-group/]
/*
 * This is a linked list problem.
 * 
 * First, recall to reverse a linked list normally, we need to have reference to three things:
 * 		>	Previous node
 * 		>	Current node
 * 		>	Next node
 * For each of the nodes, we simply
 * 		>	The current node's next pointer will point to previous node
 * 		>	Update previous node to be current node
 * 		>	Set current node to be next node
 * Repeat until you hit null pointer.
 * 
 * Now we have to reverse nodes in K groups. We can actually do what the problem asks: To apply the normal reverse linked
 * list algorithm in groups.
 * 
 * Given a linked list, we obtain the start and end node of each groups, while also having reference to the end node's next
 * node as well, so we don't get lost. (Also we should have the start node's previous node, or the end node of last reversed group)
 * Now, if we remove the end node's next pointer, then we can apply the normal reverse linked list algorithm already! Once
 * that's done, we simply connect everthing back to correct order.
 */

public class Reverse_Nodes_in_K_Group {
	
	public ListNode reverseKGroup(ListNode head, int k) {
        // Preparation
		ListNode dummy = new ListNode(0, head);
		ListNode prevEnd = dummy;
		ListNode currStart = head, currEnd;
		
		while (currStart != null) {
			currEnd = currStart;
			// Obtain the first and last node of the group
			for (int i = 0; i < k-1; ++i) {
				currEnd = currEnd.next;
				if (currEnd == null) return dummy.next;
			}
			
			// Obtain reference to next node and make last node's next pointer to null
			ListNode next = currEnd.next;
			currEnd.next = null;
			
			// Normal reverse linked list
			reverseLinkedList(currStart);
			
			// Connect back
			prevEnd.next = currEnd;
			currStart.next = next;
			
			prevEnd = currStart;
			currStart = next;
		}
		return dummy.next;
    }
	
	// Reverses the linked list provided head node, until the end (null)
	private ListNode reverseLinkedList(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
}
