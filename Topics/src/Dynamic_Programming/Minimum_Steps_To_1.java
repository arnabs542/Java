package Dynamic_Programming;

/*
 * 	Given an integer n, find the minimum number of steps to reduce n to 1, with the steps as follows:
 * 		-Decrement n by 1
 * 		-If n is divisible by 2, divide it by 2
 * 		-If n is divisible by 3, divide it by 3
 * 
 * 	Eg: To reduce 10 to 1, We take 3 steps: 10 -> 9 -> 3 -> 1
 * 	Here we can see, it's not about dividing whenever it can. We shall also consider if decrementing takes us to better solution. Therefore
 * 	we need to consider all outcomes
 *	
 */

public class Minimum_Steps_To_1 {
	
	//Recursive solution. However it keeps repeat calculating the same calls resulting in wasted time. 
	public static int minStep(int n) {
		if (n <= 1) return 0;
		if (n <= 3) return 1;
		return Math.min(minStep(n - 1) + 1, Math.min( 
						(n % 2 == 0)? minStep(n / 2) + 1: Integer.MAX_VALUE,
						(n % 3 == 0)? minStep(n / 3) + 1: Integer.MAX_VALUE) );
	}
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	//Memoization solution. We could just make a new array to store the answers to certain value calls
	public static int minStepMemoir(int n) {
		if (n <= 2) return n - 1;
		
		int[] mem = new int[n + 1];
		mem[2] = 1; mem[3] = 1;
		return memoir(n, mem);
	}
	
	public static int memoir(int n, int[] arr) {
		if (n == 1) return 0;
		if (arr[n] != 0) return arr[n];
		arr[n] = Math.min(memoir(n - 1, arr) + 1, Math.min( 
				(n % 2 == 0)? memoir(n / 2, arr) + 1: Integer.MAX_VALUE,
				(n % 3 == 0)? memoir(n / 3, arr) + 1: Integer.MAX_VALUE) );
		return arr[n];
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	//Dynamic Programming solution. Instead of calling it from the top to bottom, we could realize the fact that due to the decrement by 1
	//step, every value in range 1 to n must be called at least once. Therefore why don't we try to fill it from bottom to top?
	
	public static int minStepDP(int n) {
		if (n == 1) return 0;
		if (n <= 3) return 1;
		int[] sol = new int[n + 1];
		sol[1] = 0;
		sol[2] = 1; sol[3] = 1;
		for (int i = 4; i <= n; i ++ ) {
			sol[i] = Math.min(sol[i-1] + 1, Math.min( 
				(i % 2 == 0)? sol[i/2] + 1: Integer.MAX_VALUE,
				(i % 3 == 0)? sol[i/3] + 1: Integer.MAX_VALUE) );
		}
		return sol[n];
		
	}
	
	public static void main(String[]args) {
		
		for (int i = 1; i < 1000; i ++ ) {
			System.out.print(minStep(i));
			System.out.print(", ");
			System.out.print(minStepMemoir(i) );
			System.out.print(", ");
			System.out.print(minStepDP(i) );
			System.out.print("\n");
		}
	}
	
}
