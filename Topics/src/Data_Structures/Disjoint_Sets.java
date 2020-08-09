package Data_Structures;



/*
 * 	DISJOINT SETS
 * 
 * 	Disjoint sets are sets that has no element in common to each other. (1,2,3) and (4,5,6) are valid examples of disjoint sets
 * 	while (1,2,3) and (3,4,5) are NOT
 * 
 *	Disjoint sets can be thought as a graph operation. Let's say we have 2 graphs like above, we can introduce a NEW EDGE,
 *	(3,4) which connects node 3 and node 4. Now, Both the sets will be unified (Union)! How do we do that to represent it as
 *	1 big set of (1,2,3,4,5,6)?
 *
 *	First of all, when we have a set, we need a representation of that set (Kind of like a leader of the set). Let's say the representation
 *	of set (1,2,3) is Node 1 and set (4,5,6) is Node 4. Note that all the elements can be thinked as nodes in the graph.
 *
 *	Therefore, each node should have a pointer that points to a path to the representor. At the representor itself, the pointer can
 *	either points to itself, or be null (no information then), which tells the iterator that this node is the representation of the
 *	set.
 *
 *	So for example, the graph for set 1 would look like this:
 *		(1) <- (2) <- (3) 		OR MORE BALANCED,			(1)
 *														    ^ ^
 *															/ \
 *														  (2)  (3)
 *
 *	(Note that this will be a directed graph (One way direction only, which only leads to the representation node)
 *
 *	Now, if we want to unify the 2 sets, the following operation will be performed:
 *		>	Find the representation node of both sets
 *			(IF the representation node are same for both sets, THEY ARE IN SAME SET!, Introducing this edge will lead to a cycle!)
 *		>	We need to connect one parent to another. To determine who should be parent, we should apply some kind of ranking data
 *			(Like which representation's set has more nodes, then it shall be parent)
 *		>	Update the parent node's rank, and the ex-parent node's pointer to point to the new parent
 *
 *	Let's say that the choosen parent is (1) when joining sets (1,2,3) and (4,5,6)
 *	So when we try to look up the representation of any elements of 1,2,3,4,5,6, it shall always return (1), which means they are
 *	all in the same set!
 *
 *	To optimize the lookup time, During lookup operation (find), we will include a additional step: Connect the child node directly
 *	to the representation node. This way the depth of the tree will keep being minimized until ideally it has depth 2 only
 *
 *	--------------------------------------------------------------------------------
 *
 *	This can be implemented usign Nodes, or Arrays
 *
 *	Using array, each index represent one node, and the value in the array is the pointer to another node
 *	For parent node, the value shall be negative, and the magnitude of the value shall be the rank.
 *	(Eg: Value of -4 means it is a parent node, and having 4 total Nodes in the set)
 *	(Eg: Value of 3 means it is a child node, and the node at index 3 is its parent node)
 *
 *	This way, the array shall be initialized with all having values -1, as initially each element form a set of size 1 themselves, and
 *	they are parent of that set.
 *	
 */

public class Disjoint_Sets {
	
	private int[] pointers;
	
	public Disjoint_Sets(int size) {
		pointers = new int[size];
		for (int i = 0; i < size; i ++ ) pointers[i] = -1;
	}
	
	//Note that each node is shifted to left by 1 to fit 0 indexed array. So When finding node 1, shall be node 0 in array
	//The value in array shall represent the actual numbering of node itself. Only when accessing pointers it is shifted
	public int findRepresentative(int node) {
		
		//If attempting to find invalid node, then return Invalid Indicator;
		if (node > pointers.length || node <= 0 ) return Integer.MIN_VALUE;
		
		//Keep following the graph until reach at the representative node
		int curr = node;
		while ( pointers[curr - 1] > 0) {
			curr = pointers[curr - 1];
		}
		
		//Path Compression Optimization
		//If the node is parent itself, executing statement below will change the node from negative (Parent indicator) to
		//node itself!
		if (node != curr)
			pointers[node - 1] = curr;
		
		return curr;
	}
	
	public boolean union(int node1, int node2) {
		int leader1 = findRepresentative(node1);
		int leader2 = findRepresentative(node2);
		
		//Invalid node to union
		if (leader1 == Integer.MIN_VALUE || leader2 == Integer.MIN_VALUE ) return false;
		
		//Cycle Detected
		if (leader1 == leader2 ) return false;
		
		//Obtain both the representative's negative value
		int rank1 = pointers[leader1 - 1];
		int rank2 = pointers[leader2 - 1];
		boolean isRank2High = rank1 >= rank2;
		
		//Obtain the number of nodes carried by the smaller set
		int numNodes = pointers[ (isRank2High? leader1: leader2) - 1];
		//Set the smaller set's representative pointer to the larger set's representative
		pointers[ (isRank2High? leader1: leader2 ) - 1 ] = (isRank2High? leader2: leader1 );
		//Increase the larger set's representative's rank (Number of nodes carried)
		pointers[ (isRank2High? leader2: leader1 ) - 1 ] += numNodes;
		
		return true;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < pointers.length; i ++ ) {
			str = str.concat( (i + 1) + ": " + pointers[i] + " || ");
		}
		return str.concat("\n");
	}

}
