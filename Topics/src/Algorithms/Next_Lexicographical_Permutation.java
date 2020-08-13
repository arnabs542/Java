package Algorithms;

//https://www.nayuki.io/page/next-lexicographical-permutation-algorithm


/*
 * Given a set of numbers or alphabets (duplicates maybe?), you are asked to find the permutation (all of it!). How would you go do it?
 * Perhaps you will do recursion, dynamic programming, but it is tricky. You need to keep track of duplicates, which can
 * be taking quite a long time
 * 
 * We have a algorithm to find the Next Lexicographical Permutation (which is the next 'larger' permutation of the current given order') in
 * O(n) time. Therefore to find all permutation, we first find the smallest lexicographical permutation, and keep calling the algorithm until
 * there is no more.
 * 
 * The algorithm go as follows:
 * 		1.	Starting from the last index, find the index such that the character/digit preceding it is not following ascending order, or 
 * 			descending order if viewed from start to end
 * 				Eg: 0 1 2 5 3 3 0	< -- from the end, 5 3 3 0 is following ascending order from the end, or descending order viewed from start.
 * 										 Therefore the 'pivot' in this case is the one interrupted the order, which is '2', of index 2
 * 			(If no such pivot is found, the whole array is in descending order, therefore there is no next permutation)
 * 		
 * 		2. Iterate through the ascending order part again, and find the one character/number which is greater than the pivot itself.
 * 				Eg: 0 1 (2) 5 3 3 0 	<-- Iterating from the end, 0 is smaller than pivot. Continue
 * 											Now 3 is greater than the pivot. Therefore in this case it's '3' at index 5
 * 
 * 		3. Swap the pivot and the one element we've just found. Then, excluding the pivot, reverse the whole order to the right of the pivot
 * 				Eg: 0 1 3 5 3 2 0 	<-- We swapped the '3' (pivot) and '2' (the One element)
 * 					0 1 3 0 2 3 5   <-- We reversed the order of subarray to the right of the pivot
 * 		
 * 		Volia and this now is the next lexicographical permutation of the original array.
 */

public class Next_Lexicographical_Permutation {
	
	public static String nextPerm (String s) {
		int pivotIndex = -1;
		for (int i = s.length() - 1; i >= 1; i -- ) {
			//The pivot is found if the preceding character is smaller than the current character. Remember to break the loop
			if (s.charAt(i) > s.charAt(i-1) ) {
				pivotIndex = i-1;
				break;
			}
		}
		//The whole array is in descending order. In other words, this is maximum possible permutation already. Return nothing
		if (pivotIndex == -1) return null;
		
		int swapIndex = -1;
		for (int i = s.length() - 1; i > pivotIndex; i -- ) {
			//The character which is bigger than the pivot chaacter is found. Remember to break the loop
			if (s.charAt(pivotIndex) < s.charAt(i) ) {
				swapIndex = i;
				break;
			}
		}
		
		s = swap(s, pivotIndex, swapIndex);
		return reverseFrom(s, pivotIndex + 1);
	}
	
	//Swaps the character at i and j. Index i must be always smaller than j
	public static String swap(String s, int i, int j) {
		return s.substring(0, i) + s.charAt(j) + s.substring(i + 1, j) + s.charAt(i) + s.substring(j+1);
	}
	
	//Reverses the substring starting from index start only
	public static String reverseFrom(String s, int start) {
		StringBuilder sbRev = new StringBuilder(s.substring(start) );
		sbRev.reverse();
		return s.substring(0, start) + sbRev;
	}
	
	
	
	public static void main(String[]args) {
		String s = "abcde";
		String res = nextPerm(s);
		while (res != null) {
			System.out.println(res);
			res = nextPerm(res);
		}
	}
}
