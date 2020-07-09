package Algorithms;

import java.util.ArrayList;

/*
 * 	Selection sort is a sorting algorithm which is simple but quite inefficient. It loops n iterations which finds the index containing
 * 	minimum index each time, and swaps them with the starting index
 * 	The starting index always increment by 1 each time, narrowing down the search range
 * 
 */

public class Selection_Sort {

	public static <T extends Comparable<T>> ArrayList<T> selectSort(ArrayList<T> arr) {
		
		for (int start = 0; start < arr.size() - 1; start++ ) {
			int min = start;
			for (int check = start + 1; check < arr.size(); check++ ) {
				if ( arr.get(min).compareTo(arr.get(check) ) > 0) {
					min = check;
				}
			}
			T temp = arr.get(start);
			arr.set(start, arr.get(min) );
			arr.set(min, temp);
		}
		return arr;
	}
	
	public static void main(String[]args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(4);
		arr.add(1);
		arr.add(3);
		arr.add(1);
		arr.add(5);
		arr.add(77);
		arr.add(99);
		arr.add(54);
		System.out.println(selectSort(arr) );
	}
	
}
