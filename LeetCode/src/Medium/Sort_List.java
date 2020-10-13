package Medium;

import Linked_List.ListNode;

//https://leetcode.com/problems/sort-list/

/*
 * 	We have a lot of sorting algorithms for arrays, which provided O(1) random access time. However, problem arises when
 * 	we are trying to sort a Linked List. With random access of element taking O(N), how would we perform a sort in
 *  O(N log N)?
 *  
 *  One of solution is MERGE SORT, which uses DIVIDE AND CONQUER algorithm.
 *  
 *  For a linked list, we can split it into two halves. They essentially become 2 new linked lists, and with recursion,
 *  they will be returning those 2 linked lists, in sorted manner. Now we have to merge those 2 sorted linked list into
 *  1, by iterating through them with two seperate pointers.
 *  
 *	Merge sort basically have 3 steps:
 *		>	Splitting
 *		>	Recursion to Obtain sorted
 *		>	Merging
 *
 *	First of all, the base case is when the linked list contains only 1 nodes. In that case the linked list is technically
 *	SORTED. So just return this node as the head of SORTED LIST
 *
 *	For splitting, we have to split linked list into 2 equal parts. This can be done by first obtain the length of linked
 *	list then split it. Or the other way, we use a fast pointer which moves twice as fast as the slow pointer. When the
 *	fast pointer had hit the end of linked list, the slow pointer would be pointing at the halfway of linked list.
 *
 *	Then, we call upon the recursive function on those 2 linked lists. We can expect them to return 2 sorted linked lists.
 *	
 *	With those 2 sorted linked lists, we can MERGE them. By having 2 pointers, 1 at each linked list, we can compare the
 *	2 nodes and prioritize the one with smaller value to be put first. Iterate until both linked list's nodes have been
 *	used up
 *
 * 	Due to us splitting the linked list into halves in each recursion, the recursion itself would occur for O(lg N) times.
 * 	In each recursion, we perform splitting - O(N), and merging - O(N). So the overall time complexity is
 * 	O(N log N), while for space is the space taken up by recursion calls, of O(lg N)
 * 
 * 	==================================================================================================================
 * 
 * 	If we didn't use recursion, it would have been a O(1) space solution.
 * 
 * 	In the above recursive method, it is a top-down approach merge sort - We started with full length L, then L/2, then L/4
 * 	until they have been reduced to size 1.
 * 	In the iterative method, it will be a bottom-up approach. We assume initially the linked list is all sublists of
 * 	size 1, which is sorted of their own. Then we would merge them in PAIRS of sublist. See:
 * 
 * 		(5)-(4)-(2)-(1)
 * 	We initially assume they are all sorted lists, of size 1. Then we would merge them together, in PAIRS:
 * 	1. Merge (5)-(4)
 * 		>	(4,5)-(2)-(1)
 * 	2. Merge (2)-(1)
 * 		>	(4,5)-(1,2)
 * 	
 * 	Now they have been merged from sublists of size 1, into SORTED LISTS of at most size 2. Then we further merge them
 * 	1. Merge (4,5) and (1,2)
 * 		>	(1,2,4,5)
 * 
 * 	After this had been done, we can simply return the linked list now.
 */

public class Sort_List {

