package Algorithms;

import java.util.Arrays;

/*
 * 	Bubble sort, with the complexity O(n2), has the following workings:
 * 	Keep looping through the array, if the current element is larger compared to the next one, swap them, and keep track of the number of times
 *  swapped throughout one iteration.
 *  If the iteration is done without any swapping, then it is said that array is sorted and ready to be returned.
 *  
 *  One optimization key is that on the first time array is iterated, the last element in the array is always sorted as the maximum.
 *  Therefore there is no need to iterate until end of the array on iteration 2, just until the previous one's - 1, and so on...
 */


public class Bubble_Sort {

	static int[] bubbleSort(int[] arr) {
		
		// Outer loop - Maximum iterations we will go is N-1 iterations. Each iteration will result in 1 element getting sorted
		for (int i = 1; i < arr.length; ++i) {
			boolean isSorted = true;
			
			for (int j = 1; j < arr.length - i + 1; ++j) {
				// If the previous element is LARGER than current element, perform swapping
				if (arr[j] < arr[j-1]) {
					isSorted = false;
					// Swap
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
			
			// Optimization - Early termination
			if (isSorted) {
				System.out.printf("Early termination: Ran i = %d times\n", i);
				return arr;
			}
		}
		return arr;
	}
	
	
	
	
	
	
	// Test cases
	public static void main(String[]args) {
		int[] arr = bubbleSort(new int[] {7,5,4,6,2});
		int[] arr2 = bubbleSort(new int[] {9,8,7,6,5,4,3,2,1});
		int[] arr3 = bubbleSort(new int[] {1,2,3,4,5});
		
		System.out.println( Arrays.toString(arr) );
		System.out.println( Arrays.toString(arr2) );
		System.out.println( Arrays.toString(arr3) );
	}
	
	
	
}
