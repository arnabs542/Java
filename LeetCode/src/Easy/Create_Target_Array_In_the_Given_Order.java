package Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

//https://leetcode.com/problems/create-target-array-in-the-given-order/

public class Create_Target_Array_In_the_Given_Order {
	
	public static int[] createTargetArray(int[] nums, int[] index) {
		int[] arr = new int[nums.length];
		Arrays.fill(arr, Integer.MIN_VALUE);
		
		for (int i = 0; i < nums.length; i ++ ) {
			//If the targeted index is occupied with data already, shift all elements (including the one at index) to right by 1 space
			if ( arr[index[i]] != Integer.MIN_VALUE) {
				for (int rev = arr.length-1; rev > index[i]; rev--) 
					arr[rev] = arr[rev - 1];
				arr[index[i]] = nums[i];
			}
			//Else just occupy it
			else {
				arr[index[i]] = nums[i];
			}
		}
		return arr;
    }
	
	public static void main(String[]args) {
		createTargetArray(new int[] {1,2,3,4,0}, new int[] {0,1,2,3,0});
	}
}
