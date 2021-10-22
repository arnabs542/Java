package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

//https://leetcode.com/problems/sort-characters-by-frequency/
/*
* 	This is a string, sorting problem.
* 
*  First of all, we must agree that we have to obtain the information on the frequencies of the character. A minimum of O(N) is required.
*  One solution is that we could obtain the frequency in <char, int> pair. Eg: <'a', 3> means there are 3 'a' in the string
*  Then, we sort the pairs using the frequency as the sorting key. At the end, the list would have pairs in descending sorted order.
*  Finally, append to our result string and return.
* 
*  Time complexity is O(N + K log K) where N is length of string, K is the character space, in this case ASCII, thus 127 is adequate
* 
*  Note that the similar can also be done using priorityqueue, which also handles the sorting
* 
*  ---------------------------------------------------------------------
* 
*  To use bucket sort, we need to realize that any character c in the string can only have maximum frequency of s.length. Therefore, 
*  we can just initialize an bucket array of size s.length + 1, obtain the frequencies of characters, and put the characters into the bucket
*  by their frequencies. Eg: bucket[20] should contain characters that appear 20 times.
*  Due to natural ordering, we construct the result string by iterating the bucket backwards. At the end we'll get what we needed too.
* 
*  Time complexity is O(N), space O(N).
*/

public class SortCharactersByFrequency {
	
	// Sort by pairs uisng PQ solution
	public static String frequencySort(String s) {
		HashMap<Character, Integer> frequency = new HashMap<>();
		for (char c: s.toCharArray() ) {
			frequency.putIfAbsent(c, 0);
			frequency.replace(c, frequency.get(c) + 1);
		}

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
	
	
	// Bucket sort solution
	String frequencySort2(String s) {
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> freq = new HashMap<>();
        String[] bucket = new String[s.length() + 1];

        // Count frequencies
        for (char c : s.toCharArray())
        	freq.put(c, freq.getOrDefault(c, 0) + 1);

        // Put in bucket
        for (char c: freq.keySet()) {
        	int f = freq.get(c);
            if (bucket[f] == null) bucket[f] = "";
            bucket[ f ] += c;
        }

        // Build sorted string based on bucket
        for (int i = s.length(); i > 0; --i) {
        	if (bucket[i] == null) continue;
            for (char c : bucket[i].toCharArray()) {
                for (int f = i; i > 0; --f)
                    res.append(c);
            }
        }

        return res.toString();
    }
	
}
