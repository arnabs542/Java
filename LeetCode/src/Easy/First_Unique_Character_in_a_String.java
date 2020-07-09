package Easy;

//https://leetcode.com/problems/first-unique-character-in-a-string/

/*
 * Since constraints stated of only lowercase letters, thus bucket can be used.
 * Otherwise, it's wiser to use hashmap or similar alternatives
 */

public class First_Unique_Character_in_a_String {
	 public static int firstUniqChar(String s) {
		 int[] characters = new int[26];
		 for (int i = 0; i < s.length(); i ++ ) {
			 characters[s.charAt(i) - 'a'] ++;
		 }
		 for (int i = 0; i < s.length(); i ++ ) {
			 if (characters[s.charAt(i) - 'a'] == 1) return i;
		 }
		 return -1;
	 }
	 
	 public static void main(String[]args) {
		 System.out.println( firstUniqChar("duashdhuahud"));
	 }
}
