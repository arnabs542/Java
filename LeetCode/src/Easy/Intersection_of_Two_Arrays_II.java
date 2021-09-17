package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/intersection-of-two-arrays-ii/
/*
 * This is a Hashmap / Sorting with two pointers problem.
 * 
 * Of course, the small input size of this problem allows for brute force approach, but still requires a way
 * to record used element. (Like making it -INF)
 * 
 * -------------------
 * 
 * Using Hashmap, we would create a frequency table representing available elements left for us to be used.
 * Iterate through the other array who isn't used to build the table, if an element is in the table and frequency
 * isn't 0, we can append to res array, but remember to decrement the frequency in the table
 * 
 * --------------------
 * 
 * If both arrays are sorted, then we would use two pointers, one pointer for each array, initialized at leftmost
 * position
 * 
 * > If arr1[left] == arr2[right], then we have intersection. Append to res, move both pointers.
 * > Else if arr1[left] > arr2[right], then proceed right pointer.
 * > Otherwise proceed left pointer.
 * 
 * Iterate until one pointer hit the end of array.
 */

public class Intersection_of_Two_Arrays_II {
	// O(N) solution - Hashmap as frequency table
	public int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> res = new ArrayList<>();
		Map<Integer, Integer> freq = new HashMap<>();
		int[] longer = nums1.length > nums2.length? nums1: nums2;
		int[] shorter = longer == nums1? nums2: nums1;
		
		// Save the shorter array in hashmap to save space.
		for (int i: shorter)
			freq.put(i, freq.getOrDefault(i, 0) + 1);
		
		// For each of the elements in i, check if any is left from hashmap
		for (int i: longer) {
			if (freq.getOrDefault(i, 0) > 0) {
				freq.put(i, freq.get(i) - 1);
				res.add(i);
			}
		}

		int[] r = new int[res.size()];
		for (int i = 0; i < res.size(); ++i) r[i] = res.get(i);
		return r;
    }
	
	
	// Sorting solution
	public int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int l = 0, r = 0;
		List<Integer> res = new ArrayList<>();
		
		while (l < nums1.length && r < nums2.length) {
			if (nums1[l] == nums2[r]) {
				res.add(nums1[l]);
				++l; ++r;
			}
			else if (nums1[l] > nums2[r]) ++r;
			else ++l;
		}
		
		int[] ans = new int[res.size()];
		for (int i = 0; i < res.size(); ++i) ans[i] = res.get(i);
		return ans;
	}
}
