package Data_Structures;


/*
 * 	Skip List is a modified, SORTED Linked list, which allows for more efficient search time (Theoretically O(log N) )
 * 
 * 	It works by having 'layers' of SORTED linked list, where the bottom layer will consist of ALL of the elements stored, while the higher layer that we go,
 * 	the fewer elements that will be on that layer.
 * 
 * 	When searching for a particular element, we will always start from the highest layer. If the pointer has encountered the node where it's next value
 * 	is greater than the one to search, then it will go down one layer, where more elements were to be found, and continue searching at that layer
 * 	This continues until at the bottom layer, the element is found or not found by checking the final pointer's next node
 * 
 * 	By using this way of searching, we've skipped quite some elements since we are traversing at the higher layer first, which consists of fewer nodes.
 * 	Only when the element is not found at higher layer, then we fall back into lower layer and continue.
 * 	
 * 	During insertion, we perform similar operation to searching, but instead we are finding a suitable position for the value to be inserted.
 * 	Once found, we need to determine this value will appear in how many layers. This is done by the coin flipping:
 * 		-If rolled head, it will go up one layer. Continue flipping
 * 		-If rolled tail, stop flipping. The layer count is determined
 * 	Since fair coin flipping has probability of 0.5, rolling consequtive heads will be smaller probability of 0.5^2, 0.5^3... and so on.
 * 
 * 	This will ensure that the higher layer that we go, the fewer element will reside there, which allow for 'express' lanes to searching for elements
 * 	
 * 
 * 
 */


public class Skip_List {
	
	//A Skip List Node object, consists of a value, and an array of pointers to the next node
	private class SkipNode {
		int val;
		public SkipNode[] next;
		
		public SkipNode(int val, int limit) {
			this.val = val;
			this.next = new SkipNode[limit];
		}
	}
	//End of SkipNode class

	
	//Head of Skip List, which is a node with value of Negative Infinity (set to min value)
	private SkipNode head;
	//Limit of the number of layers this skipped list can have
	private int limit;
	
	
	//Initialize the head, and create a node which will be the last node of the list, consisting of value (Infinity) 
	public Skip_List(int limit) {
		//Limit the number of layers
		this.limit = limit;
		//The head of skip list, consisting of Theoretically negative infinity value (Here set as Integer MIN VALUE)
		head = new SkipNode(Integer.MIN_VALUE, limit);
		//The tail of skip list, consisting of Theoretically positive infinity value (Here set as Integer MAX VALUE)
		SkipNode tail = new SkipNode(Integer.MAX_VALUE, limit);
		//Connect at each layer, the head to the tail
		for (int i = 0; i < limit; i ++ ) {
			head.next[i] = tail;
		}
	}
	
	
	//Insert a node into the skip list, which requires finding the position (especially at bottom layer)
	public void insertNode(int val) {
		//Obtain the nodes where the provided val should insert next at each layer
		SkipNode[] toInsert = findPos(val);
		
		SkipNode newNode = new SkipNode(val, limit);
		
		//Get a random number by flipping coins. For each layer, connect the new nodes to its previous node
		for (int i = getRandom() - 1; i >= 0; i -- ) {
			SkipNode temp = toInsert[i].next[i];
			toInsert[i].next[i] = newNode;
			newNode.next[i] = temp;
		}
		
	}
	
	
	//Search for the particular value in the skip list
	public void search(int val) {
		SkipNode curr = head;
		//Initially start at the highest level
		int level = limit - 1;
		//Traverse counter. How many nodes traversed
		int traverse = 0;
		
		while (level >= 0 ) {
			int nextVal = curr.next[level].val;
			
			//If the element is found, just print ot the position of the node here. (Instead, I could just return the node itself for processing)
			if (nextVal == val ) {
				System.out.printf("Element %d found at level %d in front of node %d. Traversed: %d\n", val, level, curr.val, traverse);
				return;
			}
			//Else if the next value is larger, then I have to go down by one level
			else if (nextVal > val) {
				level --;
			}
			//Else if the next value is smaller, then proceed to the next node
			else {
				curr = curr.next[level];
			}
			traverse ++;
		}
		//Break out of the loop. Element is not found
		System.out.println("Element not found! Traversed: " + traverse);
	}
	
	public void remove(int val) {
		//Obtain the array of nodes at each layer. The nodes returned shall be the node before the value node itself, or may be the node that the value node
		//shall be inserted next. So we have to check if the node's next is the value node to delete
		SkipNode[] toInsert = findPos(val);
		
		for (int i = 0; i < limit; i ++ ) {
			if (toInsert[i].next[i].val == val) {
				toInsert[i].next[i] = toInsert[i].next[i].next[i];
			}
		}
		
	}
	
	//Passing a value as argument, it will create an array which records the node before the suitable insertion position of the provided value
	/*
	 * 		        5
	 * 		1   3   5   7   
	 * 		1,2,3,4,5,6,7,8,10
	 * 
	 * 	Then passing val = 9 will return an array of size 3 (Which index 0 represents lowermost layer, up to index 2 which represents layer 3
	 * 	the array passed should hold SkipNode object which is [ (8), (7), (5) ]
	 * 
	 * 	Meaning:	SkipNode value of 9 should be inserted after Node (8) at layer 1
	 * 									should be inserted after Node (7) at layer 2
	 * 									should be inserted after Node (5) at layer 3
	 */
	private SkipNode[] findPos (int val) {
		SkipNode[] toInsert = new SkipNode[limit];
		SkipNode curr = head;
		int level = limit - 1;
		
		while (level >= 0 ) {
			if ( curr.next[level].val >= val) {
				toInsert[level] = curr;
				level --;
			}
			else {
				curr = curr.next[level];
			}
		}
		
		return toInsert;
	}
	
	//Print each layer of the skip list
	private void printNode() {
		
		for (int i = limit - 1; i >= 0; i -- ) {
			System.out.print("Layer " + i + ": ");
			SkipNode curr = head;
			while (curr != null ) {
				System.out.print(curr.val + " ");
				curr = curr.next[i];
			}
			System.out.println();
		}
	}
	
	//Return a random number from 1 to limit exclusive. The probability follows that of flipped coin: 0.5, 0.25, 0.125... (Halves each time)
	// So that less number will go on the highest layer
	private int getRandom() {
		int lim = 1;
		while ( Math.random() >= 0.5 && lim < limit) {
			lim ++;
		}
		return lim;
	}
	
	
	public static void main(String[]args) {
		Skip_List sl = new Skip_List(6);
		for (int i = 0; i < 1000; i ++ ) {
			sl.insertNode(i);
		}
		sl.printNode();

		for (int i = 0; i < 1000; i ++ ) {
			sl.remove(i);
		}
		
		sl.printNode();
		
		sl.search(25);
		sl.search(10);
		sl.search(99);
		sl.search(100);
	}
	
	
}
