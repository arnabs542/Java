package KMKProgChallenge;

import java.util.Scanner;
public class MaxMin3NumRepeat {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		for (int i = 1; i <= n; i ++) {
			
			int sum = scan.nextInt();
			int max = sum;
			int min = sum;
			
			for (int j = 1; j < 3; j ++ ) {
				int nextNum = scan.nextInt();
				
				sum = sum + nextNum;
				if (nextNum > max) 
					max = nextNum;
				if (nextNum < min)
					min = nextNum;
			}
			
			System.out.println("Case #" + i + ": " + min + " " + max + " " + sum);
		}
		
		
	}

}
