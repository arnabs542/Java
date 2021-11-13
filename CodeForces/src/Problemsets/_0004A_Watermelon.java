package Problemsets;

import java.util.Scanner;

//https://codeforces.com/problemset/problem/4/A
/*
 * Simple beginner problem - Determine if there is two even numbers a and b such that a+b = w
 * 
 * Recall:
 * 		odd + odd = even
 * 		odd + even = odd
 * 		even + even = even
 * 
 * Therefore, if w is odd, then it must not possible to split into two evens. Remaining is even numbers, which should
 * always be ok to split into a+b
 * Also edge case, w=2 must only can be split into 1 + 1.
 */

public class _0004A_Watermelon {
	
	// Use bit trick (last bit) to check odd or even (You can also use modulus)
	// Edge case is w=2 - Although it is even, it can only be split into 1 and 1
	static void watermelon(int w) {
		if ( (w & 1) == 0 && w != 2) System.out.println("YES");
		else System.out.println("NO");
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		watermelon(scan.nextInt());
	}
}
