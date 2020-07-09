package Medium;

import java.util.Stack;
import java.util.regex.Pattern;

//https://leetcode.com/problems/remove-k-digits/

public class Remove_k_Digits {

	public static String removeKdigits(String num, int k) {
		StringBuilder sb = new StringBuilder(num);
		
		while (k > 0) {
			int i = 0;
			while (i < sb.length() -1 && sb.charAt(i) <= sb.charAt(i+1) ) {
				i++;
			}
			sb.deleteCharAt(i);
			k--;
		}
		
		while (sb.charAt(0) == '0' && sb.length() > 1) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
    }
	
	public static void main(String[]args) {
		System.out.println(removeKdigits("10012", 1) );
	}
}
