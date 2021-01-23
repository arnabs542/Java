package Medium;

import java.util.PriorityQueue;

//https://leetcode.com/problems/sort-the-matrix-diagonally/
/*
 * 	This is simply a simulation problem.
 * 
 * 	Traverse through the diagonals, pushing the elements into one single place where sorting algorithm can
 * 	be executed.
 * 	Then, put the sorted elements back to original matrix, or in a new matrix
 * 
 * 	--------------------------------------------------------
 * 
 * 	If we don't want to do it diagonal one by one, we can use a HashMap of PriorityQueues
 * 
 * 	Remember this property:
 * 		The row index minus column index, i - j for each grid, gives a unique indexing for diagonals.
 * 
 * 	Like so:
 * 
 * 		0	-1	-2	-3
 * 		1	0	-1	-2
 * 		2	1	0	-1
 * 		3	2	1	0
 */

public class Sort_The_Matrix_Diagonally {
	
	public int[][] diagonalSort(int[][] mat) {
		final int row = mat.length, col = mat[0].length;
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for (int r = row - 1, c = 0; c < col ; c += r == 0? 1: 0, r -= r == 0? 0: 1) {
			int currR = r, currC = c;
			
			while (currR < row && currC < col)
				heap.add( mat[currR++][currC++] );
			
			currR = r; currC = c;
			
			while (currR < row && currC < col)
				mat[currR++][currC++] = heap.poll(); 
			
		}
		return mat;
    }
	
}
