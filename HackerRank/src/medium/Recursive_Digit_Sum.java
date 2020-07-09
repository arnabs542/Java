package medium;

import java.math.BigInteger;

//https://www.hackerrank.com/challenges/recursive-digit-sum/problem

//Using Big Integer takes time, and messier, and failed to complete within time limit


public class Recursive_Digit_Sum {
	
	static int superDigit(String n, int k) {
		if (n.length() == 1 && k == 1)
			return Integer.parseInt(n);
		else {
			long sum = 0;
			for (int i = 0; i < n.length(); i ++) {
				sum += Long.parseLong("" + n.charAt(i) );
			}
			sum *= k;
			return superDigitHelper(sum);
		}
    }
	
	static int superDigitHelper(long n) {
		if ( n / 10 == 0 ) 
			return (int)n;
		else {
			long sum = 0;
			while ( n / 10 != 0 ) {
				sum += n % 10;
				n /= 10;
			}
			sum += n % 10;
			return superDigitHelper(sum);
		}
	}
	
	public static void main(String[]args) {
		System.out.println(superDigit("1234", 4) );
	}
	
}
