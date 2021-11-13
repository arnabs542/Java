package Problemsets;

import java.util.Scanner;

//https://codeforces.com/problemset/problem/71/A
/*
 * 	For each string, simply output the following format:
 * 
 * 		if len(s) <= 10, return as it is
 * 		otherwise return {first char}{length - 2}{last char}
 */

public class _0071A_Way_Too_Long_Words {
	
	static String wayTooLongWords(String s) {
		if (s.length() <= 10) return s;
		return String.format("%c%d%c", s.charAt(0), s.length()-2, s.charAt( s.length() - 1) );
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		
		while (n-- > 0) {
			System.out.println( wayTooLongWords( scan.nextLine() ) );
		}
	}
}
