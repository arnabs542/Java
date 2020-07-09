package KMKTeam2;

import java.util.Scanner;
public class Q6A_ZER0_ROWS_AND_COLS {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int dimension;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			dimension = scan.nextInt();
			
			if (dimension == 0)
				break;
			
			int[][] binaryBoard = new int[dimension][dimension];
			
			for (int row = 0; row < dimension; row ++ )
				for (int column = 0; column < dimension; column ++ )
					binaryBoard[row][column] = scan.nextInt();
			
			System.out.println("Case #" + caseNum + ": ");
			
			//Row checker
			for (int row = 0; row < dimension; row ++ ) {
				
				boolean isRowZero = true;
				
				for (int column = 0; column < dimension; column ++ )
					if ( binaryBoard[row][column] != 0)
						isRowZero = false;
				
				if (isRowZero)
					System.out.println("row " + (row + 1));
			}		//end of row checker
			
			//Column checker
			for (int column = 0; column < dimension; column ++ ) {
				
				boolean isColumnZero = true;
				
				for (int row = 0; row < dimension; row ++ )
					if ( binaryBoard[row][column] != 0)
						isColumnZero = false;
				
				if (isColumnZero)
					System.out.println("column " + (column + 1));
			}		//end of column checker
			
			
		}		//end of case completing loop
		
		
		

	}		//end of main()

}		//end of class
