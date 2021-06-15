package Easy;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-units-on-a-truck/
/*
 * 	This is a introductory greedy, sorting problem.
 * 
 * 	If we can load certain amount of boxes only, of course we will be picking the boxes with large unit as priority!
 * 	If we sort the boxes beforehand by descending units, then it is ensured the boxes we first encounter is the best
 * 	box to take.
 * 
 * 	For a O(N) runtime, observe that units can only goes up until 1000. This enables counting sort, which records
 * 	how many such boxes have i units. At the end, iterate i from largest until smallest while loading the truck 
 */

public class Maximum_Units_on_a_Truck {
	
	//	boxTypes -> { number of such boxes, units per box }
	public int maximumUnits(int[][] boxTypes, int truckSize) {
		int res = 0;
		Arrays.sort(boxTypes, (x,y)-> {
			return y[1] - x[1];
		});
		
		for (int[] boxes: boxTypes) {
			if (truckSize == 0) return res;

			res += Math.min(truckSize, boxes[0]) * boxes[1];
			truckSize -= Math.min(truckSize, boxes[0]);
		}
		return res;
    }
	
	
	//	Counting sort because values of max box per unit only goes up to 1000
	public int maximumUnits2(int[][] boxTypes, int truckSize) {
		// Unit	-> Number of such boxes
		int[] freqs = new int[1001];
		for (int[] boxes: boxTypes)
			freqs[ boxes[1] ] += boxes[0];
		
		int res = 0;
		for (int i = 1000; i >= 0 && truckSize != 0; --i) {
			res += Math.min(truckSize, freqs[i]) * i;
			truckSize -= Math.min(truckSize, freqs[i]);
		}
		return res;
	}
	
}
