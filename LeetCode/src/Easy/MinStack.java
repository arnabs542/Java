package Easy;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/min-stack/
/*
 * This is a Stack problem, which you can implement also using linked list.
 * 
 * The idea is to have more information in addition to the values being added. See;
 * In a stack, we pop only the latest element.
 * Therefore, for each and every node, we could easily save 1 more information: What is the minimum element in the stack
 * when this node is at the top of the stack? 
 * 
 * In my implementation, I have 2 stacks: One for storing normal values, and one for storing minimum element.
 * When I push a value, what I'll do on minElem stack is to determine what is the minimum value in the stack when current
 * element is on the top of the stack, which is determined by
 * 
 * 		min( minElem.peek, currElem )
 */


public class MinStack {
	Deque<Integer> stack;
	Deque<Integer> minElem;

    public MinStack() {
        stack = new ArrayDeque<>();
        minElem = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.push(val);
        minElem.push( minElem.isEmpty()? val: Math.min(minElem.peek(), val) );
    }
    
    public void pop() {
        stack.pop();
        minElem.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minElem.peek();
    }
}