package Misc;

/*
 * 		Digital Root, or sometimes called digital sum, is a single digit number which is obtained when a natural number n, is repeatedly reduced to a single digit
 * 		by summing all of its digit numbers.
 * 		
 * 		Eg: 1926 =>
 * 			1 + 9 + 2 + 6 =>
 * 			18 =>
 * 			1 + 8 =>
 * 			9
 * 		Therefore the digital sum of 1926 is 9.
 * 
 * 		There's a trick to obtain digital root faster for large integers.
 * 		-	First, 0 will contribute nothing to the digital sum so we can outright cancel it out
 * 
 * 		-	Also, 9 will also have no contribution to digital sum, because any non zeroes added to 9, will cause it to increase up to tenth's of 1X, and that 1
 * 			will be summed back, leading us back to original non zero digit
 * 				Eg: 4 + 9 => 13 => 4 (We got 4 back, so we ignore the 9)
 * 
 * 		-	Similarly, any digit combinations that sum up to 9, can be cancelled altogether
 * 				Eg: 2 + 3 + 5 + 7, We see that 2 + 7 is 9, and will be equivalent to 3 + 5 + (9), so we can outright cancel the 2 and 7 combination, left only
 * 					3 + 5 which is 8
 * 
 * 		-	If cancel the 9 and 0's lead to nothing, like 9999, then the digital sum is 9 itself. Think of it as cancel 3 of the 9's and left with one 9
 * 			in 9999
 * 
 * 		
 * 		There is a direct formula for finding digital root for different bases, like decimal system
 * 		
 * 		Let base = b
 * 		If n is 0, then decimal root is 0
 * 		Else If n modulo b - 1 = 0, then decimal root is b - 1
 * 		Else it is n modulo (b-1)
 * 
 * 		Since the second case is only specific when the modulo is exactly zero, then we could combine the second and third case so that second case
 * 		won't really occur alone
 * 
 * 		If n is not 0, then decimal root is (n - 1) % (b - 1) + 1
 * 		See how the one is extracted out of the n itself, then added back?
 * 
 * 		-------------------------------
 * 		APPLICATION OF DIGITAL ROOT
 * 		-------------------------------	
 * 
 * 		> Checking the sum of numbers quickly
 * 			Imagine in a multiple choice question where you are asked to find a sum of decimal or whole numbers quickly, then you would try finding both the
 * 			digital sum of the question, and each of the choices. The correct answer will have matching digital sum
 * 			(THIS APPLICATION IS LESS USEFUL, AS WE WILL STILL DO SUMMING)
 * 
 * 			Eg: 1.32141 + 8.9304 = 10.25181
 * 
 * 				LHS:	Digital sum of 1.32141 is 3
 * 						Digital sum of 8.9304 is 6
 * 				
 * 						Summing up the digital sum, is 9
 * 
 * 				Digital sum of the RHS, is 9 as well
 * 
 * 		> Checking the subtraction of numbers quickly
 * 			This can also applies to subtraction problems. However, instead of subtracting the digital roots down, we work it bottom-up, so cases where
 * 			we have to subtract 3 by 7 or any number larger, it won't affect us.
 * 
 * 			Eg: 141.5 - 2.8354 = 138.6646
 * 		
 * 				LHS:	Digital sum of 141.5 is 2		
 * 						Digital sum of 2.8354 is 4
 * 
 * 				RHS:	Digital sum of 138.6646 is 7
 * 		
 * 			Working bottom up,  2
 * 								4
 * 								7
 * 			The uppermost digit will be the result of summing all the bottoms. Therefore 7 + 4 is 11, which has Digital sum of 2.
 * 
 * 		> Combining sum and subtraction Checking
 * 
 * 			We should group the plus terms on the top, and then group the subtraction terms on the bottom. THen, the top group's digital root, should equal
 * 			to the bottom's digital root + x. What should be the value of x? Ask this question and shall find the answer
 * 
 * 			Eg:	127.13 + 56.71 - 39.37 - 43.77
 * 
 * 				127.13	=>	5				(ADDITION GROUP)
 * 			+	56.71	=>	1	(SUM: 6)
 * 				---------------
 * 			-	39.37	=>	4				(SUBTRACTION GROUP)
 * 			-	43.77	=>	3	(SUM: 7)
 * 			------------------------
 * 				ANS		=> 	x
 * 
 * 			So both the groups are reduced to a single sum of (6) and (7). Ask: 6 = 7 + x. What value of x will cause the sum to be 6? x is 8
 * 
 * 			Real answer to question is 100.7, which digital sum is 8
 * 			
 * 
 * 		> Checking the multiplication of numbers quickly
 * 			This digital sum rule applies to multiplication of decimal numbers as well as whole numbers. But instead of just adding, each digital sum
 * 			for each term in the expression must be multiplied with each other
 * 
 * 			Eg: 1425 x 9273 = 13214025
 * 		
 * 				LHS:	Digital sum of 1425 is 3
 * 						Digital sum of 9273 is 3
 * 						
 * 						Multiply them up (each term), we get 9.
 * 
 * 				RHS:	Digital sum of 13214025 is 9
 * 
 * 		> Find a single missing digit number
 * 			Combining above properties, we can try and find the single digit missing in the expression
 * 
 * 			Eg:	125 x 307 + 166 = 38X41. Find the X digit
 * 		
 * 				LHS: 	Digital root of 125 is 8
 * 						Digital root of 307 is 1
 * 						Multiply, get 8
 * 						Digital root of 166 is 4
 * 						Adding 8 and 4 gets 12, Digital root is 3
 * 
 * 				RHS:	Digital root is 3 + 8 + x + 4 + 1 = 7 + x
 * 		
 * 				Ask yourself, 7 + X = 3? Answer is 5.
 * 				Therefore X = 5!
 * 
 * 		> Find Divisibility by 3, 6 and 9
 * 			We can determine whether if a large integer is divisible by 3, 6 or 9 by determining its digital root
 * 		
 * 			-	If digital root is 3, 6 or 9, then it is divisible by 3
 * 			-	If digital root is 3, 6 or 9, AND is a even number, then it is divisible by 6
 * 			-	If digital root is 9, then it is divisible by 9
 * 
 * 			Eg: 13581846, Digital sum is 9.
 * 				Therefore it is divisible by 3 and 9
 * 				Since it is even as well, it is divisible by 6
 * 
 * 
 * 			
 */


public class Digital_Root {

}
