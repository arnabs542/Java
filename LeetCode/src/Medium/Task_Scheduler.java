package Medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

//https://leetcode.com/problems/task-scheduler/

/*
 * 	This is a greedy problem.
 * 	Given a list of tasks to execute, where each task executed will have a cooldown time before the CPU can execute the same task again, we need to find the maximum
 * 	time unit required to execute all tasks.
 * 
 * 	Since each task executed will have a cooldown time, then it's best to execute the task with most frequency as soon as possible, and take the gap between to
 * 	execute the second most frequent, and so on...
 * 
 * 	We could first count the frequencies of each task, and sort them based on the frequencies each task appear, with the most frequency at the head of the sorted
 * 	list (like a heap).
 * 	Basically, Following the greedy approach, we can derive a formula that how much spaces that the task will take based on the cooldown interval:
 * 		
 * 				(Frequency) * (n + 1) - n
 * 	(n + 1) is due to the fact that the task also take one unit of time, if n = 4, then T _ _ _ _ << 5 units
 * 	- n is due to the fact that last task will not longer take up cooldown time.
 * 
 * 	However, also notice for the second most frequent task, it will start executing after the most frequent task, at time unit 1.
 * 	Therefore we have to introduce a offset to the formula:
 * 				(Frequency) * (n + 1) - n + offset
 * 	
 * 	Since the intervals are the same, we can actually not do sorting by ignoring those tasks that has less frequency than the max frequency, as they can fit
 * 	into the gaps just fine. The problem arise when the max frequency occur for several different tasks.
 * 
 * 	THerefore we need to apply the offset for the number of times max frequency. If max frequency occurs in 3 different tasks, then we will simply
 * 	calculate the time unit taken, but offset by 2 (Since the third max will start occur at time unit 2)
 * 
 * 	Lastly, the time unit taken must be minimum of the tasks array length. Any time unit below it is impossible. So final result has to be compared with the
 * 	task numbers.
 * 	
 * 
 */

public class Task_Scheduler {
	
//	public int leastInterval(char[] tasks, int n) {
//		Map<Character, Integer> freq = new HashMap<>();
//		for (char c: tasks) {
//			freq.put( c, freq.getOrDefault( c, 0) + 1);
//		}
//		
//		PriorityQueue<Map.Entry<Character, Integer> > heap = new PriorityQueue<>( new Comparator<Map.Entry<Character, Integer> >() {
//			public int compare(Entry<Character,Integer> o1, Entry<Character, Integer> o2) {
//				return o2.getValue() - o1.getValue();
//			}
//		});
//		heap.addAll( freq.entrySet() );
//
//		int max = tasks.length;
//		
//		for (int i = 0; !heap.isEmpty(); i ++ ) {
//			int currFreq = heap.poll().getValue();
//			max = Math.max(max, currFreq * (n + 1) - n + i );
//		}
//		
//		return max;
//	}
	
	public int leastInterval(char[] tasks, int n) {
		int[] freq = new int[26];
		for (char c: tasks) {
			freq[c - 'A'] ++;
		}
		int max = 0;
		int maxoffset = 0;
		for (int i: freq) {
			if (i > max) {
				max = i;
				maxoffset = 1;
			}
			else if (i == max) {
				maxoffset ++;
			}
		}
		
//		PriorityQueue<Integer> heap = new PriorityQueue<>( (a,b) -> {
//			return freq[b] - freq[a];
//		});
//		for (int i = 0; i < 26; i ++ ) {
//			if (freq[i] == 0) continue;
//			heap.add(i);
//		}
//
//		int max = tasks.length;
//		
//		for (int i = 0; !heap.isEmpty(); i ++ ) {
//			int currFreq = freq[ heap.poll() ];
//			max = Math.max(max, currFreq * (n + 1) - n + i );
//		}
		
		int res = Math.max( tasks.length, freq[max] * (n + 1) - n + maxoffset);
		return res;
		
		
//		return max;
	}
}
