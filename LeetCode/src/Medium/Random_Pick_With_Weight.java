package Medium;
import java.util.Random;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3351/

/*
 * 	THis question can be confusing. The weight here actually means 'probability'. Therefore an array of [1,3,4,6] has 4 indexes from 0 to 3.
 * 	We have to randomly select an index to return each time pickIndex() is called such that the probability an index is selected is calculated
 * 	from formula (weight of index) / (Total weight).
 * 	In this case total weight is 1+3+4+6 = 14. Thus,
 * 			P(0) = 1/14
 * 			P(1) = 3/14
 * 			P(2) = 4/14
 * 			P(3) = 6/14
 * 	where P(X) = probability of index X being selected
 * 
 * 	When working with this kind where we have to randomly choose an index, we can make use of cumulative sum array of weight, and binary search
 * 	to search the range.
 * 
 * 	The cumulative sum array of weight would be [1,4,8,14], and we would generate a number in each call. The ranges would fall in as follows:
 * 		[1,1] [2,4] [5,8] [9,14] < Observe that it is inclusive of the above array value
 * 
 * 	Therefore we would use a binary search to find the range
 * 
 * 	Initialization of array uses O(n) time, and each call to search uses O(log n), overall is O(n) time
 */

public class Random_Pick_With_Weight {
	int[] cum;
	Random rand = new Random();
	
	public Random_Pick_With_Weight(int[] w) {
        // Initialization of the cumulative sum array
        cum = new int[w.length];
        cum[0] = w[0];
        for (int i = 1; i < w.length; i ++ ) {
        	cum[i] = cum[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
    	//A random number is generated. Notice the random number generated is inclusive of 0 and exclusive of the argument, therefore we have to
    	// + 1 to the returned value
    	int r = rand.nextInt( cum[cum.length-1] ) + 1;
    	
    	int left = 0;
    	int right = cum.length - 1;
    	
    	while (left < right) {
    		int mid = left + (right - left) / 2;
    		//Since the random value is already bigger, no reason to include the current value in search area
    		if (cum[mid] < r)
    			left = mid + 1;
    		//Since the check value is bigger than random value, it might be or not in this index. We have to include it in search area
    		else if (cum[mid] > r)
    			right = mid;
    		//It is exactly match the random value. Therefore it must be at this index
    		else
    			return mid;
    	}
    	//Now the right pointer should be same as left pointer. Return
    	return left;
    }
}
