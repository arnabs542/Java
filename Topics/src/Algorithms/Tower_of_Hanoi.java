package Algorithms;

import java.util.Scanner;
import java.util.Stack;

/*
 * 	Tower of Hanoi is a classical algorithm problem, which is best solved via recursion approach (Although there are
 * 	also iterative method out there)
 * 
 * 	You have 3 pillars A, B and C, and N rings on the pillar A. The Rings are the largest at bottom, smallest at top.
 * 	You want to move all of them to pillar C, such that you cannot have larger ring on top of smaller ring at any time.
 * 	How you would do that?
 * 
 * 	Turns out, the recursive solution can be easily created by following this algorithm:
 * 
 * 	>	You have N rings. For recursion, consider all N-1 as one big ring, and the bottom as separate. You want to
 * 		move the bottom ring to the target pillar, then move the N-1 one big ring to the target pillar on top of the
 * 		bottom ring.
 * 
 *  	Say we have 3 rings:
 *  
 *  	|		|		|
 *     [ ]		|		|
 *    [   ]		|		|
 *   [	   ]	|		|
 *   ====================
 *   
 *   	We consider the top two rings as one group, say G. The algorithm is:
 *   		>	Move G to pillar B.
 *   		>	Move bottom ring to pillar C
 *   		>	Move G to pillar C, on top of that largest ring
 *   
 *   	But how do we move G you might ask? That's the job of the recursion! Call recursion on that group G, which is
 *   	N-1 rings, to move to pillar B. 
 *   	Once recursion is done, move bottom ring to C is easy.
 *   	Then, call recursion again to move G to pillar C.
 *   
 *   
 *   Solving this requires 2^N - 1 moves, where N is the number of rings. Therefore, algorithm takes O(2^N) time complexity.
 *	 In case you asking, there is no better solution than O(2^N), but there is optimizatin can be done instead to make querying
 *   faster   
 *
 */

public class Tower_of_Hanoi {

	//==================================
	//	Normal solution- Just printing
	//==================================
    public static void towerOfHanoi(int n) {
        move(n, 'A', 'C', 'B');
    }
    
    private static void move(int n, char from, char to, char middle) {
        if (n == 1) 
            System.out.println("Moving ring 1 from " + from + " to " + to);
        else {
            move(n-1, from, middle, to);						//	Move N-1 rings to the intermediate pillar
            System.out.println("Moving ring " + n + " from " + from + " to " + to);	//	Move Nth ring to the target pillar
            move(n-1, middle, to, from);						//	Move N-1 rings from intermediate pillar to target pillar
        }
    }
    
    
    //==================================
    //	Using Stack to simulate pillars
    //==================================
    public static void towerOfHanoiStack(int n) {
    	Stack<Integer> A = new Stack<>();
    	Stack<Integer> B = new Stack<>();
    	Stack<Integer> C = new Stack<>();
    	
    	for (int i = n; i >=1 ; --i) A.push(i);
    	
    	System.out.println("Initially: ");
    	System.out.println("Stack A: " + A);
    	System.out.println("Stack B: " + B);
    	System.out.println("Stack C: " + C);
    	
    	move(n, A, C, B);
    	
    	System.out.println("Done moving: ");
    	System.out.println("Stack A: " + A);
    	System.out.println("Stack B: " + B);
    	System.out.println("Stack C: " + C);
    	System.out.println("Top element in C: " + C.peek() );
    }
    
    
    private static void move(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> mid) {
    	if (n == 1) 
    		to.push(from.pop() );
    	else {
    		move(n-1, from, mid, to);
    		to.push( from.pop() );
    		move(n-1, mid, to, from);
    	}
    }
    
    
    public static void main(String[] args) {
    	System.out.print("Enter size: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        towerOfHanoiStack(n);
        scan.close();
    }
}
