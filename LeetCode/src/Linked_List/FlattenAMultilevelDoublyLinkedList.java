package Linked_List;

import java.util.Stack;

//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

/*
 * 	The general idea is to keep moving forward of the linked list, and if encountered a node with a child, then call upon a function which will flatten the
 * 	child linked list, return the last node of the child, and do the following:
 * 		-Connect the node with its child
 * 		-Connect child's prev to the node
 * 		-Connect the child's tail to the next node
 * 		-Connect the next node's prev to the child's tail
 * 		-(Optional) Make the node's child to null
 * 	To avoid iterating through the child linked list again, set the current node to either the end of child linked list or the stored node.next in temp variable
 * 
 * 	This recursion function will be called whenever the node has a child, but be careful due to the implementation, the child at head and tail may be ignored so
 * 	be alert to that and apply a fix
 * 
 * 	
 * Similarly, a Stack implementation can also be used, where the next will be added first, then only the child will be added. This way the child will be popped first
 * and be connected to the node.
 */

public class FlattenAMultilevelDoublyLinkedList {
	class Node {
	    public int val;
	    public Node prev;
	    public Node next;
	    public Node child;
	}
	
	public Node flatten(Node head) {
		if (head == null) return null;
		return dfs(head);
//		getLast(head);
//		return head;
	}
	
	private Node getLast(Node node) {
		while (node.next != null) {
			if (node.child != null) {
				Node temp = node.next;
				Node end = getLast(node.child);
				
				node.next = node.child;
				node.child.prev = node;
				end.next = temp;
				temp.prev = end;
				node.child = null;
				
				node = temp;
			}
			else
				node = node.next;
		}
		if (node.child != null) {
			Node end = getLast(node.child);
			
			node.next = node.child;
			node.child.prev = node;
			node.child = null;
			return end;
		}
		return node;
	}
	
	
	private Node dfs(Node node) {
		Stack<Node> stack = new Stack<>();
		if (node.next != null) stack.push(node.next);
		if (node.child != null) {
			stack.push(node.child);
			node.child = null;
		}
		
		Node curr = node;
		while (!stack.isEmpty() ) {
			Node search = stack.pop();
			curr.next = search;
			search.prev = curr;
			
			if (search.next != null) stack.push(search.next);
			if (search.child != null) stack.push(search.child);
			
			search.child = null;
			curr = curr.next;
		}
		return node;
	}
}
