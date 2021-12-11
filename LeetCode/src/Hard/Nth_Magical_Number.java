package Hard;

//https://leetcode.com/problems/nth-magical-number/
/*
 * 	This is a Math problem with optionally binary search involved.
 * 
 * 	---------------------------------------------------------------
 * 
 * 	By writing out the case where a = 2 and b = 3, you will see the pattern:
 * 
 * 		(2) - 2, 4, 6, 8, 10, 12...
 * 		(3) -  3,   6,   9,   12...
 * 
 * 	Here, the 6 is the lowest common multiple (LCM) of 2 and 3. Below the LCM always contains 3 intermediate magical
 * 	numbers {2,3,4}. After the LCM, the pattern repeats with 2*LCM, 3*LCM...
 * 
 * 	Let Lowest common multiple be L. We can find out the number of magical numbers in a pattern - M.
 * 		M = (L / A) + (L / B) - 1.
 *	In example above, L = 6, so
 *		M = (6 / 2) + (6 / 3) - 1 = 4
 * 	
 * 	Then, we have to find the Nth magical number. Since we now know the number of magical numbers in a pattern, M,
 * 	we can calculate how many patterns are required to reach Nth magical number:
 * 		N = Mq + r
 * 	Where q is the quotient and r is the remainder. We obtain both q and r by integer division (N / M)
 * 
 * 	Therefore, now with r known, we essentially have to find out the rth magical number in a pattern.
 * 	- 	It is known that r < M.
 * 	- 	In turn, M < A+B. Even in worst case where L = AB, note that the magical numbers are also increasing by min(A/B)
 * 	  	each time, so it becomes O(A) or O(B) also.
 * 		Eg: (4, 39999) - LCM = 4*39999 = 159996. But since magical numbers = 4,8,12.... total M is 159996/4 = 39999.
 * 
 * 	With this, we can brute force our way to find  the rth magical number, plus the M*q base to obtain the correct answer.
 * 
 * 	
 */

public class Nth_Magical_Number {
	
	public int nthMagicalNumber(int n, int a, int b) {
		final int MOD = 1_000_000_007;
		
		int L = a * b / gcd(a, b);
		int M = (L / a) + (L / b) - 1;
		int Q = (n / M);
		int R = (n % M);
		
		long res = (long)L * Q % MOD;
		if (R == 0) return (int)res;
		
		int aHead = a;
		int bHead = b;
		
		for (int i = 0; i < R-1; ++i) {
			if (aHead < bHead) aHead += a;
			else bHead += b;
		}
		
		return (int)( res + Math.min(aHead, bHead) ) % MOD;
    }
	
	
	// Euclidean LCM algorithm in log(N)
	// b must always > a.
	private int gcd(int a, int b) {
		if (a == 0) return b;
		return gcd(b % a, a);
	}
	
}
