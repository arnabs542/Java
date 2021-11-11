package Algorithms;

import java.util.Arrays;
import java.util.List;

/*
 * 	Selection sort is a sorting algorithm which is simple but quite inefficient. It loops n iterations which finds the index containing
 *  maximum element each time, and swaps them with the last element in range of unsorted elements
 *  
 * 	The range of sorted array increases by 1 element each time, effectively narrowing down the sort range.
 * 
 *	Note that the reverse technique is also applicable: Find minimum element each time, and swap with the first element in range
 *	of unsorted elements.
 */

public class Selection_Sort {

	// Generic selection sort function
	public static <T extends Comparable<T>> List<T> selectSort(List<T> arr) {
		final int len = arr.size();
		
		for (int i = 0; i < len; ++i) {
			int maxIndex = 0;
			
			// 1 - Locate maxIndex - maximum element's index among unsorted range
			for (int j = 0; j < len - i; ++j ) {
				if ( arr.get(maxIndex).compareTo( arr.get(j) ) < 0)
					maxIndex = j;
			}
			
			// 2 - Swap min element with the last element in unsorted range
			T temp = arr.get(maxIndex);
			arr.set(maxIndex, arr.get( len - i - 1 ) );
			arr.set(len - i - 1, temp);
		}
		return arr;
	}
	 
	
	
	
	
	
	// Test cases
	public static void main(String[]args) {
		List<Integer> arr = Arrays.asList(4,1,3,2,5,7,6,9,8);
		List<Integer> arr2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		List<Integer> arr3 = Arrays.asList(9,8,7,6,5,4,3,2,1);
		
		System.out.println(selectSort(arr) );
		System.out.println(selectSort(arr2) );
		System.out.println(selectSort(arr3) );
	}
	
}
