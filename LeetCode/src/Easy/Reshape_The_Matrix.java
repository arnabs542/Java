package Easy;

//https://leetcode.com/problems/reshape-the-matrix/
/*
 * This is an array problem.
 * 
 * First of all, ensure that the reshaped matrix is able to fit all the original array's elements inside, by ensuring the
 * matrix size is the same.
 * 
 * For each of the indices i,j in original matrix, we can use the standard 1D array indexing (imagine the array is flattened)
 * by the formula:
 * 		idx = i * noCols + j
 * 
 * From it, we can then map it into the result array indexing by using formula:
 * 		tr = idx / c			where tr is target row index
 * 		tc = idx % c 			where tc is target column index
 */

public class Reshape_The_Matrix {
	public int[][] matrixReshape(int[][] mat, int r, int c) {
        final int oriR = mat.length;
        final int oriC = mat[0].length;
		
		//	If the reshaped matrix size is not equal to original matrix, return the original matrix
		if (oriR * oriC != r * c) return mat;
		
		int[][] res = new int[r][c];
		for (int i = 0; i < oriR; ++i) {
			for (int j = 0; j < oriC; ++j) {
				int idx = i * oriC + j;
				int tr = idx / c, tc = idx % c;
				res[tr][tc] = mat[i][j];
			}
		}
		return res;
    }
}
