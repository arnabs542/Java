package Bit_Manipulation;

//https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3319/
 
/*
 *	 Key to remember: when we want to flip a binary number, it can be done with XOR with '1', see:
 *				1 XOR 1 -> 0
 *				0 XOR 1 -> 1
 *	 Also, XOR with '0' preserves the original binary number:
 *				0 XOR 0 -> 0
 *				1 XOR 0 -> 1
 *	 Although we can use the complement bitwise operator, it will give leading 1's, which is not what we want
 *	 Therefore we could create a 'mask' which take advantage of the above XOR property: See:
 *
 *	 If we have binary number 0000 0101, then the mask should look like 1111 1000 (where the place of 0 is the digits to be preserved)
 *	 The mask can be used to XOR with the complemented binary number to extract only the useful numbers:
 *
 *	 (mask) 1111 1000 XOR (complemented bin) 1111 1010 -> 101
 *	 
 *	 To initialize the mask, use ~0, which is 1111 1111. Then, determine the places we need to preserve: by using AND operation
 *	 Key: Continue shifting left to the mask (adding 0's to the rightmost side of mask, where until certain times, will give u a mask
 *		  where when AND with the original number, will give all 0:
 *
 *			1111 1111 AND 0000 0101 --> 0000 0101 (First Iteration)
 *			1111 1110 AND 0000 0101 --> 0000 0100 (Second Iteration)
 *			1111 1100 AND 0000 0101 --> 0000 0100 (Third Iteration)
 *			1111 1000 AND 0000 0101 --> 0000 0000 (Mask is found at Fourth Iteration)
 *
 *	Use that mask to XOR with the complemented num and you'll have ur answer
 *	
 */


public class FindComplements {

}
