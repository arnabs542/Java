package Data_Structures;

import java.util.Arrays;

/*
 *  Heaps is usually a binary tree, implemented using a array. Heap can be differentiated into min and max heap,
 *  where the parent is always greater/ lesser (or we call priority higher/ lower) than its child
 *  Implemented using an array, the index can be get (let i be the index of this element) using formulae:
 *  	-Left child: 2i
 *  	-Right child: 2i + 1
 *  	-Parent: floor(i/2)
 *   **--- Note that the indexing here starts at 1, as opposed to array indexing of initially 0! ---**
 *  
 *  Height of the heap, h = log n, where n is the number of elements
 *  
 *  Creation of heap takes O(n) time
 *  Insertion and deletion(poll) takes O(log n)
 *  Peek takes O(1)
 *  Process of Heapify of entire heap takes O(n) while heapify of single node takes O(log n) - n is the number of elements, not to be confused with height
 *  Heap sort takes O(n log n), but with a ready to go min/max heap, takes only O(log n) time
 *  
 *  An example of heap's application would be a Priority queue
 */

public class Heaps {		//THIS IMPLEMENTS A MAX HEAP

	//Initial capacity of the heap, will extend using ensureExtraCapacity() if goes over
	int capacity = 10;
	//Size (Number of elements), also keeps track of the last element index
	//Be careful when deal with this, because of working with heaps start with index 1, but array works with index 0
	//At any time, it should points to the array's next empty space, while reflecting the heap size
	//Eg: After inserting only 1 element, size++ becomes 1, 1 is the next empty space index, and also the heap size
	int size = 0;
	
	int[] items = new int[capacity];
	
	//Be careful, i here refers to the array indexing, not starting from 1
	public int getLeftChildIndex(int i) { return 2 * i + 1; }
	public int getRightChildIndex(int i ) { return 2 * i + 2; }
	public int getParentIndex(int i) { return (i - 1) / 2; }
	
	//Key to remember: size - 1 is the array's last element's index. Array doesn't have element at index [size] yet
	public boolean hasLeftChild(int i) { return getLeftChildIndex(i) < size; }
	public boolean hasRightChild(int i) { return getRightChildIndex(i) < size; }
	public boolean hasParent(int i) { return getParentIndex(i) >= 0; }
	
	public void swap(int index1, int index2) {
		int temp = items[index1];
		items[index1] = items[index2];
		items[index2] = temp;
	}
	
	public void ensureExtraCapacity() {
		//Assume now you wanna insert element to items[size], but actually items[] is full with capacity!
		//Now you wanna expand the array by creating a new array of capacity * 2, copying the elements from old array
		//This is inner working of Array List
		if (size == capacity) {
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}
	
	public int peek() {
		if (size == 0 ) throw new IllegalStateException("Empty Heap!");
		return items[0];
	}
	
	public int poll() {
		if (size == 0 ) throw new IllegalStateException("Empty Heap!");
		
		int toReturn = items[0];
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return toReturn;
	}
	
	public void add(int item) {
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}
	
	public void addAll (int... items) {
		for (int i: items) add(i);
	}
	
	//The less than sign, < , can be replaced if you want a min heap as opposed to a max heap
	public void heapifyUp() {
		int index = size - 1;
		
		//While it has parent and the parent value is greater than itself, swap em
		while ( hasParent(index) && items[getParentIndex(index)] < items[index] ) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	
	public void heapifyDown() {
		int index = 0;
		
		//Looking down from the top, we need determine which one is larger among left or right child (if they exists)
		while (hasLeftChild(index) ) {
			int smallChildIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && items[getRightChildIndex(index) ] > items[smallChildIndex] )
				smallChildIndex = getRightChildIndex(index);
			
			//Now compare the child to the item itself. Are the child larger? if no, break out of the loop; everything's arranged
			if (items[smallChildIndex] < items[index] )
				break;
			//Otherwise, swap em and repeat the process again
			else
				swap(index, smallChildIndex);
			index = smallChildIndex;
		}
	}
	
	/*
	 * Notice in the main() below, i added 5 integers into the heap, and then remove them all out. See that they are arranged in
	 * Descending order? Thats HEAP SORT, taking O(n log n) time including creation of heap time
	 * 
	 * Also, given a unordered heap, we could implement a full heapify() method to compare through all available items to make this a
	 * valid heap, taking O(n) time
	 */
	
	public static void main(String[]args) {
		Heaps h = new Heaps();
		h.addAll(23,43,12,56,72);
		while (h.size > 0) {
			System.out.println(h.poll() );
		}
		
	}
	
	
}
