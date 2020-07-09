package Medium;

//https://leetcode.com/problems/counting-bits/

/*
 * 	We have to identify the pattern with this question, and it is kind of a dynamic programming problem since we're using the computed
 * 	result to produce new results
 */

public class Counting_Bits {

	public static int[] countBits(int num) {
        if (num == 0) return new int[] {0};
        int[] sol = new int[num+1];
        sol[1] = 1;
        
        int multiplier = 2;
        try {
	        while (multiplier <= num) {
	        	helper(sol, multiplier);
	        	multiplier *= 2;
	        }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return sol;
    }
	
	public static void helper(int[] arr, int n) throws ArrayIndexOutOfBoundsException{
		int prevSize = n / 2;
		int prevStart = n - prevSize;
		for (int i = 0; i < prevSize; i ++ ) {
			arr[n+i] = arr[prevStart + i];
		}
		for (int i = 0; i < prevSize; i ++ ) {
			arr[n+prevSize+i] = arr[prevStart + i] + 1;
		}
	}
	
	public static void main(String[]args ) {
		int[] test = countBits(5);
		for (int i: test) {
			System.out.println(i);
		}
	}
	
}
