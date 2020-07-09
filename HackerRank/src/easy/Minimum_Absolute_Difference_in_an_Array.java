package easy;

//https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/forum

import java.util.Arrays;

public class Minimum_Absolute_Difference_in_an_Array {
	
	static int minimumAbsoluteDifference(int[] arr) {
		Arrays.sort(arr);
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i ++ ) {
			int diff = Math.abs(arr[i] - arr[i - 1] );
			if (diff < minDiff)
				minDiff = diff;
		}
		return minDiff;
    }
	
	public static void main(String[]args) {
		System.out.println(minimumAbsoluteDifference(
				new int[] {-59,-36,-13,1,-53,-92,-2,-96,-54,75}));
	}
}
