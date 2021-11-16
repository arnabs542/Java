package Algorithms;

import java.util.Arrays;

/*
 * 		Merge Sort uses a Divide and conquer algorithm to sort the items in an array. The concept is quite simple:
 * 		Split the array into two halves, sort them, and then combine those 2 arrays into 1 sorted array
 * 
 * ---------------------------------------2 WAY MERGE SORT -------------------------------------------------------------
 * 
 * 		Before going in to the Merge sort algorithm itself, we need to know about 2-Way Merge Sort, which is HOW are we going to
 * 		combine 2 SORTED arrays into 1.
 * 
 * 		Given 2 sorted arrays like [1,6,9] and [3,4,5]. We need to combine them, and we'll use two pointers technique to achieve this
 * 		If we are using array, then we need to have a temporary array to store the sorted array, and copy it back into original array itself.
 * 		
 * 		Initialize the 2 pointers on the start of both sorted arrays, A and B. While neither of the pointers are out of range, we'll compare the
 * 		values of the 2 pointers, and take the minimal of the two, put it into the temporary array, and increment the temp array pointer as well as
 * 		the left or right pointer by one (Depends on which value is minimum)
 * 
 * 		Until one point if one of the pointers go out of range, that means we'll just copy the rest of another array into the temporary array and call it done
 * 		
 * 									A				B 				TEMP		*Bracket () means pointer location
 * 		So for example above: [ (1), 6, 9] vs [(3), 4, 5]		->	[1] 
 * 							  [ 1, (6), 9] vs [(3), 4, 5]		-> 	[1,3]
 * 							  [ 1, (6), 9] vs [3, (4), 5]		-> 	[1,3,4]
 * 							  [ 1, (6), 9] vs [3, 4, (5)]		-> 	[1,3,4,5]
 * 							  [ 1, (6), 9] vs [3, 4, 5]			-> 	[1,3,4,5,6]	*NOte from here, pointer B ran out of range, therefore just copy the rest of A
 * 							  [ 1, 6, (9)] vs [3,4,5]			-> 	[1,3,4,5,6,9]
 * 		
 * 		Time complexity for this is O(M + N) where M is length of A and N is length of B. We could say this is O(N) generally
 * 
 * 
 * 	------------------------------------------------MERGE SORT-----------------------------------------------------------------------------------------
 * 
 * 		Therefore using the above concept, we could perform merge sort already.
 * 		Given a length n array, we divide it into two halves, seperated by a middle position. Have them sorted, then we can perform the merging using above
 * 		algorithm.
 * 		When we split it into two halves, the splitting alone isn't going to do the sorting! Therefore, on one of the split halves, it has to be split again
 * 		and again, until it reaches only a single element, which is by nature, SORTED.
 * 		On that point, the recursion returns, and we will start from MERGING 2 Parts, each consisting of 1 element. (Basically we sorting 2 elements)
 * 		Once merged, it can be returned again up one recursion stack, which we will MERGE 2 Parts again, each consisting of 1 to 2 elements.
 * 
 * 		This process continues up until we are back to original question. Merging 2 split parts of the original array.
 * 
 * 		Algorithm kind of looks like this:
 * 
 * 			mergeSort( left, right) {
 * 				if (left < right) {						//While this section still consists of 2 or more element, split it
 * 					mid = (left + right ) / 2			//Obtain the position to split
 * 					mergeSort(left, mid)				//Recursive call on the left part, so that left part will be sorted
 * 					mergeSort(mid + 1, right)			//Recursive call on the right part, so that right part will be sorted
 * 										
 * 					MERGE( left, right)					//Now that left and right part are both sorted, merge them
 * 				}
 * 			}
 * 
 * 		By splitting the array into two each time, we will have maximum LOG N recursion stack each time.	
 * 		In each Recursion stack, we will be dealing with N elements in the array
 * 		Therefore the time complexity is O(N log N)
 * 
 * 
 * 	--------------------------------------------------------ANALYSIS--------------------------------------------------
 * 
 * 		+ Suitable for Large size lists
 * 		+ Most Suitable for Linked lists, where we don't need a copy or temporarly array, just re-establishing the links of nodes are enough
 * 		+ External Sorting.	Notice the merging of the linked list: It only needs 2 element at a time. Therefore it can perform external sorting where
 * 		  					most data are stored on the drives, datas are fetched only when the sorting needs it
 * 		+ Stable. Keeps the natural ordering of the original array in case of duplicate elements
 * 
 * 		- Space Consuming (Not applied for linked list). Sorting will not be done in place for arrays, and will need a temporarly array
 * 		- Slow in small problems due to recursion, which Insertion sort (O(N^2) ) or bubble sort may be faster
 * 		
 */

public class Merge_Sort {
	
	public static void mergeSort(int[] arr) {
		mergeSort(arr, new int[arr.length], 0, arr.length - 1);
	}
	
	private static void mergeSort(int[] arr, int[] temp, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(arr, temp, left, mid);
			mergeSort(arr, temp, mid + 1, right);
			twoWayMerge(arr, temp, left, right);
		}
	}
	
	private static void twoWayMerge(int[] arr, int[] temp, int left, int right) {
		int mid = left + (right - left) / 2;
		int leftPoint = left, rightPoint = mid + 1;
		
		int index = left;
		
		while (leftPoint <= mid && rightPoint <= right) {
			if (arr[leftPoint] <= arr[rightPoint] ) {
				temp[index] = arr[leftPoint];
				leftPoint ++;
			}
			else {
				temp[index] = arr[rightPoint];
				rightPoint ++;
			}
			++index;
		}
		
		//USING JAVA LIBRARY METHOD arraycopy( src, srcPos, desc, descPos, length)
		System.arraycopy( arr, leftPoint, temp, index, mid - leftPoint + 1);
		System.arraycopy( arr, rightPoint, temp, index, right - rightPoint + 1);
		System.arraycopy( temp, left, arr, left, right - left + 1);
	}
	
	
	
	
	//=================================================
	// To be parallel with UTM syllabus format:
	//=================================================
	static int MAX_SIZE;
	
	public static void mergeSortUTM(int[] arr) {
		MAX_SIZE = arr.length;
		mergeSortUTM(arr, 0, arr.length - 1);
	}
	
	private static void mergeSortUTM(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSortUTM(arr, left, mid);
			mergeSortUTM(arr, mid + 1, right);
			twoWayMergeUTM(arr, left, mid, right);
		}
	}
	
	private static void twoWayMergeUTM(int[] arr, int left, int mid, int right) {
		int[] temp = new int[ MAX_SIZE ];
		int first1 = left;
		int last1 = mid;
		int first2 = mid + 1;
		int last2 = right;
		int index = first1;
		
		for (; (first1 <= last1) && (first2 <= last2); ++index) {
			if (arr[first1] < arr[first2]) {
				temp[index] = arr[first1];
				++first1;
			}
			else {
				temp[index] = arr[first2];
				++first2;
			}
		}
		
		for (; first1 <= last1; ++first1, ++index)
			temp[index] = arr[first1];
		for (; first2 <= last2; ++first2, ++index)
			temp[index] = arr[first2];
		for (index = left; index <= right; ++index)
			arr[index] = temp[index];
	}
	
	
	
	
	
	//==========================================
	public static void main(String[]args) {
		int[] arr = {1,4,8,5,6,3,0};
		mergeSortUTM(arr);
		
		System.out.println( Arrays.toString(arr) );
	}

}
