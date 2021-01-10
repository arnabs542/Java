package Contest;

//https://leetcode.com/problems/calculate-money-in-leetcode-bank/
/*
 * 	n <= 1000. Sure we can do it in O(N) time.
 * 
 * 	When we detect it is first day of week, we go back to amount of 7 days ago + 1
 * 
 * -------------------------
 * 
 * 	We could use some arithmetic here. Find out number of weeks and the extra days.
 * 	
 * 	Since on week 2 onwards, it start at 2, 3, 4 and continue... each day amount is +1, therefore total week saved
 * 	is previous week's +7.
 * 
 * 	Use some arithmetic sum formula and can get the answer
 */
public class Calculate_Money_in_Leetcode_Bank {
	
	public int totalMoney(int n) {
		int total = 0;
		int cum = 0;
		
		for (int i = 1; i<=n; ++i) {
			if ( (i-1) % 7 == 0) cum = Math.max(1, cum-6);
			
			total += cum;
			++cum;
		}
		return total;
    }
	
	
	
	public int totalMoney2(int n) {
		int noWeek = n / 7;
		int remainDays = n % 7;
		
		int total = 28 * noWeek + ( noWeek* (noWeek-1) / 2) * 7;
		for (int i = 0; i < remainDays; ++i)
			total += ++noWeek;
		
		return total;
	}
	
}
