package Data_Structures;

/*
 * 	Queue is like a linked list, but always to take note of the first element to be dequeued, and last element to be enqueued to
 */

public class Queues<T> {

	private Node<T> first;
	private Node<T> last;
	
	//Constructor
	public Queues() {
		first = null;
		last = null;
	}
	//End of constructor
	
	boolean isEmpty() {
		return first == null;
	}
	
	boolean enqueue(T data) {
		if (isEmpty() ) {
			first = new Node<T>(data);
			last = first;
			return true;
		}
		last.next = new Node<T>(data);
		last = last.next;
		return true;
	}
	
	Node<T> dequeue(){
		if (isEmpty() ) {
			System.err.println("Trying to dequeue an empty queue");
			return null;
		}
		Node<T> toReturn = first;
		first = first.next;
		return toReturn;
	}
	
	Node<T> peek() {
		return first;
	}
	
	//Node class
	private class Node<T> {
		T data;
		Node<T> next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
	}
	//End of Node class
	
	public static void main(String[]args) {
		Queues<Integer> q = new Queues<>();
		System.out.println(q.peek());
		System.out.println(q.enqueue(1));
		System.out.println(q.peek());
		System.out.println(q.enqueue(2));
		System.out.println(q.enqueue(3));
		System.out.println(q.dequeue() );
		System.out.println(q.dequeue() );
		System.out.println(q.dequeue() );
		System.out.println(q.dequeue() );
	}
	
}
