package Medium;

//https://leetcode.com/problems/count-square-submatrices-with-all-ones/

/*
 *	A dynamic programming question. We could use a separate counting array of one larger than the original size of the array to keep track
 *	of how many squares can the element form
 *
 *	Instead of having to expand in the direction of lower right, a better approach is to expand in direction of upper left
 *
 *	Every time we meet a 1 when iterating through the matrix array, it at least would make ONE square matrix (by itself), then, we would
 *	check the element above, to the left, and diagonally and take the minimum of the square matrices those three can form, confirming how
 *	many actually this square matrix by itself is able to form
 */

public class CountSquareMatricesWithAllOnes {
	
	//	BAD SOLUTION :(
	
//	public static int countSquares(int[][] matrix) {
//		
//		int[][] addictive = new int[matrix.length][matrix[0].length];
//		int counter = 0;
//		
//		for (int row = 0; row < matrix.length; row ++ ) {
//			for (int col = 0; col < matrix[0].length; col ++ ) {
//				
//				//If i encounter a block with non-zero element, and the addictive matrices is also 0 (never touched before)
//				if (matrix[row][col] == 1 ) {
//					int[] expandable = expandSize(matrix, addictive, row, col);
//					int size = expandable[0];
//					counter += (size * (size + 1) * (2*size + 1) ) / 6 - expandable[1];
//					fill(addictive, size, row, col);
//				}
//			}
//		}
//		//end of row and column iteration loop
//		return counter;
//    }
//	//end of countSquares method
//	
//	
//	//Helper method to determine the best expendable size of square matrices using that specified row and column
//	private static int[] expandSize(int[][] matrix, int[][] addictive, int row, int col) {
//		int size = 1;
//		int deduct = addictive[row][col];
//		
//		deadend:
//		while (true) {
//			int localDeduct = 0;
//			//Checking the vertical side if all is 1
//			int checkCol = col + size;
//			for (int checkRow = row; checkRow <= row + size; checkRow ++ ) {
//				if (checkRow >= matrix.length || checkCol >= matrix[0].length || matrix[checkRow][checkCol] != 1)
//					break deadend;
//				localDeduct += addictive[checkRow][checkCol];
//			}
//			
//			//Checking the horizontal side if all is 1
//			int checkRow = row + size;
//			for (checkCol = col; checkCol <= col + size; checkCol ++ ) {
//				if (checkRow >= matrix.length || checkCol >= matrix[0].length || matrix[checkRow][checkCol] != 1)
//					break deadend;
//				localDeduct += addictive[checkRow][checkCol];
//			}
//			deduct += localDeduct - addictive[row+size][col+size];
//			size++;
//		}
//		
//		return new int[] {size, deduct};
//	}
//	//end of expandSize helper method
//	
//	//Helper method fill() to help filling the addictive matrices
//	private static void fill(int[][] addictive, int size, int row, int col) {
//		for (int r = row; r < row + size; r ++ ) {
//			for (int c = col; c < col + size; c ++ ) {
//				addictive[r][c] = size - Math.max(r - row, c - col);
//			}
//		}
//	}
//	//End of helper method fill()
//	
//	
//	private static void printMatrices(int[][] mat) {
//		for (int i = 0; i < mat.length; i ++ ) {
//			for (int j = 0; j < mat[0].length; j++ ) {
//				System.out.print(mat[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
	public static int countSquares(int[][] matrix) {
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
		
		int solution = 0;
		for (int row = 0; row < matrix.length; row ++ ) {
			for (int col = 0; col < matrix[0].length; col++ ) {
				if (matrix[row][col] == 1) {
					int formableSqr = 1 + Math.min( Math.min(dp[row][col+1], dp[row+1][col]), dp[row][col]);
					solution += formableSqr;
					dp[row+1][col+1] = formableSqr;
				}
			}
		}
		return solution;
	}
	
	//Debugging tool my friend
	public static void main(String[]args ) {
		int[][] matrix = { {0,0,1,1,1},
						   {0,1,1,1,1},
						   {0,1,1,1,1},
						   {0,1,1,1,1}, 
						   {0,1,1,1,1} };
		int[][] addictive = { {0,0,3,2,1},
				   			  {0,0,2,2,1},
				   			  {0,0,1,1,1},
				   			  {0,0,0,0,0}, 
				   			  {0,0,0,0,0} };
		int[][] matrix2 = { {0,1,1,1},
							{1,1,1,1},
							{0,1,1,1} };
		int[][] matrix3 = { {0,0,0},
							{0,1,0},
							{0,1,0},
							{1,1,1},
							{1,1,0} };
		int[][] matrix4 = { {1,0,1,0,1},
							{1,0,0,1,1},
							{0,1,0,1,1},
							{1,0,0,1,1} };
		System.out.println( countSquares(matrix4) );
	}
}
