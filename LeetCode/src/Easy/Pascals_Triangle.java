package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle/
/*
 * 	This is a Dynamic Programming Problem - Pascal Triangle!
 * 
 * 	If we have previous row, we easily compute the next row! THat's it!
 */

public class Pascals_Triangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<>( Arrays.asList(1)));
		
		for (int r = 2; r <= numRows; ++r) {
			List<Integer> row = new ArrayList<>();
			List<Integer> prevrow = res.get( r - 2 );
			row.add(1);
			
			for (int i = 0; i < prevrow.size() - 1; ++i)
				row.add( prevrow.get(i) + prevrow.get(i+1) );
			
			row.add(1);
			res.add(row);
		}
		return res;
	}
}
