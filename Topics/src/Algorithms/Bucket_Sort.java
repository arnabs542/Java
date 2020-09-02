package Algorithms;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * 	Bucket Sort by itself isn't really a 'real' sorting algorithm. It can be viewed as sugar coating of sorting
 * 
 * 	Consider we have a range of floating point numbers from 0.0 to 1.0. How do we go sorting it?
 * 	Counting sort is not possible as counting sort needs distinct values as indexing. Radix sort is not available as well. There is no
 * 	base or radix for us to counting sort on.
 * 
 * 	If those floating point numbers are UNIFORMLY DISTRIBUTED (In other words, not biased on one side), then we would use bucket sort.
 * 
 * 	The idea is, Since those numbers are uniformly distributed, we split them into smaller subarrays which follows the sorting order,
 * 	sort each of the subarrays individually, then merge them back. Then we will have a sorted array
 * 
 * 	Based on above example, we would split those floating point numbers by their tenths place ( 0.X ), then we will have 10 subarrays,
 * 	which some of them might be empty. Those subarrays are smaller, and will be easier to sort
 * 
 * 	Once each of them are sorted, we iterate from the lowest to highest in the array (0.1, 0.2, 0.3...) and merge them into one
 * 	single, sorted array.
 * 
 * 	For each element, we find out their tenth's place, place them into the respective place in the table. O(N)
 * 	Once that's done, iterate on each table's index, sort the subarrays. This is assumed to be O(1)
 * 	Then we merge them back into one. Taking O(N) time since we need iterate through all elements to merge them back
 * 
 * 	
 * 
 * 
 * 	Note:	When inserting into the table, the preferred data structure is LinkedList, as insertion is guaranteed O(1)
 * 			The sorting algorithm on the subarrays may be insertion sort, or any suitable sorting algo.
 * 
 * 
 */

public class Bucket_Sort {
	
	public static float[] bucketSort( float[] arr ) {
		//	First, create the buckets. We will be sorting them based on tenth's place
		LinkedList< Float >[] bucket = new LinkedList[10];
		
		//	Iterate through the array. Put them into bucket based on the tenth's place
		for (float f: arr) {
			int idx = (int)(f * 10);
			
			if (bucket[idx] == null) bucket[idx] = new LinkedList<>();
			
			bucket[idx].add(f);
		}
		
		int index = 0;
		
		//	Sort each subarray (Linked List)
		//	Then, i just put it into the original input array. Although involves mutating input, it gets job done
		for (LinkedList<Float> li: bucket) {
			if (li == null) continue;
			
			li.sort( (x,y) -> { return x > y? 1: -1; } );
			
			for (float f: li) {
				arr[index++] = f;
			}
		}
		
		return arr;
		
	}
	
	
	
	
	public static void main(String[]args) {
		float[] arr = { 0.665f, 0.565f, 0.3434f, 0.897f, 0.656f, 0.1234f };
		
		float[] res = bucketSort( arr );
		
		System.out.println( Arrays.toString(res) );
	}

}
