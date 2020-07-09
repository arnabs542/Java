package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1295/

/*
 * 	This is a two-pointers question. We are asked to rotate the linked list k steps to the right. Notice that the k might be larger than the linked list size.
 * 	This is example of limited state space question where it will be a cycle if k is larger than the linked list, it will go back to the original state and loop again.
 * 	Dealing with this kind of limited state space question, we need to find the loop and use modulo to prevent unnecessary calculations.
 * 
 * 	The basic idea is to find out the length of the linked list. Then we have to modulo k by the length
 * 	Notice if k is now 0, then just return head. The rotation will end up at same place.
 * 	Then, move the pointer to the length - k'th node. This is the node which the rotated linked list ends. Save the next node in temp variable and put this node's next
 * 	as null.
 * 	Iterate to the end of the linked list and attach the head to end of linked list.
 */

public class Rotate_List {
	class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
	 }
	
	private ListNode rotateRightLen(ListNode head, int k) {
		if (head == null || head.next == null) return head;
		
		//Find the length of the linked list. At the end, also record the tail of linked list
		int length = 1;
		ListNode point = head;
		while (point.next != null) {
			point = point.next;
			length ++;
		}
		ListNode tail = point;
		
		//Remove unnecessary loops by using modulus of length
		k %= length;
		//If after modulus, k = 0, means the linked list will return to initial state after rotating, so just return as it is
		if (k == 0) return head;
		//Update length to have the value of how many iterations from head of linked list to reach the end of rotated linked list
		length = length - k - 1;
		
		//Move the pointer to the node which is the end of the rotated linked list
		point = head;
		for (int i = 0; i < length; i ++ )
			point = point.next;
		
		//The next node of the end of rotated linked list will be the head of rotated linked list
		ListNode newhead = point.next;
		//Make the pointer node to be next = null, so that it won't end up in infinite loop
		point.next = null;
		//The tail of the original linked list must be attached to the head now.
		tail.next = head;
		return newhead;
	}
	
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null) return head;
		
		int length = 1;
		ListNode left = head;
		ListNode right = head;
		
		while (length <= k && right.next != null) {
			right = right.next;
			length ++;
		}
		if (k >= length) {
			k %= length;
			length = 1;
			right = head;
			while (length <= k) {
				right = right.next;
				length ++;
			}
		}
		
		if (left == right) return head;
		
		while (right.next != null) {
			left = left.next;
			right = right.next;
		}
		
		ListNode newHead = left.next;
		left.next = null;
		left = newHead;
		
		while (left.next != null) {
			left = left.next;
		}
		left.next = head;
		return newHead;
	}
}
