package KMKTeam2;

import java.util.Scanner;
public class Q4H_BULLS_AND_COWS {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numPlayer = scan.nextInt();
		
		int[] secretNum = { scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt() };
		
		//HINT: A DIGIT IS NEVER REPEATED TWICE, EITHER IN SECRET CODE OR PLAYER'S GUESS
		
		//Each loop processes a player's guess
		for (int player = 1; player < numPlayer; player ++ ) {
			
			int[] guessNum = { scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt() };
			int numBulls = 0, numCows = 0;
			
			//Each loop determines whether a digit in the guess code is a bull or a cow. 
			//i is the position of secret code, j is position of guess code
			for (int i = 0; i < 4; i ++) {
				
				//Check for number of cows in the guess code
				for (int j = 0; j < 4; j ++) {
					if ( secretNum[i] == guessNum[j] )
						numCows ++;
				}
				
				//If the digit is actually a bull, then subtract 1 from counted cows, and add 1 to bull
				if ( secretNum[i] == guessNum[i] ) {
					numCows --;
					numBulls ++;
				}
			}		//end of digit cow or bull determining loop
				
			System.out.println("Player #" + (player) + ": " + numBulls + " bulls " + numCows + " cows");
			
			
		}		//end of player guess processing loop
		
		//I also don't know why this is needed
		//int terminateZero = scan.nextInt();
		
	}		//end of main()

}		//end of class
