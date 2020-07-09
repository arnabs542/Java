package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


//https://leetcode.com/problems/insert-delete-getrandom-o1/

/*
 * 	The basic idea is to use an array list and a hash map. 
 * 	The hash map stores the value as key for direct look up whilst the value is the position of it in the array list.
 * 	The array list is used for obtaining a random value using its index (since rand() generates from 0 to n-1). It maps from index to the value
 * 	itself
 * 
 * 	Initially I used another Set to store deletedIndex every time the delete method is called. However this results in extra space and probably
 * 	result in longer time when getRandom() method is called as it has to keep iterating for the random number which isn't in the Set. Additionally,
 * 	to access the elements in the set I have to use iterator which needs to be updated every time the set is changed.
 * 
 * 	Another better idea is to use Overwriting. Every time the delete is called, the element in the array list is overwritten with the last element in
 * 	the array list. Then the array list remove( size - 1) method is called upon the last index, which uses only O(1) time because no shifting is done.
 * 	The only extra thing to do is to update the value to index mapping in the hash map.
 * 
 */
public class Insert_Delete_GetRandom_O1 {
}

class RandomizedSet {
	
	HashMap<Integer, Integer> valToIdx;
	ArrayList<Integer> arr;
	Random random;
	
	public RandomizedSet() {
        valToIdx = new HashMap<>();
        arr = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
    	//The element is already exist. No need to write anything.
    	if (valToIdx.containsKey(val) )
    		return false;
    	
    	//Add the element to the last index of the array
    	arr.add(val);
    	//The index to be placed in the map. Notice the -1 there because after adding the element, the size() would not be the actual index itself.
    	//Eg: Initially empty array, [], after adding val 7, the array [7] where element is at index 0. Note the size() will return 1 instead.
    	int index = arr.size() - 1;
        valToIdx.put(val, index);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	//The element is not exist in the whole set. Thus no need to remove anything
        if (!valToIdx.containsKey(val) )
        	return false;
        
        //Get the index of the element to remove in the array list.
        int location = valToIdx.get(val);
        //Get the value of the last element in the array list
        int overwrite = arr.get(arr.size() - 1);
        //Overwriting the element to be removed with the last element in the array list
        arr.set(location, overwrite );
        //Remove the last element in the array list, which is already overwritten to the element to be removed
        arr.remove(arr.size() - 1);
        //Update the mapping of value of last element in array list to its new location, which is the index of element to removed
        valToIdx.put(overwrite, location);
        //Remove the mapping of removed element
        valToIdx.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
    	System.out.println(arr);
        int rand = random.nextInt(arr.size() );
        
        return arr.get(rand);
    }
}