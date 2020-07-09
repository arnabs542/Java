package Linked_List;

//https://leetcode.com/problems/palindrome-linked-list/

/*
 * 	A Linked list can be reversed using a stack, by adding the nodes to the stack and popping it out
 * 	
 * 	To reverse a linked list without additional space, it must be done in-place by iterating through the linked list and making each node go to the head.
 * 	See: Reverse_Linked_List
 * 
 * 	To check palindrome using two pointers technique, either the first half or the second half must be reversed so we can check accordingly.
 * 	
 * 	One way to find the middle node without finding out the length of the linked list is by using one fast pointer and slow pointer. Fast pointer moves 2 step at a time
 * 	while slow pointer move 1. When fast pointer reaches the end, the slow pointer should be about at the middle of the linked list
 * 
 * 	However we can also reverse the linked list from the slow pointer while iterating. The slow pointer will then become the checker for the second half of the linked
 * 	list while the prev (The one responsible to link the slow pointer node to previous one) will be checker for the first half
 */

public class Palindrome_Linked_List {
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	
	//The finding out length method, the fast and slow pointer method
	
//	public boolean isPalindrome(ListNode head) {
//		if (head == null || head.next == null) return true;
//		
//		ListNode curr = head;
//		ListNode revHead = head;
//		
//		while (curr != null) {
//			curr = curr.next;
//			revHead = revHead.next;
//			if (curr == null) break;
//			curr = curr.next;
//		}
//		
////		ListNode curr = head;
////		int length = 0;
////		while (curr != null) {
////			curr = curr.next;
////			length ++;
////		}
////		
////		ListNode revHead = head;
////		for (int i = 0; i < (length % 2 == 0? length / 2: (length / 2) + 1 ); i ++ ) {
////			revHead = revHead.next;
////		}
//		curr = revHead.next;
//		revHead.next = null;
//		while (curr != null) {
//			ListNode temp = curr.next;
//			curr.next = revHead;
//			revHead = curr;
//			curr = temp;
//		}
//		
//		while (revHead != null) {
//			if (head.val != revHead.val) return false;
//			head = head.next;
//			revHead = revHead.next;
//		}
//		return true;
//	}
	
	
	public boolean isPalindromeRev(ListNode head) {
		if (head == null || head.next == null) return true;
		
		ListNode prev = null;
		ListNode fast = head;
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			ListNode slowNext = slow.next;
			slow.next = prev;
			prev = slow;
			slow = slowNext;
		}
		if (fast != null)
			slow = slow.next;
		
		while (slow != null) {
			if (prev.val != slow.val) return false;
			prev = prev.next;
			slow = slow.next;
		}
		return true;
	}
}
