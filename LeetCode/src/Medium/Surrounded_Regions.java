package Medium;

import java.util.LinkedList;

//https://leetcode.com/problems/surrounded-regions/

/*
 * 	This is a DFS/BFS problem.
 * 
 * 	Any 'O' that is not at the boundary itself, and does not connect to any 'O' located at boundary, will be converted to 'X'
 * 	at the end.
 * 	Therefore, the objective is to identify for every 'O' in the board, is it connected to any 'O' at boundary (or it is at
 * 	boundary itself?)
 * 
 * 	We can approach this in 2 ways:
 *	1.	Once found a 'O' in the matrix, we perform dfs/bfs to locate all 'O' connected to it, then determine if it is connected
 *		to 'O' at boundary. If it does, ignore the group. If it does not, then convert all of them into 'X'
 *
 *	2. 	Instead of finding out a group is connected to 'O' at boundary or not, we actively ELIMINATE those groups that are
 *		indeed connected to 'O' at boundary.
 *
 *	
 *	Method 2 is much more easier and readable to implement:
 *		- Iterate through first column, first row, last column and last row.
 *		- If it contains a 'O':
 *			- Mark it as some arbitrary character, like '!' to indicate that this 'O' is connected to boundary (Not convertable)
 *			- DFS/BFS on its 4 sides to look for more 'O' to eliminate.
 *
 *		- Once finished, the board will have 'X', 'O' and '!'.
 *			'!' is the original 'O' but is connected to boundary
 *			'O' is the ones that are not connected to any 'O' at boundary
 *			'X'
 *
 *		- Run a final iteration through the whole board:
 *			- Convert '!' to 'O'
 *			- Convert 'O' to 'X'
 */

public class Surrounded_Regions {
	
	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) return;
		
		LinkedList<int[]> queue = new LinkedList<>();
		
		//Scans through the first row and the last row
		for (int col = 0; col < board[0].length; col ++ ) {
			if (board[0][col] == 'O') 
				queue.offer(new int[] {0, col});
			if (board[board.length - 1][col] == 'O')
				queue.offer(new int[] {board.length - 1, col} );
		}
		//Scans through the first column and last column
		for (int row = 0; row < board.length; row++ ) {
			if (board[row][0] == 'O')
				queue.offer(new int[] {row, 0} );
			if (board[row][board[0].length - 1] == 'O' )
				queue.offer(new int[] {row, board[0].length - 1} );
		}
		
		while (!queue.isEmpty() ) {
			int[] coord = queue.poll();
			if (board[coord[0]][coord[1]] == 'Z' ) 
				continue;
			board[coord[0]][coord[1]] = 'Z';
			
			//Check for surrounding O's, if it is not out of bound
			//Top
			if (coord[0] - 1 >= 0 && board[coord[0] - 1][coord[1]] == 'O')
				queue.offer(new int[] {coord[0] - 1, coord[1]} );
			//Below
			if (coord[0] + 1 < board.length && board[coord[0] + 1][coord[1]] == 'O' )
				queue.offer(new int[] {coord[0] + 1, coord[1]} );
			//Left
			if (coord[1] - 1 >= 0 && board[coord[0]][coord[1] -1] == 'O' )
				queue.offer(new int[] {coord[0], coord[1] - 1} );
			//Right
			if (coord[1] + 1 < board[0].length && board[coord[0]][coord[1] + 1] == 'O') 
				queue.offer(new int[] {coord[0], coord[1] + 1} );
		}
		
		for (int row = 0; row < board.length; row ++ ) {
			for (int col = 0; col < board[0].length; col ++ ) {
				if (board[row][col] == 'Z')
					board[row][col] = 'O';
				else
					board[row][col] = 'X';
			}
		}
		
		
    }
	
}
