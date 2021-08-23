package security;

import java.util.Scanner;

public class Security_Permutations {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] fx = new int[n+1];
		
		for (int i = 1; i <= n; ++i)
			fx[i] = scan.nextInt();
		
		for (int i = 1; i <= n; ++i) {
			System.out.println( fx[fx[i]] );
		}
		
		scan.close();
	}
}
