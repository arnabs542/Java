package Data_Structures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 	Remember in Mathematics. An expression involves operands and operators. Unlike an equation, an expression doesn't have the equal
 * 	sign
 * 
 * 	The type of notation of expressions we see in daily life are in INFIX Notation, usually in the form
 * 	
 * 			<operand> <operator> <operand>		Like (5 + 2)
 * 
 * 	Note that the operand must not be a single definite value. It can also be an expression itself. See:
 * 			(5 * 2) + 3
 * 	Here ( 5 * 2 ) act as an operand itself, which by itself is an expression involving 5 and 2 as operand and * as operator
 * 
 * 	Infix notation are easy for us humans to understand and see. However, it seems more troublesome for computers to parse
 * 	and evaluate expressions in this way. This is because of INFIX NOTATIONS involves 
 * 		>	precedence		(Eg: Brackets must evaluated first before * and /, which shall be eval first than + and -)
 * 		>	assiciativity	(Eg: Power, ^ must be evaluated right to left, while arithmetic operators + - * / are from left to right)
 * 	Asking computers to evaluate infix notation expressions are both time consuming and difficult to implement
 * 
 * 	=========================================================================================================================
 * 
 * 	Computers differ from us. Computers are better at evaluating expressions in two types:
 * 		>	Prefix Notation		(AKA Polish Notation)
 * 		>	Postfix Notation	(AKA Reverse Polish Notation)
 * 
 * 	Prefix Notation comes in the form:
 * 		<operator> <operand> <operand>
 * 	Postfix Notation comes in the form:
 * 		<operand> <operand> <operator>
 * 
 * 	Like 5 + 2 * 3, in both notations:
 * 		Prefix:		+ 5 * 2 3
 * 		Postfix:	5 2 3 * +
 * 
 * 	How can we convert from one to another? In our mind, we know about precedences and associativity rules:	
 * 	The key for us to manually convert from infix to other notations is to BREAKING IT INTO SINGLE SIMPLE EXPRESSION, AND DO IT ONE
 * 	BY ONE
 * 
 * 	See example:
 * 		5 + 2 ^ 3 * 4
 * 
 * 	1.	Firstly, remember to break up into single expression. We break the one with the HIGHEST precedence first, which is
 * 			(2 ^ 3)
 * 		Therefore we convert them First:
 * 			Prefix:	5 + ( ^ 2 3 ) * 4
 * 			Postfix: 5 + ( 2 3 ^ ) * 4
 * 
 * 	2.	Then, we shall always view the whole converted single expression as ONE OPERAND. DO NOT FURTHER MODIFY
 * 		Then, the next expression in turn is the * 4:
 * 			Prefix: 5 + ( * ^ 2 3 4 )
 * 			Postfix: 5 + ( 2 3 ^ 4 * )
 * 
 * 	3.	Lastly is the 5 +:
 * 			Prefix: ( + 5 * ^ 2 3 4 )
 * 			Postfix: ( 5 2 3 ^ 4 * + )
 * 
 * 	=====================================================================================================================
 * 
 * 	INFIX TO POSTFIX CONVERSION	(SHUNTING YARD ALGORITHM)
 * 
 * 	To perform conversion from infix to postfix (or prefix), we have to utilize Stack Data Structure.
 * 	The stack data structure will be storing the operators, which the operators get added to the result depending on condition
 * 	
 * 	We will run a scan of the original expression from left to right (normal flow), and follow the rules:
 * 
 * 	>	If it is an operand, directly add it to the result
 * 	>	Else it is an operator:
 * 			1.	If the stack is empty or contains a left bracket (, directly push the operator
 * 			2.	If the incoming operator is left bracket (, directly push the left bracket
 * 			3.	If the incoming operator is right bracket ), pop the operators one by one, adding to the result until the left bracket
 * 				) is found. Note that we don't involve brackets in the final Postfix or Prefix notation
 * 			
 * 			--	Now we have to check the top of the stack, by using peek()
 * 			4.	If the incoming operator has higher precedence than the one at the top of the stack, push the incoming operator
 * 				directly
 * 			5.	If the incoming operator has lower precedence than the one at the top of the stack, pop the operator at top of stack,
 * 				adding it to result, and try again with the new operator at the top of the stack
 * 			
 * 			--	From here, the precedence is equal for both operators. Check the associativity rule of the incoming operator
 * 			6.	If Left To Right, pop the operator at top of stack, adding it to result, and try again with the new operator at
 * 				the top of the stack
 * 			7.	If Right to Left, push incoming operator directly into the stack
 * 
 * 			--	At the end of linear scan, if there is remaining operator in the stack, pop them all and add to the result
 * 
 * 	========================================================================================================================
 * 
 * 	INFIX TO PREFIX NOTATION
 * 
 * 	To perform conversion from INFIX NOTATION to PREFIX NOTATION is similar to POSTFIX CONVERSION, but with some changes:
 * 
 * 	>	The whole expression has to be reversed first for us to scan from left to right ( Or essentially we just scan from behind to
 * 		front)
 * 	>	After done with the notation conversion, the result has to be reversed once more to form a complete Prefix Notation
 * 
 * 	>	Since original expression are reversed, the brackets are reversed as well. We will scan the right bracket ) before left
 * 		bracket itself (. Nevertheless, just do it assuming the Right bracket is Left Bracket, and Left bracket is Right bracket
 * 
 * 	>	MOST IMPORTANTLY, Since it is read in reverse, ASSOCIATIVITY RULES ARE REVERSED:
 * 
 * 			-Left to Right, then push incoming operator directly into the stack
 * 			-Right to Left, then pop the operator at the top of the stack, adding it to result, and try again with the new operator
 * 			 at the top of the stack
 * 
 * 	===========================================================================================================================
 * 
 * 	EVALUATION OF THE EXPRESSION IN PREFIX AND POSTFIX
 * 
 * 	Now that we have converted expression into Prefix and Postfix Notation. How do we evaluate it?
 * 	For this we'll be using Stack as well
 * 
 * 	FOR POSTFIX:
 * 		We will use a stack for storing the operands. We will run a linear scan from left to right in the postfix expression.
 * 		As soon as we met with an operator, we will pop the 2 operands from the stack,
 * 		and evaluate the result based on the operator, and push the newly calculated value back into the stack
 * 
 * 		Note that because the operands are pushed into the stack accordingly, first operand1 then operand2, 
 * 		the first operand popped is going to be the operand2, while the second operand popped is going to be the operand1.
 * 			<operand1> <operator> <operand2>
 * 
 * 		At the end, the operand stack shall only have 1 value left, which is the final answer
 * 
 * 	FOR PREFIX:
 * 		Evaluation for prefix notation is more special. We will run a linear scan from RIGHT TO LEFT in the prefix expression.
 * 		We will push every operand in the operand stack, and as soon as we met with a operator, we will pop 2 elements from
 * 		the stack, and evaluate the result based on the operator, then push the result back
 * 
 * 		Note that in the operands popped, the first operand popped shall be operand1, and the operand2 popped shall be operand2,
 * 		which is in order
 * 
 * 	================================================================================================================
 * 
 * 	Expression Tree
 * 
 * 	Now except from evaluating the expression from Prefix and Postfix, we can construct what's called an EXPRESSION TREE
 * 
 * 	In the expression tree, each of the leaf nodes are operands, while all the intermediate nodes are operators.
 * 	Each intermediate nodes will have 2 child nodes, where the child nodes can be a leaf node of operand, or another
 * 	subtree which in the end will definitely evaluate to some value to operate on
 * 
 * 	Eg: For 2 + 5 * 3, the tree would be
 * 
 * 			(+)
 * 	        / \
 * 		  (*)  (2)
 * 		 /  \
 * 		(5) (3) 
 * 
 * 	When we do traversal, the 5 * 3 will be evaluated first, and returned as the result 15 to the root node. Then 15 + 2 will
 * 	be performed.
 * 	
 * 	To construct such tree, we will still use Stack, containing the pointer to the node objects themselves.
 * 
 * 	When we meet an operand, just make it a node and push into the stack
 * 	if it is a operator however, pop the 2 elements out from the stack, connect them using the current operator as parent node,
 * 	then push this parent node back into the stack.
 * 
 */


//	We are going to store the operands and operators in form of string. like "+", "123", "1234.3123"...
public class Infix_Prefix_Postfix_Notation_Shunting_Yard_Algo {
	
	//	Initialization of precedences mapping and Associativity checker //
	private static Map<String, Integer> precedences;
	
	private static boolean isRightAssociated( String s) {
		return s.equals("^");
	}
	
	static {
		precedences = new HashMap<>();
		precedences.put("+", 0);
		precedences.put("-", 0);
		precedences.put("*", 1);
		precedences.put("/", 1);
		precedences.put("^", 2);
	}
	//	End of Initialization of precedences mapping and Associativity checker //
	
	
	
	public static String[] convertInfixToPostfix( String[] infix ) {
		String[] postfix = new String[ infix.length ];
		Stack<String> opStack = new Stack<>();
		int index = 0;
		
		for ( int i = 0; i < infix.length; i ++ ) {
			String s = infix[i];
			//	If it is a operand (Number), not operator
			if ( s.length() != 1 || !precedences.containsKey(s) ) {
				postfix[ index ++ ] = s;
			}
			//	It IS a operator
			else {
				//	Brackets or if Stack is empty
				if ( opStack.isEmpty() || s.equals("(") ) opStack.push(s);
				else if ( s.equals(")" ) ) {
					String pop = opStack.pop();
					if ( pop.equals("(" ) ) continue;		//If met the left parenthesis, stop and proceed to next iteration
					postfix[ index ++ ] = pop;
					i --;			//Ensure in the next iteration it still is this bracket
				}
				//	Check precedences
				else {
					int precd1 = precedences.get( s );
					int precd2 = precedences.get( opStack.peek() );
					
					if (precd1 > precd2 ) {
						opStack.push( s );		//Greater precedence. Push directly
					}
					else if ( precd1 < precd2 ) {
						postfix[ index ++ ] = opStack.pop();	//Less precedence. Pop the top of stack and try again
						i --;
					}
					else {
						if ( isRightAssociated(s) ) {		//RIght to Left. Push directly
							opStack.push(s);
						} else {
							postfix[ index ++ ] = opStack.pop();	//Left to Right. Pop the top of stack and try again
							i --;
						}
					}
				}
			}
		}
		
		//Empty the remaining operators in stack
		while ( !opStack.isEmpty() ) {
			postfix[ index ++ ] = opStack.pop();
		}
		
		return postfix;
	}
	
	
	
	
	public static String[] convertInfixToPrefix( String[] infix ) {
		String[] prefix = new String[ infix.length ];
		Stack<String> opStack = new Stack<>();
		int index = infix.length - 1;		//Since the result need to be reversed, the index goes from len-1 to 0
		
		for ( int i = infix.length - 1; i >= 0; i -- ) {
			String s = infix[i];
			//	If it is a operand (Number), not operator
			if ( s.length() != 1 || !precedences.containsKey(s) ) {
				prefix[ index -- ] = s;
			}
			//	It IS a operator
			else {
				//	Brackets or if Stack is empty.	Remember brackets are reversed
				if ( opStack.isEmpty() || s.equals(")") ) opStack.push(s);
				else if ( s.equals("(" ) ) {
					String pop = opStack.pop();
					if ( pop.equals(")" ) ) continue;		//If met the left parenthesis, stop and proceed to next iteration
					prefix[ index -- ] = pop;
					i ++;			//Ensure in the next iteration it still is this bracket, by INCREMENTING
				}
				//	Check precedences
				else {
					int precd1 = precedences.get( s );
					int precd2 = precedences.get( opStack.peek() );
					
					if (precd1 > precd2 ) {
						opStack.push( s );		//Greater precedence. Push directly
					}
					else if ( precd1 < precd2 ) {
						prefix[ index -- ] = opStack.pop();	//Less precedence. Pop the top of stack and try again
						i ++;
					}
					else {
						if ( !isRightAssociated(s) ) {		//Left to Right. Push directly
							opStack.push(s);
						} else {
							prefix[ index -- ] = opStack.pop();	//Right to Left. Pop the top of stack and try again
							i ++;
						}
					}
				}
			}
		}
		
		while ( !opStack.isEmpty() ) {
			prefix[index --] = opStack.pop();
		}
		
		return prefix;
	}
	
	
	public static double evaluatePostfix( String[] postfix ) {
		Stack<String> opdStack = new Stack<>();
		
		for (String s: postfix) {
			//	If s is a operand, not a Operator
			if ( s.length() != 1 || !precedences.containsKey(s) ) {
				opdStack.push( s );
			}
			//	Else s is an operator
			else {
				//	Remember first element popped is Operand2, second element popped is Operand1
				double op2 = Double.parseDouble( opdStack.pop() );
				double op1 = Double.parseDouble( opdStack.pop() );
				
				switch(s) {
					case "+":
						opdStack.push( String.valueOf( op1 + op2 ) );
						break;
					case "-":
						opdStack.push( String.valueOf( op1 - op2 ) );
						break;
					case "*":
						opdStack.push( String.valueOf( op1 * op2 ) );
						break;
					case "/":
						opdStack.push( String.valueOf( op1 / op2 ) );
						break;
					case "^":
						opdStack.push( String.valueOf( Math.pow(op1, op2) ) );
						break;
				}
			}
			
		}
		

		assert opdStack.size() == 1: "Error in evaluating PostFix notation. Final stack size is not 1";
		
		return Double.parseDouble( opdStack.pop() );
	}
	
	
	
	
	public static double evaluatePrefix( String[] prefix ) {
		Stack<String> opdStack = new Stack<>();
		
		for ( int i = prefix.length - 1; i >= 0; i --) {
			String s = prefix[i];
			
			//	If s is a operand, not a Operator
			if ( s.length() != 1 || !precedences.containsKey(s) ) {
				opdStack.push( s );
			}
			//	Else s is an operator
			else {
				//	In PREFIX, First element popped is OPERAND1, Second element popped is OPERAND2
				double op1 = Double.parseDouble( opdStack.pop() );
				double op2 = Double.parseDouble( opdStack.pop() );
				
				switch(s) {
					case "+":
						opdStack.push( String.valueOf( op1 + op2 ) );
						break;
					case "-":
						opdStack.push( String.valueOf( op1 - op2 ) );
						break;
					case "*":
						opdStack.push( String.valueOf( op1 * op2 ) );
						break;
					case "/":
						opdStack.push( String.valueOf( op1 / op2 ) );
						break;
					case "^":
						opdStack.push( String.valueOf( Math.pow(op1, op2) ) );
						break;
				}
			}
			
		}
		

		assert opdStack.size() == 1: "Error in evaluating PostFix notation. Final stack size is not 1";
		
		return Double.parseDouble( opdStack.pop() );
	}
	
	
	
	
	public static void main(String[]args) {
		String[] ops = {"1", "+", "2", "*", "3", "-", "4", "^", "5"};
		
		String[] resPost = convertInfixToPostfix(ops);
		
		System.out.println( Arrays.toString(resPost) );
		
		System.out.println( evaluatePostfix(resPost) );
		
		
		String[] resPre = convertInfixToPrefix(ops);
		
		System.out.println( Arrays.toString(resPre) );
		
		System.out.println( evaluatePrefix(resPre) );
	}
	
}





