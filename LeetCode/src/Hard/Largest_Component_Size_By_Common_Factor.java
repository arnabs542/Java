package Hard;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/largest-component-size-by-common-factor/

/*
 * 	This is a Union Find / Disjoint Set problem
 * 
 * 	First of all, the factors of a number can be find in sqrt(N) time, and including the number N itself as factor too. (1 is not counted)
 * 
 * 	We are going to have a HashMap which points from divisor to one node which has that divisor as its factor.
 * 		
 * 		DIVISOR -> ONE OF NODE HAVING THIS DIVISOR AS FACTOR
 *		
 * 
 * 	Now, for every factor, it can either be:
 * 		>	There exists a past number M which had the same factor
 * 		>	It's the first time encountering this factor, by N
 * 
 * 	If it exists the past number M that has the same factor, then we had to union those 2 numbers together.
 * 	We will be finding the representator of the factor, and see if the representator of current node N if they are the same
 * 	If they are the same (In same Set), then do nothing (except you may want to have some optimization)
 * 	
 */

public class Largest_Component_Size_By_Common_Factor {
	
	public static int largestComponentSize(int[] A) {
		
		//	A directed graph of Disjoint Sets. At the top of the tree is the representator of the set 
		//	If the node points to a negative value, it means that the node is a representator. THe magnitude of the value
		//	is the size of set
		Map<Integer, Integer> disjoint = new HashMap<>();
		//	Mapping of divisor to one of the node having this divisor as its factor, which later can be used to find 
		//	the representator of set as well
		Map<Integer, Integer> divisorMap = new HashMap<>();
		
		//	The result
		int res = Integer.MIN_VALUE;
		
		
		for (int node: A) {
			
			//	First of all, the newly introduced node shall form a set by itself, of size 1
			disjoint.put( node, -1 );
			//	Since node N itself is also a divisor of itself (1 * N = N), put it into divisor, IF IT IS ABSENT
			//	Otherwise, it will be need to union since there is another set having N itself as divisor already
			divisorMap.putIfAbsent( node, node);
			
			//	The number as divisor itself
			int representorC1 = findRepresentor( disjoint, divisorMap.get(node) );
			int representorC2 = node;
			
			//	There exists another set which is having node N as divisor. Union them
			if (representorC1 != representorC2 ) {
				res = Math.max( res, union(disjoint, representorC1, representorC2) );
			} 
			
			//	Finding all divisors of N in sqrt(N) time
			int sqrt = (int)Math.sqrt(node);
			for (int i = 2; i <= sqrt; i ++ ) {
				
				if (node % i != 0) continue;
				
				int divisor1 = i;
				int divisor2 = node / i;
				
				//	If there is no set for the divisor already, then we shall put them pointing to this node
				divisorMap.putIfAbsent( divisor1 , node );
				divisorMap.putIfAbsent( divisor2 , node );
				
				//	DIVISOR 1 - Note we cannot just set representorA2 = node. This is because since the above
				//	may cause the node itself to be union into another set, the node itself may already be in another set already
				int representorA1 = findRepresentor( disjoint, divisorMap.get(divisor1) );
				int representorA2 = findRepresentor( disjoint, divisorMap.get(node) );
				
				//	If both the representor are of the same (IN SAME SET), don't need to union them
				if (representorA1 != representorA2 ) {
					res = Math.max( res , union(disjoint, representorA1, representorA2 ) );
				} 
				
				//	DIVISOR 2
				if (divisor1 == divisor2 ) continue;
				int representorB1 = findRepresentor( disjoint, divisorMap.get(divisor2) );
				int representorB2 = findRepresentor( disjoint, divisorMap.get(node) );
				
//				If both the representor are of the same (IN SAME SET), don't need to union them
				if (representorB1 != representorB2 ) {
					res = Math.max( res, union(disjoint, representorB1, representorB2 ) );
				} 
					
			}
			
		}
		
		return res;
    }
	
	
	//	Take in the disjoint set tree and a node, will return its representative. Recursion is used here to perform
	//	Path Compression
	private static int findRepresentor(Map<Integer, Integer> disjoint, int node) {
		if (disjoint.get(node) < 0) {
			return node;
		}
		
		int res = findRepresentor(disjoint, disjoint.get(node) );
		disjoint.put( node, res );		// Path Compression here
		return res;
	}
	
	
	//	Take in the representator of 2 Sets, and union them. Then, will return the size of union-ed set, to be compared with the
	//	result later
	private static int union(Map<Integer, Integer> disjoint, int representor1, int representor2) {
		int rank1 = -disjoint.get( representor1 );
		int rank2 = -disjoint.get( representor2 );
		
		//	Set 1 is larger, Set 2 shall be union into Set 1
		if (rank1 > rank2) {
			disjoint.put( representor1, -rank1 - rank2 );
			disjoint.put( representor2, representor1 );
		}
		//	Set 2 is larger, Set 1 shall be union into Set 2
		else {
			disjoint.put( representor1, representor2 );
			disjoint.put( representor2 , -rank2 - rank1 );
		}
		
		return -Math.min( disjoint.get(representor1) , disjoint.get(representor2) );
	}
	

}
