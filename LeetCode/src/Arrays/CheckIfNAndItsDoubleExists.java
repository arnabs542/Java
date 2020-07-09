package Arrays;

import java.util.HashSet;

/*
 * 	We can use a Hash Set, and check if each element contains a half or double of it. We add it to the hash set
 */

public class CheckIfNAndItsDoubleExists {
	
	public boolean checkIfExist(int[] arr) {
		HashSet<Integer> toFind = new HashSet<>();
		
		for (int i: arr) {
			if (toFind.contains(i * 2) || i % 2 == 0 && toFind.contains(i / 2) ) {
				return true;
			}
			toFind.add( i);
		}
		return false;
	}
}
