package easy;

//https://www.hackerrank.com/challenges/quicksort2/problem

//	More information about Quick Sort, refer Topics > Algorithms > Quick_Sort.java

public class QuickSort_2_Sorting {
	
	static void quickSort(int[] ar) {
    	quickSortRecurse(ar, 0, ar.length - 1);
    }
	static void quickSortRecurse(int[] arr, int left, int right) {
		if (left >= right) return;
		
		int pivot = partitioning(arr, left, right);
		
		quickSortRecurse(arr, left, pivot - 1);
		quickSortRecurse(arr, pivot+1, right);
		printPartialArray(arr, left, right);
	}
	//	Using same idea as previous challenge: Leftmost element as pivot
	//	However problem requests that the ordering of the partitioned array be preserved.
	//	Therefore partitioning have to perform multiple passes, but still O(N)
	private static int partitioning(int[] arr, int left, int right) {
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
	private static void printPartialArray(int[]arr, int left, int right) {
		for (int i = left; i <= right; i++ ) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	private static void swap(int[]arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
