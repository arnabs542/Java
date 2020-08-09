package Medium;

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
		int r = grid.length;
		int c = grid[0].length;
		
		int countRotten = 0;
		int countFull = 0;
		
		Queue<Integer> a = new LinkedList<>();
        Queue<Integer> b = new LinkedList<>();
		
		for (int row = 0; row < r; row ++ ) {
			for (int col = 0; col < c; col ++ ) {
				int org = grid[row][col];
				if (org > 0) {
					countFull ++;
					if (org == 2) {
						countRotten ++;
						a.offer(row);
						b.offer(col);
					}
				}
			}
		}
		
		int time = 0;
		
		while (countRotten < countFull && !a.isEmpty() ) {
			int awaiting = a.size();
			
			for (int i = 0; i < awaiting; i ++ ) {
				int xpos = a.poll();
				int ypos = b.poll();
				
				if (pollute(grid, xpos-1, ypos, r, c) ) {
					countRotten++;
					a.offer(xpos-1); b.offer(ypos);
				}
				if (pollute(grid, xpos+1, ypos, r, c) ) {
					countRotten++;
					a.offer(xpos+1); b.offer(ypos);
				}
				if (pollute(grid, xpos, ypos-1, r, c) ) {
					countRotten++;
					a.offer(xpos); b.offer(ypos-1);
				}
				if (pollute(grid, xpos, ypos+1, r, c) ) {
					countRotten++;
					a.offer(xpos); b.offer(ypos+1);
				}
			}
			time++;
		}
		
		if (countRotten < countFull) return -1;
		return time;
    }
	
	//	Attempts to pollute this grid. Returns true if it is polluted
	private static boolean pollute(int[][] grid, int x, int y, int a, int b) {
		if (x < 0 || x >= a || y < 0 || y >= b ) return false;
		
		if (grid[x][y] == 1) {
			grid[x][y] = 2;
			return true;
		}
		return false;
	}

}
