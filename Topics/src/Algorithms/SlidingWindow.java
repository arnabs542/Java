package Algorithms;

public class SlidingWindow {

	static int maximumSum(int[] arr, int size) {
		int windowSum = 0, maxSum = Integer.MIN_VALUE;
		
		for (int i = 0; i < size; i ++ ) {
			windowSum += arr[i];
		}
		maxSum = windowSum;
		
		for (int end = size; end < arr.length; end ++ ) {
			windowSum = windowSum + arr[end] - arr[end - size];
			maxSum = (windowSum > maxSum)? windowSum: maxSum;
		}
		return maxSum;
	}
	
	
	public static void main(String[]args) {
		System.out.println(maximumSum(new int[] {1,2,3,4,5,6,7,8,9}, 4) );
	}
	
}
