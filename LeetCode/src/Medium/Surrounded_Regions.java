package Medium;

import java.util.LinkedList;

//https://leetcode.com/problems/surrounded-regions/

/*
 * 	Notice any O is not surrounded by X if it connects to any other O (or itself) which is located at border. Therefore we can just initiate searching
 * 	from the border to find any O's, and any O connected to it must not be surrounded and shall be converted to X. This is a DFS or BFS problem.
 * 	
 * 	To save space, instead of using another structure to store if a O is need to be converted or not, we can just mark the excluded O with another
 * 	character like 'Z' in this case. Later when we iterate it over again, we would make all cells to contain 'X' except those with 'Z', which should
 * 	be changed back to 'O'
 * 
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
