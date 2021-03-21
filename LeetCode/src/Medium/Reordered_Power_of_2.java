package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/reordered-power-of-2/
/*
 *	This is a mathematical problem, testing you what approach you take to solve problem
 * 	
 * 	First approach would be to do exactly what the problem asks: To generate each permutation of the digit and check
 * 	each case if it is a power of 2 or not.
 * 	Permutations can be generated via backtracking algorithm with recursion. However this approach is costly. Since the
 * 	upper limit is N = 1000000000, there are 10 digits, and 10! would be 3628800. Quite costly isn't it?
 * 
 * 	Note that via bit manipulation, a power of 2 can be checked easily by looking if the whole binary string contains only
 * 	1 '1' bit and other is 0 only.
 * 
 * 	==========================================================
 * 
 * 	ABout permutations, we can simply count the frequencies of digits. For given N, count the number of 0...9 inside that
 * 	number. Then, we could prepare a list of power of 2 digit frequencies, and compare them individually. Once match, simply
 * 	return true.
 * 
 * 	This approach is much better. For LIMIT N, we only need to store log2(N) permutations. Then, When checking if a number can
 * 	be rearranged to power of 2 or not, also takes log2(N) time.
 * 
 * 	For all possible power of 2 digit counts, you can either do it at runtime at every function call, or cache it if it is called
 * 	frequently. Here I used the cache method
 * 
 * 	(Apart from counting in array, we can also use sort method)
 */

public class Reordered_Power_of_2 {
	private static final int LIMIT = 1000000000;
	private static final List<int[]> perms;
	
	static {
		perms = new ArrayList<>();
		for (int i = 1; i <= LIMIT; i <<= 1) {
			int[] freqs = new int[10];
			for (char c: Integer.toString(i).toCharArray() )
				++freqs[c - '0'];
			perms.add(freqs);
		}
	}
	
	public static boolean isPowerOf2(int i) {
		int[] freq = new int[10];
		for (char c: Integer.toString(i).toCharArray() )
			++freq[c - '0'];
		
		for (int[] perm: perms)
			if ( Arrays.equals(perm, freq) ) return true;
		return false;
	}
	
	
	public boolean reorderedPowerOf2(int N) {
        return isPowerOf2(N);
    }
}
