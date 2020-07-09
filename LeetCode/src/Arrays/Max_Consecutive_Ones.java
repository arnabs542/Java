package Arrays;


//https://leetcode.com/problems/max-consecutive-ones/

/*
* 	Simple problem which takes O(n) time.
* 	Loop through the nums. Every time you are met with a 1, increment the local count value, then compare if the local count has exceeded
* 	the max value
* 	Every time the value is met with 0, reset the local count value to 0 instead.
*/
public class Max_Consecutive_Ones {
	
	public int findMaxConsecutiveOnes(int[] nums) {
		int max = 0;
		int localCount = 0;
		for (int i: nums) {
			if (i == 1) {
				localCount ++;
				max = Math.max(max, localCount);
			}
			else {
				localCount = 0;
			}
		}
		
		return max;
  }
}
