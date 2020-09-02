package Algorithms;

import java.util.Arrays;

/*
 * 	COUNTING SORT
 * 
 * 	Counting sort, is a linear-time sorting algorithm. 
 * 	It is efficient if the length of array is large, but the possible set of values of the array are small.
 * 	Eg:	The age of people of whole country. Length of array may be millions, but range of values are small, about up to hundreds only
 * 
 * 	IF LENGTH OF POSSIBLE SET OF VALUES ARE GREATER THEN THE LENGTH OF THE ARRAY ITSELF, IT BECOMES AN INEFFICIENT ALGORITHM!
 * 	IMAGINE SORTING THE DOLLAR TRANSACTIONS WHICH CAN TAKE UP TO MILLIONS WITH COUNTING SORT!
 * 	Maybe in that case, DO RADIX SORT, WHICH UTILIZES COUNTING SORT
 * 
 * 	This seems promising, but actually there are a few catches to using this
 * 	algorithm
 * 
 * 	First and foremost, We have to know the range of values of the array. Meaning for [1,1,2,3,5,3], the range is from 1 to 5
 * 	
 * 	We would utilize extra space of O(k) where k is the number of possible ranges, to use this algorithm. Also, the sorted array
 * 	will be a new array. We cannot do it in-place
 * 
 * 	First, we would create a frequency table on the original arrays. See if our array is [1,1,2,3,5,3] like original, the frequency
 * 	table would be:
 * 		ELEMENT		|		COUNT
 * 			1				  2
 * 			2				  1
 * 			3				  2
 * 			4				  0
 * 			5				  1
 * 
 * 	The next step, is to manipulate the frequency table into a RUNNING SUM table, which is kind of like a cumulative sum table.
 * 	In each index, we would set the SUM to be the current frequency + the previous grid's frequency.
 * 
 * 		ELEMENT		|		RUNNING SUM
 * 			1					2
 * 			2					3
 * 			3					5
 * 			4					5
 * 			5					6
 * 	
 * 	Now in this step, we see that we are unable to use HashMap because we have to keep track of element numbers lesser than the
 * 	current element, which is kind of impossible to do. The easiest way is to just use array.
 * 
 * 	
 * 	Now we arrived in the last step. Create a result array of the same length as the original's.
 * 	First, the numbers stored in the RUNNING SUM table, actually represents the index of the tail of this value in sorted array
 * 	(Except it is off by 1 due to counting nature. This is easily solved by minus 1 to correct back to 0 based indexing)
 * 	
 * 	Assume we've sorted the array, let's see for greater understanding:
 * 			[1,1,2,3,3,5]
 * 	See that:
 * 		The last index of value 1 is (1), where in RUNNING SUM is (2) - 1 = 1
 * 		THe last index of value 2 is (2), where in RUNNING SUM is (3) - 1 = 2
 * 		The last index of value 3 is (4), where in RUNNING SUM is (5) - 1 = 4
 * 		THe last index of value 5 is (5), where in RUNNING SUM is (6) - 1 = 5
 * 
 * 	Using this property, we can put the items accordingly into the result array!
 * 
 * 	Iterate through the original array. For each element met, find the tail index of that element, put it in the result array at index
 * 	i - 1 (to correct to 0 based indexing), and DECREMENT THE SUM BY 1. This is because we've just put in one occurrence of the value
 * 	so the tail index shall be decreased by 1, so that the next time we met the same value again, we can put it in the correct index
 * 
 * 	Last note:	Say we are sorting the items but not pure Integers. Then, to maintain STABILITY SORTING, iterate the original array
 * 				from behind, because the elements are placed from last index, iterating from last element shall maintain the
 * 				sorting order if the elements are same
 * 
 * 
 * 
 * 	Analysis:
 * 		To find out the range of values of the array, we perform linear scan in O(N)
 * 		We initialize the counting table to contain all values 0 (Done implicitly in Java) O(K)
 * 		We count the occurrences of a value. This is done in one pass O(N)
 * 		We manipulate the table into a running sum. This is done in O(K) time
 * 		We perform placement by iterating through the original array backwards. O(N) time
 * 
 * 		TOtal Time O(3N + 2K) === O(N + K)
 * 
 * 	SPace complexity:
 * 		Count table / Running SUm table: O(K)
 * 		Result array: O(N)
 * 
 * 		Total Space: O(N + K). If exclude result array is O(K)
 * 
 * 
 * 	Note:	If there are negative integers in array, we may need to implement an offset to offset the smallest (most negative
 * 			integer) to be 0 in the counting table
 * 		
*/


class Counting_Sort {
	
	public static int[] countingSort(int[] arr ) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		
		//	Finding ranges =========================================
		for (int i: arr) {
			max = Math.max( i, max);
			min = Math.min( i, min);
		}
		
		//	The offset if there is indeed a negative value
		int offset = (min < 0)? Math.abs( min ): 0;
		
		//	We +1 because 0 also counts as one value
		int[] table = new int[ max + offset + 1 ];
		
		//	Counting =========================================
		for (int i: arr) {
			table[ i + offset ] ++;
		}
		
		//	Running sum =========================================
		for (int i = 1; i < table.length; i ++ ) {
			table[i] += table[i - 1];
		}
		
		//	Placement ========================================= 
		int[] res = new int[arr.length ];
		
		//	Iterate backwards to maintain stability of the sort
		for (int i = arr.length - 1; i >= 0; i -- ) {
			//	First, the value must be increased by offset. Then -1 so the index is corrected to 0 indexed
			int idx = table[ arr[i] + offset] - 1;
			res[idx] = arr[i];
			
			//	Remember to decrement the tail index in the running sum table
			table[ arr[i] + offset ] --;
		}
		
		return res;
	}
	
	
	
	public static void main(String[]args) {
		int[] sort = { -5, 1, 3, 4, 2, 5, -3, -1, 0, 3, 5 };
		
		int[] res = countingSort( sort );
		
		System.out.println( Arrays.toString(res) );
	}
	
}
