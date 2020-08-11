package Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/h-index/

/*
 * 	This problem can be first solved using sorting.
 * 	When sorted, at index i, we check the citation value in that element.
 * 	We know that at index i, there are len(arr) - i books that has citations greater than or equal to this element's citation count
 * 
 * 	Therefore we check:
 * 	>	If the citation value are greater than len(arr) - i books, then this cannot be the h index. Find on the left side
 * 		where there is more books
 *  >	If the citation value are lesser than len(arr) - i books, then this len(arr) - i books could be the h index. But try
 *  	and be greedy to check for right side
 *  
 *  This above can be implemented using binary search
 *  
 *  Sorting could use bucket sort to implement. If the citations exceed N, then just count it as N is enough (Since we don't really
 *  care about citations exceeded N, h index is bounded by N)
 */

public class H_index {
	
//	public int hIndex(int[] citations) {
//		Arrays.parallelSort( citations );
//		
//		int left = 0;
//		int right = citations.length - 1;
//		
//		while (left < right) {
//			int mid = left + (right - left ) / 2;
//			int nBooks = citations.length - mid;
//			
//			if ( citations[mid] >= nBooks ) {
//				right = mid;
//			}
//			else {
//				left = mid + 1;
//			}
//		}
//		
//		if (citations[left] == 0) return 0;
//		return citations.length - left;
//    }
	
	public int hIndex(int[] citations ) {
		if (citations.length == 0) return 0;
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i: citations) {
			map.put( i, map.getOrDefault(i, 0) + 1);
		}
		
		citations[0] = citations.length - map.getOrDefault(0, 0);
		for (int i = 1; i < citations.length; i ++ ) {
			citations[i] = citations[i - 1] - map.getOrDefault(i, 0);
		}
		
		int left = 0, right = citations.length - 1;
		
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (citations[mid] >= mid + 1 ) {
				left = mid;
			}
			else {
				right = mid - 1;
			}
		}
		
			
		return left == 0 && citations[0] == 0? 0:
			left == right? left + 1:
				citations[right] >= right + 1? right + 1: left + 1;
	}
	
}
