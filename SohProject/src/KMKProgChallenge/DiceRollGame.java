package KMKProgChallenge;

import java.util.Scanner;
public class DiceRollGame {

	//The dice roll game goes like this:
	/*	Initially 2 die are rolled. If two of the die didn't get the same number, the process is repeated until the 2 dies have the
	 * 	same number, which stops the game. Then, the program will output the sum of the number of the die from initially to finish
	 * 	Furthermore, during the die roll process if one of the die gets 6, then that die is 'fixed', meaning only 1 die will be 
	 * 	thrown from now on, the game continues until the last die also gets 6. Output the sum as well
	 */
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int noCase = scan.nextInt();
		
		for (int n = 1; n <= noCase; n ++ ) {
			int sum;
			int dice1 = scan.nextInt();
			int dice2 = scan.nextInt();
			sum = dice1 + dice2;
				
			//While the 2 dies aren't the same number
			while (dice1 != dice2) {
				//Flags indicating whether any of the die are 6
				boolean is1Six = (dice1 == 6);
				boolean is2Six = (dice2 == 6);
					
				//If either one of the die gets six
				if (is1Six || is2Six) {
					
					//If its die1 thats 6
					if (is1Six) {
						dice2 = scan.nextInt();
						sum += dice2 + 6;
					}
					
					//If its die2 thats 6
					else {
						dice1 = scan.nextInt();
						sum += dice1 + 6;
					}
				}		//end of if case - either one of die is 6
				
				//Else if none of the die is six,
				else {
					dice1 = scan.nextInt();
					dice2 = scan.nextInt();
					sum += dice1 + dice2;
				}
					
			}		//end of die repeat throwing loop
			
			System.out.println("Case #" + n + ": " + sum);
		}
		
	}		//end of main()

}		//end of class
