package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/design-underground-system/
/*
 * 	This is simply a Design problem.
 * 
 * 	When you check in, you get the customer's ID, board station and the time. You don't know about when the customer
 * 	will check out the train. So you have to store them first!
 * 	
 * 	The key is obviously the customer's ID. However we have to store the boarding station and time. Since they are different
 * 	datatypes, we either use Pair<> object, or create our own class for that.
 * 
 * 	When board off, look up the boarding info from the above Hashmap. Then, we can immediately store the information about
 * 	the times.
 * 	For some efficiency, store the boarding-checkout station names into single string. Another HashMap will be storing the
 * 	"boarding-checkout" string as key, and it points an integer array of size 2 (again, you can use Pair).
 * 	The integer array is [ total time, no of customers ]
 *
 */

public class Design_Underground_System {
	
	class UndergroundSystem {
		
		private class BoardingDetail {
			int time;
			String station;
			
			public BoardingDetail(int time, String station) {
				this.time = time;
				this.station = station;
			}
		}
		
		Map<Integer, BoardingDetail> onBoard;	//	Maps ID to their boarding station detail
		Map<String, int[]> times;				//	Maps String "<boardingStation-arrivalStation>" to their total time spent and counts
		
		public UndergroundSystem() {
			onBoard = new HashMap<>();
			times = new HashMap<>();
		}
		
		public void checkIn(int id, String stationName, int t) {
			onBoard.put(id, new BoardingDetail(t, stationName) );
		}
		
		public void checkOut(int id, String stationName, int t) {
			BoardingDetail d = onBoard.get(id);
			onBoard.remove(id);
			
			String encoded = d.station + "-" + stationName;
			times.putIfAbsent(encoded, new int[] {0,0} );
			
			times.compute(encoded, (k,v)-> {
				++v[1];
				v[0] += t - d.time;
				return v;
			});
		}
		
		public double getAverageTime(String startStation, String endStation) {
			String encoded = startStation + "-" + endStation;
			int[] time = times.get(encoded);
			return ((double)time[0] ) / time[1];
		}
		
	}
}
