package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//https://leetcode.com/problems/accounts-merge/
/*
 * 	This is a disjoint set problem, supposed to be HARD - HARD TO IMPLEMENT
 * 
 * 	Even if the names are the same, we cannot identify them as same person. Only if the person name is same
 * 	and there is one common email, then we can group them together.
 * 
 * 	The algorihtm breaks down into 3 steps:
 *	1. 	Construction of disjoint set. All emails in our list shall point to a disjoint set node, which in turn can be
 *		a parent itself or point to another parent node. This is the conventional way disjoint set is done.
 *
 *		Therefore for each account (having multiple email), we construct a node which we are able to reference back the
 *		owner's name later. For each email, we check if the email appeared before or not. If yes, we have to perform
 *		disjoint set merge.
 *
 *		At the end, the disjoint set shall be ready with all email pointing to the representative of group, which in turn
 *		can tell us who the name of owner is.
 *
 *	2. 	Construct a map such that { ID -> emails }. ID is the index in accounts array. I used ID and not name because 
 *		multiple person could have the same name. However, you could choose to put Node itself as key also. No problem.
 *
 *		For each email, find the representative. Put into the map with respective key.
 *
 *	3. 	Reduce to what the problem wanted - { Name -> sorted emails }.
 */







public class Accounts_Merge {
	
	// The Node of disjoint set used in this problem
	private class Node {
		// ID is the index in the original array. Eg: id = 1 means this node is created during arr[1]
		int id;
		Node parent;
		
		public Node(int id) {
			this.id = id;
		}
	}
	
	
	// The disjoint set used in this problem
	class DisjointSet {
		public Map<String, Node> map = new HashMap<>();
		
		
		public Node findRepresentative(String email) {
			if (!map.containsKey(email)) return null;
			return findRepresentative( map.get(email ) );
		}
		
		
		// Recursive findRepresentative with path compression technique
		public Node findRepresentative(Node node) {
			if (node.parent == null) return node;
			node.parent = findRepresentative(node.parent);
			return node.parent;
		}
		
		
		public void merge(String email, Node nodeToMerged) {
			Node lhs = findRepresentative(email);
			Node rhs = findRepresentative(nodeToMerged);
			if (lhs == rhs) return;
			rhs.parent = lhs;
		}
	}
	
	
	
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DisjointSet ds = new DisjointSet();
        
        // 1 - Construction of Disjoint Set
		for (int i = 0; i < accounts.size(); ++i) {
			List<String> acc = accounts.get(i);			
			Node node = new Node(i);
			
			for (int j = 1; j < acc.size(); ++j) {
				String s = acc.get(j);
				if (!ds.map.containsKey(s))
					ds.map.put(s, node);
				else
					ds.merge(s, node);
			}
		}
		
		// 2 - Reduce to a Map where { id -> [emails...] ].
		// I use ID instead of name because a name can be duplicated although they are not same person
		Map<Integer, Set<String>> map = new HashMap<>();
		
		for (List<String> acc: accounts) {
			int belongsToID = ds.findRepresentative( acc.get(1) ).id;
			
			map.putIfAbsent(belongsToID, new TreeSet<>());
			map.get(belongsToID).addAll( acc.subList(1, acc.size() ) );
		}
		

		// 3 - Finally, reduce to the format that original question wanted: {name -> emails in sorted list}
		List<List<String>> res = new ArrayList<>();
		
		for (int i: map.keySet()) {
			List<String> toAppend = new ArrayList<>();
			toAppend.add( accounts.get(i).get(0) );
			toAppend.addAll( map.get(i) );
			res.add( toAppend );
		}
		
		return res;
    }

}
