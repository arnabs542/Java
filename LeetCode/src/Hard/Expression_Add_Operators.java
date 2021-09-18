package Hard;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/expression-add-operators/
/*
 * This is a String backtracking problem.
 * (Marked - I see solution)
 * 
 * The core idea is, for every digit, we can choose from 4 operations:
 * 	>	Appending a + before current digit
 * 	>	Appending a - before current digit
 * 	>	Appending a * before current digit
 * 	>	Append current digit to the previous operand to create larger number.
 * 	
 * Therefore the recursive backtracking algorithm looks like:
 * 
 * 	if (reached end)
 * 		append path if evaluated == target
 * 
 * 	else
 * 		backtrack append +
 * 		backtrack append -
 * 		backtrack append *
 * 		backtrack append operand
 * 
 * 	
 * But then, we also need to take care of some edge cases:
 * 
 * 		>	An operand cannot have padded zeroes.
 * 		>	An operand + - * cannot go before first digit.
 * 		> 	Multiplication must be higher precedence.
 * 
 * To deal with multiplication, we would keep a reference to the previous operand. Say we have 2 - 5 * 3 and
 * we are dealing with *3, so far the variables eval = -3 and prev = -5
 * 
 * Then we simply do eval - prev + (prev * 3)
 * 										   ^
 * 									 Current operand
 */

public class Expression_Add_Operators {
	
	public List<String> addOperators(String num, int target) {
		List<String> res = new ArrayList<>();
		recurse(0, new ArrayList<>(), 0, 0, 0, num, target, res);
		return res;
    }
	
	private void recurse(int index, List<String> objects, long current, long prev, long eval, 
			String num, int target, List<String> res) {
		final int len = num.length();
		
		// Reached the end of string. Check if formed the target or not
		if (index == len) {
			if (eval == target && current == 0) {
				StringBuilder sb = new StringBuilder();
				// Each expression has a leading '+' sign like "+1+2+3". Eliminate that +
				objects.subList(1, objects.size() ).forEach(v -> sb.append(v));
				res.add(sb.toString());
			}
			return;
		}
		
		// Operation 1 - Extend current operand by this digit (Effects the rest of the operation too)
		current = current * 10 + (num.charAt(index) - '0');
		String currentInStr = Long.toString(current);
		
		// However, ensure that we are not doing operand with padded zeroes like 0005
		if (current > 0)
			recurse(index+1, objects, current, prev, eval, num, target, res);
	
		
		// Note: In the solution, we represent valid expressions like +1+2+3 which we have a leading + sign.
		// However, we would not want a leading - or *
		
		// Operation 2 - Append '+' before current operand (Already extended)
		objects.add("+");
		objects.add(currentInStr);
		recurse(index+1, objects, 0, current, eval+current, num, target, res);
		// Revert
		objects.remove( objects.size() - 1);
		objects.remove( objects.size() - 1);
		
		if (!objects.isEmpty()) {
			// Operation 3 - Append '-' before current operand (Already extended)
			objects.add("-");
			objects.add(currentInStr);
			recurse(index+1, objects, 0, -current, eval-current, num, target, res);
			// Revert
			objects.remove( objects.size() - 1);
			objects.remove( objects.size() - 1);
			
			
			// Operation 4 - Append '*' before current operand (Already extended)
			objects.add("*");
			objects.add(currentInStr);
			recurse(index+1, objects, 0, current * prev, eval - prev + (prev * current), num, target, res);
			// Revert
			objects.remove( objects.size() - 1);
			objects.remove( objects.size() - 1);
		}
	}
	
}
