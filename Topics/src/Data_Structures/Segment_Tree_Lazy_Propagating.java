package Data_Structures;

/*
 * 	Segment tree is good by itself, but suppose the segment tree has to be updated frequently:
 *  We will have to travel through the tree, reach until the bottom (Where the leaf node representing one individual element resides),
 * 	update the value for it, and go back up, comparing the child nodes value and updating the node each time. This can be quite inefficient
 * 	if the updating is done very frequently.
 * 
 * 	One optimization is to implement Lazy Propagation. This technique requires an additional tree which is same size as the segment tree
 * 	itself. Initially, the lazy tree will be initialized with all values being 0, meaning no update has to be done.
 * 
 * 	When a range of indexes has to be updated with some value, we would perform a search similar to querySearch, but this time the key
 * 	is when the query range TOTAL overlaps the current range.
 * 	Let's say the query type is MINIMUM and each value has to be incremented by constant value. Once we found the range that TOTAL overlaps
 * 	the query range, then we know for sure each value is going to be incremented by that constant, and the MINIMUM value for that range
 * 	will be incremented by the constant as well. Therefore, change the value of current node by the constant.
 * 	We won't be traveling further down the tree to update the further sub range's value. Instead, we will put the constant value to be
 * 	updated into the lazy tree. The value of the lazy tree means the value awaiting to be updated for the respective node in the segment
 * 	tree.
 * 	Then, back to the upper layer of recursion, compare the child nodes, which is updated, and update the node's value, and return.
 * 
 * 	With this, every time we travel to a certain node, we have to check if the node's value were to be updated first, before even
 * 	performing operations on it. If the node value was found to be updated from lazy tree, update the value, pass the update to
 * 	child nodes (Through lazy tree), and reset the current node's lazy tree value to 0, meaning it was well updated.
 * 
 * 
 * 	Note the operation may not always be just adding a constant though. If the segment Tree was for SUM and the values are incremented
 * 	by a constant, then the SUM of the nodes would be increased by how much elements that node covers (Difference of range + 1), times constant	
 */

//Example implementation of lazy propagating segment tree of type MINIMUM
public class Segment_Tree_Lazy_Propagating {

	static int[] segmentTree = null;
	static int[] lazyTree = null;
	static int length = -1;
	
	public static void createSegmentTree(int[] arr) {
		length = arr.length;
		
		segmentTree = new int[getPower2(length) * 2 - 1 ];
		lazyTree = new int[ segmentTree.length ];
		
		createSegmentTree(arr, 0, length - 1, 0);
	}
	
	public static int querySearch(int qleft, int qright) {
		return querySearch(0, length - 1, qleft, qright, 0);
	}
	
	public static void changeRange(int qleft, int qright, int delta) {
		changeRange(0, length - 1, qleft, qright, 0, delta);
	}
	
	//------------------------------------------
	//	PRIVATE HELPER METHODS
	//------------------------------------------
	
	private static void createSegmentTree(int[] arr, int left, int right, int pos) {
		//Leaf node. Just put the individual element into the tree and return
		if (left == right) {
			segmentTree[pos] = arr[left];
			return;
		}
		
		//Not leaf node. Left child node and right child node has to be constructed first before deciding the value of this node
		int mid = left + (right - left) / 2;
		createSegmentTree(arr, left, mid, pos*2 + 1);
		createSegmentTree(arr, mid+1, right, pos*2 + 2);
		
		segmentTree[pos] = Math.min( segmentTree[pos*2 + 1],
				segmentTree[pos*2 + 2] );
	}	//end of createSegmentTree()
	
	
	private static int querySearch(int left, int right, int qleft, int qright, int pos) {
		
		ensureUpdate(pos, left == right);
		
		//Total overlap
		if (qleft <= left && right <= qright) {
			return segmentTree[pos];
		}
		//No overlap
		if (qright < left || qleft > right )
			return Integer.MAX_VALUE;
		
		int mid = left + (right - left) / 2;
		
		return Math.min( querySearch(left, mid, qleft, qright, pos*2 + 1),
						 querySearch(mid + 1, right, qleft, qright, pos*2 + 2) );	
	}	//end of querySearch()
	
	
	private static void changeRange(int left, int right, int qleft, int qright, int pos, int delta) {
		
		ensureUpdate(pos, left == right);
		
		//Total overlap. All node values below this were to be updated. Therefore update this node's value as well as put awaiting updates
		//into lazy tree. Return.
		if (qleft <= left && right <= qright) {
			segmentTree[pos] += delta;
			if (left != right) {
				lazyTree[pos*2 + 1] += delta;
				lazyTree[pos*2 + 2] += delta;
			}
			return;
		}
		//No overlap. No updates to any of node values
		if (qright < left || qleft > right) return;
		
		//Partial overlap. Pass the incrementing to both sides since some nodes have to be updated while some don't
		int mid = left + (right - left) / 2;
		changeRange(left, mid, qleft, qright, pos*2 + 1, delta);
		changeRange(mid + 1, right, qleft, qright, pos*2 + 2, delta);
		
		segmentTree[pos] = Math.min( segmentTree[pos*2 + 1],
									 segmentTree[pos*2 + 2] );
	}	//end of changeRange()
	
	
	private static void ensureUpdate(int pos, boolean isLeaf) {
		//No updates
		if (lazyTree[pos] == 0 ) return;
		
		//Update segment tree value
		segmentTree[pos] += lazyTree[pos];
		
		if (!isLeaf) {
			lazyTree[pos*2 + 1] += lazyTree[pos];
			lazyTree[pos*2 + 2] += lazyTree[pos];
		}
			
		lazyTree[pos] = 0;
	}	//end of ensureUpdate()
	
	
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
	}	//end of getPower2()
	
	
	private static void printTrees() {
		System.out.print("Segment Tree: ");
		for (int i: segmentTree)
			System.out.print(i + " ");
		System.out.println();
		
		System.out.print("Lazy Tree: ");
		for (int i: lazyTree)
			System.out.print(i + " ");
		System.out.println();
	}
	
	//------------------------------------------------------------------------------
	
	public static void main(String[]args) {
		int[] arr = {-1, 2, 4, 1, 7, 1, 3, 2};
		
		createSegmentTree(arr);
		printTrees();
		
		changeRange(0, 3, 3);
		printTrees();
		
		changeRange(0, 3, 1);
		printTrees();
		
		changeRange(0, 0, 2);
		printTrees();
		
		System.out.println( querySearch(3, 5) );
		printTrees();
		
	}
	
}
