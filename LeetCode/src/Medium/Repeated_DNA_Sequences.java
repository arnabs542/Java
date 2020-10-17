package Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/repeated-dna-sequences/

/*
 * 	THis is s String Matching, HashTable, Rolling Hash problem, Similar to RABIN KARP ALGORITHM
 * 	
 * 	What we basically can do is to have a sliding window of size 10, iterate in one pass through the entire string.
 * 	In the process, we record the state of the string in the sliding window so that when we encounter it again, we will
 * 	immediately know that the substring is met before.
 * 	
 * 	We can store the substring directly in a HashSet or HashMap. However, note that if we were to always copy the string
 * 	again and again (No using sliding window), it will take almost N * 10 time. Is there more efficient?
 * 
 * 	One way is to represent the state of the string using number, converting it into a Hash code using rolling hash
 * 	technique. We can make it so every Decimal (Base 10) place represents the actual alphabet itself.
	 * 	A - 1
	 * 	B - 2
	 * 	C - 3
	 * 	D - 4
 * 	Note that we cannot use 0. Because 0 * 10 = 0. We are unable to shift it to left side
 * 
 * 	With this, String like "ABCDABCDAB" shall have hashcode as 1234123412. This is very easy to manipulate in sliding window.
 * 	We just have to minus the largest places, multiply 10 to shift left, then add again the newest character to update
 * 	hashcode.
 * 
 * 	----------------------------------------------------------------------------------------------------------
 * 
 * 	Using technique above will result in largest integer as 4444444444, which is out of 32 bit integer's range.
 * 	Notice that we have 4 possibilities. THis means when seen in bit, it takes only 2 bit to represent the states!
 * 		00	-	A
 * 		01	-	C
 * 		10	-	T
 * 		11	-	G
 * 
 * 	Now we have to use bit manipulation to alter and update the sliding window, however, using 32 bit integer to record
 * 	rolling hash is much more efficient, and space saving!
 */

public class Repeated_DNA_Sequences {
	
	static Map<Character, Integer> hashValues = new HashMap<>();
	static {
		hashValues.put('A', 1);
		hashValues.put('C', 2);
		hashValues.put('G', 3);
		hashValues.put('T', 4);
	}
	
	public List<String> findRepeatedDnaSequences(String s) {
		int len = s.length();
		List<String> li = new LinkedList<>();
		if (len < 10) return li;
		
		long encode = 0;
		Map<Long, Boolean> hashes = new HashMap<>();
		
		//	Initialize sliding window
		for (int i = 0; i < 10; i ++ ) {
			encode = encode * 10l + hashValues.get( s.charAt(i) );
		}
		hashes.put(encode , false);
		
		for (int i = 10; i < len; i ++ ) {
			encode = encode - hashValues.get( s.charAt(i - 10) ) * 1000000000l;
			encode = encode * 10l + hashValues.get( s.charAt(i) );
			
			Boolean bool = hashes.getOrDefault( encode , null);
			if ( bool == null) {
				hashes.put( encode , false );
			} else if ( !bool ) {
				li.add( decode(encode) );
				hashes.put( encode, true );
			}
		}
		
		return li;
	}
	
	private static String decode(long hash) {
		StringBuilder sb = new StringBuilder(10);
		
		for (int i = 0; i < 10; i ++ ) {
			long code = hash % 10;
			hash = hash / 10;
			
			if (code == 1) sb.append('A');
			else if (code == 2) sb.append('C');
			else if (code == 3) sb.append('G');
			else sb.append('T');
		}
		
		return sb.reverse().toString();
	}
	
	
	
	
	
	
	//==============================================================================
	// BIT MANIP SOLUTION =========================================================
	//==============================================================================
	
	static Map<Character, Integer> hashValues2 = new HashMap<>();
	static {
		hashValues2.put('A', 0);
		hashValues2.put('C', 1);
		hashValues2.put('G', 2);
		hashValues2.put('T', 3);
	}
	
	public List<String> findRepeatedDnaSequences2(String s) {
		int len = s.length();
		int mask = 1048575;		//	The first 20 bit is 1, other is 0.
		List<String> result = new LinkedList<>();
		if (len < 10 ) return result;
		
		int hash = 0;
		Map<Integer, Boolean> visited = new HashMap<>();
		
		for (int i = 0; i < 10; i ++ ) {
			hash <<= 2;
			hash += hashValues2.get( s.charAt(i) );
		}
		visited.put(hash, false);
		
		for (int i = 10; i < len; i ++ ) {
			hash <<= 2;
			hash += hashValues2.get( s.charAt(i) );
			hash &= mask;	//	Get rid of overflowed bits
			
			Boolean bool = visited.getOrDefault( hash , null );
			
			if (bool == null) {
				visited.put( hash , false);
			} else if (!bool) {
				visited.put( hash, true );
				result.add( decode2(hash) );
			}
		}
		
		return result;
		
	}	
	
	private static String decode2( int hash ) {
		int mask = 3;	//	Binary representation - 11. Use to get last 2 digit
		StringBuilder sb = new StringBuilder(10);
		
		for (int i = 0; i < 10; i ++ ) {
			int get = hash & mask;
			if (get == 0) sb.append('A');
			else if (get == 1) sb.append('C');
			else if (get == 2) sb.append('G');
			else sb.append('T');
			
			hash >>>= 2;		//Shift right by 2 places.
		}
		
		return sb.reverse().toString();
	}
}
