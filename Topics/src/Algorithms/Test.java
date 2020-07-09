package Algorithms;

import java.util.ArrayList;

import Data_Structures.Vertex;

public class Test {

	public static void main(String[]args) {
		ArrayList<Vertex<Integer>> vArr = new ArrayList<Vertex<Integer>>();
		for (int i = 0; i < 7; i ++ ) {
			vArr.add(new Vertex<Integer>(i) );
		}
		vArr.get(0).addAdjacent(vArr.get(1), vArr.get(2), vArr.get(3) );
		vArr.get(1).addAdjacent(vArr.get(0) );
		vArr.get(2).addAdjacent(vArr.get(6), vArr.get(0) );
		vArr.get(3).addAdjacent(vArr.get(0), vArr.get(5), vArr.get(4) );
		vArr.get(4).addAdjacent(vArr.get(6), vArr.get(3), vArr.get(5) );
		vArr.get(5).addAdjacent(vArr.get(5), vArr.get(4) );
		vArr.get(6).addAdjacent(vArr.get(4), vArr.get(2));
		
		Depth_First_Search dfs = new Depth_First_Search();
		dfs.printDFS(vArr.get(0) );
		
		/*
		 * 			0 ------ 3 ---- 4
		 * 			/\		 |	 /	|
		 * 		   /  \      |  /   | 
		 * 		  1   2      5      6
		 * 				\		   /
		 * 				 ----------
		 */
		
	}
	
}
