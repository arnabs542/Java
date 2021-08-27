package security;

import java.util.Scanner;

public class Security_Key_Spaces {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		int e = scan.nextInt();
		
		for (char c: line.toCharArray() ) {
			char shifted = (char)( ( c - '0' + e ) % 10 + '0');
			System.out.print(shifted);
		}
		scan.close();
	}
}
