package Problemsets;

import java.util.Scanner;

//https://codeforces.com/problemset/problem/231/A
/*
 * For each problem, simply find out if the sum is >= 2. If yes, add 1 to counter. At the end, return counter
 */

public class _0231A_Team {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int res = 0;
		
		while (n-- > 0)
			res += ( scan.nextInt() + scan.nextInt() + scan.nextInt() ) / 2;
		System.out.println(res);
	}
	
}
