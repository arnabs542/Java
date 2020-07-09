package LearningJava;

import java.util.ArrayList;
public class ArrayListTuto {

	public static void main(String[]args) {
		
		String[] words = {"a","b","c","d","e","f"};
		
		//ArrayList oldList = new ArrayList();				Non-generic; More dangerous to use because can consist of many types
		
		ArrayList<String> myList = new ArrayList<>();		//Generic - Only can contain data of type String
		
		//Storing each element of words[] into ArrayList
		for (String a : words) {
			myList.add(a);									//Use add() to add an element into arraylist
		}
		
		
		/*--------------------------------------ArrayList methods--------------------------------------------------
		add(element) 			- Adds an element to the end of the list
		add(int index, element) - Insert an element at the specified index, which pushes the element to the next index
								  Eg: for [1,2], if inserted a "3" at index 1, it will become [1,3,2]
		remove(element)			- Remove the specified element from the list
		remove(int index)		- Remove the element of specified index from the list
		set(int index, element) - Replace the element at specified index with the new element
		clear()					- Remove all element from the list
		size()					- Returns the number of elements in the list
		contains(element)		- Returns true if the list contains the element
		get(int index)			- Returns the element at specified index
		indexOf(element)		- Return the index of the first occurrence of the element in the list;
								  Returns -1 if the element is not found
		lastIndexOf(element)    - Return the index of last occurrence of the element in the list;
								  Returns -1 if the element is not found
		isEmpty()				- Returns true if the list is empty
		toArray()				- Returns an array containing the elements in the list in proper sequence
		
	
	
		-----------------------------------------------------------------------------------------------------------*/
	}		//end of main()
	
}		//end of class
