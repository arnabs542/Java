package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/

/*
 * 	To remove a specified element in linked list, we simply iterate the nodes. If the node's next is the target to delete element, then simply link
 * 	this node to the next node's next (skip over deleted node). Else, simply travel to the next node.
 * 	However, careful of edge cases where the head of the linked list itself is the element to be deleted. In that case, you need the initialization iteration
 * 	to set the head to first non-ToBeDeleted node.
 * 
 * 	For recursion approach, we'll do it from the last node of the linked list. We recurse down the linked list until it is null, which we will return null.
 * 	We have to check if the current sub-Linked-list's head is the toBeDeleted node. If yes, we just return the recursed back head. Else we link the head node to
 * 	the recursed back head and return this head.
 */

public class Remove_Linked_List_Elements {
	
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	
	public ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		
		ListNode curr = head;
		while (curr != null) {
			if (curr.next != null && curr.next.val == val) {
				curr.next = curr.next.next;
			}
			else
				curr = curr.next;
		}
		return head;
	}
	
	public ListNode removeRecurse(ListNode head, int val) {
		if (head == null) return null;
		
		ListNode next = removeRecurse(head.next, val);
		
		if (head.val == val) return next;
		head.next = next;
		return head;
	}
}
