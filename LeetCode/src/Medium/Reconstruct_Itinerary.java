package Medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/reconstruct-itinerary/

/*
 * 	This question is a DFS question, where I will always greedily attempt to go for the lowest lexicographically destination available. If I hit a 
 *	dead end before all the tickets are exhausted, then I shall backtrack to the point to attempt another higher topological ticket.
 *
 *	
 */

public class Reconstruct_Itinerary {
	public List<String> findItinerary(List<List<String>> tickets) {
		//Creation of the directed graph//
		HashMap<String, LinkedList<String> > mapping = new HashMap<>();
		
		for (List<String> fromTo: tickets) {
			mapping.putIfAbsent(fromTo.get(0) , new LinkedList<>() );
			mapping.get(fromTo.get(0) ).add(fromTo.get(1) );
		}
		
		//This will be the itinerary; the answer to the question
		List<String> itinerary = new LinkedList<>();
		
		//We start out at JFK and we shall start traveling
		itinerary.add("JFK");
		return recursion(mapping, itinerary, "JFK", tickets.size() + 1);
		
	}
	
	private List<String> recursion(HashMap<String, LinkedList<String> > map, List<String> itin, String current, int target) {
		//Base case: the list is equal to the targeted length which means all the tickets are exhausted
		if (itin.size() == target ) return itin;
		
		//If this airport does not direct to another airport, then this is dead end.
		if (!map.containsKey(current) ) return null;
		
		//Obtain the list of airports that this airport can travel to
		LinkedList<String> choices = map.get(current);
		//The list is empty. Dead end
		if (choices.size() <= 0 ) return null;
		
		//Copy the list to ensure for greedy choosing least lexicographically airport first
		LinkedList<String> copy = new LinkedList<>(choices);
		Collections.sort(copy);
		
		for (String to: copy) {
			//Travel to the airport. We need to record in itinerary, and exhaust the ticket (by removing the arrow from graph)
			itin.add(to);
			choices.remove(to);
			//Obtain the result of this choice
			List<String> result = recursion(map, itin, to, target);
			//This choice is bad and leads to dead end. Therefore delete from itinerary, and return the ticket
			if (result == null) {
				itin.remove( itin.size() -1);
				choices.add(to);
			}
			//Else this choice returns a full itinerary, return it.
			else 
				return result;
		}
		return null;
	}
}
