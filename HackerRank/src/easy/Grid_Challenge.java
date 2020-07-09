package easy;

//https://www.hackerrank.com/challenges/grid-challenge/problem?h_r=profile

import java.util.Arrays;

public class Grid_Challenge {
	static String gridChallenge(String[] grid) {
		char[][] strings = new char[grid.length][grid[0].length()];
		strings[0] = grid[0].toCharArray();
		Arrays.sort(strings[0] );
		for (int i = 1; i < grid.length; i ++ ) {
			strings[i] = grid[i].toCharArray();
			Arrays.sort(strings[i]);
			for (int j = 0; j < grid[0].length(); j ++ ) {
				if ( Character.compare(strings[i][j], strings[i-1][j]) < 0) return "NO";
			}
		}
		return "YES";
    }
}
