package Easy;

//https://leetcode.com/problems/power-of-four/

/*
 * 	There is always a bitwise operation trick to check if whether a digit only consists of a single bit only:
 * 		AND operation on N and N-1
 * 	If N is only one bit, then N-1 would be all 1 after that single bit, like 1000 -> 0111
 * 	Therefore if I go and AND them, it should be always be 0 if it consist of single bit only
 * 
 * 	Then, there is a specific pattern for power of fours:
 * 	1	>	1
 * 	4	>	100
 * 	16	>	10000
 * 	64	>	1000000
 * 
 * 	Therefore we just need a mask to ensure that the AND operation between N and the mask would not be 0, after the first test
 * 	(Only 1 bit)
 * 
 * 	The hexadecimal for this 10101010101.... constant is 0x55555555 (8 5's)
 */

public class Power_Of_Four {
	
	public boolean isPowerOfFour(int num) {
		int mask = Integer.parseInt("01010101010101010101010101010101", 2);
		
		//Test 1: Only 1 digits
		if ( (num & (num - 1) ) != 0 ) return false;
		
		//Test 2: Must be bit at correct place
		return (mask & num) != 0;
	}
	
	public static void main(String[]args) {
		System.out.println( Integer.toBinaryString( Integer.valueOf( "66666666" , 16) ) );
	}
}
