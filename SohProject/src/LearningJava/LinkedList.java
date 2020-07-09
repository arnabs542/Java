package LearningJava;

class Node {
	Node next;
	int data;
	public Node(int data) {
		this.data = data;
	}
}

public class LinkedList {
	
	Node head = null;
	
	void append(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node current = head;
		while (current.next != null)
			current = current.next;
		current.next = new Node(data);
	}
	
	void prepend(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node newhead = new Node(data);
		newhead.next = head;
		head = newhead;
	}
	
	void delete(int data) {
		if (head.data == data) {
			head = head.next;
			return;
		}
		Node current = head;
		while ( current.next != null && current.next.data != data)
			current = current.next;
		if (current.next == null) {
			System.out.println("deletion failed. No such data found");
			return;
		}
		current.next = current.next.next;	//Null value included
	}
	
	@Override
	public String toString() {
		if (head == null) return "[]";
		String str = "[" + head.data;
		Node current = head;
		while (current.next != null) {
			current = current.next;
			str += ", " + current.data;
		}
		return str + "]";
		
	}
	
	public static void main(String[]args) {
		LinkedList ll = new LinkedList();
		ll.append(2);
		ll.append(4);
		ll.append(6);
		ll.append(8);
		ll.prepend(0);
		ll.delete(3);
		System.out.println(ll.toString() );
	}
}
