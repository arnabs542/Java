package Easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/repeated-substring-pattern/

/*
 * 	The question can be solved as follows:
 * 
 * 	For it to be a valid repeating substring, the whole string's length must be multiplied by some whole number, like 2, 3 or etc.
 * 	So, we find out all the divisors of the string's length, which is possible repeating substring's length.
 * 	However, it is not possible to have repeating substring's length greater than half the length of original string, so filter that
 * 
 * 
 * 	For each divisor, check if it indeed is the repeating substring.
 * 	Once all the possible divisors are exhausted, then no answers are found. Return false
 * 	
 * 	Analysis:
 * 		Lets assume that all the sqrt of the String s leads to a divisor. Divisor come in pairs. Therefore O(2 * sqrt(N) )
 * 		Then for each divisor, run a linear scan to check for repeating substring O(N)
 * 
 * 	Therefore time complexity is about O( N sqrt(N) )
 */

public class Repeated_Substring_Pattern {
	
	public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        
        if (len <= 1) return false;
        
        Set<Integer> divisors = findDivisors(len);
        
        for (int i: divisors) {
        	if ( checkSample(s, i) ) return true;
        }
        return false;
        
    }
	
	
	//	Find all divisors except n itself and divisors greater than n / 2
	private static Set<Integer> findDivisors(int n) {
		int sqrt = (int)Math.sqrt(n);
		Set<Integer> res = new HashSet<>();
		for (int i = 1; i <= sqrt; i ++ ) {
			if (n % i == 0) {
				res.add(i);
				if (n / i <= n / 2) res.add( n / i );
			}
		}
		
		return res;
	}
	
	private static boolean checkSample( String s, int len ) {
		System.out.println("Checking " + len);
		int pointer = 0;
		int checkPointer = len;
		
		while (checkPointer < s.length() ) {
			if ( s.charAt(pointer) != s.charAt(checkPointer) ) return false;
			pointer = (pointer + 1) % len;
			checkPointer ++;
		}
		
		return true;
	}
	
	
	
}
