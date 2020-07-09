package Data_Structures;

/* A simple linked list consists of a series of nodes, each node having 2 attributes:
 * 		1. Data that it stores
 * 		2. Pointer/ Reference to the next node (if it is the tail, then it points to null
 * 
 * Then, when creating a linked list, it will have one attributes which always points to the head of the train
 * 
 */


public class Linked_List<T> {

	Node<T> head;
	
	//Constructors
	public Linked_List() {
		head = null;
	}
	
	public Linked_List(T data) {
		head = new Node<T>(data);
	}
	//end of Constructors

	
	// Private class for creation of instances of nodes
	private class Node<T> {
		T data;
		Node<T> next;
		
		public Node (T data) {
			this.data = data;
			this.next = null;
		}
	}
	// end of class Node
	
	// Methods for manipulating the Linked list
	boolean append (T data) {
		if (head == null)
			head = new Node<T>(data);
		else {
			Node<T> temp = head;
			while (temp.next != null)
				temp = temp.next;
			temp.next = new Node<T>(data);
		}
		return true;
	}
	
	boolean prepend (T data) {
		if (head == null)
			head = new Node<T>(data);
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.next = head;
			head = newNode;
		}
		return true;
	}
	
	boolean contains (T data) {
		if (head.data.equals(data) ) return true;
		Node<T> search = head;
		while (!search.data.equals(data) && search.next != null) {
			search = search.next;
		}
		if (search.data.equals(data) ) return true;
		return false;
	}
	
	boolean delete (T data) {
		if (head.data.equals(data) ) {
			head = head.next;
			return true;
		}
		Node<T> search = head;
		while (search.next != null && !search.next.data.equals(data) ) 
			search = search.next;
		if (search.next == null) return false;
		else {
			if (search.next.next == null) {
				search.next = null;
				return true;
			}
			else {
				search.next = search.next.next;
				return true;
			}
		}
	}
	
	@Override
	public String toString() {
		if (head == null) return "[]";
		else {
			String str = "[".concat(String.valueOf(head.data) );
			Node<T> temp = head;
			while (temp.next != null) {
				temp = temp.next;
				str = str.concat( String.format(", %s", String.valueOf(temp.data) ) );
			}
			return str.concat("]");
		}
	}
	// end of Manipulating methods
	
	public static void main(String[]args) {
		Linked_List<String> li = new Linked_List<>();
		li.append("World");
		li.prepend("Hello");
		li.append("!");
		System.out.println(li);
	}
	
}
