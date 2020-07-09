package Easy;

//https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

public class Count_Negative_Numbers_in_a_Sorted_Matrix {
	
	public static int countNegatives(int[][] grid) {
		
		int rowLength = grid.length;
		int numNegative = 0;
		int col = 0;
		int lastIndex = rowLength;
		//Since mentioned column <= 100, iterating by column is better than iterating
        //unknown rows
		for ( ; col < grid[0].length; col ++ ) {
			int left = 0;
            //Mention the pattern that the first negative numbers is higher as the column
            //goes from left to right
			int right = lastIndex;
			while (left < right) {
				int mid = left + (right - left)/2;
				if (grid[mid][col] >= 0) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
			lastIndex = left;
			//Be careful of the edge cases: if the column is all positive
			if (left == rowLength) {
				numNegative += (grid[rowLength-1][col] >= 0)? 0: 1;
			}
			else 
				numNegative += (rowLength - left);
		}
		return numNegative;
		
	}
	
	public static void main(String[]args) {
		int[][]grid = { {3,1,3}, {2,1,3}, {1,-1,-2}, {0,-1,-3} };
		System.out.println(countNegatives(grid) );
	}
}
