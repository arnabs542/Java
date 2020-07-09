package KMKProgChallenge;

import java.util.Scanner;
public class Permutate {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Permutate obj = new Permutate();
		
		int n = scan.nextInt();
		
		for (int i = 1; n >= 0; i ++) {

			int r = scan.nextInt();
			
			int permutate = obj.factorial(n) / obj.factorial(n-r);
			System.out.println("Case #" + i + ": " + permutate);
			
			n = scan.nextInt();
		}
		
		
	}		//end of main
	
	
	int factorial (int n) {
		if (n == 0)
			return 1;
		
		int factSum = 1;
		for (int i = n; i >= 1; i --) {
			factSum *= i;
		}
		return factSum;
		
	}		//end of factorial()

}		//end of class
