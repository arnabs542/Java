package Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * 		In this question, the sum of indices (row + col) of each element is equivalent to the diagonal index
 * 		Therefore using a hashmap which maps the sum of indices(key) to the linkedlist of numbers is the solution to solve the problem
 * 
 */
	
public class Diagonal_Traverse_II {
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
		int max = 0;
		int size = 0;
		for (int row = 0; row < nums.size(); row ++ ) {
			for (int col = 0; col < nums.get(row).size(); col++ ) {
				if (map.containsKey(row + col) ) 
					map.get(row + col).add(nums.get(row).get(col) );
				else {
					map.put(row + col, new LinkedList<Integer>() );
					map.get(row + col).add(nums.get(row).get(col) );
				}
				if (row + col > max) max = row + col;
				size ++;
			}
		}
		int index = 0;
		int[] sol = new int[size];
		for (int i = 0; i <= max; i ++ ) {
			LinkedList<Integer> list = map.get(i);
			for (int j = list.size() - 1; j >= 0; j-- ) {
				sol[index] = list.get(j);
				index ++;
			}
		}
		return sol;
    }
}
