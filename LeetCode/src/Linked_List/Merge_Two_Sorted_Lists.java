package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1227/

/*
 * 	This is two pointer linked list question. For iteration, we could just initialize the head to our own made node (to avoid null pointer exception)
 * 	and keep attaching the minimum of the 2 nodes from both linked list. At the end, just return head.next (exclude our own made node)
 * 
 * 	For recursion (but impractical), the base case is if both lists are null, which we will instantly return null. If either of them is null, then we have no choice
 * 	but to return the another list.
 * 	If both are not null, then we have to pick out the minimum node, and make this node.next to be the sorted linked list, which we will recurse using
 * 	the same function but with the node selected excluded
 */

public class Merge_Two_Sorted_Lists {
	static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	}
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0), tail = head;
		
		while (l1 != null || l2 != null) {
			int val1 = (l1 == null)? Integer.MAX_VALUE: l1.val;
			int val2 = (l2 == null)? Integer.MAX_VALUE: l2.val;
			
			if (val1 > val2) {
				tail.next = l2;
				tail = tail.next;
				l2 = l2.next;
			}
			else {
				tail.next = l1;
				tail = tail.next;
				l1 = l1.next;
			}
		}
		return head.next;	
	}
	
	
	public ListNode recurse(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) return null;
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		
		if (l1.val > l2.val) {
			l2.next = recurse(l1, l2.next);
			return l2;
		}
		l1.next = recurse(l1.next, l2);
		return l1;
	}
}
