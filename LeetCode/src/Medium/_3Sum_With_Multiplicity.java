package Medium;

//https://leetcode.com/problems/3sum-with-multiplicity/
/*
 * 	If you haven't do the Two Sum problem before, you shouldn't do this first.
 * 	This is a HashMap (Frequency Table), Two pointers problem.
 * 
 * 	What we will try to do is to fix two values, and check if third value, say value at index k, satisfy the condition
 * 	i < j < k, where i and j is the index of first two values.
 * 	Here we will try to fix i and j position.
 * 
 * 	However, note that we can have multiple value occurring in the array, and this is not preferred. Let's convert it into
 * 	a frequency table so we have easier computation. (In this case, it is in range [0, 100] so we could use array)
 * 
 * 	With frequency table, we could attempt to set first pointer i at 0..100. Then, try to set second pointer j but make sure
 * 	it is greater or equal to i!
 * 	Now, where should third value land? It is obtained from target-i-j. Then, first check if k is out of bounds or does not
 * 	satisfy i < j < k. 
 * 	If all ok, then break into cases:
 * 
 * 	>	i == j == k. All three are same values like target=6, and [2,2,2]. Then we have Freq(2) values, and we need to choose
 * 		3. Perform combinations formulae
 * 	>	i == j != k or i != j == k. Like above, we have to choose 2. Perform combinations on 2, while multiply the other.
 * 	>	i != j != k. You know what to do. Multiply these 3 frequencies together
 */

public class _3Sum_With_Multiplicity {
	
	public int threeSumMulti(int[] arr, int target) {
		long[] freqs = new long[101];
		for (int i: arr) 
			++freqs[i];
		
		long res = 0;
		
		//	First pointer
		for (int i = 0; i < 101; ++i) {
			if (i > target) break;				//	i is larger than target already
			if (freqs[i] == 0) continue;		//	No such number in array
			
			//	Second pointer - Reduced to Two Sum problem
			for (int j = i; j < 101; ++j) {
				if (i + j > target) break;	//	Already exceed. Impossible
				if (freqs[j] == 0 ) continue;	//	No such number in array
				
				int k = target - i - j;
                if (k > 100) continue;		//	Too large until out of index.
				if (k < j) break;			//	Smaller than j, then the case is either less than 0, or seen before. No use
											//	iterating further since j will only increase, causing k to decrease further
				
				if (i == j && j == k) res += combination_of_3( freqs[i] );
				else if (i == j) res += combination_of_2( freqs[i] ) * freqs[k];
				else if (j == k) res += freqs[i] * combination_of_2( freqs[j] );
				else res += freqs[i] * freqs[j] * freqs[k];
				
				res %= 1000000007;
			}
		}
		
		return (int)res;
    }
	
	
	private long combination_of_3(long N) {
		return (N * (N-1) * (N-2) ) / 6;
	}
	
	private long combination_of_2(long N) {
		return (N * (N-1) ) / 2;
	}
	
}
