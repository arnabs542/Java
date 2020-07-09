package medium;

import java.util.List;

//https://www.hackerrank.com/challenges/non-divisible-subset/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	The key point to realize and study is that Given 2 integers, A and B. Their sum are divisible by k ONLY if their sum of remainders,
 * 	A % k + B % k = k or 0. This would mean that if a given integer is found having modulus of i, then it cannot be paired with the integers
 * 	whose modulus is k - i. 
 * 	Modulus 0 is a special case where both integers A and B can also be divided evenly by k. That means ONLY ONE out of the modulus 0 integers
 * 	can be taken in, so that it would not result to sum up to k.
 * 	Therefore we have to use a bucket of size k to store the frequency of modulus integers
 * 	Except from modulus 0 which is a special case, we could just iterate until k / 2 as for every modulus, we have to consider it's counterpart,
 * 	and take the maximum frequency out of the two only. 
 */

public class Non_Divisible_Subset {
	public static int nonDivisibleSubset(int k, List<Integer> s) {
		int[] bucket = new int[k];
		for (int i: s) {
			bucket[i % k] ++;
		}
		
		int length = (bucket[0] > 0)? 1: 0;
		for (int i = 1; i <= bucket.length / 2; i ++ ) {
			if (i == k - i) {
				length += (bucket[i] > 0)? 1: 0;
			}
			else
				length += Math.max(bucket[i] , bucket[k - i] );
		}
		
		return length;
	}
}
