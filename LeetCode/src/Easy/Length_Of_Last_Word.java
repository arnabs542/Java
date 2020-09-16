package Easy;

// https://leetcode.com/problems/length-of-last-word/

public class Length_Of_Last_Word {
	
	
	//	Library solution
	
//	public int lengthOfLastWord(String s) {
//		String[] arr = s.split(" ");
//		return arr[ arr.length - 1 ].length();
//	}

	public int lengthOfLastWord(String s) {
		int len = 0;
		int idx = s.length() - 1;
		
		while (idx >= 0 && s.charAt(idx) == ' ') {
			idx --;
		}
		
		while (idx >= 0 && s.charAt(idx) != ' ') {
			idx --;
			len ++;
		}
		
		return len;
	}
	
	
	
	
}
