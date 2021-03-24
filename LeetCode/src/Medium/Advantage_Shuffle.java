package Medium;

import java.util.Arrays;
import java.util.TreeMap;

//https://leetcode.com/problems/advantage-shuffle/
/*
 * 	This is an array Greedy problem.
 * 
 * 	For this problem, say we have a value from B, v2, we would pair with a number in A such that the value is still
 * 	larger than v2, but is least larger. 
 * 	Also, if v2 cannot be paired anymore with any other number in A, we would pair it with the smallest number available
 * 
 * 	Therefore, how can we perform such search easily? One way is to use TreeMap.
 * 	With Treemap, we give it values of B, and hopefully it returns the least larger value to one number in A. We store it 
 * 	such that the value also counts frequencies in A. Once frequeny hit 0, delete that value as no more is available in A.
 * 
 * 	------------------------------------------------------
 * 
 * 	Best way is to do sorting. Sort A in ascending order and keep 2 pointers at both ends of it.
 * 	Then, sort B in descending order. The idea is:
 * 		>	Deal with largest element in B first. Because:
 * 			>	If it cannot be dealt with, pair with smallest number of A (left pointer of A)
 * 			>	If it can be dealt with, we can pair with largest in A as currently it is largest in B already. No worries
 */

public class Advantage_Shuffle {
	
	public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        int[] res = new int[A.length];
        
        for (int i: A)
        	bst.compute(i, (k,v)-> v == null? 0: v+1 );
        
        for (int i = 0; i < B.length; ++i) {
        	Integer toIns = bst.ceilingKey(B[i]+1);
        	
        	//	If no number is at least higher than current one, take the smallest number
        	if (toIns == null) toIns = bst.ceilingKey(0);
        	
        	res[i] = toIns;
        	bst.compute(toIns, (k,v)-> v-1 );
        	if (bst.get(toIns) == 0) bst.remove(toIns);
        }
        
        return res;
    }
	
	
	public int[] advantageCount2(int[] A, int[] B) {
		final int len = A.length;
		
		int l = 0, r = len - 1;
		Arrays.sort(A);
		
		//	Create a new 2D array of pairs <val, idx>
		//	Then, sort them based on value.
		int[][] B_idx = new int[len][2];
		for (int i = 0; i < len; ++i)
			B_idx[i] = new int[] {B[i],i};
		Arrays.sort(B_idx, (x,y)-> y[0] - x[0] );
		
		int[] res = new int[len];
		
		for (int[] i: B_idx) {
			if (A[r] > i[0]) res[i[1]] = A[r--];
			else res[i[1]] = A[l++];
		}
		
		return res;
	}

}
