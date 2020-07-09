//package KMKTeam2;

import java.util.Scanner;
public class Q6H_DIAGONAL_SUM {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int dimension;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			dimension = scan.nextInt();
			
			//If the dimension inputed is 0, terminate the program immediately
			if (dimension == 0)
				System.exit(0);
			
			//2-dimensional array representing the square numbers. First index represents row and the second represents column
			int[][] squareNum = new int[dimension][dimension];
			
			//Each loop retrieves the number along one row
			for (int row = 0; row < dimension; row ++ ) {
				//Each loop retrieves a number in a column of current row
				for (int column = 0; column < dimension; column ++ ) {
					squareNum[row][column] = scan.nextInt();
				}
				scan.nextLine();
			}
			
			int diagonalSum = 0;
			
			//Adding the elements in the square along the \ diagonal
			for (int i = 0; i < dimension; i ++ )
				diagonalSum += squareNum[i][i];
			
			//Adding the elements in the square along the / diagonal
			for (int row = 0; row < dimension; row ++ ) {
				diagonalSum += squareNum[row][dimension - row - 1];
			}
			
			System.out.println("Case #" + caseNum + ": " + diagonalSum);
			
		}		//end of case completing loop
		
		
		

	}		//end of main()

}		//end of class
