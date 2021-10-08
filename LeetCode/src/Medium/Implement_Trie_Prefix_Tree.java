package Medium;

//https://leetcode.com/problems/implement-trie-prefix-tree/
// For more details, look Topics/src/Data_Structures/Trie.java

public class Implement_Trie_Prefix_Tree {}


class Trie {
	
	TrieNode root;
	
	class TrieNode {
		TrieNode[] next = new TrieNode[26];
		boolean isWord = false;
	}
	
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
        	if (curr.next[c - 'a'] == null)
        		curr.next[c - 'a'] = new TrieNode();
        	curr = curr.next[c - 'a'];
        }
        curr.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
        	if (curr.next[c - 'a'] == null) return false;
        	curr = curr.next[c - 'a'];
        }
        return curr.isWord;
    }
    
    public boolean startsWith(String prefix) {
    	TrieNode curr = root;
        for (char c: prefix.toCharArray()) {
        	if (curr.next[c - 'a'] == null) return false;
        	curr = curr.next[c - 'a'];
        }
        return true;
    }
}