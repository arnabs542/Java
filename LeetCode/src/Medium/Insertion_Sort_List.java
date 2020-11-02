package Medium;

//https://leetcode.com/problems/insertion-sort-list/

/*
 * 	This is, obviously a insertion sort problem, with linked list.
 * 
 * 	The basic idea of insertion sort is that we keep trying to insert elements one by one into a partially sorted
 * 	linked list. Do this with every element, then the whole list will be sorted at the end.
 * 
 * 	For this to be achieved in a linked list, we can have a fake head pointer which shall point at the start of the
 * 	sorted linked list. Initially, it will be empty but hopefully at the end, it will point to head of fully sorted
 * 	linked list.
 * 	Therefore, we will be expanding this supposedly sorted linked list one by one element. From the original, unsorted
 * 	list, we will be trying to insert the element into the partially sorted linked list. This is done by just having this
 * 	element and iterate through the partially sorted linked list until it found its supposed place.
 * 	
 * 	The element will be inserted and have the connections established (The node has to point at a valid next, and
 * 	also the element previous to the inserted node also have to point to this inserted node).
 *	After that, we continue with the next element in the original linked list. Note that all the nodes in the partially
 *	linked list are all nodes that is previous to the pointer in original linked list, so we don't worry about messing up
 *	the original linked list
 *
 *	===============================================================================================================
 *
 *	One solution is by recursive method though (Although in theory it is worse due to O(N) space of recursive stack)
 *
 *	The recursive works by first recurse until the end of the list, and returning the partially sorted list from the
 *	backwards. Then on the current node, try to insert the node into the partially sorted list, and return the
 *	head of partially sorted list back up one recursion layer
 *	
 */

import Linked_List.ListNode;

public class Insertion_Sort_List {
	
	
	// RECURSIVE METHOD O(N) space complexity
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) return head;		//	Base case
		
		ListNode sorted = insertionSortList(head.next);			//	The recursion should return a partially sorted linked list
		
		//	If current node was the head, then connect and return this node
		if ( head.val <= sorted.val ) {
			head.next = sorted;
			return head;
		}
		
		//	Otherwise the head of partially sorted linked list won't change. Insert current node and return head
		ListNode curr = sorted;
		while (curr.next != null && curr.next.val < head.val) {
			curr = curr.next;
		}
		
		ListNode temp = curr.next;
		curr.next = head;
		head.next = temp;
		
		return sorted;
    }
	
	
	// ITERATIVE METHOD O(N) space complexity
	public ListNode insertionSortListIterative( ListNode head ) {
		if (head == null) return null;
		
		ListNode sortedHead = null;		//	Initially there should be nothing in partially sorted linked list
		ListNode toInsert = head;		//	The element to be insert
		ListNode temp = toInsert;		//	The next element in line to be inserted. Upon entering while loop, it will
										//	become the current element's next
		
		//	While the nodes are not yet finish inserted
		while (toInsert != null) {
			temp = temp.next;
			sortedHead = insertion( toInsert, sortedHead );
			toInsert = temp;
		}
		
		return sortedHead;
		
	}
	
	//	Given the node and head of partially sorted linked list, insert the node and return head of sorted linked list
	public ListNode insertion( ListNode nodeToInsert, ListNode sortedHead) {
		
		//	If current node was the head, then connect and return this node
		if (sortedHead == null || nodeToInsert.val <= sortedHead.val ) {
			nodeToInsert.next = sortedHead;
			return nodeToInsert;
		}
		
		//	Otherwise the head of partially sorted linked list won't change. Insert current node and return head
		ListNode curr = sortedHead;
		while (curr.next != null && curr.next.val < nodeToInsert.val) {
			curr = curr.next;
		}
		
		ListNode temp = curr.next;
		curr.next = nodeToInsert;
		nodeToInsert.next = temp;
		
		return sortedHead;
	}
	
}
