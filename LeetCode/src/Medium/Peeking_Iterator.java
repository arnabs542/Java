package Medium;

import java.util.Iterator;

//https://leetcode.com/problems/peeking-iterator/
/*
 *	 A Design Question.
 * 
 *	Constructor - Initialize the hold variable, which holds the value to be returned on the next() call
 *				  Also store the iterator itself
 *
 *	peek() - Returns the hold variable without changing anything
 *	next() - Returns the value in hold variable. Also updates the hold variable with next available value
 *			 from original iterator
 *	hasNext() - Check if there is a valid value in the hold variable
 */

public class Peeking_Iterator {
	
	//	For Integer only
	class PeekingIterator implements Iterator<Integer> {
		Integer hold;
		Iterator<Integer> it;
		
		public PeekingIterator(Iterator<Integer> iterator) {
			this.it = iterator;
			this.hold = iterator.hasNext()? iterator.next(): null;
		}
		
	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        return this.hold;
		}
		
		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
			Integer temp = hold;
			hold = this.it.hasNext()? this.it.next(): null;
			return temp;
		}
		
		@Override
		public boolean hasNext() {
		    return this.hold != null;
		}
	}
	
	
	
	
	//	To have it support other datatypes, same concept goes, except we have to implement Generics!
	class PeekingIterator2<T> implements Iterator<T> {
		
		Iterator<T> it;
		T hold;

		public PeekingIterator2(Iterator<T> iterator) {
			this.it = iterator;
			this.hold = iterator.hasNext()? iterator.next(): null;
		}
		
	    // Returns the next element in the iteration without advancing the iterator.
		public T peek() {
	        return this.hold;
		}
		
		@Override
		public boolean hasNext() {
			return hold != null;
		}

		@Override
		public T next() {
			T temp = hold;
			hold = it.next();
			return temp;
		}
		
	}

}
