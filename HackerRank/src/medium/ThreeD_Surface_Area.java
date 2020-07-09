package medium;

//https://www.hackerrank.com/challenges/3d-surface-area/problem

/*
 * 	First we determine a single stack of block's surface area without considering it's adjacent blocks blocking first.
 * 	The formula for this is (4*n) + 2 where the 4n is the 4 sides and 2 is top and bottom
 * 	Then we start considering the adjacent stacks blocking.
 * 	We have to choose the minimum: which one is higher? the adjacent or ourselves. The shorter one's height is the area that are blocked
 */

public class ThreeD_Surface_Area {
	static int surfaceArea(int[][] A) {
		int sum = 0;
		for (int row = 0; row < A.length; row ++ ) {
			for (int col = 0; col < A[0].length; col ++ ) {
				int n = A[row][col];
				sum += (4 * n + 2) - Math.min( (row == 0)? 0: A[row-1][col], n)
								   - Math.min( (row == A.length - 1)? 0: A[row+1][col] , n)
								   - Math.min( (col == 0)? 0: A[row][col-1], n)
								   - Math.min( (col == A[0].length - 1)? 0: A[row][col+1], n);
			}
		}
		return sum;
    }
}
