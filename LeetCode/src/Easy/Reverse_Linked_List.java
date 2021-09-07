package Easy;
import Linked_List.ListNode;

//https://leetcode.com/problems/reverse-linked-list/
/*
 * 	This is a Linked List problem.
 * 
 * 	To reverse the linked list in linear time and constant space, we'll use 3 pointers:
 * 		- Previous node, current node and next node
 * 	At each iteration, point the current node's pointer to previous node, then do the same with
 * 	the following nodes.
 * 	At the end, the last node will be the new head of reversed linked list
 * 
 * 	---------------------
 * 
 * 	Recursive solution:
 * 	The recursive function will obviously return the head of REVERSED linked list.
 * 	The base case is when head is null, or head.next is null (Means when reversed, it is the head)
 * 	
 * 	In other cases, obtain the head of reversed list, and reverse the next node's pointer to point to yourself
 */

public class Reverse_Linked_List {
	
	// Iterative
	public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
        	ListNode next = head.next;
        	head.next = prev;
        	prev = head;
        	head = next;
        }
        return prev;
    }
	
	// Recursive - Returns head of reversed List
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode realHead = reverseList2(head.next);
		head.next.next = head;
		head.next = null;
		return realHead;
	}
}
