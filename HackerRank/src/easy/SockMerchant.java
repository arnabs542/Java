package easy;

//https://www.hackerrank.com/challenges/sock-merchant/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

public class SockMerchant {
	
	static int sockMerchant(int n, int[] ar) {
		int[] count = new int[100];
		int pairs = 0;
		for (int c: ar) {
			count[c - 1] ++;
			if (count[c-1] % 2 == 0) pairs ++;
		}
		return pairs;
    }
}
