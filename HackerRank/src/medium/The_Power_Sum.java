package medium;

//https://www.hackerrank.com/challenges/the-power-sum/problem

public class The_Power_Sum {
	
	static int powerSum(int X, int N) {
		int upperBound = (int)Math.floor(Math.pow(X, 1.0/N) );
		return findSolution(upperBound, X, N);
    }
	
	static int findSolution(int start, int target, int pow) {
		if (start == 0) 
			return target == 0? 1: 0;
		else {
			return findSolution(start-1, target - (int)Math.pow(start, pow), pow) + findSolution(start-1, target, pow);
		}
	}
	
	public static void main(String[]args) {
		System.out.println(powerSum(100, 2) );
	}
	
}
