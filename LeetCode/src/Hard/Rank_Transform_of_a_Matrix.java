package Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/rank-transform-of-a-matrix/
/*
 * This is a Hard, Disjoint Set, Greedy problem.
 * 
 * We have to assign each elements a rank, where ranks of higher values shall be higher than those of lower value ones.
 * 
 * Approaching from greedy idea, we should always assign rank to those smaller values first, so we will not have conflicting rank
 * later on. Using idea from DP, we can record the maximum ranks that exist so far in particular column and row, so we can always
 * assign the valid, next rank.
 * 
 * 		> Sort the positions (x,y) by their actual value in matrix, ascending
 * 		> Fill the ranks according to the sorted positions. Derive rank from minRank DP array of both row and column
 * 
 * In fact, this will already be the solution if say, every element in the matrix is unique. However, that is not the case here
 * as duplicate values do exist.
 * 
 * ---------------------------
 * 
 * The greatest challenge faced in this problem is mainly from the fact that duplicate values exist. In that case, we need to
 * assign them same rank as long as those values do somehow 'connect' to each other in rows or columns.
 * 
 * This require the usage of Disjoint sets. Say we have a value X, in which it occurs in matrix N times. If suddenly one element
 * from one group is in same row/column as the other element in other group, we need to union the group together. In other words,
 * THIS GROUP OF DUPLICATED VALUES SHALL HAVE SAME RANK.
 * 
 * With this, the general algorithm goes:
 * 		>	Group (x,y) by actual value in the matrix
 * 		>	Traverse the positions (x,y) from small to large. (Assign rank to small values first)
 * 			-	Split them into groups and union related groups using Disjoint Set
 * 			-	Within each of these groups:
 * 				>	Find the rank that should be assigned to them. All of them has to have same rank. The rank is derived from
 * 					minRank from both row and column of each of these elements
 * 				>	Set the derived rank to each of these elements, as well as update minRank DP array
 */


// Abstract Union Find Data Structure, operates using index
class UnionFind {
	private int[] parent;
	
	public UnionFind(int size) {
		parent = new int[size];
		// Initially all nodes are parent themselves
		for (int i = 0; i < size; ++i)
			parent[i] = i;
	}
	
	public int find(int index) {
		if (parent[index] == index) return index;
		parent[index] = find( parent[index] );
		return parent[index];
	}
	
	public void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);
		parent[p1] = p2;
	}
}


// A Node for elements of same values, used for Disjoint Set purpose (Indirectly)
class GroupNode {
	int x, y, index;
	public GroupNode(int[] pos, int index) {
		x = pos[0];
		y = pos[1];
		this.index = index;
	}
}




public class Rank_Transform_of_a_Matrix {

	public int[][] matrixRankTransform(int[][] matrix) {
       int m = matrix.length, n = matrix[0].length;
       int[][] res = new int[m][n];
       int[] maxRankInRow = new int[m];
       int[] maxRankInCol = new int[n];
       
       // Value -> Positions. We want them sorted ascendingly
       TreeMap<Integer, List<int[]>> mapOfSameValue = new TreeMap<>();
       // Fill the map
       for (int i = 0; i < m; ++i) {
    	   for (int j = 0; j < n; ++j) {
    		   mapOfSameValue.putIfAbsent(matrix[i][j], new ArrayList<>() );
    		   mapOfSameValue.get(matrix[i][j]).add( new int[] {i, j} );
    	   }
       }
       
       // Now for each of the same values, we have to perform union find
       for (int value: mapOfSameValue.keySet()) {
    	   List<int[]> positions = mapOfSameValue.get(value);
    	   List<GroupNode> nodes = new ArrayList<>( positions.size() );
    	   // Map the positions into GroupNode, which preserves their index
    	   for (int[] p: positions)
    		   nodes.add( new GroupNode(p, nodes.size()));
    	   
    	   UnionFind uf = new UnionFind(positions.size() );
    	   
    	   // Sort by row number to find same rows
    	   Collections.sort(nodes, (a,b)-> a.x - b.x);
    	   for (int i = 0; i < nodes.size() - 1; ++i)
    		   if (nodes.get(i).x == nodes.get(i+1).x)
    			   uf.union( nodes.get(i).index, nodes.get(i+1).index);
    	   
    	   // Sort by col number to find same columns
    	   Collections.sort(nodes, (a,b)-> a.y - b.y);
    	   for (int i = 0; i < nodes.size() - 1; ++i)
    		   if (nodes.get(i).y == nodes.get(i+1).y)
    			   uf.union( nodes.get(i).index, nodes.get(i+1).index);
    	   
    	   // Now they are grouped, perform operation by group
    	   Map<Integer, List<GroupNode>> mapByGroup = new HashMap<>();
    	   for (GroupNode gn: nodes) {
    		   int parent = uf.find(gn.index);
    		   mapByGroup.putIfAbsent(parent, new ArrayList<>());
    		   mapByGroup.get(parent).add(gn);
    	   }
    	   
    	   // For each of the groups, find their supposed ranks
    	   for (List<GroupNode> li: mapByGroup.values()) {
    		   int rank = 0;
    		   
    		   // Check out their max rank in row and column
    		   for (GroupNode gn: li) {
    			   rank = Math.max(rank, maxRankInRow[gn.x] + 1);
    			   rank = Math.max(rank, maxRankInCol[gn.y] + 1);
    		   }
    		   
    		   // Now we determined the correct rank, assign ranks
    		   for (GroupNode gn: li)
    			   res[gn.x][gn.y] = maxRankInRow[gn.x] = maxRankInCol[gn.y] = rank;
    	   }
       }
       return res;
    }
}
