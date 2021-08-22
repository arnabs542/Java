package security;

import java.util.Scanner;

public class Security_Function_Inverses {
	
	// We can record 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		// Use a Map to map f(x) back into x. In this case an array is sufficient
		int[] inverse = new int[n+1];
		
		for (int i = 1; i <= n; ++i)
			inverse[ scan.nextInt() ] = i;
		
		// Output
		for (int i = 1; i <= n; ++i) 
			System.out.println( inverse[i] );
		
		scan.close();
	}
}
