package Medium;

//https://leetcode.com/problems/implement-rand10-using-rand7/

/*
 * 	Uniform Distribution means that the probability of every number selected shall be of the same.
 * 
 * 	We can check for uniform distribution by generating every possible values, and count the frequency for them occurring
 * 	If their frequency are of the same, then it is uniform distrubution
 * 
 * 
*/

public class Implement_Rand10_Using_Rand7 {

	//	Dummy function
	public int rand7() {
		return 0;
	}
	//=================
	
	//	Here Explain why Multiplying won't work. You will see there are different ways to multiply two integer
	//	to achieve a same N
	public static void findFreq() {
		int[] ans = new int[10];
		//	We try every possible value of rand7() for first val
		for (int i = 1; i<= 7; i++ ) {
			//	We try every possible value of rand7() for second val
			for (int j = 1; j <= 7; j ++ ) {
				
				//	The formula is (i*j) % 10, will give 0 - 9 inclusive. Let's see if they have same ways to form each N
				ans[ (i*j) % 10 ] ++;
			}
		}
		
		for (int i = 0 ; i < 10; i ++ ) {
			System.out.println("Ways to form " + (i+1) + " are: " + ans[i]);
		}
	}
	
	//	Here Explain why Addition won't work. You will see there are different ways to add up to a N integer
	public static void findFreq2() {
		//	The algorithm is we generate 2 rand7() values, which add up to [2 - 14]. We shift left by 1 so [1-13],
		//	If the value is > 10 then we reject it
		
		int[] freq = new int[10];
		
		for (int i = 1; i <= 7; i ++ ) {
			for (int j = 1; j <= 7; j ++ ) {
				int shifted = (i + j) - 1;
				if (shifted > 10) continue;
				freq[shifted - 1] ++;
			}
		}
		
		for (int i = 0; i < 10; i ++ ) {
			System.out.println("Ways to form " + (i + 1) + " are: " + freq[i] );
		}
	}
	
	
	//	Instead we imagine there is a 7x7 matrix. Those indexed from 1 to 40 has its own respective values.
	//	How we obtain a distributed value from calling rand7() two times?
	//	This is achieved via first generate the column number, then for the row, shall generate [0-6] value, then multiply 7
	//	(Since for each additional column, the index goes up by 7)
	public static void findFreq3() {
		int[] freq = new int[10];
		
		for (int i = 1; i <= 7; i ++ ) {
			for (int j = 0; j <= 6; j++ ) {
				int idx = i + j * 7;	
				if (idx > 40) continue;	
				
				//	Since index represents 1 to 40, we can just take modulus 10, giving [0, 9], then add 1 to it
				freq[ idx % 10] ++;
			}
		}
		
		for (int i = 0; i < 10; i ++ ) {
			System.out.println("Ways to form " + (i+1) + " are: " + freq[i] );
		}
	}
	
	
	public int rand10() {
		int index;
		
		do {
			int a = rand7(), b = rand7();
			index = a + (b - 1) * 7;
			
		} while (index > 40);
		
		return index % 10 + 1;
	}
	
	public static void main(String[]args) {
		findFreq3();
	}
	
	
}
