package easy;

import java.util.HashMap;
import java.util.LinkedList;

//https://www.hackerrank.com/challenges/beautiful-pairs/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Beautiful_Pairs {
	
	static int beautifulPairs(int[] A, int[] B) {
		HashMap<Integer, LinkedList<Integer> > mapA = new HashMap<>();
		HashMap<Integer, LinkedList<Integer> > mapB = new HashMap<>();
		
		for (int i = 0; i < A.length; i ++ ) {
			mapA.putIfAbsent(A[i], new LinkedList<Integer>() );
			mapB.putIfAbsent(B[i], new LinkedList<Integer>() );
			mapA.get(A[i]).add(i);
			mapB.get(B[i]).add(i);
		}
		
		int counter = 0;
		boolean canChangeB = false;
		boolean hasLoneA = false;
		
		for (Integer keyA: mapA.keySet() ) {
			if (!mapB.containsKey(keyA) ) hasLoneA = true;
		}
		for (Integer keyB: mapB.keySet() ) {
			if (!mapA.containsKey(keyB) )
				canChangeB = true;
			else {
				int sizeA = mapA.get(keyB).size();
				int sizeB = mapB.get(keyB).size();
				
				counter += Math.min(sizeA, sizeB);
				
				if (sizeA > sizeB) hasLoneA = true;
				else if (sizeB > sizeA) canChangeB = true;
			}
		}
		
		return counter + ( (canChangeB && hasLoneA)? 1:(canChangeB)? 0:-1);

    }
}
