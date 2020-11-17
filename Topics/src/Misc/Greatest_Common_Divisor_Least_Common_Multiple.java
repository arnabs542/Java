package Misc;

/*
 * 	Perhaps we can find the greatest common divisor of two numbers by finding out all divisors of the two numbers,
 * 	then selecting the greatest one which the two have in common.
 * 	This involves finding prime factors, and definitely will cost some time. Is there some easier solution?
 * 
 * 	=============================
 * 	EUCLIDEAN ALGORITHM
 * 	=============================
 * 
 * 	---------------
 * 	1. Overview
 * 	---------------
 * 
 * 	Euclidean algorithm is a good algorithm to find out GCD of 2 natural numbers (Even if any is negative, just take positive
 * 	of it and it still same)
 * 
 * 	What we will do is, Assume we having two numbers to find GCD, n1 and n2,
 *	WHERE n1 < n2 
 *
 *	We will obtain the remainder / modulo of n2 divided by n1. Definitely, the remainder will be less than that of n1.
 *	Then, we will continue with the smaller n1 as new n2, and took the remainder of it as n1, and repeat the process.
 *
 *	The process is repeated until the remainder is 0. If remainder is 0, that already means it is completely divisible.
 *	In that case, n1 will be the GCP solution.
 *	
 *	See algorithm:
 *
 *	while n1 is not 0
 *		remainder = n2 % n1
 *		n2 = n1
 *		n1 = remainder
 * 	return n2
 * 
 * 
 * 	-----------------
 * 	2. Theorem
 * 	-----------------
 * 
 * 	The foundation for this theorem is that
 * 		Given n2 is larger than n1, then definitely,
 * 
 * 	n2 = (n1)(q) + (r)				//	Means n2 can fit 1 or more n1 into it, added with some value
 * 									//	That means n2, when divided with n1, will produce answer q and remainder r
 * 
 * 
 * 	Now, the theorem states that:
 * 		If n2 = (n1)(q) + (r), then GCD( n2, n1 ) = GCD( n1, r )
 * 
 * 
 * 	How does this work out? This is actually quite an recurrence relation. Assuming that is true,
 * 		GCD( n2, n1 ) = GCD( n1, r)	,	then down a level,
 * 		GCD( n1, r ) = GCD( r, r1 )	,	r1 is another remainder. Now, see how they connected? THe following is equivalent
 * 
 * 		## GCD( n2, n1 ) = GCD( r, r1 )
 * 
 * 	
 * 	Now, let's dig deep into this recurrence relation. What will be base case?
 * 	When we have 2 numbers, but one is 0, then surely, their GCD is the non-zero number because
 * 		>	0 is divisible by any number
 * 		>	If that's the case, obviously greatest divisor of that non-zero number is the number itself.
 * 
 * 		GCD( n2, n1 ) = GCD( n1, r )
 * 		GCD( n1, r ) = GCD( r, r1 )
 * 				...
 * 		GCD( rn, rn+1 ) = GCD( rn+1, 0 )		<--	The base case. GCD( rn+1, 0 ) will be rn+1
 * 
 * 	Connect them together,
 * 		GCD( n2, n1 ) = GCD( rn+1, 0 )
 * 
 * 	As you can see, our mission is to find rn+1, which is found when
 * 		>	We keep replacing n2 with n1, and n1 with remainder
 * 		>	Repeat until remainder becomes 0. In that case, n1 is the GCD of whole solution
 * 
 * 
 * 
 * 	-----------------
 * 	3. Proof
 * 	-----------------
 * 	We have to prove that
 * 		GCD( n2, n1 ) = GCD( n1, r ) is true
 * 
 * 	Let d be any arbitrary common divisor of n1 and n2. Then
 * 	
 * 	d | n1 		AND		d | n2					(means d divides n1 and d divides n2)
 * 
 * 	From equation, n1 and n2 are related by
 * 		n2 = (n1)(q) + (r)
 * 		r = n2 - (n1)(q)
 * 
 * 	Let n1 and n2 be divided by that divisor d be A and B, then
 * 		(n2 - n1*q) == (Ad - Bd*q)
 * 	Since we can easily factor out d, it means that (n2 - n1*q) is divisible by d as well.
 * 	
 * 		d | (n2 - n1*q)
 * 		d | r
 * 
 * 	Down a level, it would be GCD( n1, r ). We have already assumed that d is divisor of n1, and proved that r is divisible
 * 	by d as well. Proved!
 * 
 * 
 *  -----------------
 * 	4. How about LCM?
 * 	-----------------
 * 
 * 	Having GCD in our sleeves, finding LCM is a piece of cake.
 * 
 * 	Just know this:
 * 
 * 		n1 * n2 = GCD * LCM
 * 
 * 	Therefore,
 * 
 * 		(n1 * n2) / GCD = LCM
 */


public class Greatest_Common_Divisor_Least_Common_Multiple {
	
	private static int findGCDIterative(int n1, int n2) {
		//	Eliminates negatives, and also prevent value 0 since value 0, if exist, will assign to smaller
		int greater = Math.max( Math.abs(n1), Math.abs(n2) );
		int smaller = Math.min( Math.abs(n1), Math.abs(n2) );
		
		while (smaller != 0) { 
			int remainder = greater % smaller;
			greater = smaller;
			smaller = remainder;
		}
		
		return greater;
	}
	
	
	private static int findGCDRecursive(int n1, int n2) {
		if (n1 == 0 || n2 == 0) return n1 == 0? n2: n1;
		n1 = Math.abs(n1);
		n2 = Math.abs(n2);
		
		if (n1 > n2) 
			return findGCDRecursive( n1 % n2, n2 );
		else
			return findGCDRecursive( n2 % n1, n1 );
	}
	
	
	
	
	private static int findLCM(int n1, int n2) {
		
		int gcd = findGCDIterative(n1, n2);
		return (n1 / gcd) * (n2 / gcd);
		
	}
	
	
	
	
	public static void main(String[]args) {
		System.out.println( findGCDIterative(52, 12) );
		System.out.println( findGCDRecursive(12, 52) );
		
		System.out.println( findLCM(5, 12) );
	}
	
	

}
