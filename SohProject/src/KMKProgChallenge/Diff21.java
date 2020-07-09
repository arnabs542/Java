package KMKProgChallenge;

import java.util.Scanner;
public class Diff21 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		
		for (int i = 1; i <= n; i ++) {
			int num = scan.nextInt();
			
			int diff21 = 21 - num;
			
			// standard library method Math.abs() can also be used
			if (diff21 < 0)
				diff21 = diff21 * -1;
			
			System.out.println("Case #" + i + ": " + diff21);
			
		}
		
	}

}
