package Easy;

//https://leetcode.com/problems/power-of-two/

public class Power_Of_Two {
	
	public boolean isPowerOfTwoNaive(int n) {
		for (int i = 0; true; i++ ) {
			int pow = (int)Math.pow(2, i);
			if (pow == n) return true;
			if (pow > n) return false;
		}
	}
	
	public boolean isPowerOfTwo(int n) {
		boolean isLone = false;
		while (n != 0) {
			if ( (n & 1) == 1) {
				if (isLone) return false;
				isLone = true;
			}
			n >>= 1;
		}
		return isLone;
    }
	
	//If n is power of 2, then we can keep dividing n by 2 and in the end it would end up in 1.
	public boolean isPowerIterative(int n) {
		while (n % 2 == 0) 
			n /= 2;
		return n == 1;
	}
	public boolean isPowerRecursive(int n) {
		//If n is no longer divisible by 2, return if it is 1 or not. Exception is that n = 0, then return false as well.
		if (n % 2 != 0 || n == 0 ) return n == 1;
		return isPowerRecursive(n / 2);
	}
	
	//Magical bitwise operation. We AND the n and n-1. If n is power of 2, then its binary form would have only exactly 1 Ones. Then the
	// n-1 would have all the zeroes after the lone Ones flipped to 1. See:
	/*
	 * 	Eg: If n = 16, binary = 10000
	 * 		   n-1 = 15, binary = 01111
	 * 	Therefore if we AND these two, it shall end up as zero, else it is not power of 2
	 */
	public boolean isPowerBitwise(int n ) {
		return n > 0 && (n & (n-1) ) == 0;
	}
	
	/*
	 * Mathy solution: Observe that the maximum possible power of 2 in Integer's range is 2^30. Observe also that for a higher power of two,
	 * it can be fully divisible (mod == 0) by any power equal to or less than itself.
	 * 1,2,4,8,16,32,64 (See how like 64 can be fully divisible by anything below itself)?
	 * 
	 * For any n that is 2^k, then 2^30 = 2^k (2^30-k), therefore 2^30 % 2^k == 0
	 * 
	 */
	public boolean isPowerMath(int n) {
		return n > 0 && Math.pow(2, 30)%n == 0;
	}
}
