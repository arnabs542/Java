package Hard;

import java.util.Arrays;

//https://leetcode.com/problems/burst-balloons/
//https://www.youtube.com/watch?v=IFNibRVgFBo&ab_channel=TusharRoy-CodingMadeSimple
/*
 * 	This is a Dynamic Programming, Divide n Conquer Problem, which I couldn't have solved without looking at 
 * 	solution :(
 * 	
 * 	The solution has a approach similar to Matrix Chain Multiplication which is a classical DP problem as well.
 * 	
 * 	The approach is that, we will consider every possible subarray that can be formed in the list. The subarray
 * 	represents the balloons that we will pop, and from that subarray, we will find out the maximum coins that
 * 	can be earned by attempting each balloon to be popped LAST (means, other balloons in the subarray will be popped
 * 	first, then only the selected balloon is popped last).
 * 
 * 	We will need a DP array of size N, where N is number of elements in the array.
 * 	DP array of row i and j, means the maximum coins that can be earned by popping all the balloons from index i
 * 	to index j inclusively, after considering all last balloons to be popped.
 * 
 * 	Say our example, [ 3,1,5,8 ]
 * 
 * 	Starting with subarray of size 1. The question we will ask is: What is the maximum coins that can be earned
 * 	by popping 1 balloons?
 * 		(3) - (1 x 3 x 1)
 * 		(1) - (3 x 1 x 5)
 * 		(5) - (1 x 5 x 8)
 * 		(8) - (5 x 8 x 1)
 * 	
 * 	Then, we proceed to subarray of size 2. The question will ask is: WHat is the maximum coins that can be earned
 * 	by popping 2 balloons?
 * 	Since there are 2 balloons, we are going to consider every balloon in the array to be last popped, and see which
 * 	one yields maximum profit.
 * 
 * 		(3,1) => (3) last pop, that means (1) will be popped first, so we check DP array for dp[1,1] 
 * 						(Meaning, the max coins that can be obtained by popping balloon (1) )
 * 				Then, the balloon (3) is popped. It has to be multiplied with adjacent balloons. WHat are the
 * 				adjacent balloons? The one on right side of the subarray, which is balloon 5 Since we already
 * 				consider the balloon (1) is popped before (3) is popped
 * 
 * 				Thus its dp[1,1] + (1 x 3 x 5)
 * 
 * 			  => (1) last pop. THat means (3) will be popped first, so we check DP array for dp[0,0]
 * 						(Meaning, the max coins that can be obtained by popping balloon (3) )
 * 				Then, the balloon (1) is popped. It has to be multiplied with adjacent balloons. To the left its
 * 				nothing, due to (3) already popped and (3) is the first element in array. To the right is (5). So
 * 		
 * 				dp[0,0] + (1 x 1 x 5)
 * 
 * 		Take the maximum from it.
 * 
 * 	
 * 	Say now we got to array of size 3. and selecing subarray (3,1,5). See:
 * 		
 * 		(3) last pop, that means (1,5) will be popped first. To know the maximum of popping (1,5), check dp[1,2]
 * 			Then, the balloon (3) will be popped. To the left it is nothing, and to the right is only balloon
 * 			(8), thus coins earned:
 * 
 * 				dp[1,2] + (1 x 3 x 8)
 * 
 * 
 * 		(1) last pop, that means (3) and (5) will be popped first. Check dp[0,0] and dp[2,2] for that.
 * 			THen, the balloon (1) will be popped. The left is nothing and the right is balloon (8).
 * 
 * 				dp[0,0] + dp[2,2] + (1 x 1 x 8)
 * 
 * 	
 * 	The idea is to repeat this process until the size of subarray covers the entire array, and find out the maximum
 * 	coin that can be earned for it. That is the solution
 * 	
 */

public class Burst_Balloons {
	
	public static int maxCoins(int[] nums) {
		final int len = nums.length;
		int[][] dp = new int[len][len];
		
		for (int size = 1; size <= len; ++size) {
			
			for (int start = 0; start <= len - size; ++start) {
				int end = start + size - 1;		//	End of the subarray, inclusive
				
				//	Attempt every balloon to be last popped
				for (int lastToPop = start; lastToPop <= end; ++lastToPop) {
					int earnings = 0;
					if (lastToPop > 0) earnings += dp[start][lastToPop-1];	//	Add only if there is left side in subarray
					if (lastToPop < len-1) earnings += dp[lastToPop+1][end];//	Add only if there is right side in subarray
					
					earnings += nums[lastToPop] * (start-1 >= 0? nums[start-1]: 1) *
							(end+1 < len? nums[end+1]: 1);
					
					dp[start][end] = Math.max(earnings, dp[start][end]);
				}
			}
		}
		return dp[0][len - 1];
	}
	
}
