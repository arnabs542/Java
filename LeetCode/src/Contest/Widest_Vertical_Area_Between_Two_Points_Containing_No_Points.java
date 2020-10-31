package Contest;

import java.util.Arrays;

public class Widest_Vertical_Area_Between_Two_Points_Containing_No_Points {
	
    public int maxWidthOfVerticalArea(int[][] points) {
    	Arrays.parallelSort( points, (x,y)-> {
    		return x[0] - y[0];
    	});
    	
    	int res = 0;
    	int prev = points[0][0];
    	
    	for (int i = 1; i < points.length; i ++ ) {
    		if ( prev == points[i][0] ) continue;
    		int dist = points[i][0] - prev;
    		
    		res = Math.max( res, dist );
    		prev = points[i][0];
    	}
    	
    	return Math.max(0, res - 1);
    }
}
