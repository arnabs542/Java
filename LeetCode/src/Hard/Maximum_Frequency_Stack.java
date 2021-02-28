package Hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/maximum-frequency-stack/
/*
 * 	This is a HashMap, Heap / Stack problem.
 * 
 * 	The first solution is, we have to sort the values, so that when we pop, the correct element returns.
 * 	Immediately, the Heap Data structure comes to mind.
 * 
 * 	We'll have a frequency table that maps values -> current frequency in the virtual stack. The frequency of
 * 	a value increments when pushed, and decrements everytime it is selected to be popped.
 * 
 * 	Next, have a heap that stores tuples of 3 values - (value, index, frequency). The heap is going to sort primarily
 * 	by the frequency of the value at that time 
 * 	**No need to update all previous values, they keep their state**
 * 	Only if the frequency are same, the sorting fall back to sort by index
 * 
 * 	Time commplexity for both push and pop is approximately O(log N). Space is O(N) as it scales linearly with the input
 * 
 * 	----------------------------------------------------------------------------
 * 
 * 	Notice that we are sorting by frequency. Only when frequency ties with another, we fall back to their insertion Stack
 * 	order. Can't we use the property of Stack in any way? Yes we do.
 * 
 * 	Keep a Map of Stacks. The Map will map from (frequencies)->(Stack), and the stack will keep the values in their insertion
 * 	order.
 * 
 * 	Have a example: (Credit to leetcode user - softwareshortcut)
 * 
 * 	// Stack Trace
	// push(5) - { [key:1, value: Stack<5>] }
	// push(7) - { [key:1, value: Stack<5,7>] }
	// push(5) - { [key:1, value: Stack<5,7>], [key:2, value: Stack<5>] }
	// push(7) - { [key:1, value: Stack<5,7>], [key:2, value: Stack<5,7>] }
	// push(4) - { [key:1, value: Stack<5,7,4>], [key:2, value: Stack<5,7>] }
	// push(5) - { [key:1, value: Stack<5,7,4>], [key:2, value: Stack<5,7>], [key:3, value: Stack<5,7>] }
	// pop()   - { [key:1, value: Stack<5,7,4>], [key:2, value: Stack<5,7>] } -> 5
	// pop()   - { [key:1, value: Stack<5,7,4>], [key:2, value: Stack<5>] } -> 7
	// pop()   - { [key:1, value: Stack<5,7,4>] } -> 5
	// pop()   - { [key:1, value: Stack<5,7>] } -> 4
 * 
 * 	Since we are using Map for implementation, we need to keep track of the highest level for popping purposes. Also, we need
 * 	a frequency table for pushing purpose as well.
 * 
 *	We are using Stack, so popping and pushing are both time complexity of O(1). Space is also O(N) since it scales linearly
 *	with the number of input
 */

public class Maximum_Frequency_Stack {
	
	//	Heap solution - Sorting tuples of <value, index, frequency>
	class FreqStack {
		Map<Integer, Integer> freq;
		PriorityQueue<int[]> heap;
		int index;
		
		public FreqStack() {
	        freq = new HashMap<>();
	        heap = new PriorityQueue<>( (x,y)-> {
	        	if (x[2] == y[2]) return y[1] - x[1];
	        	return y[2] - x[2];
	        });
	        index = 0;
	    }
	    
	    public void push(int x) {
	        int f = freq.getOrDefault(x, 0) + 1;
	        heap.add( new int[] {x, index++, f} );
	        freq.put(x, f);
	    }
	    
	    public int pop() {
	        int[] popped = heap.poll();
	        freq.compute(popped[0], (k,v)-> v-1);
	        return popped[0];
	    }
	}
	
	
	
	//	O(1) push and O(1) pop - Map of Stack solution
	class FreqStack2 {
		Map<Integer, Integer> freq;
		Map<Integer, Deque<Integer>> stacks;
		int height;
		
		FreqStack2() {
			freq = new HashMap<>();
			stacks = new HashMap<>();
			height = 0;
		}
		
		public void push(int x) {
			int f = freq.getOrDefault(x, 0) + 1;
			freq.put(x, f);
			stacks.putIfAbsent(f, new ArrayDeque<>());
			stacks.get(f).push(x);
			height = Math.max(height, f);
		}
		
		public int pop() {
			//	This stack is finished. So go down one level
			if (stacks.get(height).isEmpty() ) --height;
			int pop = stacks.get(height).pop();
			freq.compute(pop, (k,v)-> v-1);
			return pop;
		}
	}

}
