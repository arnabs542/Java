package Algorithms;

import java.util.Arrays;

/*
 * 	Insertion sort is a simple sorting algorithm, similar to how we sort our deck of cards
 * 
 * 	Say we have deck of unsorted cards. We may intuitively do the following to sort the cards:
 * 		1. 	Separate the cards into two parts - [Sorted][Unsorted]
 * 			Say our cards is [8,4,6,2]. Then Sorted = [8], unsorted = [4,6,2]
 * 			Say our cards is [4,8,6,2]. Then sorted = [4,8], unsorted = [6,2]
 * 
 * 		2. 	Then, we take the leftmost card in the unsorted partition, and TRIES to insert it in correct position in
 * 			the sorted partition
 * 
 * 			Eg: [4,8][6,2] ==> In this pass, we will insert 6. It shall insert at [4, X, 8]
 * 			Eg: [8][4,6,2] ==> In this pass, we will insert 4. It shall insert at [X, 8]
 * 
 * 	=============================================================================================
 * 
 * 	We always start out with sorted partition of one element, because an array of one element is always sorted in nature.
 * 
 *	Each iteration, first take the leftmost element in the unsorted partition. Let such element = e.
 *	This is the element we'll want to 'insert' into the sorted partition.
 *	 
 * 	Then for each element in unsorted partition from right to left (let index = i), we will keep checking with the element 
 *	to insert, e
 *  
 * 		IF arr[i] > e, that means e shall be at position behind of arr[i]! Move arr[i] to i+1, or you could think of it as
 * 		shifting arr[i] 1 place to the right.
 * 		Then we need to move the pointer backwards, continue the checking
 * 
 * 		IF arr[i] <= e, that means e shall be at position AFTER arr[i]. Therefore, immediately place e at position i+1. We
 * 		are done!
 * 
 *  In Arrays implementation, this is like shifting. We store the selected element in the temp variable first. If the previous element
 * 	is greater, shift it forward, overwriting the original element's place, keep doing this until we've met the suitable place
 * 	where previous element is lesser than the value we're holding, then we INSERT it, hence the name INSERTION SORT
 * 
 * 							  
 * 	Eg: In the array [1,3,5,7,2]. Sorted = [1,3,5,7], Unsorted=[2]. Now we are selecting the last element (2) to insert:
 * 																						  					   V
 * 	>	First we store 2 in a temp variable. The pointer is now at index 3 (Last in unsorted partition)	[1,3,5,7,2]
 * 																			 V		    
 * 	>	Since 7 is greater than 2, shift it and decrement pointer ====>	[1,3,5,7,7]	(We copied 7 to the next position)
 * 																		  V		    
 * 	>	Since 5 is greater than 2, shift it and decrement pointer ===> [1,3,5,5,7] 
 * 																		V	 
 * 	>	Since 3 is greater than 2, shift it and decrement pointer ===> [1,3,3,5,7]
 * 																					   
 * 	>	Now prev element (1) is lesser. We insert the temp value into the index i+1, which is the position after (1) ===> [1,2,3,5,7]
 * 
 * 	======================================================================================================
 * 
 * 	Time complexity is O(N^2). For each element, we might need to shift until the beginning of array
 * 
 * 	Note:	This can be also implemented in linked list.
 */

public class Insertion_Sort {
	
	static void insertionSort(int[] arr) {
		// Passes iteration. i points at the leftmost element in unsorted partition
		for (int i = 1; i < arr.length; i ++ ) {
			int toIns = arr[i];
			
			int pointer = i-1;
			while (pointer != -1 && arr[pointer] > toIns) {
				arr[pointer+1] = arr[pointer];
				--pointer;
			}
			
			// Insertion to correct position
			arr[pointer+1] = toIns;
		}
	}
	
	
	
	
	// Test cases
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		int[] arr2 = {9,8,7,6,5,4,3,2,1};
		int[] arr3 = {4,1,5,6,3,2,8,7,9};
		
		insertionSort(arr);
		insertionSort(arr2);
		insertionSort(arr3);
		
		System.out.println( Arrays.toString(arr) );
		System.out.println( Arrays.toString(arr2) );
		System.out.println( Arrays.toString(arr3) );
	}
	
	
}