	//==========================
	//	RECURSIVE METHOD
	//==========================
	public ListNode sortList(ListNode head) {
		
		//	Base case. It had been reduced to only 1 node
		if (head.next == null) return head;
		
		//	Split the nodes into half
		ListNode fast = head.next;
		ListNode slow = head;
		
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next == null) break;
			fast = fast.next;
		}
		
		//	Break the connection first.
		ListNode secondHead = slow.next;
		slow.next = null;
		
		
		
		//	The first half recursive call
		ListNode sorted1 = sortList(head);
		//	The second half recursive call
		ListNode sorted2 = sortList(secondHead);
		
		
		
		//	Merge the results back together. Use a dummy node to simplify code
		ListNode sorted = new ListNode(Integer.MIN_VALUE);
		ListNode tail = sorted;
		
		while (sorted1 != null && sorted2 != null) {
			//	Sorted1's value is greater. Put sorted2
			if (sorted1.val > sorted2.val) {
				tail.next = sorted2;
				sorted2 = sorted2.next;
			}
			//	Sorted2's value is greater. Put sorted1
			else {
				tail.next = sorted1;
				sorted1 = sorted1.next;
			}
			tail = tail.next;
		}
		
		//	Occurs when sorted2 nodes is all finished but sorted1 havent. THis means remaining sorted1 nodes are all larger
		while (sorted1 != null) {
			tail.next = sorted1;
			sorted1 = sorted1.next;
			tail = tail.next;
		}
		//	Occurs when sorted1 nodes is all finished but sorted2 havent. THis means remaining sorted2 nodes are all larger
		while (sorted2 != null) {
			tail.next = sorted2;
			sorted2 = sorted2.next;
			tail = tail.next;
		}
		return sorted.next;
	}
	
	
	
	//==========================
	//	ITERATIVE METHOD
	//==========================
	public static ListNode sortList2(ListNode head) {
		ListNode dummy = new ListNode();
		dummy.next = head;
		
		head = dummy;
		int size = 1;
		
		//	Eventually at some point, head = dummy and returned = tail. That means the whole linked list is sorted.
		//	At that point we'll just return the result
		while (true) {
			ListNode returned = sortRange(head, size);
			
			//	The sorting had reached the end of the linked list, therefore returning null
			if (returned == null) {
				//	The head is the very beginning, and sorting had reached end. This means the whole linked list is
				//	sorted, so return
				if (head == dummy) return dummy.next;
				
				//	Otherwise, the linked list is sorted in sizes. Upsize the sort
				size *= 2;
				//	Return the pointer to the beginning.
				head = dummy;
			} else {
				//	The head returned is not null. Continue the sort in the same size, but next cluster of nodes
				head = returned;
			}
		}
		
	}
	
	
	//	Given a exclusive listnode head, and the size of the segmented sorted array, will automatically merge the 2 
	//	seperated linked list, and finally return the tail of the linked list for the next operation
	private static ListNode sortRange(ListNode headExclusive, int size) {
		ListNode s1 = headExclusive.next;
		if (s1 == null) return null;	//	headExclusive is a tail node. Return null
		
		//	Obtain s2, the beginning node of the other sorted linked list
		ListNode s2 = s1;
		for (int i = 0; i < size && s2 != null; i ++ ) {
			s2 = s2.next;
		}
		if (s2 == null) return null;	//	If there is no s2, then since s1 is already sorted, just return null
		
		//	The tail of the original linked list. Either the tail of the second sorted linked list, or if it is shorter,
		//	the node before null
		ListNode tail = s2;
		for (int i = 1; i < size && tail.next != null; i ++ ) {
			tail = tail.next;
		}
		
		//	The node next to tail node. The node we will be connecting back once we merge sorted
		ListNode end = tail.next;
		tail.next = null;	//	Disconnect the tail so we won't end up merge sorting the whole linked list
		
		//==================
		//	Merging Phase
		//==================
		
		//	Note for first half, we didn't disconnect it from the second half. Therefore we use size to keep track of
		//	how many nodes of first half haven't been used.
		ListNode sorted = new ListNode();
		ListNode sortedTail = sorted;
		
		while (size > 0 && s2 != null) {
			//	The node in second half is less.
			if (s1.val > s2.val) {
				sortedTail.next = s2;
				s2 = s2.next;
			} 
			//	The node in first half is less.
			else {
				sortedTail.next = s1;
				s1 = s1.next;
				size --;
			}
			sortedTail = sortedTail.next;
		}
		
		//	While first half linked list haven't been finished
		while (size-- > 0) {
			sortedTail.next = s1;
			s1 = s1.next;
			sortedTail = sortedTail.next;
		}
		//	While second half linked list haven't been finished
		while (s2 != null) {
			sortedTail.next = s2;
			s2 = s2.next;
			sortedTail = sortedTail.next;
		}
		
		// Append the head to the sorted list's new head
		headExclusive.next = sorted.next;
		//	Append the sorted list's tail to the end node, (or null)
		sortedTail.next = end;
		
		return sortedTail;
	}
	
	
	
	
	
	
	private static void printLinkedList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + ",");
			head = head.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[]args) {
		int[] val = {1,2,3,4,5,6};
		ListNode head = new ListNode();
		ListNode tail = head;
		
		for (int v: val) {
			tail.next = new ListNode(v);
			tail = tail.next;
		}
		
		ListNode sort = sortList2(head.next);
		printLinkedList(sort);
	}
	
}
