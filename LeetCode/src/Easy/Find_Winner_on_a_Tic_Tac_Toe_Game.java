package Easy;

//https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
/*
 * This is a simulation, array problem.
 * 
 * The most straightforward solution is to simulate the process using 2D matrix, then lastly check each row, column,
 * diagonal and antidiagonal for any winning or not. There's total of 8 cases to check.
 * If no winner, it is a "Draw" if the board is full (AKA there are total of 9 moves), else "Pending"
 * 
 * Instead of simulating, we could also record the scores on each of the row, column, diagonal and antidiagonal.
 * Then instead of checking the board for 3-match, we check if the score is a winning score for A or B
 */

public class Find_Winner_on_a_Tic_Tac_Toe_Game {
	
	public String tictactoe(int[][] moves) {
		String[] board = new String[3 * 3];
		
		// Use indexing and % 2 to derive whose move it was
		for (int i = 0; i < moves.length; ++i) {
			String mover = i % 2 == 0? "A": "B";
			int[] m = moves[i];
			board[ m[0] * 3 + m[1] ] = mover;
		}
		
		// Now check for vertical, horizontal and diagonal.
		// Vertical check
		if (board[0] != null && board[0].equals(board[3]) && board[0].equals(board[6])) return board[0];
		if (board[1] != null && board[1].equals(board[4]) && board[1].equals(board[7])) return board[1];
		if (board[2] != null && board[2].equals(board[5]) && board[2].equals(board[8])) return board[2];
		// Horizontal check
		if (board[0] != null && board[0].equals(board[1]) && board[0].equals(board[2])) return board[0];
		if (board[3] != null && board[3].equals(board[4]) && board[3].equals(board[5])) return board[3];
		if (board[6] != null && board[6].equals(board[7]) && board[6].equals(board[8])) return board[8];
		// Diagonal check
		if (board[0] != null && board[0].equals(board[4]) && board[0].equals(board[8])) return board[0];
		if (board[2] != null && board[2].equals(board[4]) && board[2].equals(board[6])) return board[2];
		
		// No winner found. Output draw / pending depending on moves size
		return moves.length == 9? "Draw": "Pending";
    }
	
	
	// Instead of simulating in a (3x3) board, simply record scores for row, col and diagonal.
	public String tictactoe2(int[][] moves) {
		// 0,1,2 - Row, 3,4,5 - Col, 6 - Diagonal, 7 - Antidiagonal
		int[] scores = new int[8];
		
		for (int i = 0; i < moves.length; ++i) {
			int mover = i % 2 == 0? 1: -1;
			int[] m = moves[i];
			// Row
			scores[ m[0] ] += mover;
			// Col
			scores[ m[1] + 3 ] += mover;
			// Diagonal
			if (m[0] == m[1]) scores[6] += mover;
			// Antidiagonal
			if (m[0] == 2 - m[1]) scores[7] += mover;
		}
		
		// Check for winnings
		for (int s: scores) {
			if (s == 3) return "A";
			else if (s == -3) return "B";
		}
		return moves.length == 9? "Draw": "Pending";
	}
}
