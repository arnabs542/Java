package Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

//https://leetcode.com/problems/sort-characters-by-frequency/

/*
 * 	Tackling this question requires several steps:
 * 		1. First obtain the number of frequency of occurences of each character by a linear scan and hashmap (or a bucket of 255 in size)
 * 		2. Then put them in a sort of data structure that would sort them naturally (priority queue) but set the comparator to sort them
 * 		   based on their frequency, in reversed order
 * 		3. Append them to string builder. Volia
 */

public class SortCharactersByFrequency {
	
	public static String frequencySort(String s) {
		HashMap<Character, Integer> frequency = new HashMap<>();
		for (char c: s.toCharArray() ) {
			frequency.putIfAbsent(c, 0);
			frequency.replace(c, frequency.get(c) + 1);
		}
		
//		TreeMap<Integer, LinkedList<Character> > sorted = new TreeMap<>();
//		for (char c: frequency.keySet() ) {
//			sorted.putIfAbsent(frequency.get(c) , new LinkedList<>() );
//			sorted.get(frequency.get(c) ).add(c);
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		for (Integer i: sorted.descendingKeySet() ) {
//			for (char c: sorted.get(i) ) {
//				for (int count = 0; count < i; count++ ) {
//					sb.append(c);
//				}
//			}
//		}
//		return sb.toString();
		
		//Remember about comparators: Negative value will be in the front:
		// Eg: x = 5, y = 3
		// then y - x = -2, resulting in x being arranged in front, which is what we want
		PriorityQueue<Character> pq = new PriorityQueue<>( (x, y) -> {
			return frequency.get(y) - frequency.get(x);
		});
		pq.addAll(frequency.keySet() );
		
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty() ) {
			char c = pq.poll();
			for (int i = frequency.get(c); i > 0; i --) {
				sb.append(c);
			}
		}
		return sb.toString();
    }
	
	public static void main(String[]args) {
		System.out.println( frequencySort("tree"));
	}
}
