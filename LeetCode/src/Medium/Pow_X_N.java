package Medium;

import java.util.Stack;

//https://leetcode.com/problems/powx-n/

/*
 * 	My come up solution is using power rule where 2^x * 2^y will be 2*x+y
 * 	Take advantage of the fact that if we keep powering the number by itself, it will show the power of the previous x2.
 * 	Eg For Base 3:
 * 
 * 	This is the power 
 * 	
 * 	(1) -> (2) -> (4) -> (8) -> (16)...			(Each time the base number multiply by itself, it goes x2, and forms a binary system!
 * 	 |		|	   |	  |		  |
 * 	(3)	   (9)    (81)   (6561) (43046721)		(Each time it squares by itself)
 * 
 * 	This is the result of power base 3
 * 
 * 	We would keep track of the cumulative sum of the powers generated. While cumulative is lesser than targeted n, keep generating.
 * 	If it exceeds, then stop the generation and go backwards, adding those powers up as long as it doesn't go over targeted n, and multiplying
 * 	result with the power of x's.
 * 
 * 	Using above property, we can find out the power in just log n times. Since the binary system can form up any kind of number.
 *  For example, to find out 3^13, we would just do
 * 		3^8 * 3^4 * 3^1 
 * 		==> 3^(8+4+1) 
 * 		====> 6561 * 81 * 3 
 * 
 * --------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	With an ingenious way, it can be solved recursively in short and concise code.
 * 	Given the target power n, if the n is negative, we would convert it into positive and make the current value x to be 1/x
 * 
 * 	Now we have to reduce the target power n into 0 recursively. Each time we multiply x by x itself (square it), the target power would
 * 	divided by 2. However, problem arise when the power is odd by itself. To solve this, we would take out one instance of x from the
 * 	power (2^9 is equivalent to 2^8 * 2), therefore we end up with x * (x^n-1). Now we would have no problem to reduce the latter to 0.
 * 	
 * 	The base case is when the n is reduced to 0, which is equivalent to x^0, then just return 1.
 * 	When the n is odd, return x * recurse(x*x, n/2).
 *	When the n is even, return recurse(x*x, n/2) only
 *	
 *	At the end, it will reach at n = 1 where the final x will be multiplied up along with the odd number occurrences above, and give back
 *	the final result.
 *
 *	See 2^-9
 *
 *	Since power is negative, we convert 2 to 1/2 and change power to 9. So it becomes (1/2)^9, which is mathematically equivalent
 *	Now Since power is odd, we take out one occurrence and it becomes (1/2) * recurse(0.5 * 0.5, 9/2) <<< 9/2 gets 4, which is what will
 *		return if power is 8. Thats why we take out the occurrence!
 *	For (1/4) and n is 4, it would just return recurse(1/4 * 1/4, 4/2)
 *	For (1/16) and n is 2, it would just return recurse(1/16 * 1/16, 2/2)
 *	Now it is (1/256) and n is 1. It would return 1/256 * recurse(1/256 * 1/256, 1/2).
 *	Since now n is 0, return 1;
 *
 *	Returning up the recursion stack, it will become (1/2) * (1/256) * 1
 *	Which is equivalent to 2^-1 * 2^-8 * 2^0 ======> 2^-9!!!
 * 	
 */

public class Pow_X_N {
	
//	public static double myPow(double x, int n) {
//		if (n == 0) return 1;
//		
//		long nPos = Math.abs((long)(n));
//		
//		Stack<Double> li = new Stack<>();
//		li.push(x);
//		long curr = 1;
//		long cum = 1;
//		
//		while (cum < nPos) {
//			curr *= 2;
//			cum += curr;
//			li.push( li.peek() * li.peek() );
//		}
//		System.out.println(li);
//		cum = 0;
//		double result = 1;
//		
//		while (!li.isEmpty() ) {
//			Double pop = li.pop();
//			if (cum + curr > nPos) {
//				curr /= 2;
//				continue;
//			}
//			cum += curr;
//			curr /= 2;
//			result *= pop;
//		}
//		
//		return (n < 0)? 1.0/result : result;
//	}

	
	
	public static double myPow(double x, int n) {
		if (n == 0) return 1;
		long num = n;
		if (num < 0) {
			x = 1/x;
			num = -num;
		}
		
		return (num % 2 == 0)? myPow(x*x, (int)(num/2) ) : x * myPow(x*x, (int)(num/2) );
	}
	
	public static void main(String[] args) {
		System.out.println( myPow(2.0, Integer.MAX_VALUE - 1) );
	}
	
}
