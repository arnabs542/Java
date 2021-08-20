package security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Security_Bijective_Functions {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String n = scan.nextLine();
		String[] v = scan.nextLine().split(" ");
		
		System.out.println(Arrays.toString(v));
		
		// Brute force - For each element, check if seen previously
		for (int i = 0; i < v.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (v[i].equals(v[j])) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
	}
	
	
	// Optimized solution - If N is big, use Set to check if previous occurred?
	void security_bijective_functions() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Set<Integer> set = new HashSet<>();
		
		for (int i = 0; i < n; ++i) {
			if (!set.add( scan.nextInt() )) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
