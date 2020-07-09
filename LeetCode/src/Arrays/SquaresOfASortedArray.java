package Arrays;

//https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/

/*
 * 	I attempted to use binary search to find for the first index of the element which is first to be positive (previous element is negative)
 * 		-All positive array > returns 0
 * 		-All negative array > returns last index
 * 		-Length 1 array 	> returns 0
 * 
 * 	With the index obtained, Notice the array is provided in ascending order, therefore for negative part of the array, going further to the
 * 	left will end up squaring bigger, and for positive part of the array, going further to the right will end up squaring bigger as well.
 * 
 * 	Therefore I make 2 pointers one pointing to the beginning of the positive part and one pointing to the "tail" of the negative part, and
 * 	each loop I'll determine which of these will give smaller squares and include it into the resulting array
 * 
 * 
 * 	** The nested conditional checking is quite messy. Instead I can loop only when both of the pointers are in range, then outside
 * 	   the loop, i add 2 loops to loop through the remaining elements.
 */

public class SquaresOfASortedArray {

	public static int[] sortedSquares(int[] A) {
		int firstPos = firstPositive(A);
		
		int[] soln = new int[A.length];
		int right = firstPos;
		int left = firstPos - 1;
		
		for (int i = 0; i < A.length; i++ ) {
			//Both of the pointers are still in range
			if (right < A.length && left >= 0 ) {
				//Right pointer is smaller, fill with square of right pointer
				if (A[right] < A[left] * -1 ) {
					soln[i] = A[right] * A[right];
					right ++;
				}
				else {
					soln[i] = A[left] * A[left];
					left --;
				}
			}
			//Left pointer is the one in range; Right pointer is outside already
			else if (left >= 0) {
				soln[i] = A[left] * A[left];
				left --;
			}
			//RIght pointer is the one in range; Left pointer is outside already
			else {
				soln[i] = A[right] * A[right];
				right ++;
			}
		}
		
		return soln;
    }
	
	//Binary search algorithm to return the first index of the FIRST positive element (the previous element is negative)
	public static int firstPositive(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] >= 0 && mid-1 >= 0 && arr[mid-1] < 0)
				return mid;
			else if (arr[mid] >= 0)
				right = mid;
			else
				left = mid + 1;
		}
		return left;	
	}
	
	
	public static void main(String[]args) {
		int[] arr = {-4,-3,-2,-1};
		int[] ans = sortedSquares(arr);
		
		for (int i: ans) {
			System.out.println(i);
		}
	}
}
