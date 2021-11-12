package Linked_List;
import Linked_List.ListNode;

//https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/

/*
 * 	To remove a specified element in linked list, we simply iterate the nodes. If the node's next is the target to delete element, then simply link
 * 	this node to the next node's next (skip over deleted node). Else, simply travel to the next node.
 * 	However, careful of edge cases where the head of the linked list itself is the element to be deleted. In that case, you need the initialization iteration
 * 	to set the head to first non-ToBeDeleted node.
 * 
 * 	Of course, we should know about dummy node technique. Instead of checking the node's next element, we can keep reference to previous node, so that when current
 * 	node is to be removed, we simply relink the previous node's next. This is easier to code
 * 
 * 	For recursion approach, we'll do it from the last node of the linked list. We recurse down the linked list until it is null, which we will return null.
 * 	We have to check if the current sub-Linked-list's head is the toBeDeleted node. If yes, we just return the recursed back head. Else we link the head node to
 * 	the recursed back head and return this head.
 */

public class Remove_Linked_List_Elements {
	
	// Iterative, Dummynode technique
	public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val != val) {
                prev.next = curr;
                prev = curr;
            }
        }
        prev.next = null;
        return dummy.next;
	}
	
	
	// Recursive approach
	public ListNode removeRecurse(ListNode head, int val) {
		if (head == null) return null;
		if (head.val == val) return removeRecurse(head.next, val);
		
		head.next = removeRecurse(head.next, val);
		return head;
	}
}
