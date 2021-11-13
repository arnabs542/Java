package Problemsets;

import java.util.Scanner;

//https://codeforces.com/problemset/problem/158/A
/*
 * 	Since the input is already sorted, it makes process quicker
 * 	Keep track of each participant's ranking:
 * 
 * 		If score <= 0, we quit the loop. The rest of scores is also <= 0.
 * 		If ranking is less than or equal k: Qualify.
 * 		If ranking is greater than k, then we check if score is equal to kth ranker's score or not. We can also do this
 * 		via a prev tracking variable.
 */

public class _0158A_Next_Round {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int prev = 0;
		
		int res = 0;
		
		for (int i = 1; i <= n; ++i ) {
			int score = scan.nextInt();
			if (score <= 0 || (i > k && prev != score)) break;

			prev = score;
			++res;
		}
		System.out.println(res);
	}
	
}
