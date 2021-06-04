package Medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/open-the-lock/
/*
 * 	This is a Breadth first search problem.
 * 
 * 	Starting from 0000, we can make 8 moves. Here how it is:
 *	>	We can pick 1 out of 4 digits, and either spin up or down.
 *
 *	Spinning creates a new permutation of 4 digit number, which may be the correct way to the solution, or it doesn't
 *	We won't know until we've explored it.
 *
 *	Therefore, using BFS, we explore the moves, but remember to avoid computing for the numbers we've seen before. Also,
 *	deadends represent we cannot move anymore, so if we obtained a deadend, do not generate moves for it.
 *
 *	BFS is plausible because the states of numbers are limited. Maximum states is only 0000 to 9999, which is 10000.
 *	With this, we can say that the time complexity of the algorithm also be constant time.
 *	
 *	==================================================================
 *
 *	The algorithm can be improved using bi-directional BFS, where in one turn, I explore subchild of 0000, then in next
 *	turn, i explore subchild of target, then take turns.
 *	
 *	Although bi-directional BFS doesn't reduce the worst case time complexity, it does try to reduce the time taken
 *	because the explore space is narrower.
 */

public class Open_the_Lock {
	
	//	Single directional BFS search solution
	public int openLock(String[] deadends, String target) {
		Set<String> setOfDeadend = new HashSet<>();
		Set<String> visited = new HashSet<>();
		Queue<String> bfs = new ArrayDeque<>();
		int level = 0;
		
		for (String n: deadends)
			setOfDeadend.add( n );
		if (setOfDeadend.contains("0000")) return -1;
		
		visited.add("0000");
		bfs.offer("0000");
		
		while (!bfs.isEmpty() ) {
			int size = bfs.size();
			while (size-- != 0) {
				String pop = bfs.poll();
				if (target.equals(pop)) return level;
				
				//	Generate the available 8 moves
				for (int d = 0; d < 4; ++d) {
					char c = pop.charAt(d);
					String spinup = pop.substring(0, d) + (char)(c == '9'? '0': c + 1) + pop.substring(d+1);
					String spindown = pop.substring(0, d) + (char)(c == '0'? '9': c - 1) + pop.substring(d+1);
					
					//	Spinning up this digit, it is a new number and not a deadend
					if (!visited.contains(spinup) && !setOfDeadend.contains(spinup)) {
						visited.add(spinup);
						bfs.offer(spinup);
					}
					//	Spinning down this digit, it is a new number and not a deadend
					if (!visited.contains(spindown) && !setOfDeadend.contains(spindown)) {
						visited.add(spindown);
						bfs.offer(spindown);
					}
				}
			}
			++level;
		}
		//	Finished BFS and found no target. Thus return -1
		return -1;
    }
	
}
