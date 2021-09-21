package Easy;

//https://leetcode.com/problems/max-consecutive-ones/
/*
 * This is a Array problem
 * 
 * The idea is simple: Count 1s in groups. Take the maximum group length as result and return.
 * If met 0, reset the counter.
 * Else, add 1 to the counter, also update the result.
 */

public class Max_Consecutive_Ones {
	public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int curr = 0;
        
        for (int n: nums) {
        	if (n == 0) curr = 0;
        	else {
        		++curr;
        		res = Math.max(res, curr);
        	}
        }
        return res;
    }
}
