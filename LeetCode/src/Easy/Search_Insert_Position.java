package Easy;

//https://leetcode.com/problems/search-insert-position/

/*
 * 	A simple binary search problem. At the end of the binary search we have to check if the index element is less than the target to insert.
 * 	If yes, then we have to insert it after the element, else, this element has to be "pushed" to the right, and the target is inserted in
 * 	this position
 */

public class Search_Insert_Position {
	public int searchInsert(int[] nums, int target) {
		if (nums.length == 0) return 0;
		
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) /2;
			if (nums[mid] > target )
				right = mid - 1;
			else if (nums[mid] < target ) 
				left = mid + 1;
			else
				return mid;
		}
		//To insert is larger than the element, thus to insert it after this element
		if (nums[left] < target) 
			return left + 1;
		//Insert exactly in this index
		return left;
	}
}
