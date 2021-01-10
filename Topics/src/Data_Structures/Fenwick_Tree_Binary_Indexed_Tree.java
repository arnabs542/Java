package Data_Structures;

/*
 * 	Fenwick Tree, also called Binary Indexed Tree, is essentially a Array implemented implicit Binary tree that
 * 	allow us to query a aggregation data (like sum) of elements in an array in O(log N) time as well as updating it
 * 	in O(log N) time as well.
 * 
 * 	-------------------------------------------------------------
 * 
 * 	Say, we want to query the sum of elements in an array from index i to j. How we can do it?
 * 
 * 	An naive implementation is just a linear scan from i to j index. This requires O(N) time for query, and if the array
 * 	updates, O(N) is needed as well.
 * 
 * 	If we utilize extra O(N) space to create a prefix sum array (Cumulative sum), we reduce the O(N) query time down to
 * 	O(1) only. However, if any element updates, it will still take O(N) time to update, since every element after the
 * 	updated element at i index will need to be updated.
 * 
 * 	There exists some nice data structure like Segment Tree which specializes in querying min, max and sum. It provides
 * 	O(log N) query and O(log N) updating. However implementation is hard, and may use up greater deal of space! Is there
 * 	anything better?
 * 
 * 	--------------------------------------------------------------
 * 
 * 	Introducing Fenwick Tree / Binary Indexed Tree. The implementation is easy, uses only O(N) space, and querying and
 * 	updating is only O(log N).
 * 	As long as the function we are trying to query (like simple sum of elements) are inversible, we are ok to use
 * 	fenwick tree.
 * 
 * 	
 * 	The idea behind this is:
 * 		
 * 		Similar to Segment tree, we will preprocess the original array. The produced array, say T, will exactly be size
 * 		N (or N+1 for 1-index based implementation).
 * 	
 * 		Each index of T, say i, will contain the sum of elements from indices ranged ( g(i), i ], where g(i) is some function
 * 		that when we pass in i, will be not greater than i, but > 0. Essentially it will cover a partial range in the
 * 		array,
 * 
 * 		So, say to obtain some sum from 0 to 7. Say:
 * 			g(7) = 6, if we visit T[7] will get us sum of elements from index 7 to 7	 (6,7]
 * 			g(6) = 4, if we visit T[6] will get us sum of elements from index 5 to 6	 (4,6]
 * 			g(4) = 0, if we visit T[4] will get us sum of elements from index 1 to 4	 (0,4]
 * 
 * 		If our array is 1 indexed, the problem is solved by adding those partial sums!
 * 
 * 		
 *	The same idea goes for updating values. Since the tree nodes are just partial sums, ideally the nodes that involve
 *	the updated index i will only be O(log N) that much. 
 *
 *	-----------------------------------------------------------------
 *
 *	How do we implement the idea above? Fenwick comes up with the ingenious idea of using binary representation.
 *
 *	Essentially, in the binary representation, the length of the range is encoded in Least Set Bit (The 1 bit at rightmost)
 *	while the other bits represent the starting position of the range. See:
 *
 *			1 -> 001	Equivalent to 0 + 2^0. LSB is 2^0. Range start from 0 and spans 1 element. Thus [0,0]
 *			2 -> 010	Equivalent to 0 + 2^1. LSB is 2^1. Range start from 0 and spans 2 element. Thus [0,1]
 *			3 -> 011	Equivalent to 2^1 + 2^0. LSB is 2^0. Range start from 2 and spans 1 element. Thus [2,2]
 *			4 -> 100	Equivalent to 0 + 2^2. LSB is 2^2. Range start from 0 and spans 4 element. Thus [0,3]
 *			5 -> 101	Equivalent to 2^2 + 2^0. LSB is 2^0. Range start from 4 and spans 1 element. Thus [4,4]
 *			6 -> 110	Equivalent to 2^2 + 2^1. LSB is 2^1. Range start from 4 and spans 2 element. Thus [4,5]
 *	
 *	With this in mind, let's see how can we get full sum from index 1 to 6. (Assume 1-based index)
 *
 *			Start by getting value at 6. It is sum of elements from [4,5]
 *			Now, turn the LEAST SET BIT to 0! 110 -> 100 (4). Next visit 4.
 *			Get value at 4. It is sum of elements from [0, 3]
 *			Turn the LEAST SET BIT to 0. Now it becomes 000. So we stop.
 *
 *			By summing the ranges [4,5] + [0, 3], we essentially obtained sums from index 1 to 6!
 *
 *	Theres a neat bitwise trick to do this:
 *
 *			x & -x		->	Retrieves a mask where the Least Set Bit is 1. Others is 0.
 *			x - (x&-x)	->	Minus x by the mask. Since the Least Set Bit is 1, it will turn LSB to 0.
 *
 *	
 *	The reverse has to be done to updating values. It is in reversed direction, where every nodes involving the updated
 *	value must be updated. Instead of turning off the Least Set Bit, we add 1 to the Least Set Bit.
 *	As for why this works, I am still unclear lmao.
 *
 *			x & -x 		->	Retrieves a mask where the Least Set Bit is 1. Others is 0.
 *			x + (x&-x)	->	Add x by the mask. Eg: 1010 -> 1100
 *
 */



public class Fenwick_Tree_Binary_Indexed_Tree {
	
	int[] tree;
	
	//	O(N log N) preprocessing.
	public Fenwick_Tree_Binary_Indexed_Tree(int[] arr) {
		tree = new int[arr.length + 1];
		
		for (int i = 0; i < arr.length; ++i) 
			update(i, arr[i]);
	}
	
	public int querySum(int endPos) {
		if (endPos < 0) return 0;
		if (endPos >= tree.length - 1) return querySum( tree.length - 2 );
		
		++endPos;		//	1-index based implementation
		int res = 0;
		
		while (endPos > 0) {
			res += tree[endPos];
			endPos -= (endPos & -endPos);	//	Turn LSB off
		}
		return res;
	}
	
	public int querySum(int startpos, int endpos) {
		if (endpos < startpos) return 0;
		
		return querySum(endpos) - querySum(startpos - 1);
	}
	
	
	public boolean update(int pos, int delta) {
		if (pos < 0 || pos >= tree.length - 1) return false;
		
		++pos;
		
		while (pos < tree.length) {
			tree[pos] += delta;
			pos += (pos & -pos);
		}
		return true;
	}
	
	
	
	
	public static void main(String[]args) {
		int[] arr = {1,2,3,4,5};
		
		Fenwick_Tree_Binary_Indexed_Tree bit = new Fenwick_Tree_Binary_Indexed_Tree(arr);
		
		
		for (int i = 0; i < 5; ++i) {
			bit.update(i, 5);
		}
		for (int i = 0; i < 5; ++i) {
			for (int j = i; j < 5; ++j) {
				System.out.println( "i=" + i + " j=" + j + " - " + bit.querySum(i, j) );
			}
		}
		
	}
}
