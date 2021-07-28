package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/beautiful-array/
/*
 * 	This is an unintuitive, medium (HARD) math, array and divide & conquer problem.
 * 
 * 	First, notice that if we were to segment the array into two parts:
 * 		| odds | evens |
 *  Then our problem is half solved, because odd+even = odd, which is impossible for 2* nums[k] to equal
 *  
 *  So we just need to ensure that in each part |odd| and |even| does not cause inner nums[i] + nums[j] = 2 * nums[k].
 *  
 *  
 *  In other words, the |odd| and |evens| must be a beautiful array itself, too.
 *  One interesting property that beautiful array poccess, is that even if the array is multiplied by a constant,
 *  it is also beautiful array (Just maybe not a permutation of 1...N anymore) See:
 *  		
 *  	[ 1, 3, 2, 4 ] * 2 ====> [ 2, 6, 4, 8 ]
 *  
 *  Same property holds if the array is added with constant
 *  
 *  	[ 1, 3, 2, 4 ] + 1 ====> [ 2, 4, 3, 5 ]
 *  
 *  In either cases, the property nums[i] + nums[j] != 2 * nums[k] holds true
 *  
 *  
 *  
 *  In the case where a beautiful array * 2, we see that it creates a nicely even segment for N=8 or N=9!
 *  The same when the beautiful array is (array+1)/2. It creates a nicely odd segment!
 *  
 *  That means, once we separated into two segments, find the beautiful array of that size and reverse the formula!
 *  Eg: N=11
 *  		[ odd of size 6 | even of size 5]
 *  Then,
 *  		odd segment will refer to (beautifulArr(6) + 1) / 2
 *  		even segment will refer to (beautifulArr(5) * 2)
 */	

public class Beautiful_Array {
	
	// Use Memoization for performance
	Map<Integer, int[]> memo = new HashMap<>();
	
	public int[] beautifulArray(int n) {
		if (n == 1) return new int[] {1};
		if (n == 2) return new int[] {1,2};
		
		if (memo.containsKey(n) ) return memo.get(n);
		
		int[] res = new int[n];
		int[] odds = beautifulArray((n+1) / 2);
		int[] evens = beautifulArray(n/2);
		
		for (int i = 0; i < odds.length; ++i)
			res[i] = (odds[i] * 2) - 1;
		for (int i = 0; i < evens.length; ++i)
			res[i + odds.length] = (evens[i] * 2);
		
		memo.put(n, res);
		return res;
    }
	
}
