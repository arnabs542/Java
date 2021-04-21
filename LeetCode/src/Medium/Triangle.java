package Medium;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/triangle/
/*
 * 	This is a Dynamic Programming Problem.
 * 
 * 	We want to find out the minimum path sum. So what we could do is to perform depth first search from the
 * 	tip of triangle as the root.
 * 	The problem with this is, if we were to visualize the tree formed, there were overlapping subproblems:
 * 	a node may be visited twice from the parent. Therefore, we shall eliminate this computational cost via
 * 	utilizing the space: DP
 * 
 * 	DP uses bottom up approach. So initialize a 2D DP array which each grid actually maps to a node in triangle itself.
 * 	The base case is that at the bottom of triangle, the minimum is always the node value itself.
 * 
 * 	For each non-bottom node, it can explore bottom left and bottom right, therefore the formulae is
 * 			node val + MIN( dp[i+1][j], dp[i+1][j+1] )
 * 	At the end, return the dp array's tip of the triangle, consisting of minimum path from top to bottom of triangle
 * 
 * 	------------------------
 * 
 * 	Notice how the DP only uses the next row in dp array, this opens up possibility to save space by using only 1D array.
 *	
 */

public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		final int len = triangle.size();
        int[][] dp = new int[len][len];
        
        //	Initialize last row.
        for (int i = 0; i < len; ++i)
        	dp[len-1][i] = triangle.get(len-1).get(i);
        
        for (int i = len - 2; i >= 0; --i) {
        	for (int j = 0; j <= i; ++j)
        		dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
        }
        
        return dp[0][0];
    }
	
	
	//	Optimized to use only one row
	public int minimumTotal2(List<List<Integer>> triangle) {
		final int len = triangle.size();
		int[] dp = new int[len];
		
		//	Initialize row
		for (int i = 0; i < len; ++i)
        	dp[i] = triangle.get(len-1).get(i);
		
		for (int i = len - 2; i >= 0; --i) {
			for (int j = 0; j <= i; ++j)
				dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
		}
		
		return dp[0];
	}
}
