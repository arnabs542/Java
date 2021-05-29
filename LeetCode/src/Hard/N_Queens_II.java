package Hard;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/n-queens-ii/
/*
 * 	This is the same as N-Queens problem (Backtracking), except it only require us to give the number of solution
 * 	this time.
 * 
 * 	The same rule applies as in the problem N-Queens. I recommend you to refer that in Hard > N_Queens
 * 	Here I use 3 Hashsets to represent the occupied column, diagonal and antidiagonal for backtracking 
 * 
 *	Note that this code is slow for some reasons especially in C++. For top speed, you may prompt for bitwise solution to represent which
 *	cols, diagonals and antidiagonals had been visited.
 */

public class N_Queens_II {
	
	public int totalNQueens(int n) {
		return recurse(0, 0, n, n, new HashSet<>(), new HashSet<>(), new HashSet<>() );
    }
	
	private int recurse(int r, int c, int n, int q, Set<Integer> cols, Set<Integer> diags, Set<Integer> antidiags) {
		if (q == 0) return 1;		//	All queens successfully placed. 1 Solution found
		if (r >= n) return 0;		//	Otherwise we ran out of grids. Return 0 solution
		
		int next_r = r + (c + 1) / n;
		int next_c = (c + 1) % n;
		
		int res = 0;
		//	Attempts to place queen
		if (!cols.contains(c) && !diags.contains(r-c) && !antidiags.contains(r+c)) {
			cols.add(c);
			diags.add(r-c);
			antidiags.add(r+c);
			res += recurse(r+1, 0, n, q-1, cols, diags, antidiags);
			cols.remove(c);
			diags.remove(r-c);
			antidiags.remove(r+c);
		}
		//	Not placing queen
		return res + recurse(next_r, next_c, n, q, cols, diags, antidiags);
	}
	
}
