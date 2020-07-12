package Easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/reverse-bits/

/*
 * 	A Direct solution is to start the resulting binary string from 0, and for 32 times, obtain the last bit of the original binary string.
 * 	If the last bit of original is 1, then add 1 to the resulting binary string. Shift the original binary string to the right by one place
 * 	so that we can get the next second bit and so on. Also shift the resulting binary string by 1 place to the left (except when we are
 * 	at the last loop, which we don't have to shift left) so the binary string can be reversed (The last bit is pushed to furthest left).
 * 
 * 	--------------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	When the function is required to loop many times, it's better to trade space for time complexity. We will use cache.
 * 	First we have to divide the 32 bits into 4 group of bytes. We are going to check if the byte is actually been reversed before so that
 * 	we don't have to reverse the same pattern byte again.
 * 
 * 	To divide the 32 bit into 4 bytes, we need to iterate 4 times, where each time original binary string is shifted right by 8, 16,24 times,
 * 	and masked with 8 1's (255 or 0xFF in HEX) to eliminate the left part exceeding a byte value
 * 
 * 	To reverse a byte is almost the same as above method, but just iterate 8 times (which is 8 bit)
 */

public class Reverse_Bits {
	
//	public static int reverseBits(int n) {
//		int res = 0;
//		for (int i = 0; i < 31; i ++ ) {
//			System.out.printf("Pass %d: %s\n", i, Integer.toBinaryString(n) );
//			res += ( (n & 1) != 0? 1: 0);
//			n >>= 1;
//			res <<= 1;
//		}
//		return (n & 1) != 0? res + 1: res;
//    }
	
	static final Map<Byte, Integer> cache = new HashMap<>();
	public static int reverseBits(int n) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i ++ ) {
			//The i is loop 4 times. Each loop, n is shifted right 8 * i times, and use a hexadecimal mask (equivalent to 255 or 11111111 to mask
			//out the last 8 bits.
			bytes[i] = (byte) ((n >>> (8*i) ) & 0xFF);
		}
		int res = 0;
		for (int i = 0; i < 4; i ++ ) {
			res += reverseByte( bytes[i] );
			if (i < 3) {
				res <<= 8;
			}
		}
		return res;
	}
	private static int reverseByte(byte n) {
		byte ori = n;
		if (cache.containsKey(n) )
			return cache.get(n);
		
		int rev = 0;
		for (int i = 0; i < 8; i ++ ) {
			rev += (n & 1);
			n >>>= 1;
			if (i < 7)
				rev <<= 1;
		}
		cache.put(ori, rev);
		return rev;
	}
	
	
	
	
	public static void main(String[]args) {
		System.out.println((byte)(-3) );
	}
}
