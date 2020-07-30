package Algorithms;

/*
 * 	The insertion sort is almost similar to selection sort, but a little more efficient.
 * 	First iterating from second element (index 1), it checks whether the element at index 0 is greater.
 * 		YES - second element is stored in a temp variable, first element is shifted down to index 1, and index 0 is where the temp variable
 * 			  settles down (second element)
 * 		NO - everything is in place, do not touch and continue the iteration
 * 	
 * 	This procedure continues as third element, fourth element and so on until the last element. We're basically trying to insert an element
 * 	one by one into the sorted array segment, hence the name Insertion sort
 * 
 * 	Eg: 1,3,5,8 (4) <- New element to insert, is stored in temp variable
 * 	
 * 	First iteration	:	1,3,5,_,8			(As long as the element before the empty space is greater than element to insert, then shift the left element
 * 	Second iteration:	1,3,_,5,8
 * 	Third iteration :	1,3,(4),5,8			<- 4 Settles in because the element before the empty space, 3 is lesser than 4
 * 	
 * 	
 * 	Every time the checked element is lesser than the previous one, all the previous elements greater than the current element is shifted,
 * 	until a lesser element is met which the current element will be inserted in front of that element
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
