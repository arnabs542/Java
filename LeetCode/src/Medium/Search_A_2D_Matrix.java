package Medium;

//https://leetcode.com/problems/search-a-2d-matrix/

/*
 *	This is a binary search probelem.
 *
 *	First since the matrix is sorted in both rows and columns, we can first use binary search to narrow down one
 *	row which may contain the target element to find.
 *	This is done by binary searching the first element of the rows. If the first element is greater, then this row couldn't
 *	be the one row containing target, put right = mid - 1. Otherwise, the row could contain the target, so put left = mid.
 *
 *	Next we binary search on the row. This is done as usual way. Finally, check the final value if it is target value.
 *
 *	-----------------------------------
 *
 *	Instead of 2 binary searches, we can do it in one, by binary searching on elements. Given the nth element,
 *	we can know the row and column of it in the matrix by using modulo and division.
 */

public class Search_A_2D_Matrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		int rows = matrix.length;
		if (rows == 0) return false;
		int cols = matrix[0].length;
		if (cols == 0) return false;
		
		int left = 0, right = rows - 1;
		while (left < right) {
			int mid = (left + (right - left) ) + 1;
			if (matrix[mid][0] > target)
				right = mid - 1;
			else left = mid;
		}
		
		int row = left;
		left = 0; right = cols - 1;
		
		while (left < right) {
			int mid = (left + (right - left) ) + 1;
			if (matrix[row][mid] > target)
				right = mid - 1;
			else left = mid;
		}
		
		return matrix[row][left] == target;
	}
	
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		int rows = matrix.length;
		if (rows == 0) return false;
		int cols = matrix[0].length;
		if (cols == 0) return false;
		
		int left = 0, right = rows * cols - 1;
		while (left < right) {
			int mid = (left + (right - left) / 2 ) + 1;
			if ( matrix[ mid / cols ][ mid % cols ] > target ) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		return matrix[ left / cols ][ left % cols ] == target;
	}
	
}
