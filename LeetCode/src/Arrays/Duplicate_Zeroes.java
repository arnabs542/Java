package Arrays;

//https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3245/

/*
 *	This question doesn't allow us to create a new array and return it. It requires us to manipulate the array on site. Therefore,
 *	it's easy to come up with a O(n^2) solution where everytime a 0 is met, shift all elements to the right of the 0 one step to the right,
 *	then duplicate the zero at the right of it.
 *
 *	Another solution of O(n) is to make two passes. From one pass we could find out how many zeroes would be discarded when the actual array
 *	is made, then in the second pass, we would copy the array from behind until front, to preserve the data that we haven't copied yet (and preserve
 *	O(1) space complexity). However with this method be careful that if the tail (the last scanned element) is zero and we don't have more spaces
 *	to put the zero inside, we simply copy the zero and skip scanning that zero element. (Edge case)
 */

public class Duplicate_Zeroes {
    
	//O(N^2)
//	public void duplicateZeros(int[] arr) {
//		for (int i = 0; i < arr.length; i++ ) {
//			if (arr[i] == 0) {
//				for (int pos = arr.length - 2; pos > i; pos -- ) {
//					arr[pos+1] = arr[pos];
//                }
//                if (i + 1 < arr.length)
//                    arr[i+1] = 0;
//                i++;
//			}
//		}
//    }
	
	//O(N) solution of 2 passes
	public void duplicateZeros(int[] arr) {
		
		//First pass, determine how many element will be removed
		int left = 0;
		int possibleDup = 0;
		int startCopy = arr.length - 1;
		for ( ; left < arr.length - possibleDup; left ++ ) {
			if (arr[left] == 0) possibleDup++;
		}
		//Edge case check
		if (left + possibleDup > arr.length ) {
			left --;
			arr[arr.length - 1] = 0;
			startCopy --;
		}
        left--;
		
		//Second pass, to copy the front array from behind
		for ( ; startCopy >= 0 ; startCopy --) {
            System.out.println(startCopy + " " + left);
			arr[startCopy] = arr[left];
			if (arr[left] == 0) {
				arr[startCopy-1] = 0;
				startCopy--;
			}
			left --;
		}
	}
	
}
