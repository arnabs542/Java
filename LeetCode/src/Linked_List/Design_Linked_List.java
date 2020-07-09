package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/

/*
 * 	I implemented a doubly linked list, where every node has 3 attributes: val, next and prev
 * 	The linked list also keeps track of the head and tail, and so that it can function like a queue or a stack 
 */

public class Design_Linked_List {
	
	static class MyLinkedList {
		
		int length;
		Node head;
		Node tail;
		
		class Node {
			int val;
			Node next;
			Node prev;
			
			public Node(int val) {
				this.val = val;
			}
		}
		
	    public MyLinkedList() {
	        length = 0;
	        head = null;
	        tail = null;
	    }
	    
	    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	    public int get(int index) {
	    	Node result = getNode(index);
	    	if (result == null) return -1;
	    	return result.val;
	    }
	    
	    //Returns the node at specified index. Null if the index is invalid
	    public Node getNode(int index) {
	        if (index >= length) return null;
	        
	        //Since the index is at other half of the linked list, it's more efficient to iterate from backwards
	        if (index >= length / 2) {
	        	Node curr = tail;
	        	for (int i = length - index - 1; i > 0; i -- )
	        		curr = curr.prev;
	        	
	        	return curr;
	        }
	        //Else iterate from the head as usual
	        else {
	        	Node curr = head;
	        	for (int i = 0; i < index; i ++ ) {
	        		curr = curr.next;
	        	}
	        	return curr;
	        }
	    }
	    
	    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	    public void addAtHead(int val) {
	    	Node node = new Node(val);
	        if (length == 0) {
	        	head = node;
	        	tail = node;
	        	length++;
	        	return;
	        }
	        head.prev = node;
	        node.next = head;
	        head = node;
	        length ++;
	    }
	    
	    /** Append a node of value val to the last element of the linked list. */
	    public void addAtTail(int val) {
	        if (length == 0) {
	        	addAtHead(val);
	        	return;
	        }
	        Node node = new Node(val);
	        
	        tail.next = node;
	        node.prev = tail;
	        tail = node;
	        length ++;
	    }
	    
	    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
	    public void addAtIndex(int index, int val) {
	        if (index == length) {
	        	addAtTail(val);
	        	return;
	        }
	        if (index == 0) {
	        	addAtHead(val);
	        	return;
	        }
	        
	        Node get = getNode(index);
	        if (get == null) return;
	        
	        Node insert = new Node(val);
	        get.prev.next = insert;
	        insert.prev = get.prev;
	        insert.next = get;
	        get.prev = insert;
	        
	        length++;
	    }
	    
	    /** Delete the index-th node in the linked list, if the index is valid. */
	    public void deleteAtIndex(int index) {
	    	if (index >= length) return;
	    	
	        if (index == 0) {
	        	head = head.next;
	        	if (length != 1) head.prev = null;
	        	length--;
	        	return;
	        }
	        if (index == length - 1) {
	        	tail = tail.prev;
	        	tail.next = null;
	        	length--;
	        	return;
	        }
	        
	        Node get = getNode(index);
	        get.prev.next = get.next;
	        get.next.prev = get.prev;
	        
	        length --;
	    }
	    
	    public int size() {
	    	return length;
	    }
	    
	    @Override
	    public String toString() {
	    	String str = "";
	    	Node curr = head;
	    	for (int i = 0; i < length; i ++ ) {
	    		str += curr.val + " ";
	    		curr = curr.next;
	    	}
	    	return str;
	    }
	    
	}
	
	public static void main(String[]args) {
		MyLinkedList mll = new MyLinkedList();
		mll.addAtHead(7);
		mll.addAtTail(7);
		mll.addAtHead(9);
		mll.addAtTail(8);
		mll.addAtHead(6);
		mll.addAtHead(0);
		mll.addAtHead(0);
		System.out.println(mll.toString() );
		System.out.println(mll.length );
		System.out.println( mll.get(2) );
		
	}
	
}
