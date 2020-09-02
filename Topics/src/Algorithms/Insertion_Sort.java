package Algorithms;

/*
 * 	Insertion sort is a simple sorting algorithm, similar to how we sort our deck of cards
 * 
 * 	We will go through each element except the first element, starting from element 2 (Index 1)
 * 
 * 	For each element, we will be checking the previous element (Element at behind), see if it is greater than the current element
 * 	we are holding.
 * 	
 * 	IF it is greater, then move the previous element to the current place, essentially shifting it forward.
 * 	Then we need to move the pointer backwards. Keep checking if the previous element is greater or not.
 * 	IF it is lesser, then we insert the value into the index!
 * 
 *  In Arrays implementation, this is like shifting. We store the selected element in the temp variable first. If the previous element
 * 	is greater, shift it forward, overwriting the original element's place, keep doing this until we've met the suitable place
 * 	where previous element is lesser than the value we're holding, then we INSERT it, hence the name INSERTION SORT
 * 
 * 							  V
 * 	Eg: In the array [1,3,5,7,2], Assume now we are selecting the last element (2) to sort:
 * 																						     V
 * 	>	First we store 2 in a temp variable. The pointer is now at index 4 (Last)	[1,3,5,7,2]
 * 																					   V 
 * 	>	Since prev element is greater (7), shift it and decrement pointer ====>	[1,3,5,7,7]	(We copied prev element into current place)
 * 																				    V
 * 	>	Since prev element is greater (5), shift it and decrement pointer ===> [1,3,5,5,7] 
 * 																				  V
 * 	>	Since prev element is greater (3), shift it and decrement pointer ===> [1,3,3,5,7]
 * 																					    V
 * 	>	Now prev element (1) is lesser. We insert the temp value into the index ===> [1,2,3,5,7]
 * 
 * 
 * 	Time complexity is O(N^2). FOr each element, we might need to shift until the beginning of array
 * 	
 * 
 * 
 * 	Note:	This can be also implemented in linked list.
 * 		
 */

public class Insertion_Sort {
	
	static void insertionSort2(int n, int[] arr) {
		for (int i = 1; i < arr.length; i ++ ) {
			//If the current element is greater than previous element, don't touch it and continue the iteration
			if (arr[i-1] <= arr[i] ) {
				printArr(arr);
				continue;
			}
			else {
				int temp = arr[i];
				//The empty spot's location. As the element is shifted, the empty location goes forward to the head of array
				int location = i;
				while (location != 0 && arr[location - 1] > temp) {
					arr[location] = arr[location - 1];
					location --;
				}
				arr[location] = temp;
				printArr(arr);
			}
		}
	}
	
	static void printArr(int[] arr) {
		for (int n: arr) System.out.print(n + " ");
		System.out.println();
	}
	
	
}
