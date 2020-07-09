package Misc;

/*
 * Sieve of Eraosthenes is an algorithm to find prime numbers up to specified n. It was invented around 200BC meaning it is around 2200 years old
 * 
 * It works by first having a boolean to mark the eliminated number first. THen we will start looping from 2 up to n.
 * If the i is already eliminated as shown in the eliminated array, simply continue.
 * Else, it is a prime number! Therefore, we put that into our results.
 * Now, since the multiples of this prime number is not prime, we need to eliminate them, up until n.
 * 	An optimization would be start eliminating from the square of this number, which is i * i. Every loop we'll increment by i
 */

public class Sieve_of_Eratosthenes {
	
	static void printPrimes(int limit) {
		boolean[] eliminate = new boolean[limit + 1];
		
		for (int i = 2; i <= limit; i ++ ) {
			if (i % 100 == 0) System.out.println("");
			if (eliminate[i]) continue;
			
			System.out.print(i + " ");
			
			for (long e = 1L * i * i; e <= limit; e += i) {
				eliminate[(int)e] = true;
			}
			
		}
	}
	
	public static void main(String[]args) {
		long time = System.currentTimeMillis();
		printPrimes(99999999);
		System.out.println(System.currentTimeMillis() - time);
	}
	
}
