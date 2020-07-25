package Hard;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

public class Find_Minimum_In_Rotated_Sorted_Array_2 {
	
	public int findMin(int[] nums) {
		return recurse(nums, 0, nums.length - 1);
	}
	
	private static int recurse(int[] arr, int left, int right) {
		if (right == left ) return arr[left];
		if (right - left == 1) return Math.min(arr[left], arr[right] );
		if (arr[right] > arr[left] ) return arr[left];
		
		int mid = left + (right - left) / 2;
		
		if (arr[mid] > arr[left] ) {
			return recurse(arr, mid, right);
		}
		else if (arr[mid] < arr[left] ) {
			return recurse(arr, left, mid);
		}
		return Math.min( recurse(arr, left, mid), recurse(arr, mid + 1, right ) );
		
	}

}
