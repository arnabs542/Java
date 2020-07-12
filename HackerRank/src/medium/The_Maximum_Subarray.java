package medium;

//https://www.hackerrank.com/challenges/maxsubarray/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a kind of dynamic programming question. At the same time, it asks for the maximum subset (Or they call subsequence) and
 * 	the maximum sub array (Must be continuous).
 * 
 * 	To find maximum subset can be done in a linear scan. Initialize the max value to be very negative value. Then, for every integer that
 * 	is encountered, see if the current max value hold is negative value. If it is negative, just take the least negative (maybe the i
 * 	is positive) of them two by using Math.max. If the max however is positive (Already encountered at least positive value once), then
 * 	take the Math.max of sum of the max and this integer or just max itself.
 * 	The idea is to loop through the array, include all positive integers inside, and ignore the negative numbers.
 * 	However in the case of array with all negative number, just pick the least negative and return it itself only.
 * 
 * 	-----------------------------------------------------------------------------------------------------------------------
 * 
 * 	For finding maximum sub array is a dynamic programming approach with linear scan. It can be divided into sub problems where
 * 	answer can be determined by finding the maximum sub array without the current element included
 * 
 * 	For example, [-1, 2, 3, -4, 5, 10]
 * 	The base, or initialization is that the maximum sub array of [-1] itself is -1
 * 	Then with [-1, 2], The maximum can be either including this 2 element together, or just start a new sub array from this element. Which
 * 		in this case is starting new sub array of 2
 * 	Then with [-1, 2, 3], The previous maximum sub array is 2. Therefore with 3 included, it makes maximum sub array of 5.
 * 	Then [-1, 2, 5, -4], The previous maximum sub array is 5. Including this -4 becomes 1, which is better than starting new sub array of -4.
 * 		Notice that up until now, the maximum sub array is actually 5, although locally it evaluates to 1 up until this point. Therefore
 * 		we have to keep record of a global maximum sub array and compare it each time a local maximum sub array is found.
 * 	Then [-1, 2, 3, -4, 5], The previous maximum sub array is 1, include this 5 and becomes 6, which overrides the global max of 5.
 * 	Lastly [-1, 2, 3, -4, 5, 10], The previous maximum is 6, including this 10 becomes 16. Therefore global value is overriden and returned
 * 		as final answer
 */



public class The_Maximum_Subarray {
	
	static int[] maxSubarray(int[] arr) {
		int maxSeq = Integer.MIN_VALUE;
		int maxSub = Integer.MIN_VALUE;
		int prevMaxSub = 0;
		
		//To find the subsequence (subset) is direct. Start with min value. For every integer i encountered, if the current max is
		//still negative, take the least negative between i and max. Else if max is no longer negative (Got a max), then add the integer i
		//if it is positive, else don't include it. We try to include all positive numbers in the array.
		for (int i = 0; i < arr.length; i ++ ) {
			if (maxSeq < 0)
				maxSeq = Math.max(arr[i], maxSeq);
			else
				maxSeq += (arr[i] > 0)? arr[i]: 0;
			
			int localSub = Math.max( arr[i] , arr[i] + prevMaxSub);
			prevMaxSub = localSub;
			maxSub = Math.max( maxSub, localSub);
		}
		
		return new int[] {maxSub, maxSeq};
		
	}
	
}
