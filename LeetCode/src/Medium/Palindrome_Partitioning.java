package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
/*
 *	This is mainly a backtracking (which uses Depth First Search), optionally Dynamic programming question
 *
 *	Say we have the string AAB
 *	
 *	At every subarray that involves first character until the last (A, AA, AAB), if we checked that the subarray
 *	indeed forms a palindrome (A, AA), then surely, the question left is,
 *	
 *			what is the palindrome partitions to the right of this subarray?
 *
 *	This is because if the right DOES have its own palindrome partitions, surely we can just append the current
 *	palindrome to the result palindrom of right side, and have a full palindrome partitions already!
 *
 *	DP uses bottom up approach, so we can start from the back and work our way to the front, to obtain the final
 *	answer - Palindrome partitions starting with first character.
 *
 *	We use a 2D dynamic array to record the solutions to previous palindrome partitions. The first indexing is
 *	the starting index, then follows a series of String arrays which contains individual partitions.
 *
 *	Eg: At index [3], it contains one or many String arrays, each representing the possible partitions formed
 *	from the character at index 3 onwards.
 *
 * 	Iterating from backward to choose the starting character, we are going to try all possible length of substring
 * 	within the range starting character to end. If one of such is found to be palindrome, we know immediately that
 * 	a palindrome partition can be formed, so for each palindrome partition formed at starting character to the right
 * 	of current ending range, form a partition using current palindrome and that existing palindrome.
 * 
 * 	-------------------------------------------------------------------------------------
 * 
 * 	This is actually still a backtracking problem. From leftmost character, expand the substring one by one.
 * 	If found to be a palindrome, push substring into backtracking array, and recurse to find the partitions
 * 	to the right of the end of substring.
 * 
 * 	When the recursion hits the end of full length string, we know one partition is fully formed for the string
 * 	thus we push the backtracking array contents into result array.
 * 
 * 	--------------------------------------------------------------------------------------
 * 
 * 	Instead of using two pointers for checking palindrome, we can use DP to check palindrome in O(1) time instead.
 * 
 * 	A string is palindrome if 
 * 		>	Length less than or equal 3, and first & last char is same
 * 		>	Else, first & last char same, and substring in between them is also palindrome
 * 
 * 	Therefore the checking substring in between the first and last char part can be done in O(1) time using
 * 	DP table.
 */

public class Palindrome_Partitioning {
	
	
	public List<List<String>> partition(String s) {
		final int len = s.length();
        
		List<List<String>>[] arr = new ArrayList[len + 1];
		for (int i = 0; i <= len; i ++) 
			arr[i] = new ArrayList<>();
		arr[len].add( new ArrayList<>() );
		
		
		for (int i = len - 1; i >= 0; --i) {
			StringBuilder sb = new StringBuilder();
			
			for (int j = i; j < len; ++j ) {
				sb.append(s.charAt(j) );
				
				if (isPalindrome(sb) ) {
                    for (List<String> prev: arr[j+1] ) {
                    	List<String> li = new ArrayList<>();
                    	li.add( sb.toString() );
                    	li.addAll(prev);
                    	
                    	arr[i].add( li );
                    }
				}
			}
			
		}
        return arr[0];
    }
	private boolean isPalindrome(StringBuilder sb) {
		final int len = sb.length();
		for (int i = 0; i < len / 2; ++i) {
			if (sb.charAt(i) != sb.charAt(len - i - 1) ) return false;
		}
		return true;
	}
	
	
	

	public List<List<String>> partition2( String s) {
		List<List<String>> res = new ArrayList<>();
		List<String> record = new ArrayList<>();
		recurse(s, 0, record, res);
		return res;
	}
	private void recurse(String s, int start, List<String> record, List<List<String>> res) {
		final int len = s.length();
		
		//	Base case: Already reached the end of string, means it is partitioned. Add result
		if (start >= len) {
			res.add( new ArrayList<>(record) );
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < len; ++i) {
			sb.append(s.charAt(i) );
			if (isPalindrome(sb) ) {
				record.add(sb.toString() );
				recurse(s, i+1, record, res);
				record.remove( record.size() - 1 );
			}
		}
	}
	
	
	
	public List<List<String>> partition3(String s) {
		List<List<String>> res = new ArrayList<>();
		List<String> record = new ArrayList<>();
		boolean[][] isPalin = new boolean[s.length()][s.length()];
		recurse2(s, 0, record, res, isPalin);
		return res;
	}
	private void recurse2(String s, int start, List<String>record, List<List<String>> res, boolean[][] isPalin) {
		final int len = s.length();
		
		if (start >= len) res.add( new ArrayList<>(record) );
		
		for (int i = start; i < len; ++i) {
			if (s.charAt(start) == s.charAt(i) && (i - start <= 2 || isPalin[start+1][i-1] ) ) {
				isPalin[start][i] = true;
				record.add( s.substring(start, i+1) );
				recurse2(s, i+1, record, res, isPalin);
				record.remove( record.size() - 1);
			}
		}
	}

}
