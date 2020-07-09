package LearningJava;

public class VariableLengthArgument {

	public static void main(String[]args) {
		printArguments(1,2,3,4,5,5,6,6);
		System.out.println( getSum(1,2,3,4,5) );
		System.out.println( getAvg(1,2,3,4,5) );
		
		
	}		//end of main
	
	static void printArguments (int...nums) {
		for (int i = 0; i < nums.length; i ++)
			System.out.print(nums[i] + " ");
		
		System.out.println();
	}
	
	static int getSum (int...nums) {
		int total = 0;
		for (int i: nums)
			total += i;
		
		return total;
	}
	
	static double getAvg (int...nums) {
		int total = 0;
		for (int i : nums)
			total += i;
		
		return total * 1.0 / nums.length;
	}
	
	
	
}		//end of class
