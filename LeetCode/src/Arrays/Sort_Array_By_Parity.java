package Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/511/in-place-operations/3260/

/*
 * 	Two pointer approach. Note that the approach can start from the start, or each pointer at each ends
 */

public class Sort_Array_By_Parity {
	
	public int[] sortArrayByParity(int[] A) {
		int indexI = 0;
		//Initialize the indexI from the first odd number occurrence
		while (indexI < A.length && A[indexI] % 2 == 0) {
			indexI ++;
		}
		//Index j also start from the starting point of index i
		int indexJ = indexI;
		//Index j is the slow runner and index i is the fast runner
		while (indexI < A.length ) {
			if (A[indexI] % 2 == 0) {
				int temp = A[indexI];
				A[indexI] = A[indexJ];
				A[indexJ ++] = temp;
			}
			indexI++;
		}
		return A;
    }
	
	
	public int[] sortArrayByParityVariant(int[] A) {
		int i = 0, j = A.length - 1;
		while (i < j) {
			//If it's odd at index I and even at index J
			if (A[i] % 2 > A[j] % 2 ) {
				int temp = A[j];
				A[j] = A[i];
				A[i] = temp;
			}
			
			//Increment I only if it's even at i (which is correct order)
			if (A[i] % 2 == 0) i++;
			//Decrement J only if it's odd at j (which is correct order)
			if (A[j] % 2 == 1) j--;
		}
		return A;
	}
	
	
	
}
