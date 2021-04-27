package Easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/power-of-three/
/*
 * 	This is a Math problem.
 * 
 * 	The native algorithm already runs in O(log3 N) time, via iteration or recursion by simply repeatedly
 * 	dividing the original n, given that it is not negative to begin with
 * 
 * 	Some other methods are using logarithm to check if the power is actually an integer. If it is not, then
 * 	it is not valid power of three. However time complexity depends on the language implementation
 * 
 * 	Also, we could convert number into base 3, and check if it leads with a single '1' digit followed by all zeros.
 * 
 * 	Since by doing some math you know the number of solutions is relatively small, you can use HashSet for this job.
 * 
 * 	Lastly the most hardest solution, is that realize 3 is a prime number, and prime x prime is also prime.
 * 	Therefore, first you know the upper bound of problem is only until INT_MAX, which the highest power of 3 is 3^19.
 * 	Then, for a number N to be valid power of 3 which is prime, it must be able to divide 3^19. Check with modulo
 */

public class Power_of_Three {
	
	//	Naive Iterative method
	public boolean isPowerOfThree(int n) {
		if (n < 0) return false;
        while (n > 1 && n % 3 == 0) 
        	n /= 3;
        return n == 1;
    }
	
	//	Recursive method
	public boolean isPowerOfThree2(int n) {
		if (n == 1) return true;
		if (n < 1 || n % 3 != 0) return false;
		return isPowerOfThree(n / 3);
	}
	
	//	Without loops may require caching beforehand
	private static Set<Integer> pow3 = new HashSet<>();
	static {
		for (long i = 1; i < Integer.MAX_VALUE; i *= 3)
			pow3.add((int)i);
	}
	public boolean isPowerOfThree3(int n) {
		return pow3.contains(n);
	}
	
	
	//	Idea: 3 is prime. prime x prime is also prime. So check divisibility of 3^19 (Bounded)
	public boolean isPowerOfThree4(int n) {
		return n > 0 && 1162261467 % n == 0;
	}
	
}
