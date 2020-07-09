package easy;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/maximum-perimeter-triangle/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Keypoint to notice: In the sorted array, attempt to first form a triangle with the longest stick along with the second longest and third longest
 * 	If it is impossible, that already means the rest (shorter ones) cannot form the triangle, so abandon the current longest stick
 * 	and proceed to form the triangle with second longest stick, 3rd longest, 4 longest and repeat...
 */

public class Maximum_Perimeter_Triangle {
	static int[] maximumPerimeterTriangle(int[] sticks) {
		Arrays.sort(sticks);
		for (int i  = sticks.length - 1; i >= 2; i -- ) {
			if ( Math.pow(sticks[i], 2) <= Math.pow(sticks[i-1], 2) + Math.pow(sticks[i-2], 2) )
				return new int[] {sticks[i-2], sticks[i-1], sticks[i] };
		}
		return new int[] {-1};
	}
}
