package Hard;

import java.util.LinkedList;

//https://leetcode.com/problems/dungeon-game/

/*
 * 	A Dynamic Programming question which is easier to spot and realize. The question asks for the minimum health required to reach princess grid.
 * 	Therefore we could see the optimization (min) and can divide into subproblems (What is the min health required to reach princess from this
 * 	grid?)
 * 
 * 	** Second time doing this problem thoughts: Well I couldn't come up with solution the second time, because I tried DP from top left corner.
 * 		This gives a lesson: If I were to brute force, i begin with top left. DP is a bottom up problem, means we should probably start from the
 * 		destination.
 * 		Some DP problem are better off start from destination than starting from source.
 * 	
 * 	We would create a new 2D array of same size where each cell represents the min health required to reach princess from this cell. (Actually,
 * 	we could overwrite the original dungeon array as well, which further reduces the space complexity to O(1) )
 * 
 * 	The initialization would be to determine the min health to reach princess FROM PRINCESS GRID ITSELF, which depends:
 * 		-If it is damaging room, then it would be abs(damage) + 1 (So that the knight still have 1HP left and not die)
 * 		-If it is healing room, then it would be 1 (Of course, hero dies at HP 0)
 * 
 * 	Then we would simply just loop thru the array from bottom up. Each cell we would determine by the following equation:
 * 		Take the minimum value between minHealthGrid(right, bottom) --- To take the path which requires lowest health possible
 * 		and subtract the current dungeon value 						--- So that if it is damaging (negative), we would add to the required health
 * 																	--- of next step, and if it is healing, we would lessen the required health
 * 
 * 	Last note is that, if the calculated min health value is below or equal 0 (Occurs when the room heals big) we simply put required health as 1
 * 
 * 	Therefore we will return the original question:
 * 		The minimum health required to reach princess from origin (0,0)
 * 
 */

public class Dungeon_Game {
	public static int calculateMinimumHP(int[][] dungeon) {
		int rows = dungeon.length;
		int cols = dungeon[0].length;
		int[][] minHealth = new int[rows][cols];
		
		//Initialization: Health needed if i start at princess room. If it was healing room, min is 1, else
		//It will be the positive value of damage + 1.
		minHealth[rows -1][cols - 1] = (dungeon[rows-1][cols-1] >= 0)? 1: dungeon[rows-1][cols-1] * -1 + 1;
		
		for (int r = rows - 1; r >= 0; r -- ) {
			for (int c = cols - 1; c >= 0; c -- ) {
				
				//Since the loop also involves the princess grid, we don't want to have it included since we've already calculated!
				if (minHealth[r][c] != 0 ) continue;
				
				int minHP = Math.min( 
						(r + 1 < rows)? minHealth[r + 1][c]: Integer.MAX_VALUE, 
						(c + 1 < cols)? minHealth[r][c + 1]: Integer.MAX_VALUE ) 
						- dungeon[r][c];
				minHP = (minHP <= 0)? 1: minHP;
				
				minHealth[r][c] = minHP;
			}
		}
	
		//decomment this to see the final grid
//		for (int r = 0; r < rows; r ++ ) {
//			for (int c = 0; c < cols; c ++ ) {
//				System.out.print(minHealth[r][c] + " ");
//			}
//			System.out.println();
//		}
		return minHealth[0][0];
			
	}
	
	public static void main(String[]args) {
		int[][] arr = { {-2, -3, 3},
						{-5, -10, 1},
						{10, 30, -5} };
		System.out.println( calculateMinimumHP(arr) );
	}
	
}		//end of class
