package Easy;

//https://leetcode.com/problems/1-bit-and-2-bit-characters/

/*
 * 	The problem is actually asking:
 * 		>There are 2 types of character available: 1BIT - '0' is allowed, '1' alone is not allowed
 * 												   2BIT - '10' and '11' is allowed. '00' or '01' is not allowed
 * 		>We need to determine whether the last bit, which must be 0, MUST BE 1BIT or not
 * 
 * 		>That boils down to the conclusion: every time you meet a 0 in the sequence, it must be 1BIT
 * 											every time you meet a 1 in the sequence, it must take also the next bit to be '10' or '11'
 *		Using a linear scan until the end, we can see if the last bit is swallowed or not, by looking at the index is on the last bit or exceeded
 */

public class _1_Bit_and_2_Bit_Characters {
	
	public boolean isOneBitCharacter(int[] bits) {
		int index = 0;
		while (index < bits.length - 1) {
			if (bits[0] == 0) 
				index ++;
			else 
				index += 2;
		}
		return (index == bits.length - 1);
    }
}
