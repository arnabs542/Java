//package KMKTeam2;

import java.util.Scanner;
public class Q3E_DIAMOND_IN_THE_SKY {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case; A diamond is formed
		for (int i = 0; i < numCases; i ++ ) {
			
			int maxLine = scan.nextInt();
			
			System.out.println("Case #" + (i + 1) + ": ");
			
			int maxStars = 1;
			//Find how many stars in the middle of the diamond
			for (int j = 1; j < maxLine; j ++) {
				maxStars += 2;
			}
			
			int middlePos = (maxStars / 2) + 1;
			
			//Indicate whether the diamond had reached the maximum point and should begin retracting
			boolean retracting = false;
			
			//Each loop completes a line in the diamond
			for (int line = 1; line > 0;  ) {
				
				int numStars = 1;
				//Find how many stars supposed to be in this line
				for (int star = 1; star < line; star ++ )
					numStars += 2;
				//Find how many stars beside the middle position
				int sideStars = numStars / 2;
				
				//Starting position and ending position for the stars of current line 
				int startPos = middlePos - sideStars;
				int endPos = middlePos + sideStars;
				
				//Each loop determine whether to add space or star into the line
				for (int pos = 1; pos <= maxStars; pos ++) {
					if (pos < startPos || pos > endPos)
						System.out.print(" ");
					else
						System.out.print("*");
				}
				
				System.out.println("");
				
				//if not retracting, next line should be 2 star longer. Else, it should be 2 star shorter
				if (!retracting)
					line ++;
				else
					line --;
				//If diamond had reached maxLine at this point, begin retracting
				if (line == maxLine)
					retracting = true;
				
			}
			
		}
		
	}		//end of main()

}		//end of class
