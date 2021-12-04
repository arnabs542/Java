package Hard;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/stream-of-characters/

/*
 * 	This is a question which is very related to the data structure Trie.
 * 
 * 	Given an array of strings 'words' (Think of it as possible candidate of suffixes), and we will be provided with a
 * 	string character by character via query(). We have to return true whenever the string s formed by character added 
 * 	to query() will have a existing suffix in 'words'.
 * 	
 * 	-----------------------------------------------------------------------------------------
 * 
 * 	The basic intuition is that we will be constructing a trie out of the 'words' array. Then, each query will consist 
 * 	of a single letter only. We have to check if we combine all the letters provided in previous queries, will form a 
 * 	valid word in the trie.
 * 
 * 
 * 	The quick solution is that we can keep a Queue of pointers pointing to nodes in the Trie.
 * 	Every time we receive a query, we have to check 2 things:
 * 
 * 		>	In the queue, we need to poll every pointer to the node. If the node's next array does have the next letter specified,
 * 			we will offer the node's next matching letter node to the queue again to be checked in the next query
 * 
 * 			Note: We also have to check the next node retrieved to be a valid word or not. If yes, we set the flag to true, which
 * 				  will be returned later
 * 
 *		>	Aside from checking from the queue, we also have to start from the root everytime. Push the root node into the queue
 *			for every query too.
 *
 *	Example: words = ["abcdefg"]
 * 			queries = 'a', 'b', 'c', 'd', 'e'
 * 
 * 	Since the word "abcdefg" is constructed as trie, by the time we are given query('e'), we should have a set of pointers
 * 	pointing to nodes in trie:
 * 			pointers = { "a", "ab", "abc", "abcd", "abcde" }
 *
 *
 *	The problem with this solution is that the pointers may keep stacking up in the queue, which is both slow, and space consuming.
 *	
 *	==============================================================================================================
 *
 *	Instead of checking if every possible word of string s formed by queries forms a valid word in the trie, we 
 *	can reverse everything - String s and the trie. Then, we essentially only have to query the trie for valid prefix.
 *
 * 	Construct the trie in reverse (For each word in words, start from back to front). We will have a suffix trie.
 * 	Then, for each query, we store it as front of s instead of back, so we form an reversed string. 
 *
 *	We will store the past query letters in a Linked List like data structure, which we will always append the newest introduced
 *	character at the head of the linked list (So the string is reversed).
 *	With linked list, we can detect if the linked list had exceeded the maximum length of the string in the Trie, we will remove the
 *	tail of the linked list. Both inserting and removing are in Constant time
 *
 *	So when a query is introduced, we will be inserting the character into the linked list at the head. Then, since the linked list
 *	also stores any potential word in reversed order, we will use the Linked List's iterator to travel the linked list, while
 *	traversing the trie.
 *
 *	As long as we find out that the node doesn't exist (no matching node with the character), then we immediately return false.
 *	Else, if the node is find out to be forming a valid word already, then return true immediately.
 *	Else, it the traversal had finished, then just return false. No valid word is found
 *
 *	The worst time complexity of each query will be O(N) where N is the longest string in the Trie. However, we have limited
 *	the space complexity to just O(N) as well, since linked list will remove the oldest character once exceeded the possible
 *	length to query
 */



public class Stream_of_Characters {
	
	private class Node {
		boolean isWord;
		Node[] next;
		public Node() {
			isWord = false;
			next = new Node[26];
		}
	}
	
	
	private Node root;
	int limit = 0;
	List<Character> wordList;
	
	public Stream_of_Characters(String[] words) {
		root = new Node();
		wordList = new LinkedList<>();
		
		for (String s: words) {
			Node curr = root;
			limit = Math.max(s.length(), limit);
			
			for (int i = s.length() - 1; i >= 0; i -- ) {
				char c = s.charAt(i);
				if ( curr.next[c - 'a'] == null) 
					curr.next[c - 'a'] = new Node();
				curr = curr.next[c - 'a'];
			}
			
			curr.isWord = true;
		}
	}
	
	
	
    public boolean query(char letter) {
    
    	wordList.add(0, letter);
    	
    	if (wordList.size() > limit) {
    		wordList.remove( wordList.size() - 1);
    	}
    	
    	Iterator<Character> it = wordList.iterator();
    	Node curr = root;
    	
    	while (it.hasNext() ) {
    		char c = it.next();
    		Node next = curr.next[c - 'a'];
    		if (next == null) return false;
    		if (next.isWord) return true;
    		curr = next;
    	}
    	return false;
    }
	
}
