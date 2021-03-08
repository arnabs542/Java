package Easy;

//https://leetcode.com/problems/design-hashmap/
/*
 * 	A HashMap is simply an array, but to access an element, we use what is called as 'keys', which
 * 	must be hashable, like Strings (in Java, every object is hashable).
 * 
 * 	Now, A HashMap has an array of fixed size to store elements, as well as a hashing function, which
 * 	maps keys to the indices of the array. With that, all elements can be accessed in approximated O(1)
 * 	time!
 * 
 * 	Collision may happen, where 2 different keys will be hashed into the very same index. In that case,
 * 	a collision is said to be happened, and it has to be resolved.
 * 	One of such way to resolve, is to use Linkedlist. Instead of storing one element in each index, it
 * 	stores linked list, so when collision happens, it will only add elements to the linked list. of course,
 * 	the data structure should be optimized to have collision occur as little as possible.
 */

public class Design_HashMap {
	
	class MyHashMap {
		
		//	Private members
		private int capacity;
		private ListNode[] arr;
		private class ListNode {
			ListNode next;
			int key, val;
			public ListNode(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}
		
		
		//	Public Interfaces
	    public MyHashMap() {
	    	this(1000);
	    }
	    
	    public MyHashMap(int capacity) {
	    	this.capacity = capacity;
	    	arr = new ListNode[capacity];
	    }
	    
	    public void put(int key, int value) {
	        int idx = getIndex(key);

	        if ( arr[idx] != null ) {
	        	ListNode search = search( arr[idx], key );
	        	
	        	//	If search is null, means the head of linked list is the actual node
	        	if (search == null) arr[idx].val = value;
	        	//	If result value is not same key, means not found!
	        	else if (search.next == null) search.next = new ListNode(key, value);
	        	//	Found the result
	        	else search.next.val = value;
	        }
	        else arr[idx] = new ListNode(key, value);
	    }
	    
	    
	    public int get(int key) {
	        int idx = getIndex(key);
	        if (arr[idx] == null) return -1;
	        
	        ListNode search = search( arr[idx], key);
	        //	Head of linked list is result
	        if (search == null) return arr[idx].val;
	        //	If next is null means not found. Otherwise next node is the result
	        return search.next == null? -1: search.next.val;
	    }
	    
	    public void remove(int key) {
	        int idx = getIndex(key);
	        if (arr[idx] == null) return;
	        
	        ListNode search = search( arr[idx], key);
	        //	Head of linked list is result
	        if (search == null) arr[idx] = arr[idx].next;
	        //	No result found: Do nothing
	        else if (search.next == null) return;
	        //	Skip the node to delete it
	        else search.next = search.next.next;
	    }
	    
	    
	    //	Private utilities functions
	    private int getIndex(int key) { return key % this.capacity; }
	    
	    //	Search and returns the ListNode in a linked list. It returns the previous node prior
	    //	to the actual node itself. If result is at start of list, returns null. If result is
	    //	not found, returns the tail of list, where next node is null
	    private ListNode search(ListNode head, int key) {
	    	if (head.key == key) return null;
	    	while (head.next != null && head.next.key != key)
	    		head = head.next;
	    	return head;
	    }
	}
	
}
