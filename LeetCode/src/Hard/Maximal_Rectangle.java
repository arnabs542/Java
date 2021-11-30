package Hard;

import java.util.Stack;

//https://leetcode.com/problems/maximal-rectangle/
/*
 * 	This is a dynamic programming, monotonic stack problem which is dependent on one other, easier (But still HARD)
 * 	question - 84. Largest Rectangle in Histogram
 * 
 * 	Inheriting from the idea of DP, for each of the rows in the matrix, we could actually form a histogram from it!
 * 	Think of the ith row as the x=0 for the historgram, then we can show for each column j, what is the height
 * 	that it can go! See:
 * 		
 * 	[1,0,0,0,1]
 * 	[1,1,1,1,1]
 * 	[1,0,0,1,0]
 * 	[1,0,0,1,0]		<- i, our x axis
 * 
 * 	Then our histogram would be [4,0,3,0]. '4' at column 0 because from ith row, ther number of '1' extends until height 4.
 * 	
 * 
 * 	If we consider like this for each row, surely the maximum rectangle would be detected at one of the rows. Problem
 * 	is how do we find out maximum area from N histograms like this? Looking at the solution from problem: Largest
 * 	Rectangle in Histogram, we can use that N times to obtain the solution
 * 
 * 
 * 	Space complexity is O(C) - Number of columns. Time complexity is O(RC) - Row * Column
 */

public class Maximal_Rectangle {
	
	
	// Less Brute force solution using matrix sum. Time limit exceeded with time ~O(M^2N^2) and space O(MN)
	public int maximalRectangle(char[][] matrix) {
		final int r = matrix.length, c = matrix[0].length;
		int[][] sumMatrix = new int[r][c];
		int res = 0;
		
		// Convert to sumMatrix, where [i][j] is the sum of area from (0,0) to (i,j)
		for (int i = 0; i < r; ++i) {
			int rowSum = 0;
			for (int j = 0; j < c; ++j) {
				rowSum += matrix[i][j] - '0';
				sumMatrix[i][j] = rowSum + (i > 0? sumMatrix[i-1][j]: 0);
			}
		}
		
		
		// Iterate through all possible sizes
		for (int h = 1; h <= r; ++h) {
			for (int w = 1; w <= c; ++w) {
				for (int i = h-1; i < r; ++i) {
					for (int j = w-1; j < c; ++j) {
						if ( getSum(sumMatrix, i-h+1, j-w+1, i, j) == h * w )
							res = Math.max(res, h * w);
					}
				}
			}
		}
		
		
		return res;
    }
	
	private int getSum(int[][] sumMatrix, int r, int c) {
		if (r < 0 || c < 0) return 0;
		return sumMatrix[r][c];
	}
	
	
	private int getSum(int[][] sumMatrix, int r1, int c1, int r2, int c2) {
		if (r1 < 0 || c1 < 0) return 0;
		return getSum(sumMatrix, r2, c2) - getSum(sumMatrix, r1 - 1, c2) 
				- getSum(sumMatrix, r2, c1 -1) + getSum(sumMatrix, r1-1, c1-1);
	}
	
	
	
	
	
	
	// Optimal Solution using Monotonic stack from question - Largest Area in Histogram
	public int maximalRectangle2(char[][] matrix) {
		if (matrix.length == 0) return 0;
		
		final int r = matrix.length, c = matrix[0].length;
		int[] arr = new int[c];
		int res = 0;
		
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j)
				arr[j] = (matrix[i][j] == '0')? 0: arr[j] + 1;
			res = Math.max(res, maximumAreaHistogram(arr));
		}
		return res;
		
	}
	
	
	// The stack will be monotonic stack that contains indices of lower bars -> higher bars. 
	private int maximumAreaHistogram(int[] heights) {
		final int len = heights.length;
		Stack<Integer> stk = new Stack<>();
		int res = 0;
		
		for (int i = 0; i <= len; ++i) {
			int height = i == len? 0: heights[i];
			
			while ( !stk.isEmpty() && heights[ stk.peek() ] > height ) {
				int popIndex = stk.pop();
				int popHeight = heights[ popIndex ];
				int extendUntil = stk.isEmpty()? i: (i - 1 - stk.peek() );
				
				res = Math.max(res, popHeight * extendUntil );
			}
			
			stk.push(i);
		}
		return res;
	}
	
}
