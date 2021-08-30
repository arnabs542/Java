package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Keyword_Transposition_Cipher {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		scan.nextLine();
		
		while (n-- > 0) {
			List<List<Character>> ciphers = new ArrayList<>();
			
			// Step 1: Obtain Secret
			String secret = scan.nextLine();
			String toDecode = scan.nextLine();
			
			// Step 2: Fill in the secret
			boolean[] isUsed = new boolean[26];
			for (char c: secret.toCharArray() ) {
				if (isUsed[c - 'A']) continue;
				
				List<Character> li = new ArrayList<>();
				li.add(c);
				ciphers.add( li );
				isUsed[c - 'A'] = true;
			}
			
			// Step 3: Fill remaining alphabets
			int index = 0;
			for (char c = 'A'; c <= 'Z'; ++c) {
				if ( isUsed[c - 'A'] ) continue;
				ciphers.get(index).add(c);
				
				index = (index + 1) % ciphers.size();
			}
			
			// Step 4: Sort by the first character of each column
			ciphers.sort( (x,y)-> x.get(0) - y.get(0));
			
			// Step 5: Build Mapper
			char curr = 'A';
			char[] mapper = new char[26];
			for (List<Character> l: ciphers) {
				for (char c: l)
					mapper[c - 'A'] = curr++;
			}
			
			// Step 6: Decode
			for (char c: toDecode.toCharArray()) {
				if (c == ' ') System.out.print(c);
				else System.out.print( mapper[c - 'A'] );
			}
			System.out.println();
		}
		scan.close();
	}
	
}
