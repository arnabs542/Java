package Data_Structures;

import java.util.Arrays;

/*
 * 	Say you want a fast implementation for a Data Structure to perhaps find out the sum, max, min within an array range,
 * 	Square root decomposition is a nice choice for your job.
 * 
 * 	First, consider Brute force approach. Although updating a single element is in O(1) time, querying for a sum, min or
 * 	max will take O(N) time. If the query is performed many times, it is going to be time consuming.
 * 
 * 	===============================================================================================
 * 
 * 	Consider an array of length N. In Square root decomposition, we will break down the array into Å„N blocks, each
 * 	containing Å„N elements in it.
 * 	Within each block, we are going to compute for the value that we wanted. Say we want the sum, then compute the sum
 * 	for each block, and store it inside perhaps some array for quick lookup.
 * 	
 * 	That's basically what's square root decomposition is all about! Now let's analyse the cost for operations:
 * 
 * 	If we want to update a value in array, we need to:
 * 		>	Update value in original array in O(1)
 * 		>	Update sum/min/max in the corresponding block. If it is sum, it can be done in O(1) time simply by comparing
 * 			with previous value. If it is min/max, then you need O(Å„N) time for updating
 * 	
 * 	If we want to query a range in array. Say our array is length 9, broken into 3 blocks each containing 3 elements.
 * 	Say I query [1,7], then see:
 * 
 * 		Block 1 represents [0,2]. It partially overlaps in [1,7]. We need to manually iterate from [1,2] in original array
 * 		to find out sum[1,2]
 * 
 * 		Block 2 represents [3,5]. It fully overlaps in [1,7]. Thus simply refer to the precomputed value of sum.
 * 
 * 		Block 3 represents [6,8]. It partially overlaps in [1,7]. We need to manually iterate from [6,7] in original array
 * 		to find out sum[6,7]
 * 
 * 	As you can generalize, the case where PARTIALLY OVERLAP occurs 2 times, taking O(2Å„N) time. FULLY OVERLAP may occur
 * 	covering entire array, thus O(Å„N) time to get value for each block (not elements).
 * 	Thus time complexity for querying is O(3Å„N).
 * 
 * 	Space complexity is O(Å„N).
 * 	
 */


//	Example: Square root Decomposition for Query sums
public class Square_Root_Decomposition {
	
	private int[] sums;
	private int sqrtn;
	private int[] arr;
	
	public Square_Root_Decomposition(int[] arr) {
		sqrtn = (int)Math.ceil( Math.sqrt(arr.length) );
		sums = new int[sqrtn];
		this.arr = arr;
		
		for (int i = 0; i < arr.length; ++i)
			sums[i / sqrtn] += arr[i];
	}
	
	public void update(int index, int value) {
		int diff = value - arr[index];
		arr[index] = value;
		
		sums[index / sqrtn] += diff;
	}
	
	public int query(int from, int to) {
		int leftBlock = from / sqrtn;
		int rightBlock = to / sqrtn;
		int sum = 0;
		
		//	If they are from within same block, simply sum
		if (leftBlock == rightBlock) {
			while (from <= to) sum += arr[from++];
			return sum;
		}
		
		//	Otherwise it involves 2 or more blocks.
		for (int i = from; i <= (leftBlock + 1) * sqrtn - 1; ++i)
			sum += arr[i];
		for (int i = leftBlock+1; i <= rightBlock-1; ++i )
			sum += sums[i];
		for (int i = rightBlock * sqrtn; i <= to; ++i)
			sum += arr[i];
		return sum;
	}

	
	
	
	
	//	Runner code
	public static void main(String[] args) {
		int[] arr = {9, -8};
		Square_Root_Decomposition srd = new Square_Root_Decomposition(arr);
		srd.update(0, 3);
		System.out.println( srd.query(1, 1));
	}
	
}
