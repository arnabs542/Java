package Algorithms;

import java.util.Arrays;

/*
 * 	A QuickSort can be considered a competitor of Merge Sort, which both has the best time complexity of O(N log N)
 * 	The difference is
 * 		>	In Worst Case, Quick Sort uses O(N^2) time complexity as well as O(N) space instead of supposed O(log N) space
 * 		>	Merge sort is guaranteed O(N log N) time, but must be O(N) space
 * 
 * 	The key point of Quick Sort is the partitioning algorithm. What the algorithm does is basically
 * 		>	Set a pivot element
 *		>	find the respective position of that pivot in the array. The respective position of the pivot is the index i in which 
 *			to the left, all elements are always lesser than the pivot, while on the right, all elements are always greater, but both sides 
 *			are not necessary sorted
 *
 *	Ideally, the pivot element should be in the center of the array. Then, we basically have 1 element in its correct place already.
 *	Then, we would recurse on the left side and right side of the array, which ideally is split into 2 equal half parts.
 *	
 *	The base case would be when the subarray is size 1 only. In that case just return, since it is already sorted.
 *	
 *	Ideally, the recursion would go as far as O(log N) only, because the array is split into half in length each time.
 *
 *	However, in the worst case where the bad pivot is picked, and the position of pivot is at the end of array, then it will result in
 *	O(N) recursion stack, and in each recursion, we have to traverse N times, resulting in time complexity of O(N^2)
 *
 *	---------------------------------------------------------------------------------------------------------------------------
 *
 *	The partitioning algorithm
 *
 *	First, we would pick a pivot element. It is commonly the last element in the subarray, but it can be any place (may be harder
 *	to implement). That last pivoted element will be isolated, and left untouched until end of iteration of current partition call.
 *
 *	Now, we need two pointers, I and J.
 *		>	J is the explorer pointer, will pass through the subarray from left to right.
 *		>	I is the 'head' of the partitioned elements which has the value larger than the pivot element itself. Don't worry
 *			when the pivot is largest element, as I will end up pointing to pivot element at the end.
 *
 *	Now, I and J starts from leftmost of subarray, and J will start checking in this algorithm:
 *		
 *			for (j = left, j < right, j++ )
 *				if ( array[j] < pivot )
 *					swap array[i], array[j] 
 *					i ++
 *		
 *	What this does is, since J pointer moves from left to right, whenever J encounters an element smaller than the pivot element, 
 *	the lesser element should ideally be positioned as leftmost as possible. Since greater elements are ignored, pointer I should
 *	be pointing to a greater element. Swap the lesser element at J with greater element at I. This way, the lesser goes to left,
 *	and greater goes to right side. 
 *	Then, I is incremented so it points to the next greater element to be swapped in case a lesser element is encountered again
 *	by pointer J.
 *
 *	At the end, the pointer I should be pointing at the HEAD OF PARTITIONED SUBARRAY WHICH IS GREATER THAN THE PIVOT. Now, swap the
 *	pointer I with the pivot element, in that way, it is ensured that the pivot element's left side is all lesser, and right side
 *	is all greater.
 *
 *	With that partitioned, recurse on the left side and right side. Hopefully, it gets split in half and results in sorted array
 *	done in O(N log N).
 */

public class Quick_Sort {
	
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	
	
	private static void quickSort(int[] arr, int left, int right) {
		if (left >= right) return;	//	Array is size 1 or less. Do nothing
		
		//	Call partitioning algorithm on specific subarray range, and get the partitioned index
		int pivot = partition(arr, left, right);
		
		//	Then, recurse on left and right side around the partitioned element
		quickSort(arr, left, pivot-1);
		quickSort(arr, pivot+1, right);
	}
	
	
	//	The rightmost element in the range is the pivot for us for ease of algorithm
	//	Eg: [1,2,3,4,5] then 5 will be used as pivot
	private static int partition(int[] arr, int left, int right) {
		int pivot = arr[right];
		int i = left;
		
		for (int j = left; j < right; j++ ) {
			//	Element in j is lesser, and should be placed left side as possible, where pointer i is at
			if (pivot > arr[j] ) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, right);
		return i;
	}
	
	
	
	// BONUS: Using leftmost element as pivot.
	// !!! HOWEVER, THIS WAY OF PARTITIONING WILL MESS UP THE RELATIVE ORDERING OF THE ARRAY !!!
	//
	// Eg: [ (3), 1, 1, 1, 4, 5 ] where (3) is the pivot.
	// At the end, rightmost '1' will be swapped with pivot (3), resulting in [ 1, 1, 1, 3, 4, 5 ].
	// Although this is correct, but the relative ordering of '1's are messed up already, the third '1' had become first now
	private static int partitionWithLeftPivot(int[] arr, int left, int right) {
		int pivot = arr[left];
		// Do not touch the pivot element.
		int i = left + 1;
		
		for (int j = left + 1; j <= right; ++j) {
			if ( arr[j] < pivot ) {
				swap(arr, i, j);
				++i;
			}
		}
		// Because i is pointing at leftmost larger than pivot element, i-1 is one of smaller elements. 
		swap(arr, left, i-1);
		return i-1; 
	}
	
	
	
	
	//=================================================
	// To be parallel with UTM syllabus format:
	//=================================================
	public static void quickSortUTM(int[] arr) {
		quickSortUTM(arr, 0, arr.length - 1);
	}
	
	
	
	private static void quickSortUTM(int[] arr, int left, int right) {
		if (left >= right) return;	//	Array is size 1 or less. Do nothing
		
		//	Call partitioning algorithm on specific subarray range, and get the partitioned index
		int pivot = partitionUTM(arr, left, right);
		
		//	Then, recurse on left and right side around the partitioned element
		quickSortUTM(arr, left, pivot-1);
		quickSortUTM(arr, pivot+1, right);
	}
	
	
	// UTM syllabus uses a different partitioning algorithm
	// It has major flaws - When there are duplicate elements in the array, the algorithm results in
	// 						infinite loop.
	private static int partitionUTM(int[] arr, int left, int right) {
		int pivot = arr[left];
		int l = left, r = right;
		
		while (l < r) {
			while (arr[r] > pivot) --r;
			while (arr[l] < pivot) ++l;
			
			if (l < r) swap(arr, l, r);			
		}
		// At the end, l and r pointer will both stop at pivot
		return l;
	}
	
	

	//==============================================================
	private static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	
	public static void main(String[]args) {
		int[] arr = {5,1,3,7,5,5,8,10,3};
		quickSort(arr);
		int[] arr2 = {9,9,9,8,8,8,7,7,7,6,6,6,5,5,5};
		quickSort(arr2);
		int[] arr3 = {5,15,7,2,4,1,8,10,3};
		quickSortUTM(arr3);
		
		System.out.println( Arrays.toString(arr) );
		System.out.println( Arrays.toString(arr2) );
		System.out.println( Arrays.toString(arr3) );
	}
}
