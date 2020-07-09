package Recursion;

public class Factorial {

	public static void main(String[] args) {
		

	}

	// 5! = 5 x 4 x 3 x 2 x 1 = 120
	
	static int factorial (int n) {
		if (n == 1) {
			return 1;
		}
		else {
			return n * factorial(n - 1);
		}
	}
	
	
}
