package Medium;

//https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/

/*
 * 	This is a Array, Greedy, Pigeonhole principle problem.
 * 
 * 	We have to try to flip the dominos such at one of the rows have all the same values, regardless of another row.
 * 	From thinking, we can immediately deduce that since we saw the first domino, there can be at most 2 values that
 * 	satisfy the solution already - The upper value of domino 1, or lower value of domino 2.
 * 
 * 	Actually, we can brute force by attempting to form the upper row to be all same value (Not considering lower row),
 * 	from 1 to 6. If any of value is found not possible, immediately exit the loop and continue to next iteration.
 * 
 * 	We will keep a counter to keep track of how many flips we've done.
 * 	Because we are going to form the row at upper side, if the next domino's upper value is the one we're looking for,
 * 	continue without doing anything. Otherwise if the lower value only is the value we're looking, then increment the 
 * 	counter. Otherwise, the value is simply not possible.
 * 
 * 	What if the minimum number of flips are actually achieved by flipping to lower and not upper? Since there are only
 * 	2 possible flips: Upper and Lower, it can be found easily by using (len - flip).
 * 
 * 	However, using formula above will be incorrect because there are actually dominos that have same value on both upper
 * 	and lower! Then, we have to keep a duplicates counter to keep track of duplicates, then when we finding out the
 * 	complement, we use (len - flip - duplicates)!
 * 
 * 	----------------------------------------------------------
 * 
 * 	Alternatively, we can use 2 counters - Flipping to upper and flipping to lower. Then we don't have to keep a duplicate
 * 	counter, and the code may be cleaner
 * 
 * 	============================================================
 * 
 * 	PIGEONHOLE PRINCIPLE
 * 
 * 	When the value1 DOES end up successfully (There is a way to flip to all same in a row), then THERE IS NO POSSIBLE
 * 	WAY FOR VALUE2 TO FLIP TO SAME SIDE ALREADY, UNLESS FLIP1 SOLUTION END UP IN ANOTHER ROW ALSO SAME VALUES
 * 
 * 	This is because if there is a way to make upper row all same value1, then that means:
 * 		THE FREQUENCY OF VALUE1 MUST BE >= N
 *  Now, this leaves two choices for value2:
 *  	>	If frequency of value1 == N, then value2 has to be frequency == N as well for it to come up with a solution,
 *  		which is same if we done checking the value1
 *  	>	If frequency of value1 > N, then value2 frequnecy < N, not possible to form a solution!
 * 
 */

public class Minimum_Domino_Rotations_For_Equal_Row {
	
	public int minDominoRotations(int[] A, int[] B) {
        int len = A.length;
        if (len <= 0) return -1;
        
        int v = A[0];
        int counter = 0;
        int dup = A[0] == B[0]? 1: 0;
        
    	//	Check for first val
        for (int i = 1; i < len; i ++ ) {
        	//	If the upper side is already the value we're looking for, no need for flip
        	if (A[i] == v) {
        		//	If the lower side is also same value, it is duplicate domino. When finding complement, no need to
        		//	include this domino!
        		if (B[i] == v) dup++;
        	} 
        	//	Otherwise the upper side is not the value, see if the lower value is the value we're looking for
        	else if (B[i] == v) {
        		counter ++;
        	} 
        	//	Both sides are nt the value. Ended up in impossible case.
        	else {
        		counter = Integer.MAX_VALUE;
            	break;
        	}
        }
        //	Due to Pigeonhole principle, if there is valid solution for value1, then no need check for value2 already
        if (counter <= len) {
        	return Math.min( counter , len - counter - dup );
        }
        
        

        //	Seems like value1 is impossible, so we have to check value2
        v = B[0];
        counter = 0;
        dup = A[0] == B[0]? 1: 0;
        //	Otherwise, find the second val
        for (int i = 1; i < len; i ++ ) {
        	//	If the upper side is already the value we're looking for, no need for flip
        	if (B[i] == v) {
        		//	If the lower side is also same value, it is duplicate domino. When finding complement, no need to
        		//	include this domino!
        		if (A[i] == v) dup++;
        	} 
        	//	Otherwise the upper side is not the value, see if the lower value is the value we're looking for
        	else if (A[i] == v) {
        		counter ++;
        	} 
        	//	Both sides are nt the value. Ended up in impossible case.
        	else {
        		counter = Integer.MAX_VALUE;
            	break;
        	}
        }
        
        return (counter <= len)? Math.min( counter , len - counter - dup ): -1; 
        
	}
	
	
	
	/*
	 * 	CLEANER SOLUTION
	 */
	public int minDominoRotations2(int[] A, int[] B) {
		int len = A.length;
		if (len <= 0) return -1;
		
		int v = A[0];
		//	Count1 is to form value at upper row, count2 is to form value at lower row
		int count1 = 0;
		int count2 = B[0] == v? 0: 1;
		
		for (int i = 1; i < len; i ++ ) {
			if (A[i] != v && B[i] != v) {
				count1 = Integer.MAX_VALUE;
				break;
			}
			count1 += A[i] == v? 0: 1;
			count2 += B[i] == v? 0: 1;
		}
		
		if (count1 != Integer.MAX_VALUE) return Math.min( count1, count2 );
		
		v = B[0];
		count1 = A[0] == v? 0: 1;
		count2 = 0;
		
		for (int i = 1; i < len; i ++ ) {
			if (A[i] != v && B[i] != v) {
				count1 = Integer.MAX_VALUE;
				break;
			}
			count1 += A[i] == v? 0: 1;
			count2 += B[i] == v? 0: 1;
		}
		
		if (count1 == Integer.MAX_VALUE) return -1;
		return Math.min( count1 , count2);
	}

}
