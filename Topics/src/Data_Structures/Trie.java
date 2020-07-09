package Data_Structures;

import java.util.HashMap;

/*
 * 	A Trie (Aka Prefix Tree) is a tree like data structure, but is commonly used for storing a collection of string instead
 * 	(Imagine how google auto completes your search!, or spell-checker detects a misspelled word and suggests correct word!)
 * 
 *  Each Trie node has 2 attributes: 
 *  	- Either an Array or Map that maps a character to another trie node
 *  	- An flag (indicator) of isWord marking if the previous collection of character are a valid word
 */

public class Trie {
	
	private TrieNode root;
	
	private class TrieNode {
		HashMap<Character, TrieNode> map = new HashMap<>();
		boolean isWord = false;
		
	}
	
	public Trie() {
		root = new TrieNode();
	}
	
	//Insert a new word into the trie. Remember the operations:
	/*			1. start out with the root as the current node and iterate through the characters of the string
	 * 			2. if the map of current node contains the character, then update current node to refer to that next node, else
	 * 			   you have to create a new node, put a character c in it, and update current node to refer to that new node.
	 * 			   Repeat until the characters is finished iteration
	 * 			3. Lastly after you have finished the iteration of string, take the last node reference and make the flag isWord = true
	 */
	public void insert(String str) {
		TrieNode node = root;
		
		for (char c: str.toCharArray() ) {
			if (!node.map.containsKey(c) ) {
				TrieNode newNode = new TrieNode();
				node.map.put(c, newNode );
				node = newNode;
			}
			else {
				node = node.map.get(c);
			}
		}
		node.isWord = true;
	}
	
	/*
	 * 	Only need to find the sequence of characters is present in the trie. As long as you didn't find the matching character in the trie,
	 * 	return false.
	 * 	If it successfully iterated through the string without returning false, yes It is present in the trie (but may not be a valid word!)
	 */
	public boolean startsWith(String str) {
		TrieNode node = root;
		
		for (char c: str.toCharArray() ) {
			if (!node.map.containsKey(c) ) return false;
			node = node.map.get(c);
		}
		return true;
	}
	
	/*
	 * 	Searches if a string is a VALID WORD in the trie. Iterate through the string while checking if the map of current node contains the 
	 * 	character. Return false as soon as it finds nothing. After iterating through the string, you still have to check if the word is actually
	 * 	a valid word. This is done by returning the isWord boolean flag.
	 */
	public boolean search(String str) {
		TrieNode node = root;
		
		for (char c: str.toCharArray() ) {
			if (!node.map.containsKey(c) ) return false;
			node = node.map.get(c);
		}
		return node.isWord;
	}
	
	public static void main(String[]args) {
		Trie t = new Trie();
		t.insert("apple");
		System.out.println(t.search("") );
		System.out.println(t.startsWith(""));
	}

}
