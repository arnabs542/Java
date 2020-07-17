package easy;


public class Vowel_Substring {
	
	public static String findSubstring(String s, int k) {
		int len = s.length();
		
		int maxvowel = 0;
		String res = "";
		
		for (int i = 0; i < k; i ++ ) {
			if ( isVowel(s.charAt(i) ) ) {
				maxvowel ++;
			}
		}
		res = s.substring(0, k);
		
		int window = maxvowel;
		for (int i = k; i < len; i ++ ) {
			if (maxvowel == k) break;
			
			window += isVowel(s.charAt(i) )? 1: 0;
			window -= isVowel(s.charAt(i - k) )? 1: 0;
			
			if (window > maxvowel) {
				maxvowel = window;
				res = s.substring(i - k + 1, i + 1);
			}
		}
		
		return (maxvowel == 0)? "Not found!": res;
	}
	
	private static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
