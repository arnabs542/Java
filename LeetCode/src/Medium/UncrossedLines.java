package Medium;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/537/week-4-may-22nd-may-28th/3340/

public class UncrossedLines {

	public static int maxUncrossedLines(int[] A, int[] B) {
		int[][] dynamic = new int[A.length + 1][B.length + 1];
		int max = 0;
		for (int i = 0; i < A.length; i ++ ) {
			for (int j = 0; j < B.length; j ++ ) {
				if (A[i] == B[j]) {
					dynamic[i+1][j+1] = 1 + dynamic[i][j];
					max = (dynamic[i+1][j+1] > max)? dynamic[i+1][j+1]: max;
				}
				else {
					dynamic[i+1][j+1] = Math.max(dynamic[i][j+1], dynamic[i+1][j]);
					max = (dynamic[i+1][j+1] > max)? dynamic[i+1][j+1]: max;
				}
			}
		}
		return max;
		
	}
	
	public static void main(String[]args ) {
		int[] a = {2,5,1,2,5};
		int[] b= {10,5,2,1,5,2};
		System.out.println( maxUncrossedLines(a,b) );
	}
}
