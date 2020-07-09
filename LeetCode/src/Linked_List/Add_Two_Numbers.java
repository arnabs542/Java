package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1228/

/*
 * 	Use two pointers to sum. The numbers are conveniently arranged so that it follows our addition method: adding from behind
 * 	Use a boolean flag to indicate if a carry is present during the last addition so that we add 1 to the sum.
 * 	
 * 	If both pointers finished, remember to check if the carry is still true. If the carry is true, add one last ListNode of value 1.
 */

public class Add_Two_Numbers {
	static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode tail = head;
		boolean carry = false;
		
		while (l1 != null || l2 != null) {
			int sum = 0;
			if (l1 == null) {
				sum = l2.val + (carry? 1: 0 );
				l2 = l2.next;
			}
			else if (l2 == null) {
				sum = l1.val + (carry? 1: 0);
				l1 = l1.next;
			}
			else {
				sum = l1.val + l2.val + (carry? 1: 0);
				l1 = l1.next;
				l2 = l2.next;
			}
			
			carry = sum / 10 >= 1;
			tail.next = new ListNode(sum % 10);
			tail = tail.next;
				
		}
		
		if (carry) tail.next = new ListNode(1);
		
		return head.next;
	}
	
	
	
}
