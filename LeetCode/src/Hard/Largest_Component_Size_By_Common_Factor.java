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
		
		Map<Integer, Integer> disjoint = new HashMap<>();
		Map<Integer, Integer> divisorMap = new HashMap<>();
		
		int res = Integer.MIN_VALUE;
		
		for (int node: A) {
			
			disjoint.put( node, -1 );
			divisorMap.putIfAbsent( node, node);
			
			//	The number as divisor itself
			int representorC1 = findRepresentor( disjoint, divisorMap.get(node) );
			int representorC2 = node;
			
			if (representorC1 != representorC2 ) {
				res = Math.max( res, union(disjoint, representorC1, representorC2) );
				System.out.println("Divisor Map now: " + divisorMap);
			} 
			
			int sqrt = (int)Math.sqrt(node);
			for (int i = 2; i <= sqrt; i ++ ) {
				
				if (node % i != 0) continue;
				
				int divisor1 = i;
				int divisor2 = node / i;
				
				divisorMap.putIfAbsent( divisor1 , node );
				divisorMap.putIfAbsent( divisor2 , node );
				
				//	DIVISOR 1
				int representorA1 = findRepresentor( disjoint, divisorMap.get(divisor1) );
				int representorA2 = findRepresentor( disjoint, divisorMap.get(node) );
				
				//	If both the representor are of the same, don't need to union them
				if (representorA1 != representorA2 ) {
					res = Math.max( res , union(disjoint, representorA1, representorA2 ) );
				} 
				
				//	DIVISOR 2 - We find again the representor for the node, because since the union of divisor1, the node representor
				//	may have changed
				if (divisor1 == divisor2 ) continue;
				int representorB1 = findRepresentor( disjoint, divisorMap.get(divisor2) );
				int representorB2 = findRepresentor( disjoint, divisorMap.get(node) );
				
				if (representorB1 != representorB2 ) {
					res = Math.max( res, union(disjoint, representorB1, representorB2 ) );
				} 
					
			}
			
		}
		
		return res;
    }
	
	
	private static int findRepresentor(Map<Integer, Integer> disjoint, int node) {
		if (disjoint.get(node) < 0) {
			return node;
		}
		
		int res = findRepresentor(disjoint, disjoint.get(node) );
		disjoint.put( node, res );
		return res;
	}
	
	private static int union(Map<Integer, Integer> disjoint, int representor1, int representor2) {
		int rank1 = -disjoint.get( representor1 );
		int rank2 = -disjoint.get( representor2 );
		
		if (rank1 > rank2) {
			disjoint.put( representor1, -rank1 - rank2 );
			disjoint.put( representor2, representor1 );
		} else {
			disjoint.put( representor1, representor2 );
			disjoint.put( representor2 , -rank2 - rank1 );
		}
		
		return -Math.min( disjoint.get(representor1) , disjoint.get(representor2) );
	}
	

}
