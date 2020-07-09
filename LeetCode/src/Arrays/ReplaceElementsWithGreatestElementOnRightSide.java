package Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/511/in-place-operations/3259/

/*
 * 	Start the iteration from the end till 0, keeping the maximum value seen so far, initialized to -1
 * 	Every time we see the element is greater than greatest, swap the values, else just overwrite
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
	
	public int[] replaceElements(int[] arr) {
		int greatest = -1;
		for (int i = arr.length - 1; i >= 0; i --) {
			if (arr[i] > greatest ) {
				int temp = arr[i];
				arr[i] = greatest;
				greatest = temp;
			}
			else
				arr[i] = greatest;
		}
		return arr;
	}
}
