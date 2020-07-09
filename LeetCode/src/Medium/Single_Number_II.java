package Medium;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/542/week-4-june-22nd-june-28th/3368/

/*
 * 	Given all the integers MUST APPEAR THRICE except ONE AND ONLY ONE appear ONCE. How would you find the lone integer that appear once?
 * 	The key here is to realize that all integers can be represented in binary form, and each binary digit represents a fixed number (power of 2)
 *	Example, 7 in binary form is 111, which is combination of 4 + 2 + 1.
 *
 *	An interesting pattern is that for example, we're given 3 7's, [7,7,7], the bits would be 3 111's. If we were to represent this from the
 *	binary's perspective, that's
 *		(4 + 2 + 1) + (4 + 2 + 1) + (4 + 2 + 1)
 *	and can be simplified to
 *		3(4) + 3(2) + 3(1)
 *	or even
 *		3(4 + 2 + 1)
 *	Now if I were to perform modulo operation, a.k.a asking what is the remainder of dividing it by 3? Since it is perfectly divisible, it will return
 *	0.
 *
 *	Therefore note that if in an array where all integers appear three times, each sum of the individual binary places (1,2,4...) will always
 *	result to 0.
 *		[2,2,2,3,3,3] = 2 + 2 + 2 + (2 + 1) + (2 + 1) + (2 + 1) = 6(2) + 3(1).
 *		Each places modulo 3 must be 0.
 *
 *	Now here's important. Since there's a lone integer in the array, when we modulo the binary places, the respective part of the lone integer
 *	will always be remainder.
 *		[2,2,2,7] = 2 + 2 + 2 + (4 + 2 + 1) = 3(2) + 4 + 2 + 1 = 4(2) + 4 + 1
 *		When modulo in 1 place, 1 will be returned
 *		When modulo in 2 place, 2 will be returned
 *		When modulo in 4 place, 4 will be returned
 *					Sum = 1 + 2 + 4 = 7, the lone integer itself
 *
 *	Those 1,2,4... are represented by whether in their binary form, the binary digit is 1 (present) or 0 (absent), therefore we could determine that
 *	by using the bitwise AND operator, and keep a counter for the overall count.
 *	
 *	Now how do we assemble the remaining bit counts back to the original lone integer? We might just add the power of 2 to the bit place to the
 *	result, but that is ok ONLY IF IT IS UNSIGNED INTEGER. This question involves also the negative integer. Therefore what we could do is
 *	assemble it back ALSO USING THE BITWISE OPERATORS
 *	
 *	OR operator came in handy since it is able to switch ON the bit places (0 to 1). Therefore when detected that the lone integer has a 1 bit 
 *	on nth place, then we create the mask (by shifting 1 to the respective place), and OR operation on the result.
 *	
 *
 *	Assuming that elements must be in within integer range of 32 bits, then we can find out the lone integer in O(32 * n) time, by checking and adding
 *	modulo of each possible bits by 3
 *		
 */

public class Single_Number_II {
	public static int singleNumber(int[] nums) {
		int[] counts = new int[32];
		
		for (int n: nums) {
			int mask = 1;
			
			for (int i = 0; i < 32; i ++ ) {
				counts[i] += ( (n & mask) == 0)? 0: 1;
				mask <<= 1;
			}
		}
		
		int result = 0;
		for (int i = 0; i < 32; i ++ ) {
			int place = (counts[i] % 3 == 1)? (1 << i) : 0;
			result = result | place;
		}
		return result;
	}
	
	public static void main(String[]args) {
		int[] arr = {-2,-2,1,1,-3,1,-3,-3,-4,-2};
		int ans = singleNumber(arr);
		System.out.println(ans);
	}
}
