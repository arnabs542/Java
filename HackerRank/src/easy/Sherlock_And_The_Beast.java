package easy;

//https://www.hackerrank.com/challenges/sherlock-and-the-beast/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 *  Since the string must be of n length and must be largest out of any combination possible, all 5 should be in front and 3 in back
 *  Start out with all 5's as the code. While it is not satisfying the condition, keep adding number of 3's into while decrementing number of 5
 *  if it even not satisfy the condition even when all 3, the 5 count drops to -1 and break the loop, which will then indicate such code is impossible
 *  
 */

public class Sherlock_And_The_Beast {
	static void decentNumber(int n) {
		int num5 = n;
		int num3 = 0;
		while (num5 >= 0 && (num5 % 3 != 0 || num3 % 5 != 0 ) ) {
			num5 --;
			num3 ++;
		}
		if (num5 < 0) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num5; i ++ ) 
			sb.append(5);
		for (int i = 0; i < num3; i ++ )
			sb.append(3);
		System.out.println(sb);
    }
}
