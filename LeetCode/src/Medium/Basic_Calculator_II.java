package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/basic-calculator-ii/
/*
 *	This is a String Manipulation / Stack Problem
 *
 * 	Given an expression of +-/* and without parenthesis (All operations same layer), how would you calculate
 * 	final answer?
 * 
 * 	One intuition is to do a two pass iteration. On the first pass, we reduce the multiply and division operations
 * 	into one single value, therefore the whole equation becomes only + and - only, which is simple to evaluate 
 * 	in the second iteration.
 * 
 * 	---------------------------------------------------------------------------------------
 * 
 * 	With the Stack solution which is similar in a fashion to two pass string solution, we must notice two things:
 * 		>	When a + or - sign is met, the second operand may be a multiplication or division, which is of 
 * 			higher precedence and be evaluated first. Therefore the first operand must be stored first and
 * 			be taken out to calculate once the second operand side is evaluated.
 * 
 * 		>	When a * or / sign is met, we can immediately evaluate using first and second operand, since no
 * 			parenthesis exists.
 * 
 * 	THe proposed solution is we store the OPERATOR and OPERANDS that we met before, keeping track of current operand
 * 	,and once we met the next operator or hit the end of string, we know we already have the previous and tracked
 * 	operand before this newest operator. Now, what will we do on the past 2 operands?
 * 
 * 			Eg: 		2 + 5 * ...
 * 							  ^
 * 			We will hit this. We know past operator is + and past operand is 2, current operand is 5
 * 
 * 
 * 		>	+ and - : Since the next operand may be higher precedence of * or /, we therefore store the current tracked
 * 			operand, and continue. Note that -x is equivalent to +(-x) thus push -x into Stack instead
 * 
 * 		>	* and / : Immediately perform the operation of two past operands together, push result back into
 * 			Stack, then continue with next operand
 * 
 * 	In the end, the Stack that we store operands on are the ones that we need to sum it up. Return the sum of Stack
 * 	
 * 	===============================================================================================
 * 
 * 	Notice the pattern that the Stack may be redundant after all. With one extra variable, we can store the sum 
 *	of the stack easily.
 *	
 *	Therefore we will basically have 3 variables:
 *
 *			Res		Prev	Curr
 *			( )		( )		( )
 *	
 *	Prev represents the top of Stack, and Res is the sum of all elements underneath the top of Stack.
 *	Therefore the algorithm:
 *
 *		>	+ and - : The previous operand is guaranteed safe from multiplication and division. Sum into Res
 *			and put current tracked number into Prev
 *		
 *		>	* and / : Take prev and current tracked number to multiply / divide. Then put back into prev
 *
 *	
 *	At the end, return Res + Prev;
*/

class Basic_Calculator_II {
	
	//	Approach - Naive */ then +- processing
	public int calculate(String s) {
		int len = s.length();
		int token = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0; idx < len; idx ++ ) {
			char c = s.charAt(idx);
			
            if (c == ' ') continue;			//	Skip spaces. Spaces mean nothing in an expression
            
            //	Character is a digit. Tokenize it
			if ( Character.isDigit( c ) ) {
				token = token * 10 + c - '0';
			} 
			//	Character is a high precedence operator. Perform the operation
			else if ( c == '*' || c == '/' ) {
				int token2 = 0;
				
				//	Tokenize the number after the operator.
				while ( ++idx < len ) {
					char n2 = s.charAt(idx);
					if (n2 == ' ') continue;
					else if ( Character.isDigit(n2) ) token2 = token2 * 10 + s.charAt(idx) - '0';
					else break;
				}
                idx--;		//	Since the outer loop will perform idx++, we need revert change to prevent skipping
                			//	1 character
                
                //	Perform operation. Result stored in first token
				if (c == '*') token *= token2;
				else token /= token2;
			} 
			//	Otherwise is lower precedence operation +-
			else {
				sb.append(token);		//	Append the first token, and the operator to process later
				sb.append(c);
                token = 0;				//	Reset the token so the token after this +- operator can be captured
			}
		}
		sb.append(token);				//	At the end, the last token is not yet stored. 
		
		
		//	Now focus on the StringBuilder, which is resulting expression of only + and -
		len = sb.length();
		//	The whole expression will be reduced to only one integer value.
		int res = 0;
		
		for (int idx = 0; idx < len; idx++ ) {
			
			char c = sb.charAt(idx);
			
			//	Digit. Append to the result
			if ( Character.isDigit(c) ) {
				res = res * 10 + c - '0';
			} 
			//	Operator detected. Find next token then merge result
			else {
				int operand2 = 0;
				
				//	While is digit, tokenize into token2
				while (++idx < len && Character.isDigit( sb.charAt(idx) ) )
					operand2 = operand2 * 10 + sb.charAt(idx) - '0';

                idx--;		//	Since outer for loop perform idx++, we need revert one step to prevent missing
                			//	character
                
				if (c == '+') res += operand2;
				else res -= operand2;
			}
		}
		return res;
	}
	
	
	
	
	
	//	Stack solution
	public int calculate2(String s) {
		Deque<Integer> stack = new ArrayDeque<>();
		final int len = s.length();
		int currNum = 0;
		char currOpd = '+';
		
		for (int i = 0; i < len; i ++ ) {
			char c = s.charAt(i);
			
			if ( Character.isDigit(c) ) {
				currNum = currNum * 10 + c - '0';
			}
			if ( !Character.isDigit(c) && !(c == ' ') || i == len - 1 ) {
				if (currOpd == '+') stack.push(currNum);
				else if (currOpd == '-') stack.push(-currNum);
				else if (currOpd == '*') stack.push( stack.pop() * currNum );
				else stack.push( stack.pop() / currNum );
				
				currOpd = c;
                currNum = 0;
			}
		}
		
		int res = 0;
		while (!stack.isEmpty() ) {
			res += stack.pop();
		}
		return res;
	}
	
	
	
	//	 Stack concept solution without Stack
	public int calculate3(String s) {
		int res = 0;
		int prev = 0;
		final int len = s.length();
		int currNum = 0;
		char currOpd = '+';
		
		for (int i = 0; i < len; i ++ ) {
			char c = s.charAt(i);
			
			if ( Character.isDigit(c) ) {
				currNum = currNum * 10 + c - '0';
			}
			if ( !Character.isDigit(c) && !(c == ' ') || i == len - 1 ) {
				if (currOpd == '+') {
					res += prev;
					prev = currNum;
				}
				else if (currOpd == '-') {
					res += prev;
					prev = -currNum;
				}
				else if (currOpd == '*') prev *= currNum;
				else prev /= currNum;
				
				currOpd = c;
                currNum = 0;
			}
		}
		
		return res + prev;
	}
	
	
	
}