package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/word-search-ii/

/*
 * 	For this question, it is inevitable the need to iterate through every single character in the grid. However, having to loop through every searched word for each
 * 	character is more time consuming. Is there any way to easily get access to the characters and next characters in constant time? Trie/Prefix Tree is your solution.
 * 
 * 	The Trie is constructed as follows:
 * 		-There will be a root node where itself is not actually representing a character, but contains a Map to instantly find any word starting character in constant time
 * 		-At the end of a word, there may be still more characters to connect in HashMap (Imagine 'sat' and 'saturate'). Therefore we will kept a additional String variable
 * 		 in the TrieNode object. If it is null, then it is not a full word, else it stores the complete word
 * 
 * 	
 */

public class Word_Search_II {
	
	static class TrieNode {
		HashMap<Character, TrieNode> map;
		String word;
		public TrieNode() {
			map = new HashMap<>();
		}
	}
	
	public static List<String> findWords(char[][] board, String[] words) {
		
		TrieNode root = createNodes(words);
		Set<String> result = new HashSet<>();
		
		for (int i = 0; i < board.length; i ++ ) {
			for (int j = 0; j < board[0].length; j ++ ) {
				char c = board[i][j];
				if (root.map.containsKey(c) ) {
					System.out.println("Found " + c);
					 Set<String> res = DFS(board, root.map.get(c), i, j );
					 result.addAll(res);
				}
			}
		}
		
		return new ArrayList<>(result);
	}
	
	private static Set<String> DFS(char[][]board, TrieNode node, int row, int col) {
		Set<String> result = new HashSet<>();
		
		if (board[row][col] == '*') return result;
		
		if (node.word != null) result.add(node.word);
		
		char toUndo = board[row][col];
		board[row][col] = '*';
		
		if (row - 1 >= 0 && node.map.containsKey( board[row-1][col] ) )
			result.addAll( DFS(board, node.map.get(board[row-1][col]), row - 1, col ) );
		if (row + 1 < board.length && node.map.containsKey( board[row+1][col] ) )
			result.addAll( DFS(board, node.map.get(board[row+1][col]), row + 1, col) );
		if (col - 1 >= 0 && node.map.containsKey( board[row][col-1] ) )
			result.addAll( DFS(board, node.map.get(board[row][col-1]), row, col - 1) );
		if (col + 1 < board[0].length && node.map.containsKey(board[row][col+1] ) )
			result.addAll( DFS(board, node.map.get(board[row][col+1]), row, col + 1) );
		
		board[row][col] = toUndo;
		
		return result;
	}
	
	//Create a Trie using the array of strings. Will return back the root of the trie. Every node in the trie has a HashMap to find the next character, and a String word
	//Which will be null if it isn't a full word, and will be the full word itself if it has reached the full word
	private static TrieNode createNodes(String[]words) {
		TrieNode root = new TrieNode();
		
		for (String s: words) {
			TrieNode curr = root;
			for (char c: s.toCharArray() ) {
				curr.map.putIfAbsent(c, new TrieNode() );
				curr = curr.map.get(c);
			}
			curr.word = s;
		}
		
		return root;
	}
	
	public static void main(String[]args) {
	}
}
