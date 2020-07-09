package Easy;

//https://leetcode.com/problems/check-if-it-is-a-straight-line/

public class Check_if_it_is_a_Straight_Line {
    
	public boolean checkStraightLine(int[][] coordinates) {
    	int last = coordinates.length - 1;
		
		double gradient = (coordinates[0][1] - coordinates[last][1] ) * 1.0 / (coordinates[0][0] - coordinates[last][0]);
		double c = coordinates[0][1] - gradient * coordinates[0][0];
		
		for (int i = 1; i < last; i ++ ) {
			if ( coordinates[i][1] != gradient * coordinates[i][0] + c)
				return false;
		}
		return true;
		
    }
	
}
