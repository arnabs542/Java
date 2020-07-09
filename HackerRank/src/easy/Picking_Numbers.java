package easy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//https://www.hackerrank.com/challenges/picking-numbers/problem

public class Picking_Numbers {
	 public static int pickingNumbers(List<Integer> a) {
		 HashMap<Integer, Integer> map = new HashMap<>();
		 int length = 0;
		 
		 for (int n: a) {
			 map.putIfAbsent(n, 0);
			 map.replace(n, map.get(n) + 1);
		 }
		 
		 for (int i : map.keySet() ) {
			 int len = map.get(i) + map.getOrDefault(i+1, 0);
			 length = (len > length)? len: length;
		 }
		 return length;
		 
	 }
}
