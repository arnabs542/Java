package Medium;

//https://leetcode.com/problems/champagne-tower/

/*
 * 	This is a array simulation problem.
 * 
 * 	We have to ignore the fact that every glass has a fixed capacity which it can contain at most 1 cup capacity.
 * 	For every row, we can look at the previous rows result. Lets see an example:
 * 
 * 	Say initially we pour down 5 cup worth of champagne. In the first row, the champagne volume contained in the glass
 *  is 5, we will be ignoring the fact that the glass can contain only 1 cup worth of champagne
 *  Onto row 2, the first glass will depend on the last row (First row's) volume of champagne. We will be taking with formula:
 *  	(Volume of last glass champagne - 1) / 2
 *  We minus by 1 because the last row's glass may contain the 1 glass worth of champagne, if it is overflowed.
 *  We divide by 2 because the overflowed champagne flows equally to the left and right.
 *  Of course, if the last glass actually don't overflow, then the calculated value will be negative. Careful of that
 *  
 *  Now, the result will be like this:
 *  ( 5 )
 *  ( 2 )( 2 )		<-  (5 - 1) / 2
 *  (   )(   )(   )
 *  
 *  Let's compute row 3 now. Now there is 1 cup that will be dependent on 2 cups instead of one. How do we represent that?
 *  THe middle glass actually depends on half on the glass 0 and glass 1 on row 2. That means for every glass, the volume
 *  that flows into it is dependant on:
 *  	Last row glass N and Last row glass N-1
 *  
 * 	If we were to iterate backwards, we can reduce the space complexity to just O(N) without losing information!
 */

public class Champagne_Tower {
	
	public double champagneTower(int poured, int query_row, int query_glass) {
        double[] champagne = new double[query_row + 1];
        champagne[0] = poured;
        
        for (int row = 1; row <= query_row; row ++ ) {
        	for (int col = row; col > 0; col -- ) {
        		champagne[col] = Math.max(0, (champagne[col] - 1) / 2) + Math.max(0, (champagne[col - 1] - 1) / 2);
        	}
        	champagne[0] = Math.max(0, (champagne[0] - 1) / 2);
        }
        return Math.min( champagne[query_glass], 1);	//	The value may be large (Still overflowing). In that case,
        												//	Return 1 only.
    }
	
}
