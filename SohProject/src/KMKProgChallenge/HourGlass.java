package KMKProgChallenge;

import java.util.Scanner;
public class HourGlass {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int caseNo = 0; caseNo < numCases; caseNo ++ ) {
			
			//obtain the size, fill level and the character for drawing the hour glass
			int size = scan.nextInt();
			int fillLevel = scan.nextInt();
			char symbol = scan.next().charAt(0);
			
			int numRows, minimum;
			
			numRows = size + 2;
			
			int startFillRow = numRows - fillLevel;
			
			System.out.println("Case #" + (caseNo + 1) + ": ");
			
			for (int row = 1; row <= numRows; row ++) {
				
				//If it is the first row or the last row
				if (row == 1 || row == numRows) {
					for (int pos = 1; pos <= size; pos ++) {
						System.out.print(symbol + " ");
					}
					System.out.println();
				}
				//else if it is not the first row or the last row
				else {
					
					//The number of spaces needed to be allocated before start printing the current row
					int numSpaces = (row - 2);
					int firstStar = numSpaces + 1;
					int secondStar = size - numSpaces;
					
					//Going through the position of the current row one by one
					for (int pos = 1; pos <= size; pos ++) {
						
						//If the current row is equal or greater than the row to start filling
						if (row >= startFillRow) {
							//Since the position of firstStar and secondStar is inverted on the bottom half, there will be
							//2 cases we have to consider
							if ( ( pos >= firstStar && pos <= secondStar) || (pos >= secondStar && pos <= firstStar) )
								System.out.print(symbol + " ");
							else
								System.out.print("  ");
						}
						//If the position is not at the firstStar or the secondStar
						else if (pos != firstStar && pos != secondStar) 
							System.out.print("  ");
						else {
							System.out.print( symbol + " ");
						}
					}
					System.out.println();
				}
				
			}		//end of row completing loop
			
		}		//end of case completing loop
		

	}		//end of main()

}		//end of class
