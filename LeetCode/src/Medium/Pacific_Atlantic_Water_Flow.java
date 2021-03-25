package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/pacific-atlantic-water-flow/
/*
 * 	This is a graph BFS/ DFS problem. Each grid can be represented as node which connects to all 4 directions
 * 
 * 	Instead of brute force, which we need to iterate over all cells, and check if all cells if it can be connected
 * 	to both oceans, we reverse the thinking:
 * 
 * 		"What cells can be possibly be the origin for both oceans?"
 * 
 * 	The answer is, the cell which has path to both atlantic and pacific ocean. Then, we further break the problem down:
 * 	
 *		"What cells has path to atlantic?" and "What cells has path to pacific?"
 *
 *	If we have individual answer to both problems, then we can combine the solution of both problem to solve general problem.
 *	That is, if a cell is both path to atlantic and path to pacific, then that cell is connected to both oceans!
 *
 *	
 *	Start BFS/ DFS from any one ocean. Instead of as problem stated to go downwards, we only explore nodes which has higher
 *	height. Any node which is explored, is marked as possible path to the ocean. Record that in data structure.
 *
 *	At the end, compare both data structure to determine the position of cells which is connected to both oceans.
 */

public class Pacific_Atlantic_Water_Flow {
	
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();
        
        final int row = matrix.length, col = matrix[0].length;
        final int[][] dirs = { {1,0}, {0,1}, {-1,0}, {0,-1} };
        boolean[][] intersect1 = new boolean[row][col];
        boolean[][] intersect2 = new boolean[row][col];
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        //	First, explore from Pacific
        //	Initialize
        for (int i = 0; i < col; ++i) {
        	queue.add( new int[] {0, i});
        	intersect1[0][i] = true;
        }
        for (int i = 0; i < row; ++i) {
        	queue.add( new int[] {i, 0});
        	intersect1[i][0] = true;
        }
        
        //	Explore upstream to find the source
        while (!queue.isEmpty() ) {
        	int[] pos = queue.poll();
        	
        	for (int[] dir: dirs) {
        		int r = pos[0] + dir[0], c = pos[1] + dir[1];
        		try {
        			if (matrix[pos[0]][pos[1]] <= matrix[r][c] && !intersect1[r][c]) {
        				queue.add( new int[] {r, c} );
        				intersect1[r][c] = true;
        			}
        		} catch (Exception e) {}
        	}
        }
        
        
        //	Then, explore from Atlantic
        //	Initialize
        for (int i = 0; i < col; ++i) {
        	queue.add( new int[] {row - 1, i});
        	intersect2[row - 1][i] = true;
        }
        for (int i = 0; i < row; ++i) {
        	queue.add( new int[] {i, col - 1});
        	intersect2[i][col - 1] = true;
        }
        
        //	Explore upstream to find the source
        while (!queue.isEmpty() ) {
        	int[] pos = queue.poll();
        	
        	for (int[] dir: dirs) {
        		int r = pos[0] + dir[0], c = pos[1] + dir[1];
        		try {
        			if (matrix[pos[0]][pos[1]] <= matrix[r][c] && !intersect2[r][c]) {
        				queue.add( new int[] {r, c} );
        				intersect2[r][c] = true;
        			}
        		} catch (Exception e) {}
        	}
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < row; ++r) {
        	for (int c = 0; c < col; ++c) {
        		if (intersect1[r][c] && intersect2[r][c] )
        			res.add( Arrays.asList(r,c) );
        	}
        }
        
        return res;
    }
}
