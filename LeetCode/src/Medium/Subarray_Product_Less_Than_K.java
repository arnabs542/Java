package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/subarray-product-less-than-k/

/*
 * 	A sliding window / Binary search problem.
 * 
 * 	Given those positive integers (No decimals, no negatives). Can we find subarray with good product less than k? In linear time?
 * 
 * 	One intuitive idea to realize is: 
 * 		Given a array of positive integers of length l, say [1,2,3,4,5]. If this array product is less than k, then ALL
 * 		its subarray will be less than k, due to the fact that product of integer only increases value, no decreasing.
 * 
 * 	WIth this fact in mind, we can just iterate through the array using a sliding window, and a product variable keeping track of the
 * 	products in the sliding window.
 * 	Whenever we introduced a new element into sliding window, we will make a promise: Promise to use this latest added element in our
 * 	subarray. Generate all the subarrays with the last element included. Eg:	
 * 		[1,2,3,4] where 4 just be included into the subarray
 * 		Valid subarrays are [4], [3,4], [2,3,4], [1,2,3,4]
 * 	
 * 	Now, if the product of all elements in sliding window is over k, we'll have to shrink the size of sliding window from behind (tail) 
 * 	until it returns back to valid state again (product < k).
 * 	When it is ready, we have a valid sliding window which products inside it is less than k. Count number of subarrays with latest
 * 	element included in it, which is given by (head - tail + 1)
 * 
 * 	The sum of all those valid subarrays is the overall answer to the problem
 * 
 * 	--------------------------------------------------------------------------------------------------------
 * 
 * 	Multiplying is dangerous. If sliding window is large, it can result in a datatype overflow. Int datatype may be too small to contain the
 * 	product. In extreme cases, even long can't handle it.
 * 	
 * 	That's where we use the knowledge of logarithms.
 * 
 * 	>	Apply log to each element
 * 	>	Create a new array which is cumulative sum (Running sum) of the logarithms.
 * 		Eg:	log 10 = 1;
 * 		Original: [10,10,10]
 * 		Running sum: [0, 1, 2, 3]		<< Created a extra space for convenience, and easier for binary search
 * 
 * 	Key point to realize: Now since it is running sum, it basically SORTED, which means binary search is applicable.
 * 	
 * 	Like above solution, we need to find valid subarrays involving the latest element. The property of sums of logarithms is:
 * 		
 * 		log a + log b + lob c =====> log (abc)		Its their product!
 * 	
 * 	If we also apply log to the K value, then we have to find
 * 			log (...n) < log (K) 
 * 
 * 	We need to use binary search to find the index where when excluded, the product is smaller than log (K)
 * 	Basically,
 * 			Find index where
 * 		log( current sum) - log (binary index) < log (K)
 * 
 * 	This has time of O(N log N), but deals with integer overflow problem
 * 
 * 	------------------------------------------------------------------------------------
 * 
 * 	We've explored logarithms. What if logarithms is used in sliding window approach?
 * 	
 */

public class Subarray_Product_Less_Than_K {
	
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		
		//	State of the sliding window: Product of all elements in it
		long product = 1;
		int res = 0;
		
		int tail = 0;
		int head = 0;
		
		while ( head < nums.length ) {
			product *= nums[head];
			
			//	Note the tail can exceed the head pointer. This means even the latest element included also exceeds k.
			//	This way we will result in adding 0 to the res, which is correct
			while (tail <= head && product >= k ) {
				product /= nums[tail];
				tail++;
			}
			
			res += head - tail + 1;
			head ++;
		}
		
		return res;
	}
	
	
	
	public int numSubarrayProductLessThanKLOG(int[] nums, int k) {
		int len = nums.length;
		double[] logs = new double[len + 1];
		
		for (int i = 1; i < len + 1; i ++ ) {
			logs[i] = logs[i-1] + Math.log10( nums[i - 1] );
		}
		
		double klog = Math.log10(k);
		klog = Math.round( klog * 100000 );		//	We will use rounding here. This is due to floating point errors, causing exact values
												//	to also be incorrect. (Eg: [7,5] and K = 35, it might still not match up for [7,5] due
												//	to floating point precision error )
		
		
		int res = 0;
		
		//	The subarray must tail with this last element
		for (int i = 1; i < len + 1; i ++ ) {
			int left = 0;
			int right = i;
			double cum = logs[i];
			
			while (left < right) {
				int mid = left + (right - left) / 2;
				
				if ( Math.round( (cum - logs[mid]) * 100000 ) < klog) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			
			res += i - left;
		}
		
		return res;
	}
	
	
}
