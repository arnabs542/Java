package security;

import java.util.Scanner;

/*
 * 	An alphabet of definitions is simply a set of alphabets/characters. For example,
 *  {0,1} is the binary alphabet while {0,1...,9} is digit alphabet
 *  
 *  Message space is a set of strings that composed of symbols from the alphabet of definition.
 *  Ciphertext space is also a set of strings that composed of symbols from the alphabet of definition.
 *  However, it might or might not differ from that of M after being encrypted.
 */


public class Security_Message_Space_and_Ciphertext_Space {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ori = scan.nextLine();
		
		StringBuilder sb = new StringBuilder(ori.length());
		
		for (char c: ori.toCharArray() ) {
			sb.append( (char)(c + 1 > '9'? '0': c+1));
		}
		System.out.println(sb.toString() );
		scan.close();
	}
}
