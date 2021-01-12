package Medium;
import Linked_List.ListNode;

//https://leetcode.com/problems/add-two-numbers/
/*
 * 	This is a linked list problem.
 * 
 * 	If we want, we can also do it in place, Taking O(1) space. But we won't here
 * 
 * 	The idea is direct: Using two pointers, add the two nodes. If exceed 10,
 * 	store it into carry variable. The next node, the carry need to be added.
 * 
 * 	At the end, if the carry is still true, add a new node of 1.
 */

public class Add_Two_Numbers {

	//	Iterative method
	ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode tail = dummy;
		
		boolean carry = false;
		
		while (l1 != null || l2 != null) {
			int val = (l1 != null? l1.val: 0) + (l2 != null? l2.val: 0) + (carry? 1:0);
			carry = val >= 10;
			val %= 10;
			
			tail.next = new ListNode(val);
			tail = tail.next;
			
			l1 = l1 == null? l1: l1.next;
			l2 = l2 == null? l2: l2.next;
		}
		
		if (carry) tail.next = new ListNode(1);
		return dummy.next;
	}
	
	
	//	Recursive Method
	ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		return recurse(l1, l2, false);
	}
	
	ListNode recurse(ListNode l1, ListNode l2, boolean carry) {
		if (l1 == null && l2 == null)
			return carry? new ListNode(1): null;
			
		int sum = (l1 != null? l1.val: 0) + (l2 != null? l2.val: 0) + (carry? 1: 0);
		
		ListNode toAppend = recurse(l1 == null? null: l1.next, l2 == null? null: l2.next, sum >= 10);
		ListNode newNode = new ListNode(sum % 10);
		newNode.next = toAppend;
		
		return newNode;
	}
}
