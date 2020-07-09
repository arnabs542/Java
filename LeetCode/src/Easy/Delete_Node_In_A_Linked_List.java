package Easy;

//https://leetcode.com/problems/delete-node-in-a-linked-list/

/*
 * 	The question said that remove the given node without reference to its parent.
 * 	The key is to realize that the node MUST NOT be a tail; it must has at least one next node
 * 
 * 	To do this simply we just copy the next node's value into our current node, to pretend the node is the "next" node, and delete the actual
 * 	next node by setting this node.next to node.next.next
 */
class ListNode {
	int val;
	ListNode next;
	ListNode (int val) {this.val = val; };
}

public class Delete_Node_In_A_Linked_List {
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
    }
}
