package Medium;

import java.util.ArrayList;
import java.util.Arrays;

//https://leetcode.com/problems/single-number-iii/

/*
 * 	The question requires solving it in O(N) time and O(1) space complexity.
 * 
 * 	If O(n^2) solution is permitted, we could do three pointers, one left, one right and one reset pointer. The left pointer will start
 * 	from the reset pointer, which initially at head of array. The right pointer start at the end.
 * 
 * 	While not matching, move the left pointer forward. If at the end left pointer collides with right pointer, then the number at the pointers
 * 	is unique. 
 * 	Else if the left pointer value is equal to right pointer's (duplicate value), swap the value of left pointer (one of duplicate) with
 * 	reset pointer, move the reset pointer one step ahead, decrement right pointer by one step (which is also duplicate), and reset the left
 * 	pointer to reset pointer.
 * 
 * 	This solution do it in-place O(1) space, but could take O(n^2) time complexity
 * 
 * 	-----------------------------------------------------------------------------------------------------------------------
 * 
 * 	The solution to do it in O(n) time complexity is to use bit manipulation.
 * 	Here are some of XOR bitwise operation properties:
 * 		-	Performing XOR on two numbers is like asking the difference of the two binary forms. In the resulting binary string,
 * 			1 indicates at this position, two numbers has different bits, while 0 indicates two number has the same bit at this position
 *		-	Therefore, Performing XOR on TWO same numbers, will result in 0. This is because there are no difference in bits between the 
 *			two number! As long as the number is of even number count (2,4,6...), they will XOR to 0, else it will represent the number itself
 *	
 *		-	No matter how mixed the numbers are inside, as long as the numbers occurs in pairs, the result of XOR-ing all numbers will
 *			always result in 0
 *				Eg: 1,2,3,1,2,3,1,1 ---- Since all numbers has their own pairs, XORing all of them will be 0.
 *
 *	Combining the above properties, If we were to XOR all the numbers in the array, these things will happen:
 *		-	All the pairs will be cancelled to 0 due to XOR-ing the same two numbers
 *		-	Since the two numbers are unique, XORing those two numbers will yield their difference in bit positions
 *
 *	Now, since IT IS GUARANTEED to have only TWO different unique numbers, we could somehow separate them into 2 groups, based on the
 *	difference in bit positions. Using only one bit's position where the two numbers are different will be enough to separate the
 *	2 unique numbers into 2 separate groups already.
 *	Therefore find the first position where the two unique numbers differ, using it as the AND mask.
 *
 *	By separating, we don't actually need to reorganize the array such that those pass the AND mask is at the left and those fails goes to
 *	the right.
 *	When separated, There could be pairs which are located in the same group (They have no difference in binary string). Therefore, by XORing
 *	those groups, the pairs cancel and what's left will be the unique number itself!
 *
 *	Iterate through the array. If it passes the AND mask, XOR it with the 1st resulting array's value, else, XOR it with the 2nd resulting
 *	array's value.	
 *
 *
 */


public class Single_Number_III {

//	public int[] singleNumber(int[] nums) {
//		
//		if (nums.length < 2) return new int[] {0,0};
//		
//		int[] res = {Integer.MIN_VALUE, Integer.MIN_VALUE };
//		
//		int left = 0, right = nums.length - 1;
//		int reset = 0;
//		
//		while (res[1] == Integer.MIN_VALUE ) {
//			
//			left = reset;
//			while (left < right) {
//				
//				if (nums[left] == nums[right] ) {
//					int temp = nums[reset];
//					nums[reset] = nums[left];
//					nums[left] = temp;
//					reset ++;
//					right --;
//					break;
//				}
//				left ++;
//			}
//			if (left == right) {
//				if (res[0] == Integer.MIN_VALUE ) res[0] = nums[left];
//				else res[1] = nums[left];
//			}
//			
//		}
//		
//		return res;
//        
//    }
	
	public static int[] singleNumber(int[] nums) {
		if (nums.length == 0) return new int[] {0,0};
		
		int xor = 0;
		for (int i: nums) {
			xor = xor ^ i;
		}
		
		int mask = 1;
		while ( xor != 0 && (mask & xor) == 0)
			mask <<= 1;
		
		int[] res = new int[] {0, 0};
		
		for (int i: nums) {
			if ( (i & mask) > 0 )
				res[1] = res[1] ^ i;
			else
				res[0] = res[0] ^ i;
		}
		
		return res;
	}
	
	
	public static void main(String[]args) {
		int[] arr = {1,3,4,4};
		singleNumber(arr);
	}
	
	
}
