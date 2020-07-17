package easy;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Password_Decryption {
	
	public static String decryptPassword(String s) {
		StringBuilder sb = new StringBuilder(s);
		int len = s.length();
		
		int digitIdx = 0;
		char nextDigit = sb.charAt(digitIdx);
		
		for (int i = len - 1; i > digitIdx; i -- ) {
			if ( sb.charAt(i) == '0' && Character.isDigit(nextDigit) ) {
				sb.setCharAt(i, nextDigit);
				nextDigit = sb.charAt(++digitIdx);
			}
		}
		
		for (int i = 0; i < len - 2; i ++ ) {
			if ( Character.isUpperCase(sb.charAt(i) ) 
					&& Character.isLowerCase(sb.charAt(i + 1) ) 
					&& sb.charAt(i + 2) == '*') {
				char temp = sb.charAt(i + 1);
				sb.setCharAt(i + 1, sb.charAt(i) );
				sb.setCharAt(i , temp);
				sb.setCharAt(i + 2, ' ');
			}
		}
		
		return sb.substring(digitIdx).replaceAll(" ", "");
		
	}
//		Pattern upperLower = Pattern.compile("[A-Z][a-z]\\*");
//		Matcher mat = upperLower.matcher(s);
//		
//		System.out.println(mat.find() );
//		System.out.println( mat.start() );
//		mat.
//		
//		return null;
		
	
	public static void main(String[]args) {
		System.out.println( decryptPassword("pTo*Ta*o") );
	}
}
