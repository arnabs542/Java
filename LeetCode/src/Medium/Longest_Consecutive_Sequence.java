package Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/longest-consecutive-sequence/
/*
 * 	This is a Array / Hashset problem.
 * 
 * 	My initial solution is to construct intervals from the numbers given. Every element can be in either of cases:
 * 	
 * 	>	Standalone element. No element i-1 and i+1 encountered so far.
 * 	>	Middle element. i-1 and i+1 is encountered already. Merge the intervals
 * 	>	Tail element. i-1 is encountered before, but not i+1.
 * 	>	Head element. i+1 is encountered before, but not i-1
 * 
 * 	First, duplicate elements should never be considered for this algorithm to work.
 * 	Have 2 hashmap, toFrom and fromTo. 
 * 	>	toFrom maps from end of interval to start of interval. Eg: 5->1 means there is a interval 1 to 5
 * 	>	fromTo maps from start of interval to end of interval. Eg: 1->5 means there is a interval 1 to 5
 * 
 * 	In each of the four cases above, perform the appropriate operations to construct / merge intervals in both Hashmap.
 * 	Also, check to see if the interval size is so far largest.
 * 
 * 	===============================================================
 * 
 * 	The official solution was to first obtain the ability to query presence of each number in array in O(1) time.
 * 	Then, iterate each element.
 * 	
 * 	If element was potentially the head of a sequence, checked by see if i-1 is present in the array or not,
 * 	then we will determine the length of the sequence.
 * 
 * 	This is O(N) time algorithm and O(N) space.
 */

public class Longest_Consecutive_Sequence {
	
	//	Initial solution - Construct intervals. O(N) time but is slow due to Hashmaps and Hashset
	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) return 0;
		
		Set<Integer> set = new HashSet<>();
		Map<Integer, Integer> fromTo = new HashMap<>();
		Map<Integer, Integer> toFrom = new HashMap<>();
		int res = 1;
		
		for (int i: nums) {
			//	Do not consider duplicate elements
			if (!set.add(i) ) continue;
			
			//	Case 1: ... e ...
			if (toFrom.containsKey(i-1) && fromTo.containsKey(i+1)) {
				int leftmost = toFrom.get(i-1);
				int rightmost = fromTo.get(i+1);
				
				fromTo.put(leftmost, rightmost);
				toFrom.put(rightmost, leftmost);
				
				toFrom.remove(i-1);
				fromTo.remove(i+1);
				
				res = Math.max(rightmost - leftmost + 1, res);
			}
			//	Case 2: ... e X
			else if (toFrom.containsKey(i-1)) {
				int leftmost = toFrom.get(i-1);
				fromTo.put(leftmost, i);
				
				toFrom.remove(i-1);
				toFrom.put(i, leftmost);
				
				res = Math.max(res, i - leftmost + 1);
			}
			//	Case 3: X e ...
			else if (fromTo.containsKey(i+1)) {
				int rightmost = fromTo.get(i+1);
				toFrom.put(rightmost, i);
				
				fromTo.remove(i+1);
				fromTo.put(i, rightmost);
				
				res = Math.max(res, rightmost - i + 1);
			}
			//	Case 4: X e X - Individual element
			else {
				fromTo.put(i, i);
				toFrom.put(i, i);
			}
		}
		return res;
    }
	
	
	//	Best optimized solution - Single HashSet 
	public int longestConsecutive2(int[] nums) {
		Set<Integer> setOfNums = new HashSet<>();
		int res = 0;
		
		for (int i: nums)
			setOfNums.add(i);
		
		for (int i: setOfNums) {
			//	If the number is potentially the HEAD of a sequence (No prev elem)
			if (!setOfNums.contains(i-1)) {
				int streak = 0;
				for (int curr = i; setOfNums.contains(curr); ++curr)
					++streak;
				res = Math.max(streak, res);
			}
		}
		return res;
	}
	
}
