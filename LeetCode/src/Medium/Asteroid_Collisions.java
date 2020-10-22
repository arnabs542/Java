package Medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

//https://leetcode.com/problems/asteroid-collision/

/*
  This is a stack problem.

  For every asteroid, we have to know if it will get destroyed or not, based on the direction of the asteroid

  This is achieved by iterating over the asteroids. For each asteroid, it can moving to the left or right. Initially,
  all the asteroids moving to the left will never collide.
  Now, if we have one asteroid moving to the left, we have to see if it will destroy the previous one or not. THis is done
  by checking stack top. If it does, then destroy the asteroid at stack top and continue to next checking of stack top.
*/

public class Asteroid_Collisions {
	
	public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        
        for (int a: asteroids) {
        	
        	if (a > 0) {
        		stack.push(a);
        		continue;
        	}
        	
        	while ( !stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(a) ) {
        		stack.pop();
        	}
        	
        	if (stack.isEmpty() || stack.peek() < 0 ) {
        		stack.push(a);
        	} else if (stack.peek() * -1 == a ) {
        		stack.pop();
        	}
        	
        }
        
        int[] res = new int[ stack.size() ];
        int i = res.length - 1;
        while (!stack.isEmpty() ) {
        	res[i--] = stack.pop();
        }
        return res;
    }
	
}
