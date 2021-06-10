package Medium;

import java.util.Map.Entry;
import java.util.TreeMap;

//https://leetcode.com/problems/my-calendar-i/
/*
 * 	This can be thought of as a Binary Search Tree problem?
 * 
 *	A task can only be inserted if it is not overlapping with any other tasks. We have to think of 
 *	a data structure that let us perform checking for overlapping, and insertion in fast time.
 *
 *	For brute force, we only keep a list of tasks. Everytime we have to insert a task, iterate through
 *	each previously inserted task and see if they overlap or not.
 *	If none of the tasks overlap, we are safe to say that the task is not overlapping and append to the list.
 *	This apporach however takes O(N^2) time.
 *
 *	A Binary Search Tree with balanced feature, like Java's TreeMap (Red black tree) is good for this.
 *	It is able to let us search for floorkey and ceilingkey time as well as insertion in
 *	O(log N) time. Check if both floorkey and ceilingkey task is overlapping or not. If not, it is safe
 *	to say the task can be safely inserted.
 */


public class My_Calendar_I {}


class MyCalendar {
	//	Maps Starting time -> End time, sorted
	private TreeMap<Integer, Integer> schedules;

    public MyCalendar() {
        schedules = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Entry<Integer, Integer> previous = schedules.floorEntry(start);
        Entry<Integer, Integer> next = schedules.ceilingEntry(start);
        
        if (previous != null && previous.getValue() > start) return false;
        if (next != null && next.getKey() < end) return false;
        
        schedules.put(start, end);
        return true;
    }
}