package Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/rotting-oranges/
/*
 * 	This is a BFS problem. Since it involves turn-taking, and finding out how many turns to rot the whole orange, each turn
 * 	is considered one 'round' of BFS
 * 
 * 	First do a initial scan to know the amount of oranges and amount of rotten ones. Add the rotten one's position into queue.
 * 	Then on round 1, we will start to rot the oranges adjacent to it. Note that we will also change the so-mentioned fresh orange
 * 	to rotten so we won't end up in a loop. Those rotten oranges need to be added to the queue to spread the rot-ness in the next
 * 	round of BFS
 */

public class Rotting_Oranges {
	
	public int orangesRotting(int[][] grid) {
		final int r = grid.length;
        final int c = grid[0].length;

        int rounds = 0;
        int fresh = 0;
        Deque<int[]> rotten = new ArrayDeque<>();

        // Initial scan -> get fresh oranges and rotten ones
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (grid[i][j] == 1) ++fresh;
                else if (grid[i][j] == 2) rotten.addLast( new int[]{ i, j } );
            }
        }

        // BFS
        while (fresh != 0 && rotten.size() != 0) {
            ++rounds;

            for (int i = rotten.size(); i != 0; --i) {
                int[] rot = rotten.pollFirst();

                // Up
                if (rot[0] != 0 && grid[rot[0] - 1][rot[1]] == 1) {
                    --fresh;
                    grid[rot[0] - 1][rot[1]] = 2;
                    rotten.addLast( new int[]{ rot[0] - 1, rot[1] });
                }
                // Down
                if (rot[0] != r - 1 && grid[rot[0] + 1][rot[1]] == 1) {
                    --fresh;
                    grid[rot[0] + 1][rot[1]] = 2;
                    rotten.addLast( new int[]{ rot[0] + 1, rot[1] } );
                }
                // Left
                if (rot[1] != 0 && grid[rot[0]][rot[1] - 1] == 1) {
                    --fresh;
                    grid[rot[0]][rot[1] - 1] = 2;
                    rotten.addLast( new int[]{ rot[0], rot[1] - 1 });
                }
                // Right
                if (rot[1] != c - 1 && grid[rot[0]][rot[1] + 1] == 1) {
                    --fresh;
                    grid[rot[0]][rot[1] + 1] = 2;
                    rotten.addLast( new int[]{ rot[0], rot[1] + 1 });
                }
            }
        }

        return fresh != 0? -1: rounds;
    }

}
