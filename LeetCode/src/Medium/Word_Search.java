package Medium;

//https://leetcode.com/problems/word-search/

/*
 * 	A DFS Backtracking problem. We have to basically travel through each cell in the board. Each cell, we call upon a search function recursively, to have
 * 	it see if the board cell character matches the start of word.
 * 	
 * 	Inside the recursive call, if it doesn't match the character, return false
 * 	If the recursive call index exceeds the length of string itself, that means the previous characters had all been matched. Return true.
 * 	
 * 	Notice that we can't use the same grid twice. This doesn't boil down to just knowing the previous grid alone. We have to keep track of
 * 	which grid was used previously to match the word. We could actually make a boolean array to mark those visited grid true, so that during
 * 	recursion function, if it detects the current grid's boolean value is true, then it will not use this grid and therefore return false.
 * 
 * 	To optimize space, we would just record the used grid in-place using the original board. We mark it with a character that shouldn't be
 * 	in possible values in the board, anything like * % # or space will do.
 * 
 * 	
 */

public class Word_Search {

	public boolean exist(char[][] board, String word) {
		
		for (int r = 0; r < board.length; r ++ ) {
			for (int c = 0; c < board[0].length; c ++ ) {
				//Attempt to match the word using this grid results in positive. Return yes
				if ( recurse(board, word, 0, r, c) )
					return true;
			}
		}
		return false;
    }
	
	private static boolean recurse( char[][] board, String word, int idx, int posx, int posy) {
		//The index has exceeded the string's range of indices. That means all part of string is matched previously. Return true
		if (word.length() <= idx) return true;
		//This grid was used previously and marked, dont use this grid. Return false
		if (board[posx][posy] == ' ') return false;
		
		//Get the current character to match as c, and record this grid's character as well.
		char c = word.charAt(idx);
		char boardC = board[posx][posy];
		
		//No match of character. Return false
		if ( boardC != c ) return false;
		
		//Mark this grid as visited
		board[posx][posy] = ' ';
		
		//Search up, down, left and right, only if the index for it was valid
		if (posx > 0) {
			if ( recurse(board, word, idx+1, posx-1, posy) ) return true;
		}
		if (posx < board.length - 1) {
			if ( recurse(board, word, idx+1, posx+1, posy) ) return true;
		}
		if (posy > 0) {
			if ( recurse(board, word, idx+1, posx, posy-1) ) return true;
		}
		if (posy < board[0].length - 1) {
			if ( recurse(board, word, idx+1, posx, posy+1) ) return true;
		}
		
		//Finished searching up down left right but no found. 'Unvisit' this grid
		board[posx][posy] = boardC;
		
		//In cases of grid like 1x1, the above searching will not be performed at all. It should return true here
		return idx == word.length() - 1;
	}
	
}
