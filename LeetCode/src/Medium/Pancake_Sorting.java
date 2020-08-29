package Medium;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/pancake-sorting/

/*
 * 	This is a Sorting Problem
 * 	
 * 	Notice the property of a pancake flip: We will always be flipping a prefix of the array, no matter what k we choose
 * 
 * 	Therefore, to sort the array, we can utilize the similar approach to Selection Sort.
 * 	Recall that how Selection sort works:
 * 		>	Iterate through the array to find the maximum value which shall be at the end of array
 * 		>	Swap it with the last element in the array
 * 		>	Repeat the process but with the sub-array which is length N - 1. (Excluding the last swapped element)
 * 
 * 	With pancake flipping, we can perform the similar technique as well, but with additional steps:
 * 		>	Iterate through the array to find the maximum value
 * 		>	Since pancake flipping the array from idx 0 up to the index of the maximum value causes the maximum value to be
 * 			reversed to the first element, do it
 * 		>	With the maximum element as the front element, reverse the entire (sub)-array, so that the maximum element will be
 * 			reversed to it's supposed place.
 * 		>	Shrink the array by size 1
 * 
 */

public class Pancake_Sorting {
	
	public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new LinkedList<>();
        
        for (int i = A.length - 1; i >= 0; i -- ) {
        	int max = A[0];
        	int maxIdx = 0;
        	for (int idx = 0; idx <= i; idx ++ ) {
        		if (A[idx] > max) {
        			max = A[idx];
        			maxIdx = idx;
        		}
        	}
        	
        	
        	if (maxIdx == i) continue;
        	
        	if (maxIdx != 0) {
	        	revSubArr(A, maxIdx);
	        	res.add(maxIdx + 1);
        	}
        	
        	revSubArr(A, i);
        	res.add(i + 1);
        }
        
        return res;
    }
	
	//	The limit index is inclusive. That means element at index limit will also be swapped
	private static void revSubArr(int[] arr, int limit) {
		int mid = (limit + 1) / 2;
		for (int i = 0; i < mid; i ++ ) {
			int temp = arr[i];
			arr[i] = arr[limit - i];
			arr[limit - i] = temp;
		}
	}

}
