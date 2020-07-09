package Algorithms;

import java.util.LinkedList;

/*
 * 	Breadth first search is a searching algorithm for a tree, which searches layer by layer.
 * 	It utilizes a Queue to achieve the layer by layer searching.
 * 	Starting out from the starting node, it searches the starting node throughly. If a new node is encountered, it is
 * 		1. Recorded in the search result
 * 		2. Added (enqueued) to the queue
 * 	Once the exploration on the node is finished, it dequeue the queue and use that newly dequeueud node to explore again.
 * 
 *	Eg of this implementation is in Binary tree, binary search tree etc..
 */

class Node{
    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}

public class Breadth_First_Search {
	
	static void levelOrder(Node root){
		LinkedList<Node> list = new LinkedList<>();
		list.add(root);
		
		while (!list.isEmpty() ) {
			list.add(list.peek().left );
			list.add(list.peek().right );
			
			System.out.print(list.poll().data + " ");
		}	
	}
	
}
