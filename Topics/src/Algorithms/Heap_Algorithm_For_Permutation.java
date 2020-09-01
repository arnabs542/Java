package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 	Heap's Algorithm is an quite efficient algorithm for generating all the permutations of a sequence.
 * 
 * 	The algorithm itself uses Divide and Conquer strategy (Fix last element, find all permutations of all front ones),
 * 	Therefore recursion is mainly used
 * 
 * 	However, the algorithm can be unintuitive, especially with the swapping part, one can't easily understand how it works
 * 	by swapping the element with the last element result in a unique last element being placed
 * 
 * 	
 * 
 * 	The core idea of the whole algorithm is:
 * 		First fix the last element in the array, then call upon recursive function which generates all the permutations of
 * 		the front elements with the last element isolated.
 * 
 * 		Once it had came to a time to change the last element, it does this conditionally.
 * 		For every element from 0 to N-1 one (Means exclude the last element),
 * 		>	If i was Even, then swap the i element with the last element
 * 		>	If i was Odd, then swap the first element (0th) with the last element
 * 
 * 		After each swapping, we have a new last element which was never there before! Now repeat the above step of generating
 * 		permutations of the front N-1 elements (last element isolated)
 * 
 * 	The pseudocode shall look as follows:
 * 
 * 			generate( N, arr)
 * 				if (N == 1)								//	The base case. We can't permute array of length 1 anymore. Store it into
 * 					result.append( arr )				//	Result 
 * 					return
 * 
 * 				else
 * 					generate( n-1, arr )				//	Before perform swapping and all stuff, use this last element first. Isolate it
 * 					
 * 					for (int i = 0; i < n - 1; i ++ )	//	Then only we start swapping the last elements
 * 						if (i is Even)
 * 							swap( i, n - 1)
 * 						else
 * 							swap (0, n - 1)
 * 						generate( n-1, arr )			//	After each swap, isolate the last element and generate the permutation 
 * 														//	of front ones!
 * 
 * 			
 */

public class Heap_Algorithm_For_Permutation {
	
	//	Driver code
	private static List<int[]> permutate( int[] arr ) {
		List<int[]> result = new ArrayList<>();
		
		generate( arr.length, arr, result );
		
		return result;
	}
	
	
	private static void generate(int N, int[] arr, List<int[]> result) {
		
		//	Base case. If N is 1, means we have to permutate subarray of length 1, which we don't have to!
		if (N <= 1) {
			result.add( Arrays.copyOf(arr, arr.length) );
			return;
		}
		
		//	Before we do swapping of last element of subarray, let's use this as last element of subarray and permutate the rest first
		generate( N-1, arr, result );
		
		//	Then we would start to swap the last element with all of possible numbers. How this is done is hard to understand
		for (int i = 0; i < N - 1; i ++ ) {			//	i < N - 1 excludes the last element. It is also the length of subarray - 1
													//	which is the correct number of elements that shall be swapped
			if (i % 2 == 0) {
				swapInPlace(arr, i, N - 1);			//	Swap the last element with element at i
			} else {
				swapInPlace(arr, 0, N - 1);			// 	Swap the first and last element
			}
			
			generate( N-1, arr, result );			//	After each swapping, isolate the last element and generate the permutation of
													//	front ones
			
		}
		
	}
	
	private static void swapInPlace(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	
	
	
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		List<int[]> result = permutate(arr);
		
		for (int[] i: result) {
			System.out.println( Arrays.toString(i) );
		}
	}

}
