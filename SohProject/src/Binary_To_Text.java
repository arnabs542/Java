import java.util.regex.Pattern;
import java.util.Scanner;

public class Binary_To_Text {

	public static char binStrToChar(String binary) {
		if (!Pattern.matches("[01]{8}", binary) ) throw new IllegalArgumentException("This is not a valid binary string!");
		int value = 0;
		for (int i = 0; i < binary.length(); i ++) {
			value += (binary.charAt(i) == '1')? Math.pow(2, 7 - i): 0;
		}
		return (char) value;
	}
	
	public static String toASCII() {
		System.out.println("Repeatedly type in each binary string. Input anything else to return the string");
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; true; i ++ ) {
			System.out.printf("#%d: ", i);
			try {
				sb.append( binStrToChar(scan.next() ) );
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		return sb.toString();
	}
	
	public static String toASCII(String fullBinaryStr) {
		String[] arr = fullBinaryStr.split(" ");
		StringBuilder sb = new StringBuilder();
		
		for (String s: arr) {
			sb.append( binStrToChar(s) );
		}
		return sb.toString();
	}
	

	public static void main(String[]args) {
		System.out.println(toASCII() );
	}
}
