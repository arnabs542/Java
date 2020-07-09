package LearningJava;

public class ConditionalOperator {

	//Often times we have to assign a value into variable depending on 2 conditions
	//We will write as if
	/*		if (a)
	 * 			val = 1;
	 * 		else
	 * 			val = 2;
	 * 
	 */
	//There is simpler way to do this
	//		val = (condition)? true: false;
	
	public static void main(String[] args) {

		int time1 = 14;
		int time2 = 23;
		boolean aIsDay = (time1 >= 8 && time1 <= 18)? true: false;
		boolean bIsDay = (time2 >= 8 && time2 <= 18)? true: false;
		
		System.out.println(aIsDay + "\n" + bIsDay);
		
	}

}
