package Easy;

//https://leetcode.com/problems/hamming-distance/

/*
 * 	It is asking us to find the number of different bits between the two numbers. This question is bit manipulation question
 * 	An approach is to use a mask of single bit of state 1, and each time AND operation on the two numbers
 * (If bit is 0, it returns 0, else it returns 1)
 *  If the AND result isn't same, then that means this bit is different and we should increment the counter by 1
 *  Since integer is guarenteed to be in size 32 bit, then we just run through it 31 times, initially as 1.
 *  
 *  Another idea is to XOR the two numbers. Remember XOR only returns true if the bits are different. Afterwards we'll just count the number of 1 in the resulting
 *  XOR solution
 *  
 *  Also, you can use the right shift of bitwise operation on the XOR result. Right shifting causes the rightmost bits to be shifted away.
 *  By starting at i = 0, we will test the bit is on or off by AND it with 1. This is about the above two ideas combined
 *  
 *  
 *  
 */

public class Hamming_Distance {
	
	public static int hammingDistance(int x, int y) {
		int mask = 1;
		int counter = 0;
		for (int i = 0; i < 31; i ++ ) {
			if ( (x & mask) != (y & mask) ) counter++;
			mask <<= 1;
		}
		return counter;
	}
	
	private static int hammingDistanceXOR(int x, int y) {
		return Integer.bitCount( x ^ y);
	}
	
	private static int hammingDistanceCombine(int x, int y) {
		int result = x ^ y;
		int count = 0;
		while (result != 0) {
			count += (result & 1);
			result >>= 1;
		}
		return count;
	}
	
	public static void main(String[]args) {
		System.out.println(hammingDistance(2,4) );
	}
}
