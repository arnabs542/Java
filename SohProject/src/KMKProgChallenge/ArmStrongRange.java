package KMKProgChallenge;

import java.util.Scanner;
public class ArmStrongRange {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int current = scan.nextInt();
		int endNum = scan.nextInt();
		
		for ( ; current <= endNum; current++ ) {
			int num = current;
			
			//Check current is how many digits
			int numDigits = 0;
			int i = 1;
			//While the number divided by the i (multiple of 10, initially at 1) still isn't equal to 0
			while (num / i != 0) {
				numDigits ++;
				i *= 10;								//For 992, numDigits = 3, i = 1000
			}											//For 1, numDigits = 1, i = 10										
														//For 0, numDigits = 0, i = 1
			
			//Digit Extractor and Sum
			//attemptSum takes each digit extracted and power it to the number of digits, store in itself
			int attemptSum = 0;
			
			//Now i becomes the extractor factor (Eg: Since 998/1000 = 0, we need i /= 10 so we can start extract digit '9'
			i /= 10;	
			
			//Note: i != 0 should always be at front so no exception is thrown: Division by 0
			//If (num / i) isn't 0, there's more digit to extract!
			while (i != 0 && num / i != 0 ) {
				//Stores the current digit extracted
				int currentDigit = num / i;
				//the num should be updated after the digit is extracted (998 -> 98)
				num %= i;
				//To extract next digit, i has to be divided by 10, until (1/10 = 0) which the loop won't run
				i /= 10;
				attemptSum += Math.pow(currentDigit, numDigits);
			}
			
			if (attemptSum == current)
				System.out.println(current);
			
		}
		

	}		//end of main()

}		//end of class
