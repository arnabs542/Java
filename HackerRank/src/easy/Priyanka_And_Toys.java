package easy;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/priyanka-and-toys/problem

public class Priyanka_And_Toys {
	
	static int toys(int[] w) {
		int container = 0;
		int bound = Integer.MIN_VALUE;
		
		Arrays.sort(w);
		
		for (int i: w) {
			if (i > bound ) {
				container ++;
				bound = i + 4;
			}
		}
		
		return container;
		
    }
}
