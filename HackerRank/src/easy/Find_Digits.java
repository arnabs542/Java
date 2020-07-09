package easy;

//https://www.hackerrank.com/challenges/find-digits/problem

public class Find_Digits {
	static int findDigits(int n) {
		int number = n;
		int counter = 0;
		while (number > 0) {
			int digit = number % 10;
			number /= 10;
			if (n % digit == 0) counter ++;
		}
		return counter;
    }
}
