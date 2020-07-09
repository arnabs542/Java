package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import Data_Structures.Vertex;

/*	Depth first search utilizes the data structure Stack to achieve the search
 * 	
 * 	Firstly, the root node is searched and recorded. Then, if a new node connection is explored, the exploration of current node
 * 	is suspended and added to the stack, and the exploration proceeds to the explored node. This process continues until there is
 * 	nothing to be explored (leaf), then pop the stack to backtrack, and continue exploring unexplored node
 * 
 * 	To implement using stack, first we add the root node to stack
 * 	Then, for each adjacent node that the current node have, add it to the stack, while having this current stack added to visited record
 * 		(Alternatively, if the vertex itself has the isVisited attribute, you may use that as well)
 * 	After the iteration is complete (adding to stack), pop the top node from the stack and repeat this process until the stack is empty
 * 
 * 	There is mainly 2 ways to implement depth first search: Using recursion or using Stacks
 * 	Using stacks utilizes heap memory (dynamic) while recursion utilizes stack memory
 * 
 */


public class Depth_First_Search {
	
	Stack<Vertex<Integer> > stack;
	List<Vertex<Integer> > visited;
	
	public Depth_First_Search () {
		stack = new Stack<>();
		visited = new ArrayList<>();
	}
	
	public void printDFS(Vertex<Integer> rootNode) {
		stack.push(rootNode);
		
		while (!stack.isEmpty() ) {
			Vertex<Integer> thisVertex = stack.pop();
			
			if (!visited.contains(thisVertex) ) {
				visited.add(thisVertex);
				System.out.print(thisVertex + " ");
			}
			
			for (Vertex<Integer> n: thisVertex.getAdjacentList() ) {
				if (!visited.contains(n) ) 
					stack.push(n);
			}
		}
	}
	
}	//end of Depth_First_Search class
