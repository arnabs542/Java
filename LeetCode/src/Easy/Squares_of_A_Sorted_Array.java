package Easy;

//https://leetcode.com/problems/squares-of-a-sorted-array/
/*
 * 	This is a Two pointers array problem. First attempted in Arrays section. Now revisited
 * 
 * 	No matter if a number is negative or positive, when it is squared, it will become positive regardless
 * 	Therefore it is the MAGNITUDE of the number that is important
 * 
 * 	Notice this sorted array:
 * 			-10, -5, 0, 3, 7
 * 	
 * 	The magnitude starts out large, then become smaller, then eventually after the negative part and arrived
 * 	positive part, it begins to become large again.
 * 	
 * 	Therefore a idea comes to mind. Use two pointer and compare each time: which one is more suited to fill into
 * 	the result array next. The suitable one will have its square inserted, and pointer moved.
 * 
 * 	-------------------------------------------------------------------
 * 
 * 	The INSIDE OUT approach utilizes linear scan (or better yet, binary search) to find out the first non negative
 * 	element's index. Then put the 2 pointer:
 * 		Positive pointer: the result of binary search - First non negative element
 * 		Negative pointer: the previous element of the first non negative element must be negative!
 * 
 * 	Now, each time, compare. The one with smaller magnitude, will have the square inserted into res array. Do until
 * 	all elements is inserted
 * 
 * 	--------------------------------------------------------------------
 * 
 * 	Upon observation, the both ends of the array are actually large in magnitude. Therefore, we can also initialize
 * 	the two pointers to be point at the maximum magnitude first, and begin shrinking down to center.
 * 
 * 	This means the result array has to be filled reversely. However this elimiates the need of linear scan or 
 * 	binary search
 */

public class Squares_of_A_Sorted_Array {
	public int[] sortedSquares(int[] nums) {
		final int len = nums.length;
		int posPt = binarySearch(nums);
		int negPt = posPt - 1;
		int idx = 0;
		int[] res = new int[len];
		
		//	There is still comparison need to be done
		while (posPt < len && negPt >= 0 ) {
			//	Positive number pointer is larger
			if (nums[posPt] + nums[negPt] > 0)
				res[idx++] = nums[negPt] * nums[negPt--];
			else
				res[idx++] = nums[posPt] * nums[posPt++];
		}
		
		while (posPt < len)
			res[idx++] = nums[posPt] * nums[posPt++];
		while (negPt >= 0)
			res[idx++] = nums[negPt] * nums[negPt--];
		
		return res;
	}
	private int binarySearch(int[] nums) {
		int left = 0, right = nums.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			if (nums[mid] >= 0)
				right = mid;
			else 
				left = mid + 1;
		}
		return left;
	}
	
	
	
	
	public int[] sortedSquares2(int[] nums) {
		final int len = nums.length;
		int[] res = new int[len];
		
		int idx = len;
		int left = 0, right = len - 1;
		while (left <= right) {
			if (Math.abs(nums[left]) > Math.abs(nums[right]) )
				res[--idx] = nums[left] * nums[left++];
			else
				res[--idx] = nums[right] * nums[right--];
		}
		return res;
	}
}
