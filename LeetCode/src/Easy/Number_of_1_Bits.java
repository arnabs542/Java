package Easy;

//https://leetcode.com/problems/number-of-1-bits/

/*	
 * 	One simple way is to using the AND property of bits to check if a particular place in the int is ONE or ZERO. We would use a mask which
 * 	at one time, there is only one ONES in the mask, starting with 1
 * 	After checking each time, we would perform a left shift to shift the 1 to the left. Eg: 0001 to 0010 to check the second place
 * 	
 * 	Since the question said that the int is of unsigned integer, there will be 32 bits to check. We would have to check all of them.
 * 
 * 
 * 	Another way is to keep checking the tail of the n by using AND mask of 1. After checking, right shift the n, discarding the last bit which
 * 	is just done checked. Repeat until n is equal to 0
 * 
 * 
 * 
 * 	Another simpler trick is to repeatedly remove the least significant 1 of the number. Example of the procedure is as follows:
 * 		1101 --------> 1100 --------> 1000 ---------> 0000
 * 	Therefore we can break the loop when the n achieves 0 already. Since it undergoes 3 loops, the number of ones is determined to be 3.
 * 
 * 	How can we do that? We can actually repeatedly perform AND operation with the (number-1) of itself.
 * 
 * 	(1101) & 1100 ---------> (1100) & 1011) -----------> (1000) & 0111 -------------> 0000
 * 
 */

public class Number_of_1_Bits {
	public static int hammingWeight(int n) {
		int mask = 1;
		int count = 0;
		for (int i = 0; i < 32; i ++ ) {
			if ( (mask & n) != 0) {
				count++;
			}
			mask <<= 1;
		}
		return count;
    }
	
	public static int hammingWeight2(int n) {
		int count = 0;
		while (n != 0) {
			count += (n & 1);
			count >>>= 1;
		}
		return count;
	}
	
	public static int hammingWeightTrick(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n-1);
			count++;
		}
		return count;
	}
	
	public static void main(String[]args) {
		System.out.println( hammingWeight(7) );
	}
}
