package Easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/decompress-run-length-encoded-list/

public class Decompress_Run_Length_Encoded_List {
	 public int[] decompressRLElist(int[] nums) {
		int size = 0;
		for (int i = 0; i < nums.length - 1; i+=2 ) {
			 size += nums[i];
		}
		int[] arr = new int[size];
		int index = 0;
		for (int i = 1; i < nums.length; i+=2 ) {
			 Arrays.fill(arr, index, index + nums[i-1], nums[i]);
			 index += nums[i-1];
		}
		return arr;
	 }
	 
	
}
