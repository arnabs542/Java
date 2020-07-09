package Data_Structures;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Vertex (or called Nodes) is simply an object in a graph/ tree which contains data (or name) and pointers that points to other linked nodes
 * 	Optionally, it can have a property isVisited, implicating whether this node had been visited before or not
 * 
 */

public class Vertex<D> {
	
	D data;
	List<Vertex<D>> adjacent;
	boolean isVisited;
	
	public Vertex(D data) {
		this.data = data;
		adjacent = new LinkedList<Vertex<D>>();
		isVisited = false;
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public boolean addAdjacent(Vertex<D> n) {
		return adjacent.add(n);
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAdjacent(Vertex<D>... n) {
		for (Vertex<D> v: n) {
			addAdjacent(v);
		}
		return true;
	}
	
	public List<Vertex<D>> getAdjacentList() {
		return adjacent;
	}
	
}
