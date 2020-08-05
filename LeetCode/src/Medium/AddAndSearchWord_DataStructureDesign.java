package Medium;

//https://leetcode.com/problems/add-and-search-word-data-structure-design/

/*
 * 	A Trie problem which when faced with a wildcard character '.', we have to attempt for depth first search
 */

public class AddAndSearchWord_DataStructureDesign {
	
	private class Node {
		boolean isWord = false;
		Node[] map = new Node[26];
	}
	
	private Node root;
	
    public AddAndSearchWord_DataStructureDesign() {
    	root = new Node();
    }
    
    public void addWord(String word) {
    	Node curr = root;
    	
    	for (char i: word.toCharArray() ) {
    		int idx = i - 'a';
    		if ( curr.map[idx] == null)
    			curr.map[idx] = new Node();
    		curr = curr.map[idx];
    	}
    	curr.isWord = true;
    }
    
    public boolean search(String word) {
    	return searchFrom(word, 0, root);
    }
    
    private boolean searchFrom(String word, int idx, Node node) {
    	if (node == null) return false;
    	
    	for (int i = idx; i < word.length(); i ++ ) {
    		int ascii = word.charAt(i) - 'a';
    		if ( ascii == '.' - 'a' ) {
    			for (Node allNodes: node.map ) {
    				if (searchFrom(word, i+1, allNodes ) ) return true;
    			}
    			return false;
    		}
    		else {
    			if (node.map[ascii] == null) return false;
    			
    			node = node.map[ascii];
    		}
    	}
    	return node.isWord;
    	
    }
}
