package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/majority-element-ii/

/*
 * 	This is a Booyer Moore's Voting Algorithm.
 * 
 * 	Instead of finding the n/2 majority element, this one actually needs to find 2 majority elements that are more than n/3 Floored.
 * 	For details of the algorithm, I've recorded in the Topics > Boyer Moore Voting Algorithm.
 */

public class Majority_Element_II {
	
	public List<Integer> majorityElement(int[] arr) {
		int counter1 = 0, candidate1 = Integer.MIN_VALUE;
		int counter2 = 0, candidate2 = Integer.MIN_VALUE;
		
		//	First pass. Eliminate until left only 2 possible candidates
		for (int n: arr) {
			
			//	Matching candidates must be before counter = 0 checking. This is because if second counter is 0 but element matches
			//	the first candidate, it should be the first counter incrementing, not the second candidate being reset
			if (candidate1 == n) {
				counter1 ++;
			} else if (candidate2 == n) {
				counter2 ++;
			} else if (counter1 == 0) {
				candidate1 = n;
				counter1 ++;
			} else if (counter2 == 0) {
				candidate2 = n;
				counter2 ++;
			} else {
				counter1 --;
				counter2 --;
			}
		}
		
		//	Second pass. To verify both the candidates
		counter1 = 0; counter2 = 0;
		
		for (int n: arr) {
			if (candidate1 == n) counter1 ++;
			else if (candidate2 == n) counter2 ++;
		}
		
		int bound = arr.length / 3;
		
		List<Integer> res = new ArrayList<>();
		
		if (counter1 > bound) res.add(candidate1);
		if (counter2 > bound) res.add(candidate2);
		return res;
	}
	
}
