package Medium;

//https://leetcode.com/problems/h-index-ii/

/*
 * 	
 */

public class H_Index_II {
	
	public int hIndex(int[] citations) {
		if (citations.length == 0) return 0;
		
		int len = citations.length;
		int left = 0;
		int right = len - 1;
		
		//Left pointer is iterated until it is at least adjacent to right pointer (next to each other)
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			
			//Studies to the right (including the mid pointer) is greater than the citation value in this block.
			//Plausible case. Left pointer has to be moved to mid since all values to the left must be plausible as well.
			if (citations[mid] <= len - mid) 
				left = mid;
			//Not plausible case. Right pointer has to be moved to mid - 1 (mid excluded as it is not plausible)
			else
				right = mid - 1;
		}
		return len - right - 1 + ( (len - right <= citations[right])? 1: 0 ) + ( (right!=left && (len - left) <= citations[left])? 1:0);
	}
}
