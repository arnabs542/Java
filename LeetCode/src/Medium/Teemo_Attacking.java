package Medium;

//https://leetcode.com/problems/teemo-attacking/

/*
 * 	This is an interval problem.
 * 	Given the timeSeries in ascending order already, we just have to simulate the time flow of the poisoning.
 * 
 * 	We can use the start and end variable to store the 'session' of poisoning (Continuous time of poisoning).
 * 	Say if the poisoning time start at 3, and duration is 2 units, then
 * 		start = 3
 * 		end = 3 + 2 = 5
 * 				- 1 = 4		//	Since 3 is also counted as poisoning time, we have to subtract 1 to represent the actual ending time
 * 
 * 	
 * 	Iterating through the timeSeries, there can be 2 possibilities:
 * 		>	Time is before (or equal) the end time. Teemo poisoned once again when enemy is already poisoned. This means extending 
 * 			the poison time. We don't have to add the poison time to result yet. Just extend it.
 * 
 * 		>	Time is after the end time. The prior poisoning session has ended. 
 * 			Add the prior poisoning session duration into the result. Then set the starting time and supposed ending time for this
 * 			new poisoning session.
 * 
 * 	----------------------------------------------------------------------------------------------------
 * 	
 * 	A shorter version would be just adding the minimum of either
 * 		>	duration
 * 		>	difference in time passed between 2 times
 * 	into the result
 * 
 * 	Say times is [1,2] and duration is 3 unit
 * 	We will only loop once, considering '1' and '2'.
 * 
 * 	Res += min( 2 - 1, duration=3 )
 * 	
 * 	This will essentially add the difference in time between the 2 times. In this case, 1 unit.
 * 	Say if the times is [1,10], then it will only add 3 (duration), because by the time reaches time=10, poisioning session had expired
 * 	with 3 unit of time elapsed.
 * 
 * 	Since the end also counts as a poisoning session, add the duration once last time into result, and return it.
 */

public class Teemo_Attacking {
	
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		int start = 0;
		int end = -1;		//Initialized to -1 so that at the first element, it will add end - start + 1 which is 0 to result.
							//To keep the result unaltered although there is actually no poisoning session prior to this
		int res = 0;
		
		for (int t: timeSeries) {
			if (t <= end) {
				end = t + duration - 1;
			} else {
				res += end - start + 1;
				start = t;
				end = t + duration - 1;
			}
		}
		res += end - start + 1;
		return res;
	}
	
	
	//	SHorter version
	public int findPoisonedDuration_SHORT(int[] timeSeries, int duration) {
		if (timeSeries.length == 0) return 0;
		
		int res = 0;
		
		for (int i = 0; i < timeSeries.length - 1; i ++ ) {
			res += Math.min( timeSeries[i + 1] - timeSeries[i] , duration);
		}
		res += duration;
		
		return res;
	}
	
}
