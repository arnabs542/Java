package Medium;

import java.util.ArrayList;
import java.util.Arrays;

//https://leetcode.com/problems/single-number-iii/

/*
 * 	The question requires solving it in O(N) time and O(1) space complexity.
 * 
 * 	There are several easier solutions available:
 * 	>	Perform sorting and check
 * 	>	Using HashSet/ HashMap
 * 	>	Brute force O(N^2) time
 * 
 * 	However, there is an ingenious way of doing it - Using properties of bit manipulation (XOR)
 * 
 * 	-----------------------------------------------------------------------------------------------------------------------
 * 
 * 	The solution to do it in O(n) time complexity is to use bit manipulation.
 * 
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
 *	Let the two unique numbers be A and B respectively.
 *	The idea is, we have iterated once through the array to obtain the XOR of two unique numbers: A xor B.
 *	
 *	The resulting XOR shows which of the bits that A and B differ. If the AxorB's last bit is set (1), then A and B has
 *	differing bits on the last bit. if AxorB's last bit is 0, that means A and B has the same last bit value.
 *
 *	Then, we just require the LAST SET BIT IN AxorB, which is adequate to show the position which A and B differs.
 *	And this is done by the neat trick of:
 *			AxorB & -AxorB
 *
 *	Eg: 
 *			If AxorB = 0x110, then
 *			AxorB & -AxorB = 0x010
 *
 *	We iterate through the array once more, but this time XORing the values that indeed has the (AxorB & -AxorB) bit set
 *	to 1. In this way, we are able to include one of A or B only, but not both.
 *
 *	Using this, we essentially already obtained A or B. To get the other unique value, simply XOR the AxorB with A.
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
