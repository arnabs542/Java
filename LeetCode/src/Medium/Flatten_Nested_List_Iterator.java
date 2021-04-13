package Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

//https://leetcode.com/problems/flatten-nested-list-iterator/
/*
 * 	This is a Design, Stack problem
 * 
 * 	First, we need to know what is an iterator. An iterator is essentially just a structure that keeps a reference
 * 	to the original array, as well as a progress index. So when we call next(), it returns the element at progress index,
 * 	and increment the progress index.
 * 
 * 	Now, the elements may be another list as well. The problem is recursive in nature. Therefore, to emulate the effect
 * 	of recursion, we use Stack.
 * 
 * 	In my solution, I didn't realize that I can use built in iterator, therefore I used 2 stacks: One to store array and
 * 	another to store progress index.
 * 
 * 								   AB       C
 * 	Say I have the following list: [[1,2],3,[4,5]] where I marked each list with alphabet
 * 	At the first element, it is another list, so I store the outer list A index 0 into the stack so I don't lose reference to it,
 * 	and proceed to push B with index 0 as well. Now index 0 at B is a integer, so I am ok with current state. The value can 
 * 	be retrieved immediately
 * 
 * 	Stack: [ (A,0), (B,0) ] < top
 * 
 * 	When I call next(), 1 shall be returned, while incrementing the progress of B by 1:
 * 	Stack: [ (A,0), (B,1) ] < top
 * 
 * 	next() once more, we realize that the index goes out of bound for B. So pop B, it's time to progress A
 * 	Stack: [ (A,1) ] < top
 * 
 * 	next(), realize that index 2 is a nested list, so push A index 2 into stack, and also C index 0
 * 	Stack: [ (A,2), (C,0) ] < top
 * 
 * 	after 2 times next(), realize C out of bound, so pop C. Also when we try to proceed A, it is also out of bound,
 * 	so we are popping A as well. Now stack is empty
 * 	Stack: [] < top
 * 	That's how we know when it is ended.
 * 
 * 	-----------------------
 * 
 * 	Theres still a problem with above approach. See when a list is empty, we have to know to proceed!
 *	Eg: [ [], [], [1] ]
 *	Initially when we set up, it must already point at '1'!
 *
 *	Therefore when we push nested list, if we detect that the list is empty, we can call next() immediately
 *	to proceed.
 *
 *	Of course, saying is easier than action
 */




public class Flatten_Nested_List_Iterator {
	
	class NestedIterator implements Iterator<Integer> {

		Deque<List<NestedInteger>> stack;
		Deque<Integer> index;
		
		
	    public NestedIterator(List<NestedInteger> nestedList) {
	        stack = new ArrayDeque<>();
	        index = new ArrayDeque<>();
	        
	        pushStack(nestedList, 0);	
	    }

	    @Override
	    public Integer next() {
	    	List<NestedInteger> li = stack.pop();
	    	int idx = index.pop();
	    	int res = li.get(idx).getInteger();
	    	
	    	//	While the current list is still out of bound if we proceed, go up one level.
	    	while (!stack.isEmpty() && idx+1 >= li.size() ) {
	    		li = stack.pop();
	    		idx = index.pop();
	    	}
	    	
	    	//	If we finally found a list that not out of bound if proceed, then push to stack.
	    	if (idx+1 < li.size() )
	    		pushStack(li, idx+1);
	    	return res;
	    }

	    @Override
	    public boolean hasNext() {
	    	return !stack.isEmpty();
	    }
	    
	    
	    //	Smart pushing function. Detects nested list automatically. If the list pushed is empty list,
	    //	It is able to proceed automatically
	    private void pushStack(List<NestedInteger> li, int i) {
	    	stack.push(li);
	    	index.push(i);
	    	
	    	//	A list, either non empty (Integers inside), or empty
	    	if (!li.get(i).isInteger() ) {
	    		List<NestedInteger> inner = li.get(i).getList();
	    		
	    		if (inner.size() != 0) pushStack(inner, 0);		//	Non-empty, get inner list to push to stack
	    		else next();									//	Pop out the empty list and proceed to next one
	    	}
	    }
	}

}
