package Easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3325/

/*
 *	The problem is a graph theory problem, where the judge should have 0 Outgoing edges, and exactly N-1 incoming edges
 *	Even better, every time a person trusts a person, we simply decrement it by 1, and when a person get trusted, increment it by one
 *	Lastly use a linear search to check if there is a person (judge) which value is exactly N-1
 */

public class Find_The_Town_Judge {
	
	public int findJudge(int N, int[][] trust) {
		HashMap<Integer, Integer> potential = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i ++ ) {
			potential.put(i+1, 0 );
		}
		
		for (int[] t: trust) {
			potential.remove(t[0]);
			if (potential.containsKey(t[1]) )
				potential.replace(t[1], potential.get(t[1]) + 1);
		}
		
		if (potential.size() != 1) return -1;
		else {
			int i = potential.keySet().iterator().next();
			if (potential.get(i) != N-1) return -1;
			else return i;
		}
    }
	
	public int findJudgeImprovised(int N, int[][]trust) {
		int[] trustCount = new int[N];
		
		//Remember that indexing starts at 0, therefore index 0 refers to person 1
		for (int[] t: trust) {
			trustCount[t[0] - 1 ] --;
			trustCount[t[1] - 1 ] ++;
		}
		for (int i = 0; i < N; i ++ ) {
			if (trustCount[i] == N-1 ) return i+1;
		}
		return -1;
	}
	
}
