package easy;

import java.util.Iterator;
import java.util.TreeMap;

//https://www.hackerrank.com/challenges/cut-the-sticks/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
/*
 * 	At every cutting stage, the shortest will always be discarded, and the longer ones remain. This continues until there is sticks of 
 * 	equal length left.
 * 	If we does sort the array, then it would simplify the problem better, the problem left is how are we supposed to know the length of the result
 * 	array?
 */

public class Cut_The_Sticks {
	static int[] cutTheSticks(int[] arr) {
		TreeMap<Integer, Integer> heap = new TreeMap<>();
		int types = 0;
		for (int i: arr) {
			if (!heap.containsKey(i) ) types ++;
			heap.put(i, heap.getOrDefault(i, 0) + 1 );
		}
		
		int res[] = new int[types];
		int len = arr.length;
		res[0] = len;
		
		Iterator<Integer> it = heap.keySet().iterator();
		for (int i = 1; i < res.length; i++) {
			res[i] = len - heap.get( it.next() );
			len = res[i];
		}
		
		return res;
    }
}
