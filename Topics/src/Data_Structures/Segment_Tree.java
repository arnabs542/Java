package Data_Structures;

/*
 * 
 */

public class Segment_Tree {

	static int arrlength = -1;
	static int[] segmentTree = null;
	
	public static void createSegmentTree(int[] arr ) {
		segmentTree = new int[ getPower2(arr.length) * 2 - 1];
	
		createRecurse(arr, 0, arr.length - 1, 0);
		arrlength = arr.length;
	}
	
	private static int minimumRange(int left, int right) {
		return searchQuery(segmentTree, 0, arrlength - 1, left, right, 0);
	}
	
	public static int searchQuery(int[] arr, int left, int right, int qleft, int qright, int pos) {
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
	
	}
	
	private static void createRecurse(int[] arr, int left, int right, int pos) {
		if (left == right) {
			segmentTree[pos] = arr[left];
			return;
		}
		
		int mid = left + (right - left) / 2;
		
		createRecurse(arr, left, mid, 2*pos + 1);
		createRecurse(arr, mid + 1, right, 2*pos + 2);
		segmentTree[pos] = Math.min( segmentTree[2*pos + 1], segmentTree[2*pos + 2] );
		
	}
	
	
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
		
		System.out.println("\n" + minimumRange(0, 5) );
	}
	
}
