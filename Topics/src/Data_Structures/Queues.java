package Data_Structures;

/*
 * 	Queues are one of the linear Data Structures available. It is almost like the opposite of Stack data structure
 * 	Queues can be think of as an extension of linked list, except that Queues shall only add and remove elements from either of the two
 * 	ends: either the HEAD or TAIL
 * 
 * 	Queues follow the FIFO order: First in, First Out. The first element that gets added into the queue, will be the first to be removed
 * 	from the queue. Just like a queue in real life!
 * 
 * 	Let's see some of the implementation of Queues: Either in arrays, or using linked list (More preferred)
 * 	Note that TWO POINTERS technique is VERY IMPORTANT AND USEFUL in implementation of Queues
 * 
 * 	Common interfaces of Queue includes the following methods:
 * 
 * 	>	enqueue() / offer()	- Add element to the tail of the queue
 * 	>	dequeue() / poll()  - Remove element at the head of the queue
 * 	>	peek() 				- Obtain the element at the head of queue without removing it
 */


class Queues {
	
	/*
	 * 	QUEUE IMPLEMENTATION USING ARRAY
	 * 
	 * 	Queue can be implemented using arrays. There are actually upsides and downsides for implementation using array:
	 * 
	 * 	UPSIDE:
	 * 		>	Easily extendable. If we suddenly decide to random access a element in the queue, it is entirely possible. Actually,
	 * 			it can become a random access available linked list too! 
	 * 			Random access opens up certain possibilities. Eg: If the queue is sorted, we can perform binary search!
	 * 	DOWNSIDE:
	 * 		>	Size is almost fixed. We have to allocate a fixed size array at the construction of the Array Queue. Once the size limit
	 * 			is exceeded, we either reject the new insertion, or allocate a larger array which takes O(N) time for that.
	 * 
	 *	
	 *	The idea of implementation is, we will keep TWO POINTERS:
	 *		HEAD POINTER - Points at the element which we will poll() / dequeue() next
	 *		TAIL POINTER - Points at the empty space which we will offer() / enqueue() next
	 *
	 *	Apart from that, we can have some supporting datas inside our queue:
	 *		size - The current size of queue. Useful to decide if the queue is empty or full
	 *		capacity - The limit of the queue. Used along with size to make decisions
	 *
	 *	Everytime we add element to the queue, element is inserted at the tail pointer, then tail pointer increments.
	 *	Everytime we remove element from the queue, element is returned from head pointer, then head pointer increments.
	 *
	 *	Now, what happens in following case?
	 *			( ) ( ) ( ) (3) (4) (5) ( )
	 *						 ^           ^
	 *					    HEAD       TAIL
	 *	
	 *	When we add a new element, where shall the TAIL pointer go? We can modify it so that tail will utilize the empty space at the beginning
	 *	of the array, by using MODULO operation. 
	 *	This concept is known as CIRCULAR QUEUE (ARRAY)
	 */
	class Queue_Array {
		
		private int capacity, size;
		private int headPt, tailPt;
		private int[] arr;
		
		//	Constructor. Allocate the array of size capacity specified by user.
		public Queue_Array(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			this.headPt = 0;
			this.tailPt = 0;
			this.arr = new int[ capacity ];
		}
		
		public boolean enqueue(int element) {
			//	The queue is full. Do not add element anymore. Return false to indicate a failed enqueue
			if (size >= capacity) {
				return false;
			}
			arr[tailPt] = element;
			tailPt = (tailPt + 1) % capacity;	//	Increment tail pointer. If at the end, then return to 0
			size ++;
			return true;
		}
		
		public int dequeue() {
			//	The queue is empty. Nothing to return. Therefore use Integer.MIN_VALUE as flag
			if (size <= 0) return Integer.MIN_VALUE;
			
			int result = arr[headPt];
			headPt = (headPt + 1) % capacity;	//	Increment head pointer. If at the end, then return to 0
			size --;
			return result;
		}
		
		public int peek() {
			if (size <= 0) return Integer.MIN_VALUE;
			
			return arr[headPt];
		}
		
		public int getSize() { return size; }
		public int getCapacity() { return capacity; }
		
	}
	
	
	
	/*
	 * 	QUEUE IMPLEMENTATION USING LINKED LIST
	 * 
	 * 	The most common and usual way to implement a Queue is to use a linked list. This is due to the fact that linked list can be
	 * 	extended easily without costing much time O(1), and no need for allocating static memory like array does.
	 * 
	 * 	Like implementation using arrays, we will still keep 2 pointers. The 2 pointers point to the beginning and the end of the	
	 * 	linked list respectively.
	 * 
	 * 	The implementation is more simple to understand than arrays, especially circular queue array. However, the downside of using
	 * 	linked list is that random accessing element in the middle is going to cost linear time complexity
	 */
	class Queue_Linkedlist {
		
		class Node {
			int val;
			Node next;
			public Node(int val) { this.val = val; }
		}
		
		private int size;
		Node head;
		Node tail;
		
		public Queue_Linkedlist() {
			this.size = 0;
			head = null;
			tail = null;
		}
		
		public boolean enqueue(int val) {
			Node node = new Node(val);
			if (size == 0) {
				head = node;
				tail = node;
				size ++;
				return true;
			}
			
			tail.next = node;
			tail = node;
			size++;
			return true;
		}
		
		public int dequeue() {
			if (size == 0) return Integer.MIN_VALUE;
			int result = head.val;
			head = head.next;
			size --;
			return result;
		}
		
		public int getSize() { return size; }
	}
	
	
}
