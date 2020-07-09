package easy;

import java.util.HashMap;

//https://www.hackerrank.com/challenges/equality-in-a-array/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * Simple hash map problem. Count the most occurred element frequency count in the array, and that means we have to remove the rest of elements
 * to reach a array of all same element
 */

public class Equalize_The_Array {
	static int equalizeArray(int[] arr) {
		HashMap<Integer, Integer> count = new HashMap<>();
		int max = 0;
		for (int i: arr) {
			int toPut = count.getOrDefault(i, 0) + 1;
			max = (max < toPut)? toPut: max;
			count.put(i, toPut);
		}
		return arr.length - max;
    }
}
