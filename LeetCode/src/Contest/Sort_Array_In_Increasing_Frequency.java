package Contest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Sort_Array_In_Increasing_Frequency {
	
	public int[] frequencySort(int[] nums) {
		
		int[] freq = new int[201];
		for (int i: nums) {
			freq[ i + 100 ] ++;
		}
		
		TreeMap<Integer, List<Integer> > map = new TreeMap<>();
		for (int i = 0; i < 200; i ++ ) {
			if ( freq[i] != 0) {
				map.putIfAbsent( freq[i], new ArrayList<>() );
				map.get(freq[i] ).add( i - 100 );
			}
		}
		
		int idx = 0;
		int[] res = new int[nums.length];
		
		for (int f: map.keySet() ) {
			List<Integer> li = map.get( f );
			li.sort( (x,y) -> {
				return y - x;
			});
			for (int l: li) {
				for (int i = 0; i < f; i++ ) {
					res[idx++] = l;
				}
			}
		}
		
		return res;
    }

}
