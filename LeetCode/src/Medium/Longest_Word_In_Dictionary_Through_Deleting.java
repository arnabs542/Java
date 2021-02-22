package Medium;

import java.util.LinkedList;
import java.util.List;

//	https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
/*
 * 	This is a String, sorting problem, or Queue, Trie-like idea problem.
 * 
 * 	The real brute force way to solve this is to generate all the possible subsequences of string s
 * 	and check if that subsequence is in dictionary. Of course, this would not pass the test cases.
 * 
 * 	-------------------------------------------------------------
 * 
 * 	Instead of finding all subsequences, one might come up with a O(N^2) solution by checking each word
 * 	in dictionary. FOr each word in dictionary, iterate through s to see if the word is indeed a subsequence.
 * 	Actual time complexity is actually O(NM), N is number of words in dictionary, M is length of S, or average length
 * 	of the word, depends.
 * 
 * 	Now, to make earlier terminations, one can sort the dictionary words first, so that we check the longest one first.
 * 	If same length, sort by lexicographically. However in the worst case, time complexity still doesn't change
 * 
 * 	--------------------------------------------------------------
 * 
 * 	One solution that derives from Trie concept is, since all are lowercase letters, we will have 26 buckets, containing
 * 	'progresses' of words in the dictionary. All the buckets will contain a Pair of <Word, position>. Each bucket represents
 * 	a character, from a to z, and it contains all the searches that need to be continued.
 * 
 * 	Say we have dictionary word 'abe'. We are looking for 'a' first, so put the index and position 0 into the bucket 'a'.
 *  So, when iterating string s, if met 'a', then we take it out from bucket 'a', increment position, and put into next 
 *  character bucket 'b'. If later we met 'b', then take it out, increment position, and put in bucket 'e'. If once again
 *  'e' is found, then we know whole word is found, only then we update the result.
 *  
 *  Here we use O(N) space where N is number of words. Each word we only storing a pair of <word index, position>
 *  Time complexity however, is also O(NM) as above. For each char in string s, we potentially has to iterate through
 *  all the words in dictionary if all matches!
 */

public class Longest_Word_In_Dictionary_Through_Deleting {
	
	public String findLongestWord(String s, List<String> d) {
        final int len = d.size();
		LinkedList<int[]>[] pending = new LinkedList[26]; 
		for (int i = 0; i < 26; ++i) pending[i] = new LinkedList<>();
		String res = "";
		
		//	Initialize all the dictionary words
		for (int i = 0; i < len; ++i)
			pending[ d.get(i).charAt(0) - 'a' ].addLast( new int[] {i, 0} );
		
		//	Now iterate through the String s. All the pendings will be updated
		for (char c: s.toCharArray() ) {
			LinkedList<int[]> list = pending[c - 'a'];
			
			for (int i = list.size(); i > 0; --i) {
				int[] pop = list.removeFirst();
				String word = d.get(pop[0]);
				if (++pop[1] >= word.length() ) {			// Now the word is complete. Update res
					//	Update only if same length but lexicographically smaller, or length longer
					if ( (word.length() == res.length() && word.compareTo(res) < 0) || word.length() > res.length() ) 
						res = word;
				}
				//	Otherwise the word is not yet complete. Push it to respective pending
				else {
					pending[ word.charAt(pop[1]) - 'a' ].add(pop);
				}
			}
		}
		
		return res;
    }
	
}
