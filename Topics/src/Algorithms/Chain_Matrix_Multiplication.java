package Algorithms;

import java.util.Arrays;

//https://www.youtube.com/watch?v=prx1psByp7U&ab_channel=AbdulBari

/*
 * 	Given a series of valid matrices to multiply together. Different multiplication combinations can actually result
 * 	in different number of computations needed! Consider:
 * 		A x B x C
 * 
 * 	We can multiply (AxB) first then only C, or multiply (BxC) first then only A. These 2 potentially end up with
 * 	different computing power used!
 * 	By using one of Dynamic Programming Technique: Chain Matrix Multiplication, we can find out the minimum computation
 * 	required, and potentialy trace back the path used to generate combination of minimum computation steps.
 * 	This is a Dynamic Programming, and Divide & Conquer based algorithm
 * 
 * 	------------------------------------------------------------------------------------------
 * 
 * 	What is the cost to multiply 2 matrices?
 * 
 * 	* Remember that a multiplication can be done only when the first matrix's column is same as second matrix's row
 * 
 * 	Say we are multiplying A and B. Say A is of size (i,j) and B is of size (j,k). THen, the cost is:
 * 		
 * 		i x j x k.
 * 	
 * 	This is because for every row in matrix A, for every column (element) in matrix A in that row, we have
 * 	to iterate through the matrix B's column one by one to obtain the sum of product.
 * 
 * 	-------------------------------------------------------------------------------------------
 * 
 * 	The Dynamic Programming approach to find out the minimum computation from bottom up: It tries to multiply
 * 	matrices in sizes: size 1 (A,B,C)... , then size 2 (AB, BC, CD)..., then size 3... (ABC, BCD, CDE)...	
 * 	Since the computation needed for previous sizes is already calculated, it may be reused over and over again 
 * 	easily in DP tabulation table, avoiding repeated calculation
 * 
 * 	We will prepare a DP table which tracks the MINIMUM COMPUTATION needed for multiplication from i-th matrix
 * 	until j-th matrix.
 * 
 * 	Say Matrices we want to multiply is A, B, C, D. Then M(0,2) means minimum computation needed for A x B x C.
 * 	M(1,2) is then B x C, and lastly M(0,3) is A x B x C x D, which is what we want to obtain!
 * 
 * 	Now, how do we compute the value to put in each grid?
 * 	Say we are attempting to multiply B,C and D. We are trying to multiply matrices of size 3 here. Since we
 * 	already know the computation needed for all pairs in size 2 due to bottom up DP, we can divide the current 
 * 	problem into 2 partitions so that it is guaranteed the subproblem is matrix multiplication of smaller size, 
 * 	computed previously using DP and easily obtained!
 * 
 * 			(A) (B C D) 	OR
 * 			(A B) (C D)		OR
 * 			(A B C) (D)
 * 
 * 	Observe that we can partition left side from size 1 until size 3, which is size of matrix mutliplication - 1.
 * 	Therefore, we can iterate all possibilites of partitioning and find out the computation needed:
 * 
 * 		Computation power( leftStart, leftEnd ) + Computation power( leftEnd + 1, rightEnd )
 * 			+	(Row size Left) * (Column size Left) * (Column size Right)
 * 
 * 	It is the sum of MINIMUM computation power of left partition, and MINIMUM computation power of right partition, 
 * 	and lastly, the computation power to multiply the resulting left and right matrix.
 * 
 * 
 * 	>	Start with multiplication of matrices of size 1 only. Eg: (A), (B), (C) ...
 * 		The computation power is always 0. We can skip this size 1 in actual implementation
 * 
 * 	>	Then, start with size 2. Eg: (AB), (BC), (CD)...
 * 
 * 	>	Repeat until the size covers the entire matrix multiplication size (In case of ABCD, it is 4).
 * 		The answer will be stored at Row 0, column 3 (0 indexing) in DP array.
 * 		Because that grid represents MINIMUM computation power from first element until last element
 * 
 * 	----------------------------------------------------------------------------------------------
 * 
 * 	To be able to trace back and obtain the path done to compute using minimum computation power, we could
 * 	use another tabulation table which tracks the leftEnd (partition limit of left matrix) whcih results
 * 	in minimum computation.
 * 
 * 	Eg: If multiplication of ABCD, the path[0][3] is 3, that means the partitioning:
 * 			
 * 			size 3
 * 			  v
 * 			(ABC) (D)
 * 
 * 	results in minimal computation cost compared to other combinations like (A)(BCD) or (AB)(CD)
 */

//	Simple container representing a matrix size
class MatrixSizes {
	int i, j;
	public MatrixSizes(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class Chain_Matrix_Multiplication {

	public static int calculateMinimum(MatrixSizes[] matrices) {
		final int size = matrices.length;
		int[][] dp = new int[size][size];
		int[][] path = new int[size][size];
		
		//	Start by solving computation needed for mutliplication of matrices of size 2 first, then slowly
		//	go to 3, 4 and so on...
		for (int multiSize = 2; multiSize <= size; ++multiSize) {
			
			//	Iterate through all associative pairs of multiplication possible. (AB), then (BC), then (CD)...
			for (int start = 0; start < (size - multiSize + 1); ++start) {
				int end = start + multiSize - 1;	//	Ending point for the current range, inclusive
				
				//	Those combos can be multiplied in various ways. Find out the minimum for that by partitioning
				//	into two parts
				for (int partitionEnd = start; partitionEnd < end; ++partitionEnd) {
					
					int costLeftPartition = dp[start][partitionEnd];
					int costRightPartition = dp[partitionEnd+1][end];
					int computationNeeded = costLeftPartition + costRightPartition
							+ matrices[start].i * matrices[partitionEnd].j * matrices[end].j;
					
					//	If the grid is not yet filled before, or current computation is less than existing past
					//	computation, overwrite
					if (dp[start][end] == 0 || dp[start][end] > computationNeeded) {
						dp[start][end] = computationNeeded;
						path[start][end] = partitionEnd + 1;
					}
					
				}
			}
		}
		
		for (int[] arr: dp) {
			System.out.println( Arrays.toString(arr) );
		}
		System.out.println("Partition sizes for minimum computation:");
		for (int[] arr: path) {
			System.out.println( Arrays.toString(arr) );
		}
		return dp[0][size - 1];
	}
	
	
	
	public static void main(String[]args) {
		MatrixSizes[] sizes = {
			new MatrixSizes(5,4),
			new MatrixSizes(4,6),
			new MatrixSizes(6,2),
			new MatrixSizes(2,7)
		};
		
		System.out.println("Minimum: " + calculateMinimum( sizes ) );
	}
	
}
