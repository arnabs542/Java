package Contest;

//https://leetcode.com/contest/biweekly-contest-42/problems/average-waiting-time/

public class Average_Waiting_Time {
	
	public double averageWaitingTime(int[][] customers) {
		if (customers.length == 0) return 0;
		
		int currT = 0;
		double waited = 0;
		
		for (int[] cust: customers) {
			//Arrival phase
			waited += Math.max( 0, currT - cust[0] );
			currT = Math.max(currT, cust[0] );
			
			//Cooking phase
			waited += cust[1];
			currT += cust[1];
		}
		
		return waited / customers.length;
        
    }

}
