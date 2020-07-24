package Medium;


//https://leetcode.com/problems/odd-even-linked-list/

public class Odd_Even_Linked_List {

	class ListNode {
		int val;
		ListNode next;
		ListNode() {
		}
		ListNode(int val) {
			this.val = val;
		}
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	/*
	 * 	This is a mistake because I arranged them based on the val of each node, not based on the indexing of node itself
	 */
//	public ListNode oddEvenList(ListNode head) {
//		if (head == null) return null;
//		
//		ListNode evenHead = null;
//		ListNode evenTail = null;
//		
//		while (head != null && head.val % 2 == 0) {
//			if (evenHead == null) {
//				evenHead = head;
//				evenTail = head;
//			}
//			else
//				evenTail.next = head;
//			head = head.next;
//		}
//
//		if (head == null) return evenHead;
//		
//		ListNode curr = head;
//		
//		while (curr.next != null ) {
//			if (curr.next.val % 2 == 0) {
//				if (evenHead == null) {
//					evenHead = curr.next;
//					evenTail = curr.next;
//				}
//				else {
//					evenTail.next = curr.next;
//					evenTail = evenTail.next;
//				}
//				curr.next = curr.next.next;
//			}
//			else {
//				curr = curr.next;
//			}
//		}
//		evenTail.next = null;
//		curr.next = evenHead;
//		return head;
//	}
	

	public ListNode oddEvenList(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return head;
		
		ListNode currentNode = head;
		ListNode evenHead = null;
		ListNode lastOdd = head;
		ListNode lastEven = null;
		
		//We will be starting with the second node
		boolean isEven = true;
		
		while (currentNode.next != null) {
			currentNode = currentNode.next;
			
			//Currently we are at evenly numbered node
			if (isEven) {
				if (evenHead == null) {
					evenHead = currentNode;
					lastEven = currentNode;
				}
				else {
					lastEven.next = currentNode;
					lastEven = currentNode;
				}
			}
			//Else, we are currently at a odd numbered node
			else {
				lastOdd.next = currentNode;
				lastOdd = currentNode;
			}
			
			isEven = !isEven;
		}
		
		lastOdd.next = evenHead;
		lastEven.next = null;
		return head;
	}
	
	public ListNode oddEvenListSecondTry(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode evenHead = head.next;
		ListNode evenTail = evenHead;
		head.next = head.next.next;
		
		ListNode curr = head;
		int index = 3;
		while (curr.next != null) {
			if (index % 2 != 0) {
				curr = curr.next;
			}
			else {
				evenTail.next = curr.next;
				evenTail = evenTail.next;
				curr.next = curr.next.next;
			}
			
			index++;
		}
		
		evenTail.next = null;
		curr.next = evenHead;
		return head;
	}
	
}
