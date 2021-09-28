package Easy;

//https://leetcode.com/problems/sort-array-by-parity-ii/
/*
 * 	This is an array problem.
 * 
 * 	If we are using extra space (or avoiding inplace mutation), then it is relatively easy to solve.
 * 
 * 	Otherwise, we must use two pointers, to refer to two respective places where we have to swap:
 * 	-	Index is odd but element is even
 * 	-	Index is even but element is odd
 *
 *	Using two pointers, locate these two elements respectively, and swap them.
 */

public class Sort_Array_By_Parity_II {
	
	// O(N) space solution
	public int[] sortArrayByParityII(int[] nums) {
        int[] res = new int[ nums.length ];
        int oddPt = 1, evenPt = 0;
        
        for (int n: nums) {
        	if (n % 2 == 0) {
        		res[evenPt] = n;
        		evenPt += 2;
        	} else {
        		res[oddPt] = n;
        		oddPt += 2;
        	}
        }
        return res;
    }
	
	// O(1) inplace solution - Two pass, two pointers
	public int[] sortArrayByParityII2(int[] nums ) {
		final int len = nums.length;
		int invalidOdd = 1;
		
		// Locate closest index where i is odd, but num is even
		while (invalidOdd < len && nums[invalidOdd] % 2 == 1)
			invalidOdd += 2;
		
		for (int i = 0; i < nums.length; i += 2) {
			// Find index where i is even, but num is odd
			if (nums[i] % 2 == 0) continue;
			
			// Swap
			int temp = nums[i];
			nums[i] = nums[invalidOdd];
			nums[invalidOdd] = temp;
			
			// Update invalidOdd
			while (invalidOdd < len && nums[invalidOdd] % 2 == 1)
				invalidOdd += 2;
		}
		return nums;
	}
}
