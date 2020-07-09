package easy;

//https://www.hackerrank.com/challenges/service-lane/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Service_Lane {

	//A kind of DP solution where I store the entry points and exit points as a tabulation table. However for the test case where there is
	//100000 width, it will cause OutOfMemoryError as 100000^2 (Consider each cell stores 1 byte) --> ~10GB of memory! (Not even my RAM is enough) )
	//It works for small test cases though. Therefore keep in mind DP solution may be space consuming sometimes
	static int[] serviceLane(int n, int[] width, int[][] cases) {
		//The vertical row represents the entry point, and horizontal column represents exit point, where column 0 represents exiting at the 
		//same as entry point
		byte[][] dp = new byte[n][];
		
		//Entry point
		for (int entry = 0; entry < n; entry ++ ) {
			dp[entry] = new byte[n - entry];
			//Entry point and exit point is of the same, just take the width of this single service lane (Although exit and entry shall never met)
			dp[entry][0] = (byte) width[entry];
			//Exit point after entry point
			for (int exit = 1; exit < n - entry; exit ++ ) {
				dp[entry][exit] = (byte) Math.min(dp[entry][exit-1], width[entry + exit] );
			}
		}
		
		int[] result = new int[cases.length];
		for (int i = 0; i < result.length; i ++ ) {
			int entry = cases[i][0];
			int exit = cases[i][1] - cases[i][0];
			result[i] = dp[entry][exit];
		}
		return result;
    }
	
	
	static int[] serviceLaneIt(int n, int[] width, int[][]cases ) {
		int[] result = new int[cases.length];
		
		for (int i = 0; i < cases.length; i ++ ) {
			int entry = cases[i][0];
			int exit = cases[i][1];
			int min = Integer.MAX_VALUE;
			while (min != 1 && entry <= exit) {
				if (width[entry] < min)
					min = width[entry];
				entry++;
			}
			result[i] = min;
		}
		return result;
	}
	
}
