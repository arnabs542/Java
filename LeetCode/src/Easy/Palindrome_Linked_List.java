package Easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-linked-list/
/*
 * 	This is a linked list problem.
 * 
 * 	The most direct way is to store the entire linked list in a random access O(1) data structure to easily gain access
 * 	to any element, and therefore checking each one if they are palindrome or not through classical palindrome checking
 * 	algorithm.
 * 
 * 	The time taken will be O(N) (two passes) but O(N) extra space as well
 * 
 * 	-------------------------------------------------------------------------------
 * 
 * 	Palindrome is nothing but like a mirror at the middle, like: 1,2,3 | 3,2,1. or 1,2,3 |4| 3,2,1.
 * 
 * 	If we are able to reverse one half of it like 3,2,1 | 3,2,1, then checking palindrome is easily done without extra
 * 	space. In fact, we can do it.
 * 
 * 	Using two pointers which one moves 2 nodes at a time, when the fast pointer hits the end, the slow one will be exactly
 * 	at the middle of the linked list. While slow pointer is moving, we can reverse the linked list up until the slow pointer
 * 	simultaneously. 
 * 
 * 	Whether you want to recover the reverse linked list is up to you, as this problem doesn't require it.
 * 
 * 	Time taken is O(N) as well, but space reduced to O(1)
 */

public class Palindrome_Linked_List {
	
	//	O(2N) time, but O(N) space method, by recording the linked list in random access data structure like array
	public boolean isPalindrome(ListNode head) {
        List<Integer> li = new ArrayList<>();
        for (; head != null; head = head.next)
        	li.add(head.val);
        
        final int len = li.size();
        for (int i = 0; i < len / 2; ++i) {
        	if (li.get(i) != li.get(len - i - 1) ) return false;
        }
        return true;
    }
	
	
	//	O(2N) time, but O(1) space as only pointers are used. 
	public boolean isPalindrome2(ListNode head) {
		if (head.next == null) return true;
		
		ListNode prev = null;
		ListNode fast = head.next;
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			fast = fast.next.next;		//	Move 2 steps
			
			//	Reverse the node at slow pointer
			ListNode next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		
		//	Get the second half, and determine the first half head based on whether linked list is odd or even lengthed
		ListNode secondHead = slow.next;
		slow.next = prev;
		ListNode firstHead = fast == null? slow.next: slow;
		
		//	Iterate to check. Note that at the end the linked list is mutated
		for (; firstHead != null; firstHead = firstHead.next, secondHead = secondHead.next)
			if (firstHead.val != secondHead.val) return false;
		
		return true;
	}
	
	
	//	Identical approach to above, but fixes the linked list
	public boolean isPalindrome2Fixed(ListNode head) {
		if (head.next == null) return true;
		
		ListNode prev = null;
		ListNode fast = head.next;
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			fast = fast.next.next;		//	Move 2 steps
			
			//	Reverse the node at slow pointer
			ListNode next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		
		//	Get the second half, and determine the first half head based on whether linked list is odd or even lengthed
		ListNode secondHead = slow.next;
		if (fast != null) slow.next = prev;
		ListNode firstHead = fast == null? prev: slow;
		prev = fast == null? slow: secondHead;
		
		boolean isPalindrome = true;
		
		//	Iterate to check. Note that at the end the linked list is mutated
		for (; firstHead != null; secondHead = secondHead.next) {
			if (firstHead.val != secondHead.val) isPalindrome = false;
			
			//	Reverse the reversed linked list to recover original state
			ListNode next = firstHead.next;
			firstHead.next = prev;
			prev = firstHead;
			firstHead = next;
		}
			
		return isPalindrome;
	}
}
