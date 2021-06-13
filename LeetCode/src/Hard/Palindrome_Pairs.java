package Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/palindrome-pairs/
/*
 * 	This is a Trie problem.
 * 
 * 	A palindrome has some prefix p, and suffix s that is reflection of prefix p. How can we find out pairs of palindromes
 * 	from an array of string?
 * 
 * 	For Brute force, it is to basically select each string, then in nested loop, iterate all strings and check whether their
 * 	concatenated string is a palindrome or not. Time taken is O(N^2 * K) where N is number of strings, K is avg length of strings
 * 
 * 	However, possible candidates of second string can majority be eliminated if we compare their suffix with the selected
 * 	string's prefix. Eg: For selected string "abc", we only consider:
 * 		>	Exact string ""
 * 		>	Exact string "a" (Although it is not palindrome, cases like "aaa" and "a" needs checking)
 * 		>	Exact string "ba"
 * 		>	Exact string "cba"
 * 		>	Any other string that ends with "cba"
 * 
 * 	For this job, a Trie structure is suitable. We'll construct a SUFFIX tree, and if the particular node represents a 
 * 	complete word, instead of storing the string, we store the index of that word inside the array.
 */




public class Palindrome_Pairs {
	//	TrieNode class
	private class TrieNode {
		public TrieNode[] next = new TrieNode[26];
		int index = -1;
	}
	
	//	Trie solution
	public List<List<Integer>> palindromePairs(String[] words) {
		final int len = words.length;
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        
        //	Construct the TrieNode first
        for (int i = 0; i < len; ++i) {
        	String s = words[i];
        	TrieNode curr = root;
        	for (int idx = s.length() - 1; idx >= 0; --idx) {
        		int c = s.charAt(idx) - 'a';
        		if (curr.next[c] == null) curr.next[c] = new TrieNode();
        		curr = curr.next[c];
        	}
        	curr.index = i;
        }
        
        //	For each of the words, find the possible palindrome strings from the Trie
        for (int i = 0; i < len; ++i) {
        	search(words, i, root, 0, res);
        }
        
        return res;
    }
	
	
	private void search(String[] words, int left, TrieNode right, int leftIdx, List<List<Integer>> res) {
		if (right.index != -1 && left != right.index && isPalindrome(words[left] + words[right.index]) )
			res.add( Arrays.asList(left, right.index) );
		
		//	Left string haven't get exhausted yet
		if (words[left].length() > leftIdx) {
			int c = words[left].charAt(leftIdx) - 'a';
			if (right.next[ c ] == null) return;
			
			search(words, left, right.next[ c ], leftIdx+1, res);
		} 
		//	Left string is exhausted. Search for any word possible
		else {
			for (TrieNode t: right.next) 
				if (t != null)
					search(words, left, t, leftIdx, res);
		}
	}
	
	
	private boolean isPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) return false;
			++l; --r;
		}
		return true;
	}
}
