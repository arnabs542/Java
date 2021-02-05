package Algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 	Huffman Coding Algorithm is an Greedy based Algorithm for Data compression.
 * 
 * 	Say we have a string "ABABABBCDEA" to be transferred. If we send it in ASCII code, each one of them
 * 	will take up 8 bit space. Can we send each character in less number of bits?
 * 
 * 	We could attempt fixed size encoding. Since the string has only ABCDE, so we need enough bits to represent
 *  5 states, which is 3 bit.
 *  With this, every bit shares equally 3 bits to be transferred, which is quite nice. However, we can do even more!
 *  
 *  Introducing Huffman Coding Algorithm! AKA Variable length encoding algorithm.
 *  The idea goes as follows:
 *  
 *  	>	If a character appear many times, we could want the encoded to take as less bit as possible!
 *  	>	As for character that appear few times only, it can take more bit compared to the one that appear many times
 *  
 *  Therefore you can see the greedy part here.
 *  
 *  --------------------------------------------------------------
 *  
 *  First, we need a frequency table to know how many times each character appear (Sometimes also represented in
 *  probabilities which character appear)
 *  Then, we construct what's called Huffman Tree. The tree is constructed as follows:
 *  	>	Sort the characters based on frequency
 *  	>	Pop the minimum 2, combine them with a parent node which carries a value of the sum of the frequency
 *  		of 2 characters popped.
 *  	>	Put the parent node back into the heap
 *  	>	Repeat until 1 node left in heap, which is the root node
 *  
 *  A tree will be constructed. How to know the encoding of each character?
 *  	>	If we go left subtree, it is '0'
 *  	>	If we go right subtree, it is '1'
 *  
 *  --------------------------------------------------------------
 *  
 *  The tree is easily used when decoding. Starting from root node, reach each encoded bit. If 0, go left, else go right.
 *  If reached at a character, simply get the character to decode it.
 *  
 *  This algorithm ensures to have no ambiguity, because no characters encoding will have the same prefix!
 */

public class Huffman_Coding_Algorithm {
	
	static class Node {
		char c;
		int netFreq;
		Node left, right;
		public Node(int netFreq) { this.netFreq = netFreq; }
		public Node(char c, int netFreq) { this.netFreq = netFreq; this.c = c; }
	}
	
	static Map<Character, Integer> getFreqTable(String s) {
		Map<Character, Integer> freq = new HashMap<>();
		for (char c: s.toCharArray() ) 
			freq.compute(c, (k, v)-> v == null? 1: v+1 );
		return freq;
	}
	
	
	static Node generateHuffmanTree( Map<Character, Integer> freq ) {
		PriorityQueue<Node> heap = new PriorityQueue<>( (x,y)-> x.netFreq - y.netFreq );
		
		freq.forEach( (k,v)-> heap.add( new Node(k, v) ) );
		
		while (heap.size() > 1) {
			Node first = heap.poll();
			Node second = heap.poll();
			Node parent = new Node( first.netFreq + second.netFreq );
			parent.left = first;
			parent.right = second;
			heap.add(parent);
		}
		
		return heap.poll();
	}
	
	
	//	Left side is 0, Right side is 1
	static Map<Character, String> getMappingFromHuffmanTree(Node root) {
		StringBuilder sb = new StringBuilder();
		Map<Character, String> map = new HashMap<>();
		
		dfs(root, sb, map);
		return map;
	}
	private static void dfs(Node node, StringBuilder sb, Map<Character,String> map) {
		if (node == null) return;
		
		if (node.c != '\0') 
			map.put( node.c , sb.toString() );
		else {
			sb.append('0');
			dfs(node.left, sb, map);
			sb.deleteCharAt( sb.length() - 1 );
			
			sb.append('1');
			dfs(node.right, sb, map);
			sb.deleteCharAt( sb.length() - 1 );
		}
	}
	
	
	//	Note that String still uses 1 byte to store each characters 
	static String encodeHuffman(String s, Map<Character,String> map) {
		StringBuilder sb = new StringBuilder();
		for (char c: s.toCharArray() ) sb.append( map.get(c) );
		return sb.toString();
	}
	
	
	static String decodeHuffman(Node root, String encoded) {
		if (encoded.isEmpty() && root != null) return Character.toString(root.c);
		
		Node curr = root;
		StringBuilder res = new StringBuilder();
		
		for (char c: encoded.toCharArray() ) {
			if (c == '0') curr = curr.left;
			else curr = curr.right;
			
			if (curr.c != '\0') {
				res.append(curr.c);
				curr = root;
			}
		}
		
		return res.toString();
	}
	
	
	
	public static void main(String[]args) {
		String msg = "ada";
		Map<Character, Integer> freq = getFreqTable(msg);
		Node root = generateHuffmanTree(freq);
		Map<Character, String> map = getMappingFromHuffmanTree(root);
		String encoded = encodeHuffman(msg, map);
		String decoded = decodeHuffman(root, encoded);
		
		System.out.println("msg: " + msg);
		System.out.println("freq: " + freq);
		System.out.println("map: " + map);
		System.out.println("encoded: " + encoded);
		System.out.println("decoded: " + decoded);
	}

}
