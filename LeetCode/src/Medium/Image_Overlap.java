package Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/image-overlap/

/*
 * 	This is a BRUTE FORCE solution. No matter how we think, it shall be impossible to optimize above O(n^4)
 * 
 * 	Given two images, we can perform the transformation of either left, right, up or down. Then, we need to see how many
 * 	overlap of 1's in two images for each transformation
 * 
 * 	The solution would be to iterate through all the transformations.
 * 	Notice that we only need to transform one image, where the other just keep fixed in its place.
 * 
 * 	Let's see the possible transformation and understand better with a 1D array, of size 3
 * 	Edge cases:
 * 
 * 	A	| 1 | 0 | 1 |
 * 	B			| 1 | 0 | 1 |
 * 
 * 	AND
 * 
 * 	A			| 1 | 0 | 1 |
 * 	B	| 1 | 0 | 1 |
 * 
 * 	You can see, the first edge case has transformation X of -2, where the second edge case has transformation X of 2
 * 	>	We map the grid number in A to grid number in B by just adding with the transformation.
 * 		See:	In case 1, the index 2 in A is equivalent to index 0 in B, which is ( 2 + (-2) )
 * 				In case 2, the index 0 in A is equivalent to index 2 in B, which is ( 2 + (2) )
 * 	>	How about those indexes that is out of bounds? In that case, we shall not initialize the loop variable to include the out
 * 		of bounds index. 
 * 		See:	Since in case 1, index 0, 1 in A will map to -2, -1, which is invalid index. In that case,
 * 				the loop shall start from index 2 , and stop right at index 3
 * 				Since in case 2, index 1, 2 in A will map to 3, 4, which is out of bounds index. In that case,
 * 				the loop shall start from 0, and stop right at SIZE - TRANSFORMATION
 * 
 * 	This let us concludes what index we shall loop from, and stop at where
 * 
 * 		for (int i = Math.max(0, 0 - shift);					<-- If the shifting causes it to be negative, start from 0
 * 				i < Math.min( size, size - shift);				<-- If the shifting causes overbound, stop right at size
 * 				i ++ )
 * 
 * 		
 * 	Therefore time complexity:
 * 		>	We iterate through all the shiftings of X, and Y. O(N^2)
 * 		>	For each shifting, we count the occurrences of overlap, meaning we iterate 
 * 			through each grid of both the 2D arrays. O(N^2)
 * 
 * 			In the end, it is O(N^4)
 * 
 * 	================================================================================================================
 * 
 * 	Instead of focusing on shifting, we can focus on the actual grid that contains 1 themselves.
 * 	First we obtain the information on the grid that contains 1 in both 2D arrays, essentially discarding those with 0
 * 
 * 	Then, we generate all the possible transformation matrices. (How much transform X and Y), generated in the same
 * 	manner as above 
 * 	Then for each possible transformation, iterate through all the indices pair of A that contains 1, apply the transformation,
 * 	and see if that transformed indices is contained in B
 * 
 * 	This also has time complexity of O(N^4), with added space complexity of O(N^2). Let's see
 * 	
 * 	>	We generate all the possible trasformation matrices. O(N^2)
 * 	>	We iterate through all the indices pair of A. In worst case A is all 1. Therefore it is O(N^2)
 * 
 * 	Therefore O(N^4)
 * 
 */

public class Image_Overlap {
	
//	public int largestOverlap(int[][] A, int[][] B) {
//		int size = A.length;
//		int res = Integer.MIN_VALUE;
//		
//		//	Iterate over all possible shifts, starting with A at the leftmost upper corner, until A at downmost right corner
//		//	Say if it is 3x3 square matrix, all possible shift on X shall be -2, -1, 0, 1, and 2.
//		for (int offX = -size + 1; offX < size; offX ++ ) {
//			for (int offY = -size + 1; offY < size; offY ++ ) {
//				
//				int count = 0;
//				
//				//	Let's say the shiftX is -1, then on grid A, index 1 is actually index 0 on grid B
//				for (int x = Math.max(0 , 0 - offX); x < Math.min(size, size - offX); x ++ ) {
//					for (int y = Math.max(0, 0 - offY); y < Math.min(size, size - offY); y ++ ) {
//						if (A[x][y] + B[x + offX][y + offY] == 2 ) count ++;
//					}
//				}
//				
//				res = Math.max(res, count);
//			}
//		}
//		
//		return res;
//		
//	}
	
	
	
	public int largestOverlap(int[][] A, int[][] B) {
		Map<Integer, Set<Integer> > mapA = new HashMap<>();
		Map<Integer, Set<Integer> > mapB = new HashMap<>();
		int size = A.length;
		int res = 0;
		
		for (int i = 0; i < size; i ++ ) {
			for (int j = 0; i < size; j ++ ) {
				if ( A[i][j] == 1 ) {
					mapA.putIfAbsent(i, new HashSet<>() );
					mapA.get(i).add(j);
				}
				if ( B[i][j] == 1 ) {
					mapB.putIfAbsent(i, new HashSet<>() );
					mapB.get(i).add(j);
				}
			}
		}
		
		
		//	Form all possible transformation matrix
		for (int offX = -size + 1; offX < size; offX ++ ) {
			for (int offY = -size + 1; offY < size; offY ++ ) {
				int count = 0;
				
				for (int x1: mapA.keySet() ) {
				for (int y1: mapA.get(x1) ) {
					int x2 = x1 + offX, y2 = y1 + offY;
					
					if (mapB.containsKey(x2) && mapB.get(x2).contains(y2) ) count++;
				}
				}
				
				res = Math.max(count, res);
			}
		}
		
		return res;
	}

}
