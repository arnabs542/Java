package Medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/brick-wall/
/*
 * This is a HashMap problem.
 * 
 * 	Intuitively, we want to draw a verticle line among the bricks. Therefore since the problem
 * 	stated that crossing the middle of two adjacent bricks doesn't count as crossing a brick,
 * 	we would greedily want to cross as many 'middle spaces between two bricks' as possible
 * 
 * 		0 1  2  3  4
 *  Eg: [ ][ ][   ]
 *  	[    ][   ]
 *  	[ ][ ][   ]
 *  
 *  We see if we cross column 2, that column has most 'middle spaces between two bricks', so we
 *  choose to cross that column!
 *  
 *  Unfortunately, we cannot simply use array to record middle space counts, because the brick may
 *  span up to INT_MAX, which is too much to handle. Fortunately, we can use HashMap.
 *  
 * 	For each row, keep a running sum of the widths. The running sum will indicate the end of a single
 * 	brick in that row, therefore is a 'space between bricks'. Record it as increment in the Hashmap
 * 	in x position.
 * 
 * 	At the end, find out the column with max no of middle spaces, and return (No of rows - spaces)
 */

public class Brick_Wall {
	
	public int leastBricks(List<List<Integer>> wall) {
		final int height = wall.size();
		Map<Integer, Integer> count = new HashMap<>();
		
		for (List<Integer> row: wall) {
			int sum = 0;
			for (int b: row) {
				count.compute(sum, (k,v)-> (v == null)? 1: v+1 );
				sum += b;
			}
		}
		
		count.remove(0);
		int res = 0;
		for (int v: count.values() ) 
			res = Math.max(res, v);
		
		return height - res;
    }
	
}
