package Medium;

import java.util.HashMap;
import java.util.Stack;

//https://leetcode.com/problems/contiguous-array/

/*
 * 	The key for O(n) solution is that realize if we plot the graph of the counter which every time we encounter 1, it increments by 1 and
 * 	every time it encounters 0, it decrements by 1.
 * 	We have to observe the graph that every time it has equal zeroes and ones, it would touch the same level again (Not necessary 0).
 * 	Therefore we have to take a hash map to record the earliest index that the point is passed before. Later if the point is touched again,
 * 	it straight indicates there is a equal ones and zeroes somewhere and we have to check if the length is the maximum. This way we could achieve
 * 	O(n) time complexity in exchange for O(n) space complexity.
 */

public class ContiguousArray {

//	public static int findMaxLength(int[] nums) {   
//		int initZero = 0;
//		int initOne = 0;
//		int windowLength = (nums.length % 2 == 0)? nums.length: nums.length - 1;
//		for (int i = 0; i < windowLength; i ++ ) {
//			if (nums[i] == 0) initZero ++;
//			else initOne ++;
//		}
//		
//		while (windowLength > 1) {
//			int zero = initZero;
//			int one = initOne;
//			System.out.println("window length is " + windowLength + " with " + zero + "and" + one);
//			if (zero == one) return windowLength;
//			
//			for (int pos = windowLength; pos < nums.length; pos ++ ) {
//				if (nums[pos] == 0) zero ++;
//				else one ++;
//				
//				if (nums[pos - windowLength] == 0) zero --;
//				else one --;
//				
//				if (zero == one) return windowLength;
//			}
//			
//			if (nums[windowLength - 1] == 0 ) initZero --;
//			else initOne--;
//			if (nums[windowLength - 2] == 0) initZero --;
//			else initOne--;
//			windowLength -= 2;
//		}
//		return 0;
//    }
	/*
	 * 	Brute force approach, which is O(n2) and time limit exceeded
	 */
	
//	public static int findMaxLength(int[] arr) {
//		HashMap <Integer, Integer> map = new HashMap<>();
//		map.put(0, 0);
//		map.put(-1, 0);
//		int length = 1;
//		int max = 0;
//		
//		while (length <= arr.length ) {
//			int currVal = (arr[length - 1] == 0)? -1: 1;
//			
//			for (int pos = length; pos > 0; pos --) {
//				int toAdd = map.get(pos-1) + currVal;
//				map.put(pos, toAdd);
//
//				if (toAdd == 0) {
//					max = Math.max(max, pos);
//				}
//			}
//			length ++;
//		}
//		return max;
//	}
	/*
	 * Still a O(n2) approach
	 */
	
	public static int findMaxLength(int[] arr) {
		HashMap<Integer, Integer> record = new HashMap<>();
		record.put(0, 0);
		int pos = 0;
		int maxlen = 0;
		
		for (int i = 0; i < arr.length; i ++ ) {
			pos += (arr[i] == 0)? -1: 1;
			
			//We shall consider the first element in array to be indexed from 1 onwards, as we have defined starting point as 0
			if (record.containsKey(pos) ) {
				maxlen = Math.max(maxlen, i - record.get(pos) + 1 );
			}
			record.putIfAbsent(pos, i + 1);
		}
		return maxlen;
	}
	
	
	
	public static void main(String[]args) {
		int[] n = {0,1,0};
		System.out.println( findMaxLength(n) );
	}
}
