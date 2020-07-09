package KMKTeam2;

import java.util.Scanner;
public class Q1G_UNCLE_MUTHU_AND_HIS_MILK_BOTTLES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//LOGIC: Fill in the most efficient bottle first of 10L, then 7L, then 5L, then 1L
		/*	Special Cases: If after filling the 10L the leftover is exactly 4L, 3L, 2L or 9L:
		 * 			4L: Pour out 1 10L milk, so 14L there, and use 2 7L bottle instead of 4 1L bottle
		 * 			3L: Pour out 1 10L milk, so 13L there, and use 1 7L, 1 5L and 1 1L instead of 3 1L bottle
		 * 			2L: Pour out 1 10L milk, so 12L there, and use 1 7L, 1 5L instead of 2 1L bottle
		 * 			9L: Pour out 1 10L milk, so 19L there, and use 2 7L, 1 5L instead of 4 1L bottle and 1 5L bottle
		 * 
		 *			Be careful from above as if the milk is only 4L or 3L from beginning, use of 1L bottle cannot be avoided
		 */
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int demandLitres = scan.nextInt();
			
			int tenLBottles, sevenLBottles, fiveLBottles, oneLBottles;
			
			tenLBottles = demandLitres / 10;
			demandLitres %= 10;
			
			if (demandLitres == 4 && tenLBottles != 0) {
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 2 ) );
				continue;
			}
			else if (demandLitres == 3 && tenLBottles != 0) {
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 3) );
				continue;
			}
			else if (demandLitres == 2 && tenLBottles != 0) {
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 2));
				continue;
			}
			
			else if (demandLitres == 9 && tenLBottles != 0) {
				System.out.println("Case #" + (i + 1) + ": " + (tenLBottles - 1 + 3));
				continue;
			}
			
			
			sevenLBottles = demandLitres / 7;
			demandLitres %= 7;
			
			fiveLBottles = demandLitres / 5;
			demandLitres %= 5;
			
			oneLBottles = demandLitres / 1;
			demandLitres %= 1;
			
			System.out.println("Case #" + (i + 1) + ": " + (tenLBottles + sevenLBottles + fiveLBottles + oneLBottles) );
		
		}
	}		//end of main()
	
	
}		//end of class
