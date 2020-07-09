package easy;

//https://www.hackerrank.com/challenges/drawing-book/problem

public class Drawing_Book {
	static int pageCount(int n, int p) {
		return Math.min( p/2 , (n % 2 == 0)? (n-p+1) / 2: (n-p)/2 );
	}
}
