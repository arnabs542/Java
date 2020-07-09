package easy;

import java.util.ArrayList;

//https://www.hackerrank.com/challenges/lisa-workbook/problem

public class Lisas_Workbook {
	
	 static int workbook(int n, int k, int[] arr) {
		 
		 //Remember to start pages from 1
		 ArrayList< ArrayList<Integer> > pages = new ArrayList< ArrayList<Integer> >();
		 
		 //Each chapter filling operation
		 for (int chap = 1; chap <= n; chap ++) {
			 int questionNo = 1;
			 
			 //Each page filling operation
			 for (int p = 1; p <= getChapterPages(k, arr[chap - 1] ); p ++ ) {
				 ArrayList<Integer> questions = new ArrayList<Integer>();
				 
				 //Adding questions to page operation
				 for (int i = 0; i < k; i ++) {
					 questions.add(questionNo);
					 questionNo ++;
					 
					 if (questionNo > arr[chap - 1]) break;
				 }
				 
				 pages.add(questions);
			 }
			 
		 }
		 
		 //pages[0] = page 1, remember that
		 int special = 0;
		 for (int i = 0; i < pages.size(); i ++ ) {
			 if (pages.get(i).contains(i + 1) ) special ++;
		 }
		 
		 return special;
	 }
	 
	 private static int getChapterPages(int limit, int question) {
		 if (question == 0) return 0;
		 return (question / limit) + ( (question % limit == 0)? 0:1);
	 }
	 
	 public static void main(String[]args) {
		 System.out.println( workbook(5, 3, new int[]{4,2,6,1,10}  ) );
	 }
	 
}
