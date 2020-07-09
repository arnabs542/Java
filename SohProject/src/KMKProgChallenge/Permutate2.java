package KMKProgChallenge;

import java.util.Scanner;
public class Permutate2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		for (int i = 1; n >= 0; i ++) {

			int r = scan.nextInt();
			int fact1 = 1, fact2 = 1;
			
			if (n != 0) {
				for (int j = n; j >= 1; j --)
					fact1 *= j;
			}
			
			if (n-r != 0) {
				for (int j = n-r; j >= 1; j --)
					fact2 *= j;
			}
			
			System.out.println("Case #" + i + ": " + (fact1 / fact2) );
			
			n = scan.nextInt();
		}
		
		
	}		//end of main

}		//end of class
