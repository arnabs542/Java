package Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/valid-square/

/*
 * 	This is a Mathematical Based Question
 * 
 * 	A square, if we compare all point with all other 3 points, it will basically have 2 lengths only.
 * 		>	Side Length
 * 		>	Vertex Length
 * 
 * 	Furthermore, the side lengths should be equal, and occur 4 times only (Accounting into repetition, thats 8)
 * 				 the vertex lengths should be equal, and occur 2 times only (Accounting into repetition, thats 4)
 * 
 * 	So, for each point, as long as it is not itself, find distance, record the length occurrences in frequency table
 * 	If there is only unique lengths, and the length occurrence is one 8, and one 4 (IF repetition), thats A SQUARE
 * 
 * 	================================================================================================================
 * 
 * 	If we sort the 4 points, first by x value, then only by y value, we will get a nice pattern which
 * 		>	First and last element is a vertex
 * 		>	Second and third element is a vertex
 * 
 * 	Then we easily check if the lengths of one point with other points are equal. This can simplify things
 */

public class Valid_Square {
	
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		Map<Double, Integer> freq = new HashMap<>();
		int[][] points = new int[4][];
		points[0] = p1; points[1] = p2; points[2] = p3; points[3] = p4;
		
		for (int i = 0; i < 4; i ++ ) {
			for (int j = 0; j < 4; j ++ ) {
				if (i == j) continue;
				double dist = findDist(points[i], points[j]);
				freq.putIfAbsent( dist , 0 );
				freq.put( dist, freq.get(dist) + 1 );
			}
		}
		
		if (freq.size() != 2) return false;
		
		boolean is8 = false, is4 = false;
		for (int i: freq.values() ) {
			if (i == 8) is8 = true;
			if (i == 4) is4 = true;
		}
		return is8 && is4;
		
    }
	
	
	
	public boolean validSquare2( int[] p1, int[] p2, int[] p3, int[] p4 ) {
		int[][] points = new int[4][];
		points[0] = p1; points[1] = p2; points[2] = p3; points[3] = p4;
		
		double idx1 = Integer.MIN_VALUE, idx2 = Integer.MIN_VALUE;
		int[] freq = new int[2];
		
		for (int i = 0; i < 4; i ++ ) {
			for (int j = 0; j < 4; j ++ ) {
				if (i == j) continue;
				double dist = findDist(points[0], points[1] );
				
				if (dist == idx1 || idx1 == Integer.MIN_VALUE) {
					idx1 = dist;
					freq[0] ++;
				} else if (dist == idx2 || idx2 == Integer.MIN_VALUE ) {
					idx2 = dist;
					freq[1] ++;
				} else return false;
			}
		}
		
		return (freq[0] == 8 && freq[1] == 4 || freq[0] == 4 || freq[8] == 8 );
	}
	
	
	
	public boolean validSquare3( int[] p1, int[] p2, int[] p3, int[] p4 ) {
		int[][] points = new int[4][];
		points[0] = p1; points[1] = p2; points[2] = p3; points[3] = p4;
		
		Arrays.sort( points, (x,y)-> {
			if (x[0] == y[0]) return x[1] - y[1];
			return x[0] - y[0];
		});
		
		double side = findDist( points[0], points[1] );
		double vertex = findDist( points[1], points[2] );
		if ( findDist( points[0], points[3] ) != vertex ) return false;
		if ( findDist( points[0], points[2] ) != side ) return false;
		if ( findDist( points[3], points[1] ) != side ) return false;
		if ( findDist( points[3], points[2] ) != side ) return false;
		return side != vertex;
	}
	
	
	private double findDist(int[] p1, int[] p2) {
		int dx = p1[0] - p2[0];
		int dy = p1[1] - p2[1];
		
		return Math.sqrt( dx * dx + dy * dy);
	}
	
}
