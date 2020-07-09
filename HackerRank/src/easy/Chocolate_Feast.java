package easy;

//https://www.hackerrank.com/challenges/chocolate-feast/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Chocolate_Feast {
	
	static int chocolateFeast(int n, int c, int m) {
		int choco = n / c;
		int wrapper = choco;
		while (wrapper >= m) {
			int exc = wrapper / m;
			choco += exc;
			wrapper = wrapper - (exc * m) + (exc);
		}
		return choco;
    }
	
}
