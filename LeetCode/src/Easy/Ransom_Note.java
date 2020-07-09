package Easy;

import java.util.HashMap;

// Either use hash map, or use a bucket (since only assume lower-case letters, it is easy to dealt with)

public class Ransom_Note {
	public static boolean canConstruct(String ransomNote, String magazine) {
		int[] count = new int[26];
		for (char c: magazine.toCharArray() )
			count[(int)(c) - 97] ++;			//To deal with ASCII code, you can also say "c - 'a'" --->  count[c - 'a']
		for (char c: ransomNote.toCharArray() ) {
			if (count[(int)(c) - 97] == 0)
				return false;
			count[(int)(c) - 97] --;
		}
		return true;
    }
	
	public boolean canConstructMAP(String ransomNote, String magazine) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (char c: magazine.toCharArray() ) {
			map.putIfAbsent(c, 0);
			map.replace(c, map.get(c)+1 );
		}
		for (char c: ransomNote.toCharArray() ) {
			if (!map.containsKey(c) || map.get(c) == 0) 
				return false;
			map.replace(c, map.get(c)-1 );
		}
		return true;
    }
	
	public static void main(String[]args) {
		String rN = "abcdefggg";
		String mag = "abbccddefg";
		System.out.println( canConstruct(rN, mag));
	}
}
