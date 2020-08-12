package Easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle-ii/

/*
 * 	This is an arrays problem. We need to detect the pattern of the Pascal Triangle
 * 	
 * 	First of all, the number of elements given row Index (Starting from 0) is rowIdx + 1.
 * 		Row 0 - 1 element			(1)
 * 		Row 1 - 2 element			(1,1)
 * 		Row 2 - 3 element			(1,2,1) ...
 * 
 * 	Secondly, The row must start and end with 1
 * 	
 * 	With this we can come up with a Time complexity O(n^2) solution, using only O(n) space (Space of a row only)
 * 
 * 	At every row, we first append an element '1' to the end of the list,
 * 	Then, we start an inner loop with pointer j where j starts from previous row's last element, and stop at index 1 inclusive
 * 	The function of this pointer j is to calculate the sum of two elements j and j-1 from previous row
 * 	After calculating sum, we just replace index j's element with the sum. This is safe since in next iteration the value at index j
 * 	won't be used again
 * 
 * 	Example:
 * 			Row 4 (0 based indexing):	1,4,6,4,1
 * 
 * 		Then to find row 5, first add 1 to end of list:
 * 										1,4,6,4,1,1
 * 		The pointer j will start from prev row's last element, which is the 1 element at last but one element (second last)
 * 		We will add j and j-1, and replace at index j
 * 
 * 												 j
 * 										1,4,6,(4,1),1	=> 1,4,6,4,5,1
 * 
 * 											   j
 * 										1,4,(6,4),5,1	=> 1,4,6,10,5,1
 * 	
 * 											 j
 * 										1,(4,6),10,5,1	=> 1,4,10,10,5,1
 * 
 * 										   j
 * 										(1,4),10,10,5,1	=> 1,5,10,10,5,1	
 * 
 * 		The j will stop at index 1. With this one row is done
 *	
 *	=====================================================================================================================
 */

public class Pascal_Triangle_II {
	
	//	ITERATIVE METHOD
	
//	public List<Integer> getRow(int rowIndex) {
//        List<Integer> result = new ArrayList<>(rowIndex + 1);
//        result.add(1);
//        
//        for (int i = 1; i <= rowIndex; i ++ ) {
//        	result.add(1);
//        	
//        	for (int j = i - 1; j > 0; j -- ) {
//        		result.set(j, result.get(j) + result.get(j - 1) );
//        	}
//        }
//        
//        return result;
//    }
	
	
	
	//	RECURSIVE METHOD
	
	public List<Integer> getRow(int rowIndex) {
		List<Integer> li = new ArrayList<>();
		
		return recursion(rowIndex, li);
	}
	private List<Integer> recursion(int target, List<Integer> li ) {
		int size = li.size();
		if (size > target) return li;
		
		li.add(1);
		for (int i = size - 1; i > 0; i -- ) {
			li.set(i, li.get(i) + li.get(i - 1) );
		}
		return recursion(target, li);
	}
	
}
