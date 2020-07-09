package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/

/*
 * 	To reverse a linked list, an O(n) approach is that:
 * 		You know initially, the head of the linked list is always going to end up at the tail of the resulting linked list. Therefore, using the head as our pivot,
 * 		we keep moving the pivot's next node to the head, until the pivot is at the end of the list (next points to null)
 * 
 * 	To move the pivot's next node to the head, we need to do several things:
 * 		- Connect the pivot to the next node's next node.
 * 		- Let the pivot's next node to connect to the head
 * 		- Update the head pointer to the pivot's next node
 * 
 * 	Iterate this above algorithm until the pivot's next is null
 * 
 * 
 * 	Another approach is to reverse the arrowhead instead of moving the nodes itself to the head.
 * 	We know for the fact that initially the linked list head must end up pointing at null at the resulting array. Therefore
 * 	first we keep the reference of the node's next, and point the node's next to the previous node (initially as null).
 * 	
 * 	Now update the previous to the node you just processed, and update the current node to be processed to the reference we kept in the first hand
 * 
 * 	Iterate until the current node to be processed is null, which we will return previous node as the head of linked list
 * 
 * 
 * 
 * 	As for the recursive approach of reversing arrowhead, We will first obtain the end node of the linked list, which will be the head of resulting array.
 * 	After doing it recursively, we will start from the bottom up approach, by starting to reverse the arrows from the second last node.
 * 	Point the current next node's next pointer to current, then the current's next pointer to null. Then return the end node.
 * 
 * 	1 -> 2 -> 3  >>RECURSION>> 2 -> 3 >>RECURSION>> 3 >>RETURNS(3)>> 2 <- 3 >>BOTTOMUPREVERSE>> 1 <- 2 <- 3 >>RETURNS(3)
 */

public class Reverse_Linked_List {
	
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	
	public ListNode reverseList(ListNode head) {
		if (head == null) return null;
		
//		ListNode pivot = head;
//		
//		while (pivot.next != null) {
//			ListNode toMove = pivot.next;
//			ListNode connect = toMove.next;
//			
//			pivot.next = connect;
//			toMove.next = head;
//			head = toMove;
//		}
//		return head;
		
		return recurse(head, head);
	}
	
	public ListNode reverseListArrow(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		
		while (curr != null ) {
			ListNode temp = curr.next;
			
			curr.next = prev;
			
			prev = curr;
			curr = temp;
		}
		return prev;
	}
	public ListNode recurse(ListNode head, ListNode pivot) {
		if (pivot.next == null) return head;
		
		ListNode toMove = pivot.next;
		ListNode connect = toMove.next;
		
		pivot.next = connect;
		toMove.next = head;
		head = toMove;
		
		return recurse(head, pivot);
	}
	
	
	public ListNode recurse2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode end = recurse2(head.next);
		
		head.next.next = head;
		head.next = null;
		
		return end;
	}
	
}
