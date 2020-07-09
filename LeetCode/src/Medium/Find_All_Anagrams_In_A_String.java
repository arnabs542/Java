package Medium;

///https://leetcode.com/problems/find-all-anagrams-in-a-string/

/*
 * 	My first attempt was writing a helper method and keep the character count of anagram and string seperate, but this would mean everytime
 * 	i move the window, it has to run 26 iterations to check the character map one by one if it has difference (which means the substring is not anagram)
 * 
 * 	Instead, a solution is to keep a difference count and only using one hashmap / bucket
 * 	The bucket works like this:
 * 		-Negative = There is an unwanted character in the window
 * 		-0 		  = Exactly correct amount of this character in the window
 * 		-Positive = The character is needed in the window
 * 
 * 	-Eg : String: abcd, Anagram: abc
 * 	-Initially the map would be {b:1 c:1 d:1} and difference should be initialized to 3, the length of anagram (since window is still at size 0)
 * 	-Now we make the window, after that the map should be {a:-1 b:0 c:0 d:1} and difference is 2, 1 for the excess 'a' and 1 for absence of 'd'
 * 	-After the window moves to 'bcd', the map should become {a:0 b:0 c:0 d:0} and difference is 0. Whenever the difference is 0, that's the substring!
 */

import java.util.ArrayList;
import java.util.List;

public class Find_All_Anagrams_In_A_String {
	 public List<Integer> findAnagrams(String s, String p) {
		 if (s.length() < p.length() ) return new ArrayList<Integer>();
		 
		 List<Integer> solutions = new ArrayList<>();
		 int[] bucket = new int[26];
		 int difference = p.length();
		 
		 for (char c: p.toCharArray() ) {
			 bucket[c - 'a'] ++;
		 }
		 
		 for (int i = 0; i < p.length(); i ++ ) {
			 bucket[s.charAt(i) - 'a'] --;
			 if ( bucket[s.charAt(i) - 'a'] >= 0 )
				 difference --;
			 else 
				 difference ++;
		 }
		 if (difference == 0)
			 solutions.add(0);
		 
		 int remove = 0;
		 int add = p.length();
		 while (add < s.length() ) {
			 //Remove operation here
			 bucket[s.charAt(remove) - 'a'] ++;
			 //if the removed is actually valid
			 if (bucket[s.charAt(remove) - 'a'] > 0)
				 difference ++;
			 else
				 difference --;
			 remove ++;
			 
			 bucket[s.charAt(add) - 'a'] --;
			 if (bucket[s.charAt(add) - 'a'] >= 0)
				 difference --;
			 else
				 difference ++;
			 add++;
			 
			 if (difference == 0) solutions.add(remove);
		 }
		 
		 return solutions;
	 }
	 
	
}
