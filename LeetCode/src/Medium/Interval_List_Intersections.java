package Medium;

import java.util.LinkedList;

//https://leetcode.com/problems/interval-list-intersections/
/*
 * 	Although not very obvious, it is a two pointers question
 * 
 * 	At any iteration i, we would need to compare two intervals, one from A and one from B, done by having two pointers
 * 	pointing at one interval in A and B respectively.
 * 	In each iteration, we will update either A and B pointer, to be compared in next iteration.
 * 
 * 	
 * 	The basic, intuitive algorithm here is:
 * 		1.	Loop the indices of A or B until there is a valid intersection
 * 		2.	Determine the intersection and add it to results
 * 		3.	Increment either one of the A or B index
 * 		4. 	Repeat step 1-3 until the indices ran out of bound
 * 
 * 	However step 1 can be simplified without checking:
 * 		Since every intersection requires us to take the maximum of the lows, and minimum of the highs, we could simply just do it,
 * 		but a valid intersection between A and B is that, the intersection's low must be less than the intersection's high, if this
 * 		rule is broken, that means A and B has no intersection at all!
 * 
 * 	To determine step 3, check:
 * 		-	If A's high is greater than B's high, then proceed B
 * 		-	If B's high is greater than A's high, then proceed A
 */

public class Interval_List_Intersections {

	//	public int[][] intervalIntersection(int[][] A, int[][] B) {
	//		if (A.length == 0 || B.length == 0) return new int[0][0];
	//		
	//		int[] first = A[0];
	//		int[] second = B[0];
	//		int firstIndex = 0;
	//		int secondIndex = 0;
	//		
	//		LinkedList<int[]> result = new LinkedList<>();
	//		
	//		general:
	//		while (true) {
	//			while (second[1] < first[0] ) {
	//				secondIndex ++;
	//				if (secondIndex >= B.length) break general;
	//				second = B[ secondIndex ];
	//			}
	//			while (second[0] > first[1] ) {
	//				firstIndex ++;
	//				if (firstIndex >= A.length) break general;
	//				first = A[ firstIndex ];
	//			}
	//			while (second[1] < first[0] || second[0] > first[1]) {
	//				if (second[1] < first[0]) {
	//					secondIndex ++;
	//					if (secondIndex >= B.length) break general;
	//					second = B[ secondIndex ];
	//				}
	//				else {
	//					firstIndex ++;
	//					if (firstIndex >= A.length) break general;
	//					first = A[ firstIndex ];
	//				}
	//			}
	//			
	//			result.add( new int[] {Math.max(first[0], second[0]) , Math.min(first[1], second[1]) } );
	//			
	//			if (first[1] >= second[1] ) {
	//				secondIndex++;
	//				if (secondIndex >= B.length) break general;
	//				second = B[secondIndex];
	//			}
	//			else {
	//				firstIndex ++;
	//				if (firstIndex >= A.length) break general;
	//				first = A[firstIndex];
	//			}
	//		}
	//		
	//		int[][] r = new int[result.size()][2];
	//		for (int i = 0; i < result.size(); i ++ ) {
	//			int[] get = result.get(i);
	//			r[i][0] = get[0];
	//			r[i][1] = get[1];
	//		}
	//		return r;
	//    }
	//My initial code which is lengthy and probably messy
	
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		int aIndex = 0;
		int bIndex = 0;
		LinkedList<int[]> result = new LinkedList<>();
		
		while (aIndex < A.length && bIndex < B.length) {
			int high = Math.min(A[aIndex][1], B[bIndex][1] );
			int low = Math.max(A[aIndex][0], B[bIndex][0] );
			
			if (high >= low)
				result.add( new int[] {low, high} );
			
			if (A[aIndex][1] > B[bIndex][1])
				bIndex ++;
			else
				aIndex ++;
		}
		
		int[][] r = new int[result.size()][2];
		for (int i = 0; i < result.size(); i ++ ) {
			int[] get = result.get(i);
			r[i][0] = get[0];
			r[i][1] = get[1];
		}
		return r;
	}
	
}
