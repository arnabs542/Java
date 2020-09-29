package Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

//https://leetcode.com/problems/word-break/

/*
 * 	This is a DP / Trie problem.
 * 
 * 	The first approach is to use pure Trie.
 * 	First, we will construct the Trie of the entire wordDict. Remember to mark the end of word with the boolean isWord
 * 	To avoid using recursion, we can use any data structure (Stack or queue or any stuff that can be easily emptied and refilled)
 * 	to store POINTERS to TrieNodes.
 * 
 * 	The idea is as follows:
 * 	>	Each new character introduced, there is either 2 roles played by this character:
 * 		-	The start of a new word. But for this to be valid, the previous character must be the last character of a valid dictionary
 * 			word
 * 		-	The next character of a currently tracked dictionary word. In other words, it is a middle part (not starting) of a dictionary
 * 			word
 * 
 * 	>	To deal with case 1, use a boolean flag to indicate whehter the previous character is the end of a valid dictionary word.
 * 		This way when a new character is introduced and boolean flag says true, then we can check from the root whether this character
 * 		can form a new word.
 * 	>	To deal with case 2, we use the data structure to keep pointers of currently tracked words. Each character, we will basically empty
 * 		out the data structure (Iterating them), If the trie node contains the currently introduced character, push the next trie node
 * 		into the data structure
 * 
 * 	Worst Case is when the pointer becomes as many as the length of the dictionary. In that case, the time complexity is
 * 	O(NK) where N is length of the string and K is length of the dictionary
 * 
 * 	-------------------------------------------------------------------------------------------------------
 * 
 * 	Another real DP solution. The core idea is:
 * 		We can record a dp array of booleans. The boolean indicate whether the substring starting from index 0 until that index is
 * 		a valid work break or not.
 * 
 * 	With every character introduced, we will check if this character is the last character of a dictionary word. This checking can be
 * 	easily done if we keep a REVERSED TRIE (Words stored in reverse). If it is indeed a dictionary word, then we also need to check
 * 	before the current word if it is a valid word break or not.
 * 	Only if these 2 conditions are fulfilled, we can put TRUE into the dp array.
 * 	At the last index of dp array stores the overall answer to the problem.
 * 
 * 	Time complexity is O(NL), where N is the length of the string, and L is the length of longest dictionary word
 */

public class Work_Break {
	
	class Trie {
		Trie[] next = new Trie[26];
		boolean isword;
	}

	
	//	Solution 1 implementation
	public boolean wordBreak(String s, List<String> wordDict) {
		
		Trie root = new Trie();
		
		//	Creation of the Trie
		for (String str: wordDict) {
			Trie curr = root;
			for (char c: str.toCharArray() ) {
				if ( curr.next[c - 'a'] == null) {
					curr.next[c - 'a'] = new Trie();
				}
				curr = curr.next[c - 'a'];
			}
			curr.isword = true;
		}
		//-------------------------
		
		
		Deque<Trie> queue = new ArrayDeque<>();
		boolean isWord = true;
		
		for (char c: s.toCharArray() ) {
			
			if (isWord) {
				isWord = false;
				queue.offer(root);
			}
			
			int size = queue.size();
			for (int i = 0; i < size; i ++) {
				Trie poll = queue.poll();
				
				Trie next = poll.next[ c - 'a' ];
				if (next != null) {
					isWord = next.isword? true: isWord;
					queue.offer(next);
				}
			}
		}
		return isWord;
	}
	
	
	public boolean wordBreak2(String s, List<String> wordDict) {
		
		Trie root = new Trie();
		
		//	Reverse Trie Creation
		for (String str: wordDict) {
			Trie curr = root;
			
			for (int i = str.length() - 1; i >= 0; i -- ) {
				char c = str.charAt(i);
				if (curr.next[ c - 'a' ] == null) {
					curr.next[ c - 'a'] = new Trie();
				}
				curr = curr.next[ c - 'a' ];
			}
			curr.isword = true;
		}
		//-------------------------
		
		boolean[] dp = new boolean[s.length() + 1 ];
		dp[0] = true;
		
		for (int i = 0; i < s.length(); i ++ ) {
			Trie curr = root;
			boolean res = false;
			
			for (int j = i; j >= 0; j -- ) {
				curr = curr.next[ s.charAt(j) - 'a'];
				if (curr == null) break;
				
				System.out.println("Exist: " + s.charAt(j) );
				if (curr.isword) {
					res = dp[i]? true: res;
				}
			}
			
			dp[i + 1] = res;
			
		}
		
		return dp[ s.length() ];
	}
	
	
	
	
}
