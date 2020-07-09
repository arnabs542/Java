package Data_Structures;

//In stacks, it's easier to let each node track the previous node ( However don't do this in more complicated implementation )

public class Stacks<T> {

	private Node<T> last;
	
	//Constructor
	public Stacks() {
		this.last = null;
	}
	//end of Constructor
	
	void push(T data) {
		if (last == null) {
			last = new Node<T>(data);
		}
		else {
			Node<T> newNode = new Node<>(data);
			newNode.prev = last;
			last = newNode;
		}
	}
	
	Node<T> peek() {
		return last;
	}
	
	Node<T> pop() {
		if (last == null) {
			System.err.println("Trying to pop an empty stack");
			return null;
		}
		Node<T> toReturn = last;
		last = last.prev;
		return toReturn;
	}
	
	//class Node
	private class Node<T> {
		T data;
		Node<T> prev;
		
		public Node(T data) {
			this.data = data;
			this.prev = null;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
	}
	//end of class Node
	
	public static void main(String[]args) {
		
	}
}
