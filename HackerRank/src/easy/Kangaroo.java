package easy;

public class Kangaroo {

	//https://www.hackerrank.com/challenges/kangaroo/problem
	
	public static void main(String[]args) {
		
	}
	
	static String kangaroo (int x1, int x2, int v1, int v2) {
		
		//n is derived from the linear graph consisting of 2 lines representing 2 kangaroos. The graph is formed by y = mx+c where:
		// y: Position or the x position
		// m: the v value (distance per jump) of each kangaroo
		// c: the initial position of each kangaroo
		
		double n = (1.0) * (x2 - x1) / (v1 - v2);
		//If n intersect only at negative x axis (impossible for them to met)
		if (n < 0) return "NO";
		
		//Return "YES" if n is a whole number, else, return "NO"
		return ( ((int)n == n)? "YES":"NO");
		
	}
	
}
