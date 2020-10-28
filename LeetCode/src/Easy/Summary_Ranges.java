package Easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/summary-ranges/

/*
This is a Array problem.

Given the property that the array is sorted, we could just keep the pointer to the current progress in the array.
While we are not at the end of array yet, loop through this:

	Set the current element pointed by pointer as range starting point. 
	Then, while the next element is one greater than this current element, increment the index
	At the end, we will stop at either array end or the end of the range.
	Check if the start index is same as end index. If yes, just append the number. Otherwise, append the range
*/

public class Summary_Ranges {
	
	public List<String> summaryRanges(int[] nums) {
        final int len = nums.length;
        int idx = 0;
        List<String> res = new ArrayList<>();
        
        while (idx < len) {
        	int start = idx;
        	while ( idx + 1 < len && nums[idx + 1] == nums[idx] + 1 )
        		idx ++;
        	res.add( idx == start? Integer.toString(nums[start]): String.format("%d->%d", nums[start], nums[idx] ) );
        	idx ++;
        }
        
        return res;
    }
	
}
