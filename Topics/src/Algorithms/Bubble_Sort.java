package Algorithms;

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
		int numOfSwap = -1;				//Instead of using the numOfSwap, you could also use break statement
		int lastSorted = 1;				//Optimization feature, keep track how many iteration is done, therefore on next iteration no need go till end
		while (numOfSwap != 0) {
			numOfSwap = 0;
			for (int i = 0; i < arr.length - lastSorted; i ++ ) {
				if (arr[i] > arr[i+1] ) {
					numOfSwap ++;
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
			lastSorted ++;
		}
		return arr;
	}
	
	public static void main(String[]args) {
		int[] arr = bubbleSort(new int[] {7,5,4,6,2});
		for (int i : arr) 
			System.out.println(i);
	}
	
	
	
}
