package Data_Structures;

import java.util.Arrays;

/*
 * 	Segment Tree is a binary tree based data structure which is implemented preferably using an array. Its use is to enable efficient
 * 	querying of an array like the max, min or sum without doing it in O(N) time. In fact, using a segment tree, the max, min or sum
 * 	of the entire or part of the array can be found in O(log n) time. This is great if the query is asked many times over and over again.
 * 
 * 	First of all, the size of the segment tree were to be determined. Since it basically is binary tree, it will be the
 * 	nextPow2( arr.length) * 2 - 1 sized. Eg: The next power 2 of 9 is 16, and the next power 2 of 8 is 8 itself.
 * 	Therefore for array of size 9, the size of tree should be 16 * 2 - 1 = 31.
 * 	In case where the arr.length is not exactly a power of 2, some of the array spaces will be ignored and never accessed
 * 	(Those spaces are non-existent nodes in the binary tree)
 * 
 * 	Each node in the segment tree will represent the answer for the query in certain range.	The head or root node, will be the query answer
 * 	for the entire array, which is index from 0 to arr.length - 1.
 * 	For example, for the array [1,2,3,4,5,6] of length 6, and segment tree is based on query of maximum value, then the root node
 * 	will represent the answer to "The maximum value for the array from index 0 to 5", which should hold 6
 * 
 * 	The child nodes will have the ranges split in half. From root node of example above, the range is from index 0 to 5. If split in half,
 * 	then the left child node shall represent the query answer for range 0 to 2, and right child node shall represent answer for range
 * 	3 to 5. This propagation continues until the left and right range become equal, which represent only single element in the array.
 * 
 * 	Both the construction and querying of segment tree is mainly recursive.
 * 	During construction, we shall check if the left range is equal to the right range. If it is, that means the range only consisted of a
 * 	single element and the answer to the query is the element itself. Therefore we would just place the element value in this node
 * 	and return.
 * 	If the left range is not equal to right range, then the left child and the right child needs to be constructed first using recursive call, 
 *  then only compare the left child and right child's value to obtain the answer for this node.
 *  
 *  
 *  When doing query searches, we have to keep track of the query left range, query right range, current node's left range and right range,
 *  and the current position in the segment tree.
 *  The query range will always be in one of three situations below:
 *  	-Partial Overlap: The query range is partially overlapping the current node range. We need to search both the left and right child,
 *  					  and return the minimum/maximum/sum of the two (Based on type of query)
 *  	-No Overlap: The query range does not overlap with current node range at all. This node value has nothing to do with the current
 *  				 query. So return either some MAX value or MIN value or special value (Based on type of query)
 *  	-Total Overlap: The query range is fully covering the current node range. There is no need to search further.
 *  					 Therefore just return this node's value to be further compared by the upper layer recursive call.
 *  
 *  Every call, we have to calculate the node's left range, node's right range, and the respective position (index) of the segment tree
 *  of the node, and should perform respective action based on the 3 situations above.
 *  
 *  If we want to change a particular value in the array, this could be done by traversing to the node which represent that single element
 *  alone, updating the value, and for parent nodes, compare the child nodes again to update the value.
 */


//Example implementation of a minimum Segment tree. Provides minimum value as query type
public class Segment_Tree {

	static int arrlength = -1;
	static int[] segmentTree = null;
	
	
	public static void createSegmentTree(int[] arr ) {
		segmentTree = new int[ getPower2(arr.length) * 2 - 1];
	
		createSegmentTree(arr, 0, arr.length - 1, 0);
		
		arrlength = arr.length;
	}
	
	public static int minimumRange(int left, int right) {
		return searchQuery(segmentTree, 0, arrlength - 1, left, right, 0);
	}
	
	public static void changeValue(int pos, int newVal) {
		changeValue(pos, newVal, 0, arrlength - 1, 0);
	}
	
	//----------------------------------------------------------------------------------------------------
	// PRIVATE HELPER METHODS
	//----------------------------------------------------------------------------------------------------
	
	private static int searchQuery(int[] arr, int left, int right, int qleft, int qright, int pos) {
		System.out.println("Search " + left + ", " + right);
		
		//Total overlap. Return this value in array is enough
		if (left >= qleft && qright >= right) {
			System.out.println("Return TOTAL " + arr[pos]);
			return arr[pos];
		}
		//No overlap. Return max/min or special value based on the segment tree purpose
		if (qleft > right || qright < left) {
			System.out.println("No overlap MAX");
			return Integer.MAX_VALUE;
		}
		
		//Partial overlap. Find both sides
		int mid = left + (right - left) / 2;
		
		int res = Math.min( searchQuery(arr, left, mid, qleft, qright, pos*2 + 1 ),
						 searchQuery(arr, mid + 1, right, qleft, qright, pos*2 + 2) );
		System.out.println("Return PARTIAL " + res);
		return res;
	
	}	//end of searchQuery()
	
	private static void createSegmentTree(int[] arr, int left, int right, int pos) {
		if (left == right) {
			segmentTree[pos] = arr[left];
			return;
		}
		
		int mid = left + (right - left) / 2;
		
		createSegmentTree(arr, left, mid, 2*pos + 1);
		createSegmentTree(arr, mid + 1, right, 2*pos + 2);
		segmentTree[pos] = Math.min( segmentTree[2*pos + 1], segmentTree[2*pos + 2] );
		
	}	//end of createSegmentTree()
	
	private static void changeValue(int pos, int val, int left, int right, int treepos) {
		//Total overlap (The position is found, update the value)
		if (left == right && left == pos) {
			segmentTree[treepos] = val;
			return;
		}
		//No overlap. Just return this value to be compared
		if (left > pos || right < pos) return;
		
		//Inside range. Find out which to find and compare them
		int mid = left + (right - left ) / 2;
		
		if (pos > mid) changeValue(pos, val, mid + 1, right, treepos*2 + 2);
		else changeValue(pos, val, left, mid, treepos*2 + 1);
		
		segmentTree[treepos] = Math.min( segmentTree[treepos*2 + 1], segmentTree[treepos*2 + 2] );
		
	}	//end of changeValue()
	
	
	//Given argument i, will return the nearest greater power 2 of i. Eg: i is 9, the nearest greatest power of 2 is 16. If i is 16, return 16
	private static int getPower2(int i) {
		if (i <= 0) return 0;
		int copy = i;
		
		int count = 0;
		while (i > 0) {
			i >>>= 1;
			count ++;
		}
		int potential = (1 << count);
		
		return (potential / copy == 2)? copy: potential;
	}
	
	//----------------------------------------------------------------------------------------------------------------
	
	public static void main(String[]args) {
		int[] arr = {-1, 3, 4, 0, 2, 1};
		createSegmentTree(arr);
		
		for (int i: segmentTree) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		System.out.println( minimumRange(0, 5) );
		
		changeValue(3, -5);
		
		for (int i: segmentTree) {
			System.out.print(i + ", ");
		}
		
		System.out.println( minimumRange(0, 5) );
	}
	
}
