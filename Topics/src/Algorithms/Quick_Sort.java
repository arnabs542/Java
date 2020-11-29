package Algorithms;

/*
 * 	A QuickSort can be considered a competitor of Merge Sort, which both has the best time complexity of O(N log N)
 * 	The difference is
 * 		>	In Worst Case, Quick Sort uses O(N^2) time complexity as well as O(N) space instead of supposed O(log N) space
 * 		>	Merge sort is guaranteed O(N log N) time, but must be O(N) space
 * 
 * 	The key point of Quick Sort is the partitioning algorithm. What the algorithm does is basically
 * 		>	Set a pivot element
 *		>	find the respective position of that element in the array. The respective position means the position in which 
 *			to the left, all elements are always lesser while on the right, all elements are always greater, but not necessary 
 *			sorted
 *
 *	Ideally, the pivot element should be in the center of the array. Then, we basically have 1 element in its correct place already.
 *	Then, we would recurse on the left side and right side of the array, which ideally is split into 2 equal half parts.
 *	
 *	The base case would be when the subarray is size 1 only. In that case just return, since it is already sorted.
 *	
 *	Ideally, the recursion would go as far as O(log N) only, because the array is split into half in length each time.
 *
 *	However, in the worst case where the bad pivot is pick, and the position of pivot is at the end of array, then it will result in
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
	//	Driver method
	public static void quickSort(int[] arr) {
		quickSortRecurse(arr, 0, arr.length - 1);
	}
	
	
	private static void quickSortRecurse(int[] arr, int left, int right) {
		if (left >= right) return;	//	Array is size 1 or less. Do nothing
		
		//	Call partitioning algorithm on specific subarray range, and get the partitioned index
		int pivot = partitionOtherWay2(arr, left, right);
		
		//	Then, recurse on left and right side around the partitioned element
		quickSortRecurse(arr, left, pivot-1);
		quickSortRecurse(arr, pivot+1, right);
	}
	
	
	//	The rightmost element in the range is the pivot for us for ease of algorithm
	//	Eg: [1,2,3,4,5] then 5 will be used as pivot
	private static int partition(int[] arr, int left, int right) {
		int pivot = arr[right];
		int i = left;
		
		for (int j = left; j < right; j++ ) {
			//	Element in J is lesser, and should be placed left side as possible, where pointer I was
			if (pivot > arr[j] ) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, right);
		return i;
	}
	
	
	private static void swap(int[]arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	
	//BONUS: If you decide to use the leftmost element as the pivot, we cannot make i and j go right. The entire thinking has
	//		 to be reversed: i and j go R to L, and when pivot is less than elem, swap and decrement i
	private static int partitionOtherWay(int[] arr, int left, int right) {
		int pivot = arr[left];
		int i = right;
		
		for (int j = right; j > left; j--) {
			if (pivot < arr[j] ) {
				swap(arr, i, j);
				i--;
			}
		}
		swap(arr, left, i);
		return i;
	}
	
	
	//	However, above method will certainly mess up the original array's ordering by swapping. Therefore, this is alternative way but
	//	it takes more time. Becuase when it encounters a larger element, it shifts every element left until the element
	//	reaches the end...
	private static int partitionOtherWay2(int[] arr, int left, int right) {
		int pivot = arr[left];
		int i = right;
		
		for (int j = right; j > left; j --) {
			if (pivot < arr[j]) {
				int temp = arr[j];
				int k = j;
				while (k + 1 <= right && arr[k + 1] < pivot) {
					arr[k] = arr[k + 1];
					k++;
				}
				arr[k] = temp;
				i--;
			}
		}
		for (int l = left; l < i; l++) {
			arr[l] = arr[l+1];
		}
		arr[i] = pivot;
		return i;
	}
	
	
}
