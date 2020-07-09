package Arrays;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3270/

/*
 * 	It is easy if we are permitted extra memory for this. We could just do a HashSet, initialize it and remove the elements that we met in the
 * 	array.
 * 	
 * 	To do this in O(n) time and O(1) space (in-place), we have to think to transform the array itself into some sort of counter.
 * 	This exploits the fact that the value of the nums must be in range 1 to n where n is the size of array itself.
 * 	We can make the indexing of the array as the representation of the value itself. For example index 0 represents frequency of value 1,
 * 	index 1 represents frequency of value 2...
 * 	Since we only need to know which element was never met, we mark those who exists in the array with a negative integer, Eg: -1.
 * 	We keep a pointer to keep track of the progress. Every time we met an element which was not negative, we follow its path and mark the location
 * 	with a negative number. To not lose information, we store the value beforehand and then do the iteration again with the value overwritten,
 * 	until another negative number is met.
 * 
 * 
 * 	We can optimize the solution to not do the iteration part. We just mark the value to its negative counter part. In the first iteration
 * 	no matter it is negative or not we'll just consider it, go to its place, and change value to negative if it's positive beforehand
 *	
 *---------------------------------------------------------------------------------------------------------------------------------------------
 *
 *	AN ALTERNATIVE SOLUTION
 *	is to doing some kind of O(n) sort. For every value we met we'll swap it with it's respective place represented by index.
 *	Eg: For value 3 we'll swap it with element in index 2 (Since indexing starts with 0). (After swapping stay in place to check again)
 *	The constraints are:
 *		-If the value in that place is already of the same, don't swap them
 *		-If the current value is already in place, do nothing
 *	
 *	In the end, the values should be in their respective places already. The final iteration checks if the value matches the index. If not,
 *	this value does not exist
 */

public class FindAllNumbersDissapearedInAnArray {

	public List<Integer> findDisappearedNumbers(int[] nums) {
		int pointer = 0;
		
		while (pointer < nums.length ) {
			if (nums[pointer] > 0) {
				//We have to go to this toDo index and change value to -1. Remember that lower bound of values is 1, thus we have to -1 here
				int toDo = nums[pointer] - 1;
				while (nums[toDo] > 0) {
					//Take note of the value at the toDo location beforehand, which we use again in iteration later
					int i = nums[toDo] - 1;
					nums[toDo] = -999;
					toDo = i;
				}
			}
			pointer++;
		}
		
		List<Integer> result = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i ++ ) {
			//The value at this location is not negative. That means it is not exist in the array and this place never got visited
			if (nums[i] > 0) 
				result.add(i + 1);
		}
		return result;
    }
	
	
	public List<Integer> findDisappearedNumbersSort(int[] nums) {
		List<Integer> result = new LinkedList<>();
		int pointer = 0;
		
		while (pointer < nums.length ) {
			//If the element at the toSwap index is same as this element we're going to swap OR the element at this index is at its supposed place
			if ( nums[ nums[pointer] -1 ] == nums[pointer] || pointer + 1 == nums[pointer] )
				pointer++;
			else {
				int temp = nums[pointer];
				nums[pointer] = nums[ nums[pointer] - 1];
				nums[ temp - 1] = temp;
			}
		}
		for (int i = 0; i < nums.length; i ++ ) {
			if (nums[i] != i + 1)
				result.add(i+1);
		}
		return result;
	}
	
}
