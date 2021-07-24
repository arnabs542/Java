package Hard;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//https://leetcode.com/problems/word-ladder-ii/
/*
 * 	This is a HARD to implement Graph, BFS, Backtracking problem.
 * 
 * 	First of all, once you realize this is a graph problem, you may be tempted to immediately build the graph. A node
 *  containing a string is connected with another node if they differ by one character only.
 *  We can do this by permutating the string character by character, since the string is small.
 *  
 *  However, we have to think once the graph is built, how are we going to retrieve all the shortest paths? BFS will do,
 *  but do we need to maintain a list of paths and at the end, pick up all the ones that are valid? It can be inefficient.
 *  
 *  Instead, we build the graph using BFS. We intelligently build the graph that contain the property:
 *  	>	No nodes will connect back to upper layer in BFS
 *  	>	No nodes will interconnect with other nodes in same layer
 *  This way, once the graph is built, we can use backtracking to retrieve the path without costing too much on the performance.
 */

public class Word_Ladder_II {
	
	int len;
	String endWord;
	List<List<String>> res;
	Map<String, List<String>> graph;
	Set<String> availableNodes;
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		len = wordList.size();
		this.endWord = endWord;
		res = new ArrayList<>();
		graph = new HashMap<>();
		availableNodes = new HashSet<>(wordList);		// Set with all wordlist available in it
		
		// Build the graph using BFS. If return false, means cannot reach endWord given beginWord
		if (!bfsBuildGraph(beginWord)) return res;
		
		// Perform backtracking on the graph
		backtrack(beginWord, new ArrayList<>( Arrays.asList(beginWord) ));
		return res;
    }
	
	// Build a graph using BFS, given the beginning word
	// The graph is guaranteed to not contain a cycle, and nodes in same layer does not interconnect
	// To optimize, stop when the layer contains the endString already. No need to further extend
	private boolean bfsBuildGraph(String beginWord) {
		Queue<String> bfs = new ArrayDeque<>();
		bfs.add(beginWord);
		boolean endInLayer = false;
		
		while (!bfs.isEmpty() && !endInLayer) {
			// Store all the nodes in current layer in a Set (No nodes will connect between same layers)
			Set<String> currLayer = new HashSet<>(bfs);
			bfs.clear();

			for (String s: currLayer) {
				graph.put(s, new ArrayList<>());
				availableNodes.remove(s);
				
				// Generate available permutations
				for (int i = 0; i < s.length(); ++i) {
					for (char c = 'a'; c <= 'z'; ++c) {
						String perm = s.substring(0, i) + c + s.substring(i+1);
						
						if (availableNodes.contains(perm) && !currLayer.contains(perm)) {
							if (perm.equals(endWord)) endInLayer = true;	// Already found end
							graph.get(s).add(perm);
							bfs.offer(perm);
						}
					}
				}
				
			}
		}
		return endInLayer;		// Whether we have traversed until found endWord?
	}
	
	// Backtracking on the graph
	private void backtrack(String currWord, List<String> progress) {
		if (currWord.equals(endWord)) {
			res.add(new ArrayList<>(progress));
			return;
		}
		
		// A leaf node which does not lead to endWord.
		if (!graph.containsKey(currWord)) return;
		
		for (String next: graph.get(currWord)) {
			progress.add(next);
			backtrack(next, progress);
			progress.remove( progress.size() - 1);
		}
	}
	
}
