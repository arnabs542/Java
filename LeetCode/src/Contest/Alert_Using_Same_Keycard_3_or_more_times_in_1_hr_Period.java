package Contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Alert_Using_Same_Keycard_3_or_more_times_in_1_hr_Period {
	
	public List<String> alertNames(String[] keyName, String[] keyTime) {
		
		int len = keyName.length;
		Map<String, List<Integer> > map = new HashMap<>();
		
		for (int i = 0; i < len; i ++ ) {
			String name = keyName[i];
			String[] time = keyTime[i].split(":");
			
			int minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1] );
			
			map.putIfAbsent( name , new ArrayList<>() );
			map.get(name).add( minutes );
		}
		
		List<String> names = new ArrayList<>();
		
		for (String name: map.keySet() ) {
			List<Integer> times = map.get(name);
			Collections.sort( times );
			
			for (int i = 2; i < times.size(); i ++ ) {
				if (times.get(i) - 60 <= times.get(i - 2) ) {
					names.add(name);
					break;
				}
			}
			
		}
		
		Collections.sort( names );
		return names;
    }
}
