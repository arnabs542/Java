package Medium;

//https://leetcode.com/problems/design-circular-queue/
/*
 * 	This is a design data structure problem - For queue
 * 
 * 	If the two pointers - front and rear of the queue runs out of space after use, then the
 * 	queue is not so efficient. say I have a queue of size 5, I push 5 elements, and pop 5 elements.
 * 	Then the queue should be reusable with 5 space left! 
 * 
 * 	This type of queue is efficient, and is called "Ring Buffer", where last position of the queue is
 * 	essentially connected back to the first position in the queue.
 * 
 * 	Turns out, it is easily done via two pointers and a modulus operation. Say my queue is of size 5,
 * 	and the rear pointer is now at index 4 (last position). If I want to enqueue some elements, the
 * 	next index to work on is actually just (i+1) % 5, where 5 is the capacity of the queue. WIth this,
 * 	I get back index 0, which is start of queue! (Just check if the queue is full or not so we don't
 * 	overwrite useful data).
 */

public class Design_Circular_Queue {
	
	class MyCircularQueue {
		int capacity;
		int size;
		int[] queue;
		int front, rear;
		
		public MyCircularQueue(int k) {
			capacity = k;
			size = front  = 0;
			rear = k - 1;
			queue = new int[k];
		}

		public boolean enQueue(int value) {
			if (size == capacity) return false;
			
			rear = (rear + 1) % capacity;
			++size;
			queue[rear] = value;
			return true;
		}
		
		public boolean deQueue() {
			if (size == 0) return false;
			
			front = (front + 1) % capacity;
			--size;
			return true;
		}
		
		public int Front() {
			if (size == 0) return -1;
			
			return queue[front];
		}
		
		public int Rear() {
			if (size == 0) return -1;
			
			return queue[rear];
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public boolean isFull() {
			return size == capacity;
		}
	}
}
