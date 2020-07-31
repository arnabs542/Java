package KMKTeam2;

import java.util.Scanner;
public class Q6G_MAGIC_SQUARE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++) {
			
			//Stores a magic square with the first index being row and second index being column
			int [][] magicSquare = new int[3][3];
			//Indicates whether the 3x3 number is a magic square or not
			boolean isMagic = true;
			
			//Each loop retrieves the numbers in a row and determines whether this row satisfies the magic square
			for (int row = 0; row < 3; row ++ ) {
				//The total for this row's number
				int thisRowTotal = 0;
				
				for (int column = 0; column < 3; column ++ ) {
					magicSquare[row][column] = scan.nextInt();
					thisRowTotal += magicSquare[row][column];
				}
				scan.nextLine();
				
				//If this row's total is not 15, it's not magic square
				if (thisRowTotal != 15)
					isMagic = false;
			}
			
			//Each loop finds the total of the number along the column and determines whether this column satisfies the magic square
			for (int column = 0; column < 3; column ++ ) {
				int thisColumnTotal = 0;
				
				for (int row = 0; row < 3; row ++ ) {
					thisColumnTotal += magicSquare[row][column];
				}
				
				//If this column's total is not 15, it's not magic square
				if (thisColumnTotal != 15)
					isMagic = false;
			}
			
			//The sum of the numbers along 2 diagonals, / and \ direction
			int diagonal1Total = magicSquare[0][0] + magicSquare[1][1] + magicSquare[2][2];
			int diagonal2Total = magicSquare[0][2] + magicSquare[1][1] + magicSquare[2][0];
			
			//If one of the sum of numbers along the diagonals are not 15, it's not a magic square
			if (diagonal1Total != 15 || diagonal2Total != 15)
				isMagic = false;
			
			if (isMagic)
				System.out.println("Case #" + caseNum + ": Yes");
			else
				System.out.println("Case #" + caseNum + ": No");
			
			
			
			
		}		//end of case completing loop
		
		

	}		//end of main()

}		//end of class
