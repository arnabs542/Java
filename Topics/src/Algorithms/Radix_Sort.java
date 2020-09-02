package Algorithms;

import java.util.Arrays;

/*
 * 
 * 	Counting sort is good for small range of values. But consider this:
 * 	I have an array of length 100, but the range of values is 1 to 10000.
 * 	With counting sort of time complexity O(N + K) where K = range of values = 10000, it takes much more time, already equivalent
 * 	to O(N^2)!
 * 
 * 	Radix - Same meaning as Base, as in Base 2, Base 8, Base 10...
 * 	
 * 	Radix sort preserves the use of counting sort, but limits the K to become the Radix of the elements. If it was integer, then
 * 	K becomes 10 (Base 10!). It will perform counting sort on each possible place values by D times, where D is the number
 * 	of places (in integer, digits) of the maximum value
 * 
 * 	First and foremost, the counting sort algorithm used must be STABLE, preserving the natural order of the original array.
 * 	
 * 	The basic idea is that we will do counting sort on each of the places (ones, tenths, hundreds), from the least significant ONES
 * 	up until the maximum place. 
 * 
 * 	Example:  [ 75, 170, 802, 45, 66, 90, 24, 2 ]
 * 	
 * 	The counting sort will set up a counting table of size B, B = base = 10, storing from 0 - 9.
 * 	Therefore in the first iteration, we will counting sort the least significant digit (Ignoring the digits at other place values)
 * 
 * 	Result After first Pass: [ 170, 90, 802, 2, 24, 75, 45, 66 ]
 * 	Notice that all of the elements are sorted based on ONES place, while preserving the natural order of original array
 * 
 * 	Result After second Pass: (SORTING BY Tenths Place): [ 802, 2, 24, 45, 66, 170, 75, 90 ]
 * 	If there is no TENTH's PLACE for some elements (like 2), then assume it is 0 (Lowest).
 * 
 * 	Result After third Pass: (SORTING BY Hundreds Place): [2, 24, 45, 66, 75, 90, 170, 802 ]
 * 
 * 
 * 
 * 
 * 	Time Complexity:
 * 		We are going to perform a counting sort on each places - O( D ) (Number of digits)
 * 		IN each counting sort is O(N + k), where k is the base. - O( N + B)
 * 		Therefore the time complexity is O(D * (N + k) )
 * 
 * 	Space Complexity:
 * 		Same as Counting sort, O(N + k)
 * 
 * 
 * 	Note:	It's technically possible to Radix sort Strings, but may not be practical due to some reasons:
 * 				-Base is 26. Although it's still considered OK
 * 				-One string may be veryyyy long while others are short. This forces all string to be sorted as same length as the
 * 		         longest one. Still, if the length of string is longer than length of array, it's not quite worth it anymore
 * 
 */

public class Radix_Sort {
	
	public static int[] radixSort(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i: arr ) {
			max = Math.max( max, i);
		}
		
		int places = 0;
		while (max > 0) {
			places ++;
			max /= 10;
		}
		
		for (int i = 1; i <= places; i ++ ) {
			arr = countingSort(arr, 10, i );
		}
		
		return arr;
	}
	
	
	
	private static int[] countingSort(int[] arr, int base, int place ) {
		
		int[] table = new int[base];
	
		int divisor = (int) Math.pow(10, place - 1);
		
		for (int i: arr ) {
			int digit = (i / divisor) % 10;		//Get the last digit
			table[digit] ++;
		}
		
		//	Running sum =========================================
		for (int i = 1; i < base; i ++ ) {
			table[i] += table[i - 1];
		}
		
		//	Placement ========================================= 
		int[] res = new int[arr.length ];
		
		//	Iterate backwards to maintain stability of the sort
		for (int i = arr.length - 1; i >= 0; i -- ) {
			int digit = ( arr[i] / divisor) % 10;
			int idx = table[ digit ] - 1;
			res[idx] = arr[i];
			//	Remember to decrement the tail index in the running sum table
			table[ digit ] --;
		}
		
		return res;
	}
	
	
	
	
	
	
	public static void main(String[]args) {
		int[] arr = { 75, 170, 802, 45, 66, 90, 24, 2 };
		
		int[] res = radixSort( arr );
		
		System.out.println( Arrays.toString(res) );
	}

}
