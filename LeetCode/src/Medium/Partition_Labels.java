package Medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/partition-labels/


/*
 * 	This is a Greedy question.
 * 	We have to partition the strings (Meaning to split the strings, no rearranging), such that each part's character cannot occur
 * 	in another part.
 * 
 * 	For me, we need to get more information regarding the string itself. Therefore, here I obtained the frequency of each character
 * 	in String S, at most 26 lowercase letters.
 * 
 * 	Now, I will also have a Set, where the characters included in the current partition are put inside it.
 * 	
 * 	So the algorithm goes as this:
 * 
 * 		For each character in S:
 * 			if the character is not present in Set, add it
 * 			Since we used this current character, decrement the frequency count in the freq table
 * 			If the character in freq table hit 0, means we exhausted this character. At this point, remove the character from the
 * 			Set
 * 			Now, if the set is empty (Means a valid partition is formed), add to the result the length of the partition
 * 
 * 
 * 
 * 	================================================================
 * 
 * 	Instead of using the frequency of each character, we can also use the Last index of occurrence of this character to
 * 	solve the problem.
 * 
 * 	For each character:
 * 		If we have reached the current partition's last length for partitioning, then add the result this length of partition
 * 		Otherwise, find the last occurrence of this current character.
 * 		We will keep a variable to keep track of the largest index of the last character included in the current partition.
 * 		If it is larger, then we overwrite it.
 * 	
 * 
 */


public class Partition_Labels {

	public static List<Integer> partitionLabels(String S) {
        
		int[] freq = freqChar(S);
		Set<Integer> included = new HashSet<>();
		
		int len = 0;
		List<Integer> res = new LinkedList<>();
		
		for (char c: S.toCharArray() ) {
			len ++;
			int ascii = c - 'a';
			if ( !included.contains( ascii ) ) {
				included.add( ascii );
			}
			
			if ( --freq[ascii] == 0 ) {
				included.remove( ascii );
			}
			
			if ( included.isEmpty() ) {
				res.add(len);
				len = 0;
			}
		}
		
		return res;
    }
	
	private static int[] freqChar( String S ) {
		int[] freq = new int[26];
		
		for (char c: S.toCharArray() ) {
			freq[c - 'a'] ++;
		}
		
		return freq;
	}
	
	
	
	public static void main(String[]args) {
		partitionLabels("ababcbacadefegdehijhklij");
	}
}
