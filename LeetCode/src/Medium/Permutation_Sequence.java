package Medium;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3366/

/*
 * 	Trying to generate the permutation is too tedious. You can use backtracking and recursion etc but in worst case it will still be O(n!)
 * 	We can observe an O(n) solution through some trial and error and finding the pattern
 * 
 * 	Given a set in ascending order, kept in data structure like a list, we can see that to find the kth permutation, we do the following pattern:
 *		-For example, if we list down the permutations of 3 digits, 1 2 3:
 *		123
 *		132
 *		213
 *		231
 *		312
 *		321
 *		Although it is not obvious in this 3 digit example, if we see how many permutations start with one particular digit, it is actually
 *		(n - 1)! , which is 2 in this case. Let's see the 4 digit ones:
 *		1234
 *		1243
 *		1324
 *		1342
 *		1423
 *		1432
 *		2134 ...
 *		You can see in this case it is 6 cases which is starting with digit 1. Why is this? This is because after selecting 1 as starting point,
 *		there are n - 1 digits remaining. Then to fill in the remaining spots, there are (n-1)! ways, resulting in 6 ways to start with digit 1.
 *
 *		Note that if k = 1, there is no permutation process needed to be done, as the list is already in order. Therefore we will take k value
 *		as k - 1.
 *
 *		Since to find the kth permutation, we would just follow suit the pattern. We know for sure how many permutations it will be which starts
 *		with particular digit, we can find out given k, what digit it starts with, using division.
 *		Eg: for k = 4 and 3 digits, firstly we decrement k by 1. 
 *		First digit is k / (n-1)!, which is 3 / 2! --- 1th digit selected. So it starts with 2, using 0 as starting index. Remaining: (1,3)
 *		
 *		How about the remaining 2 places? We take the modulo of above equation as our new k. The new k will be 1, and1 / 1! will be 1. So we will
 *		take the element at 1th index --- 3. Remaining: (1)
 *
 *		Now just fill in the rest with the last element : 231.
 */

public class Permutation_Sequence {
	
//	public static String getPermutation(int n, int k) {
//		//Initialization of the lowest lexicographical permutation which is in ascending order
//		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i <= n; i ++ ) {
//			sb.append(i);
//		}
//		System.out.println("1.\t" + sb.toString() );
//		
//		for (int i = 1; i < k; i ++ ) {
//			nextLexicoPerm(sb);
//			System.out.println((i+1) + ".\t" + sb.toString() );
//		}
//		
//		return sb.toString();
//	}
//	
//	private static void nextLexicoPerm(StringBuilder sb) {
//		//Set the pivot to be at the place where the first encounter of descending order viewed from the back
//		int pivot = sb.length() - 1;
//		while (pivot > 0 && sb.charAt(pivot - 1) > sb.charAt(pivot) ) {
//			pivot --;
//		}
//		pivot --;
//		if (pivot < 0) return;
//		
//		//Set the greater pointer to the place where the digit is greater than the pivot
//		int greater = sb.length() - 1;
//		while (greater > pivot && sb.charAt(pivot) > sb.charAt(greater) ) {
//			greater --;
//		}
//		
//		char temp = sb.charAt(greater);
//		sb.setCharAt(greater, sb.charAt(pivot) );
//		sb.setCharAt(pivot, temp);
//		
//		sb.replace(pivot + 1, sb.length(), new StringBuilder(sb.substring(pivot + 1)).reverse().toString() );
//		
//	}
	
	public static String getPermutation(int n, int k) {
		List<Integer> ascending = new LinkedList<>();
		IntStream.rangeClosed(1, n).forEach(ascending::add);
		StringBuilder sb = new StringBuilder();
		
		int factorial = 1;
		for (int i = 1; i < n; i ++ ) 
			factorial *= i;
		
		k--;
		
		while ( !ascending.isEmpty() ) {
			int elementSelect = k / factorial;
			k %= factorial;
			sb.append(ascending.remove(elementSelect) );
			factorial /= (ascending.size() == 0)? 1: ascending.size();
		}
		
		return sb.toString();
	}
	
	public static void main(String[]args) {
		System.out.println(getPermutation(9, 70));
	}
	
}
