package easy;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/electronics-shop/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	What we could do to optimize this solution is to sort the keyboards in decending order while drives in ascending order.
 * 	If we found that the first pair of keyboard and drive(most expensive and cheapest) exceeds the budget, proceed with the keyboard,
 * 	else, (below budget) then increase the drives index
 * 	Now the crucial point: If the budget goes over again, we would go to the next cheaper keyboard, but NOT REPEATING THE DRIVES again, because
 * 	the previous drive would no matter what, end up with cheaper one. Remember that with THE MOST EXPENSIVE KEYBOARD, the previous drive already
 * 	can kept it below the budget limit, thus looping it again would end up redundant as it must be cheaper than the current maximum.
 * 	
 * 	This way the time complexity could be ended up in O(n log n) only (due to sorting) and not O(n^2) because the drives and keyboards are only iterated
 * 	at most 1 times only ( O(m+n))
 */

public class Electronics_Shop {
	static int getMoneySpent(int[] keyboards, int[] drives, int b) {
		int max = -1;
		Arrays.sort(keyboards);
		Arrays.sort(drives);
		
		for (int k = keyboards.length - 1; k >= 0; k --) {
			int d = 0;
			for ( ; d < drives.length; d ++ ) {
				if (keyboards[k] + drives[d] > b) break;
				else if (keyboards[k] + drives[d] > max) 
					max = keyboards[k] + drives[d];
			}
		}
		return max;
    }

}
