package Medium;

import java.util.TreeMap;

//https://leetcode.com/problems/contains-duplicate-iii/

/*
 * 	This question can be solved in O(N log N) time using TreeMap (Or TreeSet) (Ordered Map)
 * 
 * 	The base idea is about Sliding Window. For each element introduced, we would need to look behind k elements to see if
 * 	there is an element where the absolute difference is at most t.
 * 
 * 	TreeMap implements Binary Search Tree for its keys. Therefore, not only its key is kept in sorted order, we could easily search
 * 	for the least key greater (Ceiling key) or least key lesser(Floor key) than the current element in O(log N) time.
 * 
 * 	Since there can be duplicates in the array, we use the Tree Mapping to store the frequency of the values currently in the
 * 	sliding window. When sliding the window, the element to be removed will have its frequency decremented. Once it hits 0,
 * 	we will delete that key
 * 	
 * 	For each element introduced, before we put it in the TreeMap, find for the ceiling key and floor key. If one of them ends
 * 	up having absolute difference at most t to the newly introduced element, return true
 */

public class Contains_Duplicate_III {

	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		int len = nums.length;
		
		if (len == 0 || k == 0) return false;
		
		TreeMap<Integer, Integer> window = new TreeMap<>();
		
		window.put( nums[0] , 1);
		
		//	Initialization of Window
		for (int i = 1; i < len && i <= k; i ++ ) {
			int toPut = nums[i];
			
			Integer ceil = window.ceilingKey( toPut );
			Integer floor = window.floorKey( toPut );
			
			if ( ceil != null && Math.abs( ceil * 1l - toPut ) <= t ) return true;
			if ( floor != null && Math.abs( floor * 1l - toPut ) <= t ) return true;
			
			window.put( toPut, window.getOrDefault( toPut, 0 ) + 1);
		}
		
		//	Sliding of window
		for (int i = k + 1; i < len; i++ ) {
			int toDel = nums[i - k - 1];
			int prev = window.get(toDel);
			
			if (prev == 1) window.remove(toDel);
			else window.put( toDel, prev - 1);
			
			int toPut = nums[i];
			
			Integer ceil = window.ceilingKey( toPut );
			Integer floor = window.floorKey( toPut );
			
			if ( ceil != null && Math.abs( ceil * 1l - toPut ) <= t ) return true;
			if ( floor != null && Math.abs( floor * 1l - toPut ) <= t ) return true;
			
			window.put( toPut, window.getOrDefault( toPut, 0 ) + 1);
		}
		
		return false;
    }
	
}
