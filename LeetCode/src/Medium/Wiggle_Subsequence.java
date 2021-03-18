package Medium;

//https://leetcode.com/problems/wiggle-subsequence/
/*
 * 	This is a Dynamic Programming, Greeedy problem!
 * 
 * 	My first approach is as follows:
 * 	Visualize the problem as finite state machine: There are 2 states:
 * 		>	State where we are currently in down wiggle
 * 		>	State where we are currently in up wiggle
 * 
 * 	To transition from states:
 * 		>	Down wiggle > Up wiggle requires us to meet with next num which is larger than last down wiggle number!
 * 		>	Up wiggle > Down wiggle requires us to meet with next num which is smaller than last up wiggle number!
 * 
 *  Greedy algorithm states that: At every step, we always pick the local optimal solution, which will lead us to global
 *  optimal solution.
 *  
 *  Notice that in a linear sequence, like 1,2,3,4,5. If this is a up wiggle, recall that up wiggle DP is used to update
 *  down wiggle DP in the future! So, we would really prefer if the last element in up wiggle DP is as large as possible.
 *  (Although in later solutions you'll see this is not required in code)
 *  
 *  Now, the states are relatively small, and we can implement the finite state machine and greedy using DP. 
 *  With DP, it represent 2 states (Think of it as 2 DP arrays), which:
 *  	>	One where ith element represents max length where range stops at ith elem in nums, and is in down wiggle state
 *  	>	One where ith element represents max length where range stops at ith elem in nums, and is in up wiggle state
 *  
 *  To update the DP on each iteration, recall the state machine transitions:
 *  	>	We can either not extend the newest elem, which means inherit from previous subsequence, potentially updating
 *  		the last element of the subsequence, or
 *  	>	Extend the subsequence from the opposite DP array. Eg: down DP is extended based on up DP. len will increment by
 *  		1
 *  
 *  ----------------------------------------------------
 *  
 *  Turns out, the DP algorithm can be much simpler.
 *  
 *  At a certain point in the iteration, there can be 3 cases:
 *  	n[i] > n[i-1], ie: A Up wiggle
 *  	n[i] < n[i-1], ie: A Down wiggle
 *  	n[i] == n[i-1], ie: No wiggle
 *  
 *  In case 1 and 2, we can immediately update the up/down DP counter with opposite down/up DP counter + 1. Why is this so?
 *  
 *  Say n[i] > n[i-1], a up wiggle. Now, down[i-1] represents a subsequence that ranges until n[i-1] ending with a down
 *  wiggle. Let the end of subsequence be x:
 *  	>	If x is larger than n[i], then x > n[i-1] is already a down wiggle, extending with n[i-1] is legitimate
 *  	>	If x is smaller than n[i], then simply replace x with n[i]. Now the subsequence in down[i-1] ends with n[i-1],
 *  		and surely we can extend with n[i]!
 *  
 *  
 */

public class Wiggle_Subsequence {
	
	//	Initial solution - Using Finite State Machine and DP
	public int wiggleMaxLength(int[] nums) {
		
		int prevInc = Integer.MAX_VALUE;
		int prevDec = Integer.MIN_VALUE;
		int incLen = 0, decLen = 0;
		
		for (int i: nums) {
			int currInc = prevInc, currDec = prevDec;
			int incLen2 = incLen, decLen2 = decLen;
			
			//===============================
			//	Updating the Increment state
			//===============================
			//	The decrement state can transition to current increment state thru this number, and it is
			//	worthy to do so (after transition, length is higher)
			currInc = i;
			if (i > prevDec && decLen + 1 > incLen ) 
				incLen2 = decLen + 1;
			
			
			//===============================
			//	Updating the Decrement State
			//===============================
			//	The increment state can transition to current decrement state thru this number, and it is
			//	worthy to do so (after transition, length is higher)
			currDec = i;
			if (i < prevInc && incLen + 1 > decLen)
				decLen2 = incLen + 1;
			
			//=============================================
			//	DP - Current becomes previous in next iter
			//=============================================
			prevInc = currInc; prevDec = currDec;
			incLen = incLen2; decLen = decLen2;
		}
		
		return Math.max( incLen, decLen);
    }
	
	
	//	Space optimized DP solution
	public int wiggleMaxLength2(int[] nums) {
		if (nums.length < 2) return nums.length;
		
		int down = 1, up = 1;
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] > nums[i-1]) up = down + 1;
			else if (nums[i] < nums[i-1]) down = up + 1;
		}
		return Math.max(down, up);
	}
	
	
	//	Pure greedy solution
	public int wiggleMaxLength3(int[] nums) {
		if (nums.length < 2) return nums.length;
		
		int prevdiff = nums[1] - nums[0];
		int count = prevdiff == 0? 1: 2;		//	If diff = 0, means front 2 is same, only 1 can picken.
		
		for (int i = 2; i < nums.length; ++i) {
			int diff = nums[i] - nums[i-1];
			if ( (diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0) ) {
				++count;
				prevdiff = diff;
			}
		}
		return count;
	}
}
