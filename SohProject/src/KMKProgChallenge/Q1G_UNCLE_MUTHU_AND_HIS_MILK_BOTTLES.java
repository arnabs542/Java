package KMKProgChallenge;

import java.util.Scanner;
public class Q1G_UNCLE_MUTHU_AND_HIS_MILK_BOTTLES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//LOGIC: Fill in the most efficient bottle first of 10L, then 7L, then 5L, then 1L
		/*	Special Cases: If after filling the 10L the leftover is exactly 4L, 3L, 2L or 9L:
		 * 			4L: Pour back 1 10L milk, so 14L left, and use 2 7L bottle instead of 4 1L bottle
		 * 			3L: Pour back 1 10L milk, so 13L left, and use 1 7L, 1 5L and 1 1L instead of 3 1L bottle
		 * 			2L: Pour back 1 10L milk, so 12L left, and use 1 7L, 1 5L instead of 2 1L bottle
		 * 			9L: Pour back 1 10L milk, so 19L left, and use 2 7L, 1 5L instead of 4 1L bottle and 1 5L bottle
		 * 
		 *			Be careful from above as if the milk is only 4L or 3L from beginning, use of 1L bottle cannot be avoided
		 */
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int demandLitres = scan.nextInt();
			
			int tenLBottles, sevenLBottles, fiveLBottles, oneLBottles;
			
			//Fill in all the 10L bottles first
			tenLBottles = demandLitres / 10;
			demandLitres %= 10;
			
			//If the initial demandLitres ends with 4 and is not exactly 4L
			if (demandLitres == 4 && tenLBottles != 0) {
				//Pour out 1 10L milk, so 14L there, and use 2 7L bottle instead of 4 1L bottle
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 2 ) );
			}
			
			//If the initial demandLitres ends with 3 and is not exactly 3L
			else if (demandLitres == 3 && tenLBottles != 0) {
				//Pour out 1 10L milk, so 13L there, and use 1 7L, 1 5L and 1 1L instead of 3 1L bottle
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 3) );
			}
			
			//If the initial demandLitres ends with 2 and is not exactly 2L
			else if (demandLitres == 2 && tenLBottles != 0) {
				//Pour out 1 10L milk, so 12L there, and use 1 7L, 1 5L instead of 2 1L bottle
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 2));
			}
			
			//If the initial demandLitres ends with 9 and is not exactly 9L
			else if (demandLitres == 9 && tenLBottles != 0) {
				//Pour out 1 10L milk, so 19L there, and use 2 7L, 1 5L instead of 4 1L bottle and 1 5L bottle
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 3));
			}
			
			//Otherwise, fill in the largest bottle possible, then second large, then continue until finish
			else {
				sevenLBottles = demandLitres / 7;
				demandLitres %= 7;
				
				fiveLBottles = demandLitres / 5;
				demandLitres %= 5;
				
				oneLBottles = demandLitres / 1;
				demandLitres %= 1;
				
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles + sevenLBottles + fiveLBottles + oneLBottles) );
			}
			
		}		//end of case completing loop
		
		
	}		//end of main()
	
	
}		//end of class
