package Easy;

public class Increasing_Decreasing_String {
	public String sortString(String s) {
		StringBuilder sb = new StringBuilder();
		int[] charCount = new int[26];
		int charLeft = s.length();
		boolean reverse = false;
		
		for (char c: s.toCharArray() ) 
			charCount[c - 'a'] ++;
		
		while (charLeft > 0) {
			if (!reverse) {
				reverse = true;
				
				for (int i = 0; i < 26; i ++ ) {
					if (charCount[i] > 0) {
						sb.append((char)(i + 'a'));
						charCount[i] --;
						charLeft --;
					}
				}
			}
			else {
				reverse = false;
				for (int i = 25; i >= 0; i-- ) {
					if (charCount[i] > 0) {
						sb.append((char)(i + 'a'));
						charCount[i] --;
						charLeft --;
					}
				}
			}
			
		}
		return sb.toString();
    }
}
