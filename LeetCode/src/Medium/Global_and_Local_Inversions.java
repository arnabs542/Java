package Medium;

//https://leetcode.com/problems/global-and-local-inversions/
/*
 * 	This is an array problem with some math twist
 * 
 * 	The key point to realize and solve this easily, is notice the array consists of indices of array, aka the array
 * 	is permutations of (0,1,2...n-1)
 * 
 * 	What can you deduce from this property? See this: (0,3,1,2). At index 1, if the number is 3, what this means?
 * 	It means, we skipped 1 and 2, and they will be placed later in the array! If this is the case, then 2 global inversions
 * 	is produced! Add 2 to the global inversion counter!
 * 
 * 	To check for local inversion is easy task. Simply compare this current element with the next one.
 * 
 * 	Now, we can optimize here: once global inversion exceeds local inversion, instantly return true. Why is this the case?
 * 	Becuase local inversions could never overchase global inversion anymore, simply due to the fact that:
 * 
 * 		>	Every local inversion is also a global inversion. So if local inversion + 1, global inversion also + 1!
 * 			There is no single case where local inversion increases while global inversion stay still!
 * 
 * 	-------------------------------
 * 
 * 	This leads to the shortest code. If at an index i, the element at it is at least i+2, then i know global inversion
 * 	instantly +2 while local inversion +1 only. Then i can already return false
 */

public class Global_and_Local_Inversions {
	public boolean isIdealPermutation(int[] A) {
		int global = 0;
		int local = 0;
		
		for (int i = 0; i < A.length - 1; ++i) {
			global += Math.max( A[i] - i, 0 );
			if (A[i] > A[i+1]) ++local;
			
			if (global > local) return false;
		}
		return true;
	}
	
	
	public boolean isIdealPermutation2(int[] A) {
		for (int i = 0; i < A.length; ++i)
			if (Math.abs(A[i] - i) >= 2) return false;
		return true;
	}
}
