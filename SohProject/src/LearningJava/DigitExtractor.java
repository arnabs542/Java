package LearningJava;

import java.util.Scanner;
public class DigitExtractor {
	
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		
		int a = scan.nextInt();
		
		//Make num as our number
		//Repeat looping until the number is 0 in value
		//Divide num by 10 after each loop
		for (int num = a; num != 0; num /= 10) {
			System.out.println(num % 10);
		}
	}
	
	
	
}
