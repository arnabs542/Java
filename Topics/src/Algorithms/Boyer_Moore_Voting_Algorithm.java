package Algorithms;

/*
 * This is an algorithm that will find the majority element in an array, where the majority element is the element that occurs more than
 * floor(n/2) times in the array. Eg: in array of size 20, majority element must occur >10 times
 * 
 * First, we assume the first element in the array as majority element and initialize the counter to 1
 * Once we get to the next element, there are 2 cases:
 * 		> If the next element is not the assumed majority element, decrement the counter by 1
 * 		> If the next element is the assumed majority element, increment the counter by 1
 * 
 * Then, check the counter. If the counter is 0 now, this current element will become the assumed majority element, and counter will reset to 1
 * At the end of the iteration, whatever left in the assumed majority element is indeed the majority element
 * 
 * This algorithm is perfect for voting cases involving TWO parties only (More than two, it might
 * not be majority element anymore), and assumed that everyone voted for either one of the parties (No abstain votes)
 * 
 * O(n) time complexity
 * O(1) space complexity
 */

public class Boyer_Moore_Voting_Algorithm {
	public static int majorityElement(int[] nums) {
		int assume = nums[0];
		int counter = 0;
		for (int i: nums) {
			if (i == assume) counter ++;
			else counter --;
			
			if (counter == 0) {
				counter = 1;
				assume = i;
			}
		}
		return assume;
	}
	
	public static void main(String[]args) {
		int[] arr = {1,1,1,2,2,2, 1,2,2};
		System.out.println( majorityElement(arr) );
	}
	
}
