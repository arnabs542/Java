package Medium;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

/*
 * 	This is a binary search problem. We basically has to search for the head of the sorted array, which has the smallest value.
 * 
 * 	-	If the array is not rotated, then the highest value will be at the end, and the lowest value will be at the start.
 * 
 * 	My first attempt is to keep a range to search, and make a fast pointer and slow pointer. The fast pointer's speed will always multiply by 2 each time, while
 * 	the slow one will always move by 1 step. Every step, the value at fast pointer is checked against the slow pointer's.
 * 	If the fast pointer's value is lesser than that of the slow's, that means fast pointer has surpassed the lowest value. Therefore limit the range of search
 * 	where right is limited to fast pointer, and left is limited to the slow pointer's
 * 	If the fast pointer is out of range, then limit the left range to the slow pointer's, and reset both the pointer to the left range.
 * 
 * 	--------------------------------------------------------------------------------------------------------------
 * 
 * 	As for the better binary search approach, we will find a mid value at each search.
 * 	At each iteration, it is checked if the lowest value is at the left limiting range, by doing right > left? If the sorted array is surely rotated, then right
 * 	shall be lower than the left array.
 * 	If not, a mid value is grabbed. If the value at mid is greater than the left, then we know that from left limit up until mid, it is always increasing. Therefore
 * 	we shall search the right side, excluding the mid value itself.
 * 	Else, we shall search the left side, since the left is greater than the mid, the lowest value must be in between that caused mid to be less than the left limit
 */

public class Find_Minimum_In_Rotated_Sorted_Array {

//	public int findMin(int[] nums) {
//		int len = nums.length;
//		int left = 0, right = len - 1;
//		
//		while (left < right) {
//			System.out.println(left + " " + right);
//			int slowP = left, fastP = left + 1;
//			
//			while (fastP < right && nums[slowP] < nums[fastP] ) {
//				fastP = fastP + (fastP - slowP) * 2;
//				slowP ++;
//			}
//			if (left == len - 1) {
//				return nums[0];
//			}
//			if (fastP - slowP == 1)
//				return nums[fastP];
//			
//			if (fastP >= len )
//				left = slowP;
//			else {
//				right = fastP;
//				left = slowP;
//			}
//		}
//		return 0;
//    }
	
	public int findMin(int[] nums) {
		int left = 0, right = nums.length - 1;
		
		while (left < right) {
			if (nums[left] < nums[right] )
				return nums[left];
			
			int mid = left + (right - left) / 2;
			if (nums[mid] >= nums[left] ) {
				left = mid + 1;
			}
			else
				right = mid;
		}
		return nums[left];
	}
	
}
