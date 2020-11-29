package easy;

//https://www.hackerrank.com/challenges/quicksort1/problem

//	For information on Quick SOrt, Refer Topics > Algorithms > Quick_Sort.java

public class QuickSort_1_Partition {
	
	static int[] quickSort(int[] arr) {
		int len = arr.length;
		int i = len - 1;
		
		//	Remember: Leftmost element is pivot, and we have to do it reverse
		for (int j = i; j > 0; j--) {
			if ( arr[0] < arr[j] ) {
				swap(arr, i, j);
				i--;
			}
		}
		swap(arr, 0, i);
		return arr;
	}
	private static void swap(int[]arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
