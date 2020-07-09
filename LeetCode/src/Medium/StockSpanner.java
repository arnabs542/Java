package Medium;

import java.util.ArrayList;
import java.util.Stack;

//https://leetcode.com/problems/online-stock-span/

/*
 * 	Although intuitively, every time we add a trading day, we have to scan through the previous one, and break when the previous day is greater
 * 	than current's, at worst case (the price added was the greatest in history), it would need to gone through the whole history,
 *  This results in a time complexity of O(n2)!
 *  
 *  An ingenious way to solve this is to somehow, find a shortcut so we'll skip certain days which we know would be included in the span.
 *  We would need to utilize the indexing of the days (starting from 0) and also keep track of the prices of each day
 *  
 *  We would create a stack which stores the index of days, starting from 0, such that:
 *  	- The indexing of days will always kept in the order of descending order of prices
 *  	- If the price we are adding today is greater than the previous day's price, keep popping the price until we've met with the one day
 *  	  with the greater price
 *  	- The span would be this day's index - the day's index at the top of the stack
 *  	- Lastly we push the day's index to the top of the stack, that way the stack is always kept in the decreasing order of prices
 *  
 *  Every day's index would be at most pushed and popped once, thus resulting in time complexity O(2n), which is O(n)
 */

public class StockSpanner {
	
	int day;
	Stack<Integer> decreasingDays;
	ArrayList<Integer> priceList;
	
	public StockSpanner() {
		day = 0;
		decreasingDays = new Stack<>();
		priceList = new ArrayList<>();
    }
    
    public int next(int price) {
    	int ans;
    	if (decreasingDays.isEmpty() ) {
    		ans = 1;
    	}
    	else {
    		while (!decreasingDays.isEmpty() && price >= priceList.get( decreasingDays.peek() )) {
    			decreasingDays.pop();
    		}
    		ans = (decreasingDays.isEmpty() )? day + 1: day - decreasingDays.peek();
    	}
    	
    	priceList.add(price);
    	decreasingDays.push(day);
    	day++;
    	return ans;
    }
    
}
