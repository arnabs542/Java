package KMKProgChallenge;

import java.util.Scanner;
public class MaxMin3Num {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		for (int i = 1; i <= n; i ++) {
			int num1 = scan.nextInt();
			int num2 = scan.nextInt();
			int num3 = scan.nextInt();
			
			int max, min;
			
			//If num1 is the largest
			if (num1 > num2 && num1 > num3) {
				max = num1;
				if (num2 < num3)
					min = num2;
				else
					min = num3;
			}
			
			//Else if num2 is the largest
			else if (num2 > num1 && num2 > num3) {
				max = num2;
				if (num1 < num3)
					min = num1;
				else
					min = num3;
			}
			
			//Else if num3 is the largest
			else {
				max = num3;
				if (num1 < num2)
					min = num1;
				else
					min = num2;
			}
			
			System.out.println("Case #" + i + ": " + min + " " + max);
		}
		
		
	}

}
