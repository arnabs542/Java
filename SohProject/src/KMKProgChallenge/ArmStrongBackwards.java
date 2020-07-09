package KMKProgChallenge;

import java.util.Scanner;
public class ArmStrongBackwards {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int current = scan.nextInt();
		int endNum = scan.nextInt();
		
		for ( ; current <= endNum; current++ ) {
			int num = current;
			
			//Check current is how many digits
			int numDigits = 1;
			while (num / 10 != 0) {
				numDigits ++;
				num /= 10;
			}
			
			//Reset the value of num to current number
			num = current;
			
			//Digit Extractor and Sum
			//attemptSum takes each digit extracted and power it to the number of digits, store in itself
			int attemptSum = 0;
			
			while (num != 0) {
				int digit = num % 10;
				attemptSum += Math.pow(digit, numDigits);
				num /= 10;
			}
			
			if (attemptSum == current)
				System.out.println(current);
			
		}
		

	}		//end of main()

}		//end of class
