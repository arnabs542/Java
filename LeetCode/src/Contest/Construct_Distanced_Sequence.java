package Contest;

//https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
/*
 * 	This problem turns out to be backtracking problem...
 * 
 * 	We trying to generate the maximum lexicographical array - AKA try to put big values as front as possible
 * 
 * 	However, we are not fixing the value to be insert and trying to move to another position when the position fails.
 * 	This easily results in a non maximum permutation since the fixed value is large, and we are moving it backwards, and
 * 	it may block the next large value from occupying the front spaces.
 * 
 * 	Notice that a solution must be possble. A value must be able to occupy at each space. This guarantee is a big hint
 * 
 * 	Think reversely. Instead of fixing value, we fixing the position and try value from largest to smallest - Greedy.
 * 	Of course, repeated values not allowed. Do Record that
 */
public class Construct_Distanced_Sequence {
	
	public int[] constructDistancedSequence(int n) {
        return recurse(0, new boolean[n+1], new int[n*2 - 1], n);
    }
	
	
	private int[] recurse(int pos, boolean[] used, int[] arr, int n) {
		final int len = arr.length;
		
		
		if (pos >= len) return arr;		//	Position exceeded means array is complete.
		if (arr[pos] != 0) return recurse(pos+1, used, arr, n);		//	If the position is occupied, proceed to next space.
		
		//	Try to fill in current position, try from largest value to small ones
		for (int i = n; i >= 1; --i) {
			if (used[i] ) continue;
			
			//	If i is 1 (no need to check) OR the next i grid is also available, then only we fill
			if ( i == 1 || ( pos + i < len && arr[pos+i] == 0) ) {
				arr[pos] = i;
				if (i != 1) arr[pos+i] = i;
				used[i] = true;
				
				int[] res = recurse( pos + 1, used, arr, n);
				if (res != null) return arr;
				
				//	Reverse the changes.
				arr[pos] = 0;
				if (i != 1) arr[pos+i] = 0;
				used[i] = false;
			}
		}
		return null;
	}

}
