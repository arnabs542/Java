package Medium;

//https://leetcode.com/problems/gas-station/

/*
 * 	This is considered to be a greedy problem.
 * 
 * 	Given a circular path of gas stations and cost to travel there, we can think of them as a circular, directed graph.
 * 	To travel to the next node requires a certain cost, which is ( Gas refilled at station - cost to travel to next station )
 * 
 * 	See:
 * 	
 * 	Station		|	A	|	B	|	C	|	D	|	E	|
 * 	Refill		|	1	|	2	|	3	|	4	|	5	|
 * 	Cost		|	3	|	4	|	5	|	1	|	2	|
 * 	Weight		|	-2	|	-2	|	-2	|	3	|	3	|
 * 
 * 	We have to find the station to start, which can complete the whole circuit without the tank going empty (negative)
 * 	
 * 	We could keep a start variable and a tank variable. The start variable stores the station which we are starting from, and the
 * 	tank variable means the current gas amount in the tank.
 * 	
 * 
 * 	>	We have to consider the worst: How do we cover all possible routes? If the gas station is at E, then it will need to go through
 * 		E > A > B > C > D > E! A most direct way to do this is loop 2 times and extra one step to go back to the original station.
 * 		This will cover all possible stations
 * 		If there is a solution, certainly we can infinitely loop the circuit without running out of fuel!
 * 
 * 	>	At every node introduced, we have 2 choices:
 * 		-	To use this station as starting
 * 		-	This station is an intermediate station. We've reached here from previous station
 *		Therefore we have to choose the one which is the best.
 *		-	If we start from this station, the tank will be 0 from here.
 *		-	If this station is intermediate, then we will take the previous tank, refill and subtract the cost
 *
 *	>	Notice that it is optimal to start from this station only when the previous tank, after applying the difference, is less than
 *		0. This means we are unable to reach this station!
 *
 *	>	Therefore the algorithm is as follows:
 *			Initialize the tank and station to be 0. Start applying the refill and cost differences to the tank.
 *			When the tank had fall below 0 (not == 0), we have to start from the current station. Change the start station to the
 *			current one, and reset tank to 0.
 *			Iterate 2 circles to cover all possibilities. Remember an extra step so the edge case (station is the last element) can
 *			be satisfied.
 *
 *	-----------------------------------------------------------------------------
 *
 *	A more genious solution is, upon plotting the graph of the refill and cost differences (Cumulative), we can see here:
 *
 *		A	|	B	|	C	|	D	|	E	|
 * 		-2	|	-4	|	-6	|	-3	|	0	|
 * 
 * 	Ideally, we shall always start from a minimum station, because if we start from any other stations, it is equivalent to setting our
 * 	x axis at that level (Like our sea level). Since we know there is a more minimum point, and we need to certainly reach that point
 * 	due to cycle, it is impossible to have that station as solution
 * 
 * 	Eg:	If we set starting position at D, our CALIBRATED 0 TANK LEVEL is -3. If the graph fall below -3, it means the tank is emptied
 * 		Since we know there is a -6 at C, D must not be the solution 
 * 
 * 	=====================================================================================
 * 
 * 	Now, we have obtained
 * 	>	From solution 1, the starting station candidate.
 * 	>	From solution 2, the minimum cumulative station
 * 
 * 	We have to verify it. How?
 * 
 * 	1. 	A direct verification is to simulate the driving! Initialize the tank and drive your car one cycle and see if the tank has
 * 		gone below 0!
 * 
 * 	2.	More ingenious way is, the sum of the gases in total must greater than or equal to the sum of gas costs. If it is true,
 * 		we can directly return the answer already. In fact, this shall be done before the finding of station to directly 
 * 		elimiate impossible cases.
 * 
 */

public class Gas_Station {
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int len = gas.length;
		
		int tank = 0;
		int startNode = 0;
		
		//	Loop 2 times with one extra step
		for (int i = 0; i < len * 2 + 1; i ++ ) {
			
			int node = i % len;
			
			//	Apply the refill and cost
			tank += gas[node] - cost[node];
			
			//	Tank has gone below 0. Setting current station as starting point
			if (tank < 0 ) {
				startNode = (node + 1) % len;
				tank = 0;
			}
		}
		
		//	Simulate the driving to verify solution
		tank = 0;
		for (int i = 0; i < len + 1; i ++ ) {
			int node = (startNode + i) % len;
			tank += gas[node] - cost[node];
			
			if (tank < 0) return -1;
		}
		
		return startNode;
	}
	
}
